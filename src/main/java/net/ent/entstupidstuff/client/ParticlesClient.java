package net.ent.entstupidstuff.client;

import net.ent.entstupidstuff.client.particle.HammerBoomParticleFactory;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;

public class ParticlesClient {

    public static void initalize() {

        //ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.MAPLE_LEAVES, spriteProvider -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider));
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.PHANTOM_FLAME, FlameParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.HAMMER_BOOM, HammerBoomParticleFactory::new);
        
    }
    
}
