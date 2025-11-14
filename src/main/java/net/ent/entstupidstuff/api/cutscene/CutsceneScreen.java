package net.ent.entstupidstuff.api.cutscene;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class CutsceneScreen extends Screen {

    private static final Identifier TEXTURE_ID = Identifier.of("entstupidstuff", "cutscene_frame");

    private final String videoPath;
    private final boolean disableMovement;
    private final boolean hideHud;

    private FFmpegFrameGrabber grabber;
    private NativeImageBackedTexture videoTexture;
    private Thread videoThread;
    private volatile boolean running = true;
    private volatile boolean hasFinished = false;

    private int videoWidth = 1920;
    private int videoHeight = 1080;
    private double frameRate = 30.0;
    private long frameDelay;

    private final Java2DFrameConverter converter = new Java2DFrameConverter();

    private SourceDataLine audioLine;

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
                EntStupidStuff.LOGGER.error("Video file not found: {}", videoPath);
                close();
                return;
            }

            grabber = new FFmpegFrameGrabber(videoFile);
            grabber.start();

            videoWidth = grabber.getImageWidth();
            videoHeight = grabber.getImageHeight();

            // Video texture
            videoTexture = new NativeImageBackedTexture(() -> "cutscene_frame", videoWidth, videoHeight, false);
            MinecraftClient.getInstance().getTextureManager().registerTexture(TEXTURE_ID, videoTexture);

            // Audio
            if (grabber.getAudioChannels() > 0) {
                AudioFormat format = new AudioFormat(
                        grabber.getSampleRate(),
                        16,
                        grabber.getAudioChannels(),
                        true,
                        false
                );
                audioLine = AudioSystem.getSourceDataLine(format);
                audioLine.open(format);
                audioLine.start();
            }

            // Start decoding thread
            videoThread = new Thread(this::playVideo, "Cutscene-Video-Thread");
            videoThread.setDaemon(true);
            videoThread.start();

            EntStupidStuff.LOGGER.info("Started cutscene: {}x{}", videoWidth, videoHeight);

        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Failed to initialize cutscene", e);
            close();
        }
    }

    // ----------- VIDEO DECODE LOOP -------------------

    private void playVideo() {
        try {
            Frame frame;
            while (running && (frame = grabber.grab()) != null) {

                // -------- VIDEO --------
                if (frame.image != null) {
                    BufferedImage img = converter.convert(frame);
                    if (img != null && videoTexture != null) {
                        NativeImage nativeImg = videoTexture.getImage();
                        if (nativeImg != null) {
                            copyBufferedImageToNativeImage(img, nativeImg);

                            // Schedule texture upload on render thread
                            MinecraftClient.getInstance().execute(() -> {
                                if (videoTexture != null) videoTexture.upload();
                            });
                        }
                    }
                }

                // -------- AUDIO --------
                if (frame.samples != null && audioLine != null) {
                    int channels = frame.samples.length;
                    int sampleCount = ((java.nio.ShortBuffer) frame.samples[0]).remaining();
                    ByteBuffer audioBytes = ByteBuffer.allocate(sampleCount * 2 * channels);

                    for (int i = 0; i < channels; i++) {
                        java.nio.ShortBuffer channelBuffer = (java.nio.ShortBuffer) frame.samples[i];
                        channelBuffer.rewind();
                        while (channelBuffer.hasRemaining()) {
                            short s = channelBuffer.get();
                            audioBytes.put((byte) (s & 0xff));
                            audioBytes.put((byte) ((s >> 8) & 0xff));
                        }
                    }
                    audioLine.write(audioBytes.array(), 0, audioBytes.position());
                }
            }

            hasFinished = true;

        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Error playing video", e);
            hasFinished = true;
        }
    }

    // ----------- DIRECT GPU UPLOAD -------------------

    private void copyBufferedImageToNativeImage(BufferedImage bufferedImage, NativeImage nativeImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();

    if (bufferedImage.getType() == BufferedImage.TYPE_INT_ARGB || 
        bufferedImage.getType() == BufferedImage.TYPE_INT_RGB) {
        // Direct copy for int-based BufferedImages
        int[] data = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        IntBuffer buffer = MemoryUtil.memIntBuffer(nativeImage.imageId(), width * height); //Same as before
        for (int i = 0; i < data.length; i++) {
            int argb = data[i];
            // Convert ARGB -> ABGR (NativeImage expects ABGR)
            int abgr = (argb & 0xFF00FF00) | ((argb & 0xFF) << 16) | ((argb >> 16) & 0xFF);
            buffer.put(i, abgr);
        }
    } else {
        // Fallback for byte-based images (like 3BYTE_BGR)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y); // Converts byte-based BGR/YUV to ARGB automatically
                int abgr = (rgb & 0xFF00FF00) | ((rgb & 0xFF) << 16) | ((rgb >> 16) & 0xFF);
                nativeImage.setColor(x, y, abgr); //Same as before
            }
        }
    }
}

    private void updateTextureFromFrame(Frame frame) {
        if (videoTexture == null || frame == null || frame.image == null) return;

        ByteBuffer buffer = (ByteBuffer) frame.image[0]; // RGBA
        buffer.rewind();

        NativeImage img = videoTexture.getImage();
        if (img == null) return;

        int width = frame.imageWidth;
        int height = frame.imageHeight;

        // Make a copy of the buffer for thread safety
        byte[] temp = new byte[width * height * 4];
        buffer.get(temp);

        // Schedule GPU update on render thread
        MinecraftClient.getInstance().execute(() -> {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int i = (y * width + x) * 4;
                    int r = temp[i] & 0xFF;
                    int g = temp[i + 1] & 0xFF;
                    int b = temp[i + 2] & 0xFF;
                    int a = temp[i + 3] & 0xFF;

                    // pack ARGB (what NativeImage expects)
                    int color = (a << 24) | (r << 16) | (g << 8) | b;

                    img.setColor(x, y, color); // 1.21 method
                }
            }

            videoTexture.upload();
        });
    }


    // ----------- RENDERING -------------------

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (hasFinished) {
            close();
            return;
        }

        context.fill(0, 0, width, height, 0xFF000000);

        if (videoTexture != null) {
            float videoAspect = (float) videoWidth / videoHeight;
            float screenAspect = (float) width / height;

            int rw, rh, rx, ry;
            if (screenAspect > videoAspect) {
                rh = height;
                rw = (int) (height * videoAspect);
                rx = (width - rw) / 2;
                ry = 0;
            } else {
                rw = width;
                rh = (int) (width / videoAspect);
                rx = 0;
                ry = (height - rh) / 2;
            }

            context.drawTexture(
                    RenderPipelines.GUI,
                    TEXTURE_ID,
                    rx, ry,
                    0, 0,
                    rw, rh,
                    videoWidth, videoHeight
            );
        }

        super.render(context, mouseX, mouseY, delta);
    }

    // ESC closes the cutscene
    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.isEscape()) {
            close();
            return true;
        }
        return super.keyPressed(input);
    }

    // ----------- CLEANUP -------------------

    @Override
    public void close() {
        running = false;

        try {
            if (videoThread != null && videoThread.isAlive()) videoThread.join(1000);
        } catch (Exception ignored) {}

        try {
            if (grabber != null) {
                grabber.stop();
                grabber.release();
            }
        } catch (Exception ignored) {}

        if (audioLine != null) {
            audioLine.stop();
            audioLine.close();
        }

        if (videoTexture != null) {
            MinecraftClient.getInstance().getTextureManager().destroyTexture(TEXTURE_ID);
            videoTexture.close();
        }

        if (client != null) client.setScreen(null);

        CutsceneManager.stopCutscene();
    }

    @Override
    public boolean shouldPause() { return false; }
    @Override
    public boolean shouldCloseOnEsc() { return true; }
}