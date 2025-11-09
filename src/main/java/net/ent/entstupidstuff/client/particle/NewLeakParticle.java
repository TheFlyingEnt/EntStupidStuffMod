package net.ent.entstupidstuff.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class NewLeakParticle extends BillboardParticle {
	private final Fluid fluid;
	protected boolean obsidianTear;

	NewLeakParticle(ClientWorld world, double x, double y, double z, Fluid fluid, Sprite sprite) {
		super(world, x, y, z, sprite);
		this.setBoundingBoxSpacing(0.01F, 0.01F);
		this.gravityStrength = 0.06F;
		this.fluid = fluid;
	}

	protected Fluid getFluid() {
		return this.fluid;
	}

	@Override
	public BillboardParticle.RenderType getRenderType() {
		return BillboardParticle.RenderType.PARTICLE_ATLAS_OPAQUE;
	}

	@Override
	public int getBrightness(float tint) {
		//return this.obsidianTear ? 240 : super.getBrightness(tint);
		return 240;
	}

	@Override
	public void tick() {
		this.lastX = this.x;
		this.lastY = this.y;
		this.lastZ = this.z;
		this.updateAge();
		if (!this.dead) {
			this.velocityY = this.velocityY - this.gravityStrength;
			this.move(this.velocityX, this.velocityY, this.velocityZ);
			this.updateVelocity();
			if (!this.dead) {
				this.velocityX *= 0.98F;
				this.velocityY *= 0.98F;
				this.velocityZ *= 0.98F;
				if (this.fluid != Fluids.EMPTY) {
					BlockPos blockPos = BlockPos.ofFloored(this.x, this.y, this.z);
					FluidState fluidState = this.world.getFluidState(blockPos);
					if (fluidState.getFluid() == this.fluid && this.y < blockPos.getY() + fluidState.getHeight(this.world, blockPos)) {
						this.markDead();
					}
				}
			}
		}
	}

	protected void updateAge() {
		if (this.maxAge-- <= 0) {
			this.markDead();
		}
	}

	protected void updateVelocity() {
	}

	@Environment(EnvType.CLIENT)
	static class ContinuousFalling extends NewLeakParticle.Falling {
		protected final ParticleEffect nextParticle;

		ContinuousFalling(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.nextParticle = nextParticle;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.markDead();
				this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
			}
		}
	}

	@Environment(EnvType.CLIENT)
	static class Dripping extends NewLeakParticle {
		private final ParticleEffect nextParticle;

		Dripping(ClientWorld world, double x, double y, double z, Fluid fluid, ParticleEffect nextParticle, Sprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.nextParticle = nextParticle;
			this.gravityStrength *= 0.02F;
			this.maxAge = 40;
		}

		@Override
		protected void updateAge() {
			if (this.maxAge-- <= 0) {
				this.markDead();
				this.world.addParticleClient(this.nextParticle, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
			}
		}

		@Override
		protected void updateVelocity() {
			this.velocityX *= 0.02;
			this.velocityY *= 0.02;
			this.velocityZ *= 0.02;
		}
	}



	@Environment(EnvType.CLIENT)
	static class Falling extends NewLeakParticle {
		Falling(ClientWorld clientWorld, double d, double e, double f, Fluid fluid, Sprite sprite) {
			this(clientWorld, d, e, f, fluid, (int)(64.0 / (Math.random() * 0.8 + 0.2)), sprite);
		}

		Falling(ClientWorld world, double x, double y, double z, Fluid fluid, int maxAge, Sprite sprite) {
			super(world, x, y, z, fluid, sprite);
			this.maxAge = maxAge;
		}

		@Override
		protected void updateVelocity() {
			if (this.onGround) {
				this.markDead();
			}
		}
	}

	@Environment(EnvType.CLIENT)
	public static class FallingMushroomSporeBlossomFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public FallingMushroomSporeBlossomFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(
			SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random
		) {
			int j = (int)(64.0F / MathHelper.nextBetween(clientWorld.getRandom(), 0.1F, 0.9F));
			NewLeakParticle NewLeave = new NewLeakParticle.Falling(clientWorld, d, e, f, Fluids.EMPTY, j, this.spriteProvider.getSprite(random));
			NewLeave.gravityStrength = 0.005F;
			NewLeave.setColor(0.34F, 0.90F, 0.94F);
			return NewLeave;
		}
	}


	@Environment(EnvType.CLIENT)
	static class Landing extends NewLeakParticle {
		Landing(ClientWorld clientWorld, double d, double e, double f, Fluid fluid, Sprite sprite) {
			super(clientWorld, d, e, f, fluid, sprite);
			this.maxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
		}
	}



}
