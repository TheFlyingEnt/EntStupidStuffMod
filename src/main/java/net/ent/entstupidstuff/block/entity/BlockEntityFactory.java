package net.ent.entstupidstuff.block.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BedBlockEntity;
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

    /*public static final BlockEntityType<BedBlockEntity> GSW_WHITE_BED =
    Registry.register(
        BuiltInRegistries.BLOCK_ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "glowing_white_bed"),
        FabricBlockEntityTypeBuilder.create(BedBlockEntity::new, BlockFactory.GSW_WHITE_BED).build()
    );*/

    public static void registerBlockEntities() {
        EntStupidStuff.LOGGER.info("Registering Block Entities for " + EntStupidStuff.MOD_ID);
    }
    

    /*
     * public static final BlockEntityType<EnchantingTableBlockEntity> ENCHANTING_TABLE = create(
		"enchanting_table", BlockEntityType.Builder.create(EnchantingTableBlockEntity::new, Blocks.ENCHANTING_TABLE)
	);
     */

    public static void onInitialize() {}

    

    
    
}
