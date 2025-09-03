package net.ent.entstupidstuff.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class HammerBoomParticleFactory implements ParticleFactory<SimpleParticleType> {
    private final SpriteProvider spriteProvider;

    public HammerBoomParticleFactory(SpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        return new HammerBoomParticle(world, x, y, z, vx, vy, vz, this.spriteProvider);
    }
}
