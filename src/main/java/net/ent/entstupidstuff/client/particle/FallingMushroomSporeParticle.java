package net.ent.entstupidstuff.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class FallingMushroomSporeParticle extends AnimatedParticle {

    protected FallingMushroomSporeParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float gravity) {
        super(world, x, y, z, spriteProvider, gravity);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.collidesWithWorld = true;
    }

    @Override
    public void tick() {
        super.tick();

        // Apply gravity and motion
        this.velocityY -= this.gravityStrength;
        this.move(this.velocityX, this.velocityY, this.velocityZ);

        // Friction
        this.velocityX *= 0.98F;
        this.velocityY *= 0.98F;
        this.velocityZ *= 0.98F;

        // Remove when on ground
        if (this.onGround) {
            this.markDead();
        }
    }

    // ─────────────────────────────
    // VARIANTS
    // ─────────────────────────────

    @Environment(EnvType.CLIENT)
	public static class FallingMSporeBlossomFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public FallingMSporeBlossomFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double g, double h, double i, Random random) {
            int j = (int)(64.0F / MathHelper.nextBetween(world.getRandom(), 0.1F, 0.9F));
            FallingMushroomSporeParticle.Falling particle = new FallingMushroomSporeParticle.Falling(
                world, x, y, z, type, this.spriteProvider
            );
			particle.gravityStrength = 0.005F;
			particle.setColor(0.32F, 0.5F, 0.22F);
			return particle;
		}
	}

    @Environment(EnvType.CLIENT)
    public static class Dripping extends FallingMushroomSporeParticle {
        private final ParticleEffect nextParticle;

        protected Dripping(ClientWorld world, double x, double y, double z, ParticleEffect nextParticle, SpriteProvider spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.02F);
            this.nextParticle = nextParticle;
            this.maxAge = 40;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.age >= this.maxAge) {
                this.markDead();
                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Falling extends FallingMushroomSporeParticle {
        private final ParticleEffect nextParticle;

        protected Falling(ClientWorld world, double x, double y, double z, ParticleEffect nextParticle, SpriteProvider spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.06F);
            this.nextParticle = nextParticle;
            this.maxAge = (int) (64.0 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        public void tick() {
            super.tick();
            if (this.onGround) {
                this.markDead();
                this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Landing extends FallingMushroomSporeParticle {
        protected Landing(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.0F);
            this.maxAge = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        }
    }

    // ─────────────────────────────
    // FACTORY CLASSES
    // ─────────────────────────────

    @Environment(EnvType.CLIENT)
    public static class FallingFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprites;

        public FallingFactory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z,
                                       double g, double h, double i, Random random) {
            int life = (int)(64.0F / MathHelper.nextBetween(world.getRandom(), 0.1F, 0.9F));
            FallingMushroomSporeParticle.Falling particle = new FallingMushroomSporeParticle.Falling(
                    world, x, y, z, type, this.sprites
            );
            particle.maxAge = life;
            particle.gravityStrength = 0.005F;
            particle.setSprite(this.sprites.getSprite(random));
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class DrippingFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprites;

        public DrippingFactory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z,
                                       double g, double h, double i, Random random) {
            FallingMushroomSporeParticle.Dripping particle = new FallingMushroomSporeParticle.Dripping(
                    world, x, y, z, type, this.sprites
            );
            particle.setSprite(this.sprites.getSprite(random));
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class LandingFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprites;

        public LandingFactory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z,
                                       double g, double h, double i, Random random) {
            FallingMushroomSporeParticle.Landing particle = new FallingMushroomSporeParticle.Landing(
                    world, x, y, z, this.sprites
            );
            particle.setSprite(this.sprites.getSprite(random));
            return particle;
        }
    }
}