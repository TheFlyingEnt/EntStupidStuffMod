package net.ent.entstupidstuff.client.particle;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleGroup;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class MushroomSuspendedParticle extends BillboardParticle {
    protected MushroomSuspendedParticle(ClientWorld world, double x, double y, double z, Sprite sprite) {
        super(world, x, y - 0.125, z, sprite);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.scale *= (this.random.nextFloat() * 0.6F + 0.2F);
        this.maxAge = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.collidesWithWorld = false;
        this.velocityMultiplier = 1.0F;
        this.gravityStrength = 0.0F;
    }

    protected MushroomSuspendedParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, Sprite sprite) {
        super(world, x, y - 0.125, z, velX, velY, velZ, sprite);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.scale *= (this.random.nextFloat() * 0.6F + 0.6F);
        this.maxAge = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.collidesWithWorld = false;
        this.velocityMultiplier = 1.0F;
        this.gravityStrength = 0.0F;
    }

    @Override
    public BillboardParticle.RenderType getRenderType() {
        // You can also use PARTICLE_SHEET_TRANSLUCENT if you want glow-like transparency
        return BillboardParticle.RenderType.PARTICLE_ATLAS_OPAQUE;
    }

    // ---------- FACTORY VARIANTS ---------- //

    @Environment(EnvType.CLIENT)
    public static class BlueGlowFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public BlueGlowFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz, Random random) {
            double j = random.nextGaussian() * 1.0E-6F;
            double k = random.nextGaussian() * 1.0E-4F;
            double l = random.nextGaussian() * 1.0E-6F;
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, j, k, l, this.spriteProvider.getSprite(random));
            particle.setColor(0.34F, 0.90F, 0.94F);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class RedGlowFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public RedGlowFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz, Random random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, this.spriteProvider.getSprite(random));
            particle.setColor(1.0F, 0.3F, 0.3F); // Red glow tone
            particle.maxAge = MathHelper.nextBetween(random, 40, 80);
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class GreenGlowFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public GreenGlowFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz, Random random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, this.spriteProvider.getSprite(random));
            particle.setColor(0.3F, 0.9F, 0.4F); // Green glow tone
            particle.maxAge = MathHelper.nextBetween(random, 100, 200);
            particle.gravityStrength = 0.005F;
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SporesFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public SporesFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz, Random random) {
            MushroomSuspendedParticle particle = new MushroomSuspendedParticle(world, x, y, z, 0.0, -0.02F, 0.0, this.spriteProvider.getSprite(random)) {
                @Override
                public Optional<ParticleGroup> getGroup() {
                    return Optional.of(ParticleGroup.SPORE_BLOSSOM_AIR);
                }
            };
            particle.setColor(0.8F, 0.6F, 0.9F); // soft purple spores
            particle.maxAge = MathHelper.nextBetween(random, 150, 300);
            return particle;
        }
    }

    @Override
	public int getBrightness(float tint) {
		//return this.obsidianTear ? 240 : super.getBrightness(tint);
		return 240;
	}
}
