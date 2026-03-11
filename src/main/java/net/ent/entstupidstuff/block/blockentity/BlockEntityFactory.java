package net.ent.entstupidstuff.block.blockentity;

import java.util.stream.Stream;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

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

    /*public static final BlockEntityType<HorizontalBannerBlockEntity> BANNER = register2(
		"horizontal_banner",
		HorizontalBannerBlockEntity::new,
		BlockFactory.WHITE_VERTICAL_BANNER
	);

    private static <T extends BlockEntity> BlockEntityType<T> register2(String string, BlockEntitySupplier<? extends T> blockEntitySupplier, Block... blocks)
    {
        //FabricBlockEntityTypeBuilder.create(blockEntitySupplier, Set.of(blocks)).build()
        //FabricBlockEntityTypeBuilder.create(MushroomAuraBlockEntity_2::new, BlockFactory.MUSHROOM_AURA_BLOCK_2).build()

		Util.fetchChoiceType(References.BLOCK_ENTITY, string);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, 
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, string), 
            FabricBlockEntityTypeBuilder.create(blockEntitySupplier, blocks).build()
        );
	}*/

    public static BlockEntityType<HorizontalBannerBlockEntity> HORIZONTAL_BANNER;



    @FunctionalInterface
	interface BlockEntitySupplier<T extends BlockEntity> {
		T create(BlockPos blockPos, BlockState blockState);
	}



    

    public static void registerBlockEntities() {
        EntStupidStuff.LOGGER.info("Registering Block Entities for " + EntStupidStuff.MOD_ID);

        Block[] bannerBlocks = Stream.concat(
            BlockFactory.HORIZONTAL_BANNERS.values().stream(),
            BlockFactory.HORIZONTAL_WALL_BANNERS.values().stream()
        ).toArray(Block[]::new);

        HORIZONTAL_BANNER = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "horizontal_banner"),
            FabricBlockEntityTypeBuilder.create(
                HorizontalBannerBlockEntity::new,
                bannerBlocks
            ).build()
        );




    }

    public static void onInitialize() {
        registerBlockEntities();
    }


    

    
    
}
