package net.ent.entstupidstuff.particle;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleTypesFactory {

  public static final SimpleParticleType MAPLE_LEAVES = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "maple_leaves"), FabricParticleTypes.simple());
  public static final SimpleParticleType PHANTOM_FLAME = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "phantom_flame"), FabricParticleTypes.simple());
  public static final SimpleParticleType HAMMER_BOOM = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "hammer_boom"), FabricParticleTypes.simple());
  public static final SimpleParticleType FALLING_MUSHROOM_SPORE_BLOSSOM = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "falling_mushroom_spore_blossom"), FabricParticleTypes.simple());
  public static final SimpleParticleType FALLING_MUSHROOM_SPORE = Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "falling_mushroom_spore_air"), FabricParticleTypes.simple());

  public static void initalizer() {
    //Starting
  }


}
