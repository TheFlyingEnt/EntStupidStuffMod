package net.ent.entstupidstuff.client.particle;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class HammerBoomParticle extends AnimatedParticle {

    public HammerBoomParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.01f); // 0.05f = animation speed
        this.maxAge = 11; // 8 frames
        this.setVelocity(vx, vy, vz);
        this.setSpriteForAge(spriteProvider);
        this.scale *= 30.0F;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
