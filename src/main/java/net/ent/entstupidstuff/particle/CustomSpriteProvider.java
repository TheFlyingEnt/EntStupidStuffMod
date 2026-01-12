package net.ent.entstupidstuff.particle;

import java.util.List;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.RandomSource;

public class CustomSpriteProvider implements SpriteSet {
    private final List<TextureAtlasSprite> sprites;

    // Constructor accepts a list of sprites
    public CustomSpriteProvider(List<TextureAtlasSprite> sprites) {
        if (sprites == null || sprites.isEmpty()) {
            throw new IllegalArgumentException("Sprite list cannot be null or empty.");
        }
        this.sprites = sprites;
    }

    @Override
    public TextureAtlasSprite get(int index, int maxIndex) {
        // Ensure index is within bounds
        if (sprites.isEmpty()) {
            throw new IllegalStateException("Sprite list is empty.");
        }

        return sprites.get(index % sprites.size());  // Return a valid sprite
    }

    @Override
    public TextureAtlasSprite get(RandomSource random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

    @Override
    public TextureAtlasSprite first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirst'");
    }
}
