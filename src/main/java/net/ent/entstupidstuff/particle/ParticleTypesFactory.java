package net.ent.entstupidstuff.particle;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ParticleTypesFactory {

    public static final SimpleParticleType MAPLE_LEAVES = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "maple_leaves"), FabricParticleTypes.simple());
    public static final SimpleParticleType PHANTOM_FLAME = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "phantom_flame"), FabricParticleTypes.simple());
    public static final SimpleParticleType HAMMER_BOOM = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hammer_boom"), FabricParticleTypes.simple());
    public static final SimpleParticleType FALLING_MUSHROOM_SPORE_BLOSSOM = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "falling_mushroom_spore_blossom"), FabricParticleTypes.simple());
    public static final SimpleParticleType FALLING_MUSHROOM_SPORE = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "falling_mushroom_spore_air"), FabricParticleTypes.simple());
    public static final SimpleParticleType TYRE_SMOKE = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "tyre_smoke"), FabricParticleTypes.simple());

    public static void initalizer() {
        //Starting
    }


}
