package net.ent.entstupidstuff.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;

public class HammerBoomParticle extends SimpleAnimatedParticle {

    public HammerBoomParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.01f); // 0.05f = animation speed
        this.lifetime = 11; // 8 frames
        this.setParticleSpeed(vx, vy, vz);
        this.setSpriteFromAge(spriteProvider);

        this.quadSize *= 30.0F;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
      return Layer.OPAQUE;
   }
}
