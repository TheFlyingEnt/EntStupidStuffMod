package net.ent.entstupidstuff.particle;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleTypesFactory {

    public static final SimpleParticleType MAPLE_LEAVES = FabricParticleTypes.simple();
    public static final SimpleParticleType PHANTOM_FLAME = FabricParticleTypes.simple();

    public static void initalizer() {
      register();
    }
    
  private static void register() {

    //List<Sprite> mapleLeavesSprites = new ArrayList<>();
    //mapleLeavesSprites.add(MinecraftClient.getInstance().getSpriteAtlas((new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "particle/maple_leaves_1")))));
    //mapleLeavesSprites.add(MinecraftClient.getInstance().getSpriteAtlas().getSprite(new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "particle/maple_leaves_2"))));
    //CustomSpriteProvider spriteProvider = new CustomSpriteProvider(mapleLeavesSprites);

    //ParticleFactoryRegistry.getInstance().register(ParticleTypesFactory.MAPLE_LEAVES, spriteProvider2 -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> 
    //        new MapleLeavesParticle(world, x, y, z, spriteProvider)
    //  );


		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "maple_leaves"), MAPLE_LEAVES);
    Registry.register(Registries.PARTICLE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "phantom_flame"), PHANTOM_FLAME);

	}


}
