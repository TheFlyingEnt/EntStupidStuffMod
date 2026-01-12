package net.ent.entstupidstuff.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class NewLeakParticle extends SingleQuadParticle {
	private final Fluid fluid;
	protected boolean obsidianTear;

	NewLeakParticle(ClientLevel world, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
		super(world, x, y, z, sprite);
		this.setSize(0.01F, 0.01F);
		this.gravity = 0.06F;
		this.fluid = fluid;
	}

	protected Fluid getFluid() {
		return this.fluid;
	}

	@Override
	public SingleQuadParticle.Layer getLayer() {
		return SingleQuadParticle.Layer.OPAQUE;
	}

	@Override
	public int getLightColor(float tint) {
		//return this.obsidianTear ? 240 : super.getBrightness(tint);
		return 240;
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.updateAge();
		if (!this.removed) {
			this.yd = this.yd - this.gravity;
			this.move(this.xd, this.yd, this.zd);
			this.updateVelocity();
			if (!this.removed) {
				this.xd *= 0.98F;
				this.yd *= 0.98F;
				this.zd *= 0.98F;
				if (this.fluid != Fluids.EMPTY) {
					BlockPos blockPos = BlockPos.containing(this.x, this.y, this.z);
					FluidState fluidState = this.level.getFluidState(blockPos);
					if (fluidState.getType() == this.fluid && this.y < blockPos.getY() + fluidState.getHeight(this.level, blockPos)) {
						this.remove();
					}
				}
			}
		}
	}

	protected void updateAge() {
		if (this.lifetime-- <= 0) {
			this.remove();
		}
	}

	protected void updateVelocity() {
	}

	@Environment(EnvType.CLIENT)
	static class ContinuousFalling extends NewLeakParticle.Falling {
		protected final ParticleOptions nextParticle;

		ContinuousFalling(ClientLevel world, double x, double y, double z, Fluid fluid, ParticleOptions nextParticle, TextureAtlasSprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.nextParticle = nextParticle;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.remove();
				this.level.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
			}
		}
	}

	@Environment(EnvType.CLIENT)
	static class Dripping extends NewLeakParticle {
		private final ParticleOptions nextParticle;

		Dripping(ClientLevel world, double x, double y, double z, Fluid fluid, ParticleOptions nextParticle, TextureAtlasSprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.nextParticle = nextParticle;
			this.gravity *= 0.02F;
			this.lifetime = 40;
		}

		@Override
		protected void updateAge() {
			if (this.lifetime-- <= 0) {
				this.remove();
				this.level.addParticle(this.nextParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
			}
		}

		@Override
		protected void updateVelocity() {
			this.xd *= 0.02;
			this.yd *= 0.02;
			this.zd *= 0.02;
		}
	}



	@Environment(EnvType.CLIENT)
	static class Falling extends NewLeakParticle {
		Falling(ClientLevel clientWorld, double d, double e, double f, Fluid fluid, TextureAtlasSprite sprite) {
			this(clientWorld, d, e, f, fluid, (int)(64.0 / (Math.random() * 0.8 + 0.2)), sprite);
		}

		Falling(ClientLevel world, double x, double y, double z, Fluid fluid, int maxAge, TextureAtlasSprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.lifetime = maxAge;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.remove();
			}
		}
	}

	@Environment(EnvType.CLIENT)
	public static class FallingMushroomSporeBlossomFactory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteProvider;

		public FallingMushroomSporeBlossomFactory(SpriteSet spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(
			SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random
		) {
			int j = (int)(64.0F / Mth.randomBetween(clientWorld.getRandom(), 0.1F, 0.9F));
			NewLeakParticle NewLeave = new NewLeakParticle.Falling(clientWorld, d, e, f, Fluids.EMPTY, j, this.spriteProvider.get(random));
			NewLeave.gravity = 0.005F;
			NewLeave.setColor(0.34F, 0.90F, 0.94F);
			return NewLeave;
		}
	}


	@Environment(EnvType.CLIENT)
	static class Landing extends NewLeakParticle {
		Landing(ClientLevel clientWorld, double d, double e, double f, Fluid fluid, TextureAtlasSprite sprite) {
			super(clientWorld, d, e, f, fluid, sprite);
			this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));
		}
	}



}
