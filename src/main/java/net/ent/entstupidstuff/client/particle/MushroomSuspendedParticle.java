package net.ent.entstupidstuff.client.particle;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleLimit;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class MushroomSuspendedParticle extends SingleQuadParticle {
    protected MushroomSuspendedParticle(ClientLevel world, double x, double y, double z, TextureAtlasSprite sprite) {
        super(world, x, y - 0.125, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.quadSize *= (this.random.nextFloat() * 0.6F + 0.2F);
        this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    protected MushroomSuspendedParticle(ClientLevel world, double x, double y, double z, double velX, double velY, double velZ, TextureAtlasSprite sprite) {
        super(world, x, y - 0.125, z, velX, velY, velZ, sprite);
        this.setSize(0.01F, 0.01F);
        this.quadSize *= (this.random.nextFloat() * 0.6F + 0.6F);
        this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        // You can also use PARTICLE_SHEET_TRANSLUCENT if you want glow-like transparency
        return SingleQuadParticle.Layer.OPAQUE;
    }

    // ---------- FACTORY VARIANTS ---------- //

    @Environment(EnvType.CLIENT)
    public static class BlueGlowFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public BlueGlowFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            double j = random.nextGaussian() * 1.0E-6F;
            double k = random.nextGaussian() * 1.0E-4F;
            double l = random.nextGaussian() * 1.0E-6F;
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, j, k, l, this.spriteProvider.get(random));
            particle.setColor(0.34F, 0.90F, 0.94F);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class RedGlowFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public RedGlowFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, this.spriteProvider.get(random));
            particle.setColor(1.0F, 0.3F, 0.3F); // Red glow tone
            particle.lifetime = Mth.randomBetweenInclusive(random, 40, 80);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class GreenGlowFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public GreenGlowFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, this.spriteProvider.get(random));
            particle.setColor(0.3F, 0.9F, 0.4F); // Green glow tone
            particle.lifetime = Mth.randomBetweenInclusive(random, 100, 200);
            particle.gravity = 0.005F;
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SporesFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public SporesFactory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, 0.0, -0.02F, 0.0, this.spriteProvider.get(random)) {
                @Override
                public Optional<ParticleLimit> getParticleLimit() {
                    return Optional.of(ParticleLimit.SPORE_BLOSSOM);
                }
            };
            particle.setColor(0.8F, 0.6F, 0.9F); // soft purple spores
            particle.lifetime = Mth.randomBetweenInclusive(random, 150, 300);
            return particle;
        }
    }

    @Override
	public int getLightColor(float tint) {
		//return this.obsidianTear ? 240 : super.getBrightness(tint);
		return 240;
	}
}
