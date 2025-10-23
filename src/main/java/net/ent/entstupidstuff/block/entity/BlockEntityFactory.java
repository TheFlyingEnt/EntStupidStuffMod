package net.ent.entstupidstuff.block.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/*
 * Vanilla Reference: BlockEntityType.java
 */

public class BlockEntityFactory<T extends BlockEntity> {
    
    public static final BlockEntityType<DarkEnchantingTableBlockEntity> DARK_ENCHANTING_TABLE =
    Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "dark_enchanting_table"),
        FabricBlockEntityTypeBuilder.create(DarkEnchantingTableBlockEntity::new, BlockFactory.ConceptEnchantment2).build()
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
