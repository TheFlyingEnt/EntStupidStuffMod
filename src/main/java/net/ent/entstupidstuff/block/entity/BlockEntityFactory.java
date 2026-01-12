package net.ent.entstupidstuff.block.entity;

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
