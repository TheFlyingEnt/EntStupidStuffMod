package net.ent.entstupidstuff.api.cutscene;

import com.mojang.blaze3d.systems.RenderSystem;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback;
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

public class CutsceneScreen extends Screen {
    private final String videoPath;
    private final boolean disableMovement;
    private final boolean hideHud;
    
    private MediaPlayerFactory mediaPlayerFactory;
    private EmbeddedMediaPlayer mediaPlayer;
    private VideoRenderer videoRenderer;
    private AtomicBoolean finished = new AtomicBoolean(false);

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
            // Initialize VLC media player
            mediaPlayerFactory = new MediaPlayerFactory();
            mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
            
            videoRenderer = new VideoRenderer(width, height);
            
            // Set up video surface
            CallbackVideoSurface videoSurface = new CallbackVideoSurface(
                videoRenderer,
                videoRenderer,
                true,
                VideoSurfaceAdapters.getVideoSurfaceAdapter()
            );
            mediaPlayer.videoSurface().set(videoSurface);
            
            // Add event listener to detect when video ends
            mediaPlayer.events().addMediaPlayerEventListener(new uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter() {
                @Override
                public void finished(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer) {
                    finished.set(true);
                }
            });
            
            // Start playing
            mediaPlayer.media().play(videoPath);
            
        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("Failed to initialize video player", e);
            close();
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Check if video finished
        if (finished.get()) {
            close();
            return;
        }

        // Render black background
        context.fill(0, 0, width, height, 0xFF000000);
        
        // Render video frame
        if (videoRenderer != null && videoRenderer.hasFrame()) {
            videoRenderer.renderToScreen(context, width, height);
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
        if (mediaPlayer != null) {
            mediaPlayer.controls().stop();
            mediaPlayer.release();
        }
        if (mediaPlayerFactory != null) {
            mediaPlayerFactory.release();
        }
        if (videoRenderer != null) {
            videoRenderer.cleanup();
        }
        
        if (client != null) {
            client.setScreen(null);
        }
        CutsceneManager.stopCutscene();
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    // Video renderer class
    private static class VideoRenderer implements BufferFormatCallback, RenderCallback {
        private int width;
        private int height;
        private ByteBuffer buffer;
        private int textureId = -1;
        private boolean hasFrame = false;

        public VideoRenderer(int screenWidth, int screenHeight) {
            this.width = screenWidth;
            this.height = screenHeight;
        }

        @Override
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
            return new RV32BufferFormat(sourceWidth, sourceHeight);
        }

        @Override
        public void allocatedBuffers(ByteBuffer[] buffers) {
            this.buffer = buffers[0];
        }

        @Override
        public void display(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer, ByteBuffer[] nativeBuffers, BufferFormat bufferFormat) {
            hasFrame = true;
        }

        public boolean hasFrame() {
            return hasFrame;
        }

        public void renderToScreen(DrawContext context, int screenWidth, int screenHeight) {
            // Implement OpenGL texture rendering from buffer
            // This is a simplified version - full implementation would upload buffer to texture
            // and render it properly scaled to screen
        }

        public void cleanup() {
            if (textureId != -1) {
                //RenderSystem.deleteTexture(textureId);
            }
        }
    }
}
