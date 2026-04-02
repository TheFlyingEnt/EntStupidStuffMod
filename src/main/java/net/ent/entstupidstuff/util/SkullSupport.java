package net.ent.entstupidstuff.util;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModSkullStype;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SkullSupport {

    public static void onInitialize() {

        for (ModSkullStype type : ModSkullStype.values()) {
            SkullBlock.Type.TYPES.put(type.getSerializedName(), type);
        }

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.DROWNED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.DROWNED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BLAZE_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BLAZE_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BREEZE_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BREEZE_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.HUSK_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.HUSK_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.STRAY_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.STRAY_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BOGGED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BOGGED_WALL_SKULL);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_LOBBER_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_LOBBER_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SCORCHED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SCORCHED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SLIMED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SLIMED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FROSTBITTEN_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FROSTBITTEN_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FUNGAL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FUNGAL_WALL_HEAD);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREBONE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREBONE_SKULL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREPER_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREPER_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SOUL_SKELETON_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SOUL_SKELETON_WALL_SKULL);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BRAIN_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BRAIN_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_FIRE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_FIRE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_HORN_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_HORN_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_TUBE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_TUBE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BUBBLE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BUBBLE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_UNUSED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_UNUSED_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_DEFAULT_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_DEFAULT_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_RED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_RED_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_BLUE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_BLUE_WALL_SKULL);
    }
    
}
