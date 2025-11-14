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
    //private FFmpegFrameGrabber audioGrabber;
    private Java2DFrameConverter converter;
    private NativeImageBackedTexture videoTexture;
    private Thread videoThread;
    //private Thread audioThread;
    private volatile boolean running = true;
    private volatile boolean hasFinished = false;
    
    private int videoWidth = 1920;
    private int videoHeight = 1080;
    
    private SourceDataLine audioLine;
    
    // Queue for frames to be rendered on main thread
    //private BlockingQueue<BufferedImage> frameQueue = new LinkedBlockingQueue<>(3);
    private BlockingQueue<Frame> frameQueue = new LinkedBlockingQueue<>(3);
    private BufferedImage currentFrame = null;

    //updated fps: //TBA
    private long lastFrameTimeNano = 0; // last time we updated texture
    private double videoFrameDuration = 0;  // seconds per frame
    private Frame lastFrame = null;          // last frame displayed

    

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
            
            // CRITICAL: Force pixel format to BGR24 (RGB) instead of YUV
            // This makes JavaCV convert YUV420p to RGB automatically
            grabber.setPixelFormat(org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_BGR24);
            
            grabber.start();

            videoFrameDuration = 1.0 / grabber.getFrameRate();
            lastFrameTimeNano = System.nanoTime();
            lastFrame = null;
            
            videoWidth = grabber.getImageWidth();
            videoHeight = grabber.getImageHeight();
            
            EntStupidStuff.LOGGER.info("Video info: {}x{} @ {}fps, {} audio channels @ {}Hz", 
                videoWidth, videoHeight, grabber.getFrameRate(), 
                grabber.getAudioChannels(), grabber.getSampleRate());
            
            converter = new Java2DFrameConverter();
            
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
                    BufferedImage image = converter.convert(frame);
                    if (image != null) {
                        if (frameCount == 1) {
                            EntStupidStuff.LOGGER.info("First frame: {}x{}, type: {}", 
                                image.getWidth(), image.getHeight(), image.getType());
                        }
                        
                        // Add to queue (will block if queue is full, providing backpressure)
                        try {
                            //frameQueue.put(image);
                            frameQueue.put(frame.clone());
                        } catch (InterruptedException e) {
                            break;
                        }
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
    
    private void updateTexture(Frame frame) {
        if (frame == null || videoTexture == null) return;

        try {
            NativeImage nativeImage = videoTexture.getImage();
            if (nativeImage == null) return;

            // frame.image[0] is ByteBuffer containing BGR
            ByteBuffer buffer = (ByteBuffer) frame.image[0];
            buffer.rewind();

            // NativeImage expects ABGR
            for (int y = 0; y < videoHeight; y++) {
                for (int x = 0; x < videoWidth; x++) {
                    int b = buffer.get() & 0xFF;
                    int g = buffer.get() & 0xFF;
                    int r = buffer.get() & 0xFF;
                    int abgr = 0xFF000000 | (b << 16) | (g << 8) | r;
                    nativeImage.setColor(x, y, abgr);
                }
            }

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

        // TBT: Support for Custom FPS
        /*
        long now = System.nanoTime();
        double elapsed = (now - lastFrameTimeNano) / 1_000_000_000.0;

        if (elapsed >= videoFrameDuration) {
            // Try to get next frame from queue, non-blocking
            Frame nextFrame = frameQueue.poll();
            if (nextFrame != null) {
                lastFrame = nextFrame;
                updateTexture(nextFrame);
            }
            lastFrameTimeNano = now;
        }

        // Render black background
        context.fill(0, 0, width, height, 0xFF000000);

        // Render video frame
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
        } */




        //Version Locked to 30fps:
        
        // Process next frame from queue if available (on render thread)
        Frame frame = frameQueue.poll();
        if (frame != null) updateTexture(frame);

        
        // Render black background
        context.fill(0, 0, width, height, 0xFF000000);
        
        // Render video frame
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
            
            EntStupidStuff.LOGGER.info("Drawing texture at ({}, {}) size {}x{}", 
                renderX, renderY, renderWidth, renderHeight);
            
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
        
        if (converter != null) {
            converter.close();
        }
        
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
}