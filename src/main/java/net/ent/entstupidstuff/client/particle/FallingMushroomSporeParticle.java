package net.ent.entstupidstuff.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class FallingMushroomSporeParticle extends SimpleAnimatedParticle {

    protected FallingMushroomSporeParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float gravity) {
        super(world, x, y, z, spriteProvider, gravity);
        this.setSize(0.01F, 0.01F);
        this.hasPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();

        // Apply gravity and motion
        this.yd -= this.gravity;
        this.move(this.xd, this.yd, this.zd);

        // Friction
        this.xd *= 0.98F;
        this.yd *= 0.98F;
        this.zd *= 0.98F;

        // Remove when on ground
        if (this.onGround) {
            this.remove();
        }
    }

    // ─────────────────────────────
    // VARIANTS
    // ─────────────────────────────

    @Environment(EnvType.CLIENT)
	public static class FallingMSporeBlossomFactory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteProvider;

		public FallingMSporeBlossomFactory(SpriteSet spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double g, double h, double i, RandomSource random) {
            int j = (int)(64.0F / Mth.randomBetween(world.getRandom(), 0.1F, 0.9F));
            FallingMushroomSporeParticle.Falling particle = new FallingMushroomSporeParticle.Falling(
                world, x, y, z, type, this.spriteProvider
            );
			particle.gravity = 0.005F;
			particle.setColor(0.32F, 0.5F, 0.22F);
			return particle;
		}
	}

    @Environment(EnvType.CLIENT)
    public static class Dripping extends FallingMushroomSporeParticle {
        private final ParticleOptions nextParticle;

        protected Dripping(ClientLevel world, double x, double y, double z, ParticleOptions nextParticle, SpriteSet spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.02F);
            this.nextParticle = nextParticle;
            this.lifetime = 40;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.age >= this.lifetime) {
                this.remove();
                this.level.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Falling extends FallingMushroomSporeParticle {
        private final ParticleOptions nextParticle;

        protected Falling(ClientLevel world, double x, double y, double z, ParticleOptions nextParticle, SpriteSet spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.06F);
            this.nextParticle = nextParticle;
            this.lifetime = (int) (64.0 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        public void tick() {
            super.tick();
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Landing extends FallingMushroomSporeParticle {
        protected Landing(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
            super(world, x, y, z, spriteProvider, 0.0F);
            this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        }
    }

    // ─────────────────────────────
    // FACTORY CLASSES
    // ─────────────────────────────

    @Environment(EnvType.CLIENT)
    public static class FallingFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public FallingFactory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z,
                                       double g, double h, double i, RandomSource random) {
            int life = (int)(64.0F / Mth.randomBetween(world.getRandom(), 0.1F, 0.9F));
            FallingMushroomSporeParticle.Falling particle = new FallingMushroomSporeParticle.Falling(
                    world, x, y, z, type, this.sprites
            );
            particle.lifetime = life;
            particle.gravity = 0.005F;
            particle.setSprite(this.sprites.get(random));
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class DrippingFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public DrippingFactory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z,
                                       double g, double h, double i, RandomSource random) {
            FallingMushroomSporeParticle.Dripping particle = new FallingMushroomSporeParticle.Dripping(
                    world, x, y, z, type, this.sprites
            );
            particle.setSprite(this.sprites.get(random));
            return particle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class LandingFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public LandingFactory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z,
                                       double g, double h, double i, RandomSource random) {
            FallingMushroomSporeParticle.Landing particle = new FallingMushroomSporeParticle.Landing(
                    world, x, y, z, this.sprites
            );
            particle.setSprite(this.sprites.get(random));
            return particle;
        }
    }
}