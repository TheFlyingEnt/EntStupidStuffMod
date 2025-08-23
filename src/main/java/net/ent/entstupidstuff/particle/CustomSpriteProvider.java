package net.ent.entstupidstuff.particle;

import java.util.List;

import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.random.Random;

public class CustomSpriteProvider implements SpriteProvider {
    private final List<Sprite> sprites;

    // Constructor accepts a list of sprites
    public CustomSpriteProvider(List<Sprite> sprites) {
        if (sprites == null || sprites.isEmpty()) {
            throw new IllegalArgumentException("Sprite list cannot be null or empty.");
        }
        this.sprites = sprites;
    }

    @Override
    public Sprite getSprite(int index, int maxIndex) {
        // Ensure index is within bounds
        if (sprites.isEmpty()) {
            throw new IllegalStateException("Sprite list is empty.");
        }

        return sprites.get(index % sprites.size());  // Return a valid sprite
    }

    @Override
    public Sprite getSprite(Random random) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }
}
