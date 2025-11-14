package net.ent.entstupidstuff.api.cutscene;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CutsceneScreen extends Screen {
    private static final Identifier TEXTURE_ID = Identifier.of("entstupidstuff", "cutscene_frame");

    private final String videoPath;
    private final boolean disableMovement;
    private final boolean hideHud;

    private FFmpegFrameGrabber grabber;
    //private Java2DFrameConverter converter;
    private NativeImageBackedTexture videoTexture;
    private Thread videoThread;
    private volatile boolean running = true;
    private volatile boolean hasFinished = false;

    private int videoWidth = 1920;
    private int videoHeight = 1080;

    private SourceDataLine audioLine;

    // Queue of Frames decoded by the worker thread (holds up to 3 frames)
    //private final BlockingQueue<Frame> frameQueue = new LinkedBlockingQueue<>(3);
    private BlockingQueue<RawFrame> frameQueue = new LinkedBlockingQueue<>(3);

    // Timing / scheduling fields (time-based scheduler)
    private volatile long frameDurationNano = 33_333_333L; // default ~30 FPS
    private volatile long nextFrameTimeNano = 0L; // when to show next frame (nano)
    private volatile RawFrame lastFrame = null; // last frame that was displayed

    public CutsceneScreen(String videoPath, boolean disableMovement, boolean hideHud) {
        super(Text.literal("Cutscene"));
        this.videoPath = videoPath;
        this.disableMovement = disableMovement;
        this.hideHud = hideHud;
    }

    @Override
    protected void init() {
        super.init();

        try {
            File videoFile = new File(videoPath);
            if (!videoFile.exists()) {
                EntStupidStuff.LOGGER.error("Video file not found: " + videoPath);
                close();
                return;
            }

            // Initialize FFmpeg grabber
            grabber = new FFmpegFrameGrabber(videoFile);

            // Force BGR24 so converter gives BufferedImage in a consistent RGB form
            grabber.setPixelFormat(org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_BGR24);

            grabber.start();

            videoWidth = grabber.getImageWidth();
            videoHeight = grabber.getImageHeight();

            double fps = grabber.getFrameRate();
            if (fps <= 0 || Double.isNaN(fps) || Double.isInfinite(fps)) {
                fps = 30.0; // fallback
            }
            frameDurationNano = (long) (1_000_000_000.0 / fps);
            // nextFrameTimeNano is initialized when first frame arrives (below)

            EntStupidStuff.LOGGER.info("Video info: {}x{} @ {}fps, {} audio channels @ {}Hz",
                    videoWidth, videoHeight, fps,
                    grabber.getAudioChannels(), grabber.getSampleRate());

            //converter = new Java2DFrameConverter();

            // Create texture for video frames with proper format
            videoTexture = new NativeImageBackedTexture(() -> "cutscene_frame", videoWidth, videoHeight, false);
            MinecraftClient.getInstance().getTextureManager().registerTexture(TEXTURE_ID, videoTexture);

            // Initialize with black frame
            NativeImage nativeImage = videoTexture.getImage();
            if (nativeImage != null) {
                for (int y = 0; y < videoHeight; y++) {
                    for (int x = 0; x < videoWidth; x++) {
                        nativeImage.setColor(x, y, 0xFF000000); // Black with full alpha
                    }
                }
                videoTexture.upload();
            }

            EntStupidStuff.LOGGER.info("Created texture: {}x{}", videoWidth, videoHeight);

            // Setup audio if available
            if (grabber.getAudioChannels() > 0) {
                try {
                    AudioFormat audioFormat = new AudioFormat(
                            (float) grabber.getSampleRate(),
                            16,
                            grabber.getAudioChannels(),
                            true,
                            false
                    );
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                    audioLine = (SourceDataLine) AudioSystem.getLine(info);
                    audioLine.open(audioFormat);
                    audioLine.start();
                    EntStupidStuff.LOGGER.info("Audio initialized: {} channels @ {} Hz",
                            grabber.getAudioChannels(), grabber.getSampleRate());
                } catch (Exception e) {
                    EntStupidStuff.LOGGER.error("Failed to initialize audio", e);
                    audioLine = null;
                }
            }

            // Start video playback thread
            videoThread = new Thread(this::playVideo, "Cutscene-Video-Thread");
            videoThread.setDaemon(true);
            videoThread.start();

        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Failed to initialize cutscene", e);
            close();
        }
    }

    private void playVideo() {
        try {
            Frame frame;
            int frameCount = 0;

            while (running && (frame = grabber.grab()) != null) {
                frameCount++;

                // Process video frame
                if (frame.image != null) {
                    ByteBuffer src = (ByteBuffer) frame.image[0];

                    RawFrame raw = new RawFrame();
                    raw.width = frame.imageWidth;
                    raw.height = frame.imageHeight;

                    // Copy only the byte buffer (BGR24)
                    ByteBuffer copy = ByteBuffer.allocateDirect(src.remaining());
                    int oldPos = src.position();
                    copy.put(src);
                    copy.flip();
                    src.position(oldPos); // restore for FFmpeg

                    raw.buffer = copy;

                    try {
                        frameQueue.put(raw); // blocks if full
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                // Process audio frame
                if (frame.samples != null && audioLine != null) {
                    playAudioFrame(frame);
                }
            }

            EntStupidStuff.LOGGER.info("Video finished. Processed {} frames", frameCount);
            hasFinished = true;

        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Error playing video", e);
            hasFinished = true;
        }
    }

    /**
     * Convert Frame (BGR24 ByteBuffer in frame.image[0]) into NativeImage texture.
     * This MUST be called from the render thread (we call it there).
     */
    private void updateTexture(RawFrame frame) {
        if (frame == null || videoTexture == null) return;

        try {
            NativeImage nativeImage = videoTexture.getImage();
            if (nativeImage == null) return;

            ByteBuffer buffer = frame.buffer;
            buffer.rewind();

            int w = frame.width;
            int h = frame.height;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int b = buffer.get() & 0xFF;
                    int g = buffer.get() & 0xFF;
                    int r = buffer.get() & 0xFF;

                    int abgr = 0xFF000000 | (b << 16) | (g << 8) | r;
                    nativeImage.setColor(x, y, abgr);
                }
            }

            // Upload will happen on render thread (this method is called from render thread)
            videoTexture.upload();
        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Error updating texture", e);
        }
    }

    private void playAudioFrame(Frame frame) {
        try {
            if (audioLine == null || frame.samples == null) return;

            int channels = frame.samples.length;
            int sampleCount = ((java.nio.ShortBuffer) frame.samples[0]).remaining();

            byte[] audioData = new byte[sampleCount * channels * 2];
            int offset = 0;

            // frame.samples[...] are ShortBuffers per channel
            for (int i = 0; i < sampleCount; i++) {
                for (int ch = 0; ch < channels; ch++) {
                    java.nio.ShortBuffer channelBuffer = (java.nio.ShortBuffer) frame.samples[ch];
                    short sample = channelBuffer.get(i);
                    audioData[offset++] = (byte) (sample & 0xFF);
                    audioData[offset++] = (byte) ((sample >> 8) & 0xFF);
                }
            }

            audioLine.write(audioData, 0, audioData.length);
        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Error playing audio", e);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (hasFinished) {
            close();
            return;
        }

        // Getting Screen Size


        final long now = System.nanoTime();

        // If we haven't started timing yet (no frames decoded yet), set start to now
        if (nextFrameTimeNano == 0L) nextFrameTimeNano = now;

        // Advance frame(s) as many times as needed to catch up to "now".
        // This will consume frames from the queue and call updateTexture(...) on render thread.
        while (now >= nextFrameTimeNano) {
            RawFrame polled = frameQueue.poll(); // non-blocking
            if (polled != null) {
                lastFrame = polled;
                updateTexture(lastFrame);
            }
            nextFrameTimeNano += frameDurationNano;

            // If no frames were available this pass, break (we'll try again next render)
            if (polled == null) break;
        }

        // Render black background
        context.fill(0, 0, width, height, 0xFF000000);

        // Draw the last uploaded texture (could be the initial black frame or last decoded)
        if (videoTexture != null) {
            float videoAspect = (float) videoWidth / videoHeight;
            float screenAspect = (float) width / height;

            int renderWidth, renderHeight, renderX, renderY;

            if (screenAspect > videoAspect) {
                renderHeight = height;
                renderWidth = (int) (height * videoAspect);
                renderX = (width - renderWidth) / 2;
                renderY = 0;
            } else {
                renderWidth = width;
                renderHeight = (int) (width / videoAspect);
                renderX = 0;
                renderY = (height - renderHeight) / 2;
            }

            context.drawTexture(
                    RenderPipelines.GUI_TEXTURED,
                    TEXTURE_ID,
                    renderX, renderY,
                    0.0f, 0.0f,
                    renderWidth, renderHeight,
                    videoWidth, videoHeight
            );
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.isEscape()) {
            close();
            return true;
        }
        return super.keyPressed(input);
    }

    @Override
    public void close() {
        cleanup();
        if (client != null) {
            client.setScreen(null);
        }
        CutsceneManager.stopCutscene();
    }

    public void cleanup() {
        running = false;

        if (videoThread != null && videoThread.isAlive()) {
            try {
                videoThread.interrupt();
                videoThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (grabber != null) {
            try {
                grabber.stop();
                grabber.release();
            } catch (Exception e) {
                EntStupidStuff.LOGGER.error("Error stopping grabber", e);
            }
        }

        /*if (converter != null) {
            converter.close();
        }*/

        if (audioLine != null) {
            audioLine.drain();
            audioLine.stop();
            audioLine.close();
        }

        if (videoTexture != null) {
            MinecraftClient.getInstance().getTextureManager().destroyTexture(TEXTURE_ID);
            videoTexture.close();
            videoTexture = null;
        }

        frameQueue.clear();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    public boolean isPlayerMovementDisabled() {
        return disableMovement;
    }

    public boolean shouldHideHud() {
        return hideHud;
    }

    private static class RawFrame {
        ByteBuffer buffer;
        int width, height;
    }


}


