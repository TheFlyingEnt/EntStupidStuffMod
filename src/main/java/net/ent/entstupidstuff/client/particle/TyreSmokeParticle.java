package net.ent.entstupidstuff.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class TyreSmokeParticle extends SingleQuadParticle {
 
    TyreSmokeParticle(
            ClientLevel level,
            double x, double y, double z,
            double vx, double vy, double vz,
            TextureAtlasSprite sprite) {
 
        super(level, x, y, z, sprite);
 
        // Slightly larger than campfire for denser cloud feel
        this.scale(3.5F);
        this.setSize(0.25F, 0.25F);
 
        // 10–12 second lifetime: 200–240 ticks
        this.lifetime = this.random.nextInt(40) + 200;
 
        // Near-zero gravity so smoke hovers at spawn height
        this.gravity = 0.0F;
 
        // Caller supplies near-zero velocity — particle stays in place.
        // The tiny random factor gives each puff a slightly different
        // drift so the cloud looks organic rather than a static blob.
        this.xd = vx + (this.random.nextFloat() - 0.5F) * 0.003F;
        this.yd = 0.001F + this.random.nextFloat() * 0.001F; // barely rises
        this.zd = vz + (this.random.nextFloat() - 0.5F) * 0.003F;
    }
 
    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
 
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
 
            // Very slow random drift — 10x slower than campfire so
            // particles stay near their spawn point for the full lifetime
            this.xd += this.random.nextFloat() / 50000.0F * (this.random.nextBoolean() ? 1 : -1);
            this.zd += this.random.nextFloat() / 50000.0F * (this.random.nextBoolean() ? 1 : -1);
 
            // Gravity applied each tick (negative = slight downward pull)
            this.yd -= this.gravity;
 
            this.move(this.xd, this.yd, this.zd);
 
            // Fade out during the final 90 ticks (campfire fades in last 60)
            // Rate: 0.9 / 90 = 0.01 per tick — very gradual
            // Fade out in the final 120 ticks (6 seconds) — very gradual
            // so the cloud dissolves smoothly rather than popping out
            if (this.age >= this.lifetime - 120 && this.alpha > 0.01F) {
                this.alpha -= 0.007F;
            }
        } else {
            this.remove();
        }
    }
 
    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }
 
    // ═══════════════════════════════════════════════════════════
    //  FACTORY  (Fabric ParticleProvider with RandomSource)
    // ═══════════════════════════════════════════════════════════
 
    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
 
        private final SpriteSet sprites;
 
        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }
 
        @Override
        public Particle createParticle(
                SimpleParticleType type,
                ClientLevel level,
                double x, double y, double z,
                double vx, double vy, double vz,
                RandomSource random) {
 
            TyreSmokeParticle particle = new TyreSmokeParticle(
                level, x, y, z, vx, vy, vz,
                this.sprites.get(random)   // pick a random frame from the sprite sheet
            );
            particle.setAlpha(0.90F);
            return particle;
        }
    }
}