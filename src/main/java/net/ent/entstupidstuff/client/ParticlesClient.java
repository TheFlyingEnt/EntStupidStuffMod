package net.ent.entstupidstuff.client;

import net.ent.entstupidstuff.client.particle.HammerBoomParticleFactory;
import net.ent.entstupidstuff.client.particle.ModFallingLeaveParticle.MapleProvider;
import net.ent.entstupidstuff.client.particle.MushroomSuspendedParticle.BlueGlowFactory;
import net.ent.entstupidstuff.client.particle.NewLeakParticle.FallingMushroomSporeBlossomFactory;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;

public class ParticlesClient {

    public static void initalize() {
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.PHANTOM_FLAME, FlameParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.HAMMER_BOOM, HammerBoomParticleFactory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.FALLING_MUSHROOM_SPORE_BLOSSOM, FallingMushroomSporeBlossomFactory::new);//FallingMSporeBlossomFactory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.FALLING_MUSHROOM_SPORE, BlueGlowFactory::new);//MapleProvider
        ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.MAPLE_LEAVES, MapleProvider::new);//MapleProvider
        
        
    }
    
}
