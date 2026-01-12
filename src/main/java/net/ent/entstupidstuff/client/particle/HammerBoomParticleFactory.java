package net.ent.entstupidstuff.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class HammerBoomParticleFactory implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spriteProvider;

    public HammerBoomParticleFactory(SpriteSet spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    @Override
    public Particle createParticle(SimpleParticleType arg0, ClientLevel world, double x, double y, double z,
            double vx, double vy, double vz, RandomSource arg8) {
        return new HammerBoomParticle(world, x, y, z, vx, vy, vz, this.spriteProvider);
    }

}
