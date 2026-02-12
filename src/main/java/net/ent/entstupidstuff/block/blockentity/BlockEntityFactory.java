package net.ent.entstupidstuff.block.blockentity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/*
 * Vanilla Reference: BlockEntityType.java
 */

public class BlockEntityFactory<T extends BlockEntity> {
    
    public static final BlockEntityType<DarkEnchantingTableBlockEntity> DARK_ENCHANTING_TABLE =
    Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "dark_enchanting_table"),
        FabricBlockEntityTypeBuilder.create(DarkEnchantingTableBlockEntity::new, BlockFactory.DARK_ENCHANTMENT_TABLE).build()
    );

    public static final BlockEntityType<MushroomAuraBlockEntity> MUSHROOM_AURA_BLOCK_ENTITY =
    Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mushroom_aura_block"),
        FabricBlockEntityTypeBuilder.create(MushroomAuraBlockEntity::new, BlockFactory.MUSHROOM_AURA_BLOCK).build()
    );

    public static final BlockEntityType<MushroomAuraBlockEntity_2> MUSHROOM_AURA_BLOCK_ENTITY_2 =
    Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mushroom_aura_block_2"),
        FabricBlockEntityTypeBuilder.create(MushroomAuraBlockEntity_2::new, BlockFactory.MUSHROOM_AURA_BLOCK_2).build()
    );

    

    public static void registerBlockEntities() {
        EntStupidStuff.LOGGER.info("Registering Block Entities for " + EntStupidStuff.MOD_ID);
    }

    public static void onInitialize() {
        registerBlockEntities();
    }


    

    
    
}
