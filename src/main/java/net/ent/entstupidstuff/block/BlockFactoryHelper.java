package net.ent.entstupidstuff.block;

import net.ent.entstupidstuff.item.ModGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.sound.BlockSoundGroup;

public class BlockFactoryHelper {

    public static void NatureFamily(String blockName, String suffix, MapColor mapColor, MapColor mapColor2, Boolean flamable, Boolean withLeaves){

        Block LOG = BlockFactoryUpt.register(blockName + "_log" + suffix, 
        Blocks.createLogBlock(mapColor, mapColor));

        Block STRIPPED_LOG = BlockFactoryUpt.register("stripped_" + blockName + "_log" + suffix, 
        Blocks.createLogBlock(mapColor, mapColor));

        Block WOOD = BlockFactoryUpt.register(blockName + "_wood" + suffix, 
        new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

        Block STRIPPED_WOOD = BlockFactoryUpt.register("stripped_" + blockName + "_wood" + suffix, 
        new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

        if (withLeaves) {
            Block LEAVES = BlockFactoryUpt.register(blockName + "_leaves" + suffix, new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
        }

        ModGroup.addToNatural(blockName + "_log" + suffix);
        ModGroup.addToNatural("stripped_" + blockName + "_log" + suffix);
        ModGroup.addToNatural(blockName + "_wood" + suffix);
        ModGroup.addToNatural("stripped_" + blockName + "_wood" + suffix);
        ModGroup.addToNatural(blockName + "_leaves" + suffix);

        ModGroup.addToDeco(blockName + "_log" + suffix);
        ModGroup.addToDeco("stripped_" + blockName + "_log" + suffix);
        ModGroup.addToDeco(blockName + "_wood" + suffix);
        ModGroup.addToDeco("stripped_" + blockName + "_wood" + suffix);
        ModGroup.addToDeco(blockName + "_leaves" + suffix);

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_leaves" + suffix)), 30, 60);
        }
        
        
    }

    public static void BaseFamily(String blockName, String varient, Block baseBlock, Boolean flamable){

        Block STAIRS = BlockFactoryUpt.register(blockName + "_stairs" + varient, 
            new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock))); 

        Block SLAB = BlockFactoryUpt.register(blockName + "_slab" + varient, 
            new SlabBlock(AbstractBlock.Settings.copy(baseBlock)));

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add(STAIRS, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(SLAB, 5, 20);
        }

        ModGroup.addToDeco(blockName + "_stairs" + varient);
        ModGroup.addToDeco(blockName + "_slab" + varient);
    }

    public static void MosicFamily(String blockName, String varient, Block baseBlock, Boolean flamable){

        Block MOSAIC  = BlockFactoryUpt.register(blockName + "_mosaic" + varient,
            new Block(AbstractBlock.Settings.copy(baseBlock)));

        Block MOSAIC_STAIRS = BlockFactoryUpt.register(blockName + "_mosaic_stairs" + varient, 
            new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock))); 

        Block MOSAIC_SLAB = BlockFactoryUpt.register(blockName + "_mosaic_slab" + varient, 
            new SlabBlock(AbstractBlock.Settings.copy(baseBlock)));

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);
        }

        ModGroup.addToDeco(blockName + "_mosaic" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }
    
    public static void InteractionFamily(String blockName, String suffix, Block baseBlock, MapColor mapColor, Boolean flamable, Boolean IsWooden){

        Block FENCE = BlockFactoryUpt.register(blockName + "_fence" + suffix, 
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor((mapColor)))); 

        Block FENCE_GATE = BlockFactoryUpt.register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)))); 

        Block DOOR = BlockFactoryUpt.register(blockName + "_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor))));

        Block GLASS_DOOR = BlockFactoryUpt.register(blockName + "_glass_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor)))); 

        Block TRAP_DOOR = BlockFactoryUpt.register(blockName + "_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block GLASS_TRAP_DOOR = BlockFactoryUpt.register(blockName + "_glass_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block P_PLATE = BlockFactoryUpt.register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)))); 

        Block BUTTON = BlockFactoryUpt.register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor((mapColor)))); 

        ModGroup.addToDeco(blockName + "_planks" + suffix);
        ModGroup.addToDeco(blockName + "_stairs" + suffix);
        ModGroup.addToDeco(blockName + "_slab" + suffix);
        ModGroup.addToDeco(blockName + "_fence" + suffix);
        ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        ModGroup.addToDeco(blockName + "_door" + suffix);
        ModGroup.addToDeco(blockName + "_glass_door" + suffix);
        ModGroup.addToDeco(blockName + "_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_glass_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        ModGroup.addToDeco(blockName + "_button" + suffix);

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_planks" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_slab" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_button" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_planks" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_slab" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_button" + suffix)), 5, 20);

        }

        if (IsWooden) {
            BlockFamilies.register(baseBlock)
                .button(BUTTON)
                .fence(FENCE)
                .fenceGate(FENCE_GATE)
                .pressurePlate(P_PLATE)
                .slab(BlockFactoryUpt.callBlock(blockName + "_slab" + suffix))
                .stairs(BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix))
                .trapdoor(TRAP_DOOR).trapdoor(GLASS_TRAP_DOOR)
                //.sign(SIGN, WALL_SIGN)
                .door(DOOR).door(GLASS_DOOR)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
        }
    }

    public static void StoneFamily(String blockName, String varient, Block baseBlock, Boolean onlyWall) {

        Block Wall = BlockFactoryUpt.register(blockName + "_wall" + varient, new WallBlock(AbstractBlock.Settings.copy(baseBlock)));
            if (!onlyWall) {
            Block Chizeled = BlockFactoryUpt.register(blockName + "chiseled" + varient, new Block(AbstractBlock.Settings.copy(baseBlock)));
            Block Cracked = BlockFactoryUpt.register("cracked_" + blockName + "_" + varient, new Block(AbstractBlock.Settings.copy(baseBlock)));
        }

    }

    public static void VanillaAdditions_Wood(String blockName, Block baseBlock, BlockSetType bST, Block trapdoor, Block door, Boolean flamable){

        MosicFamily(blockName, "", baseBlock, flamable);
        Block GLASS_TRAPDOOR = BlockFactoryUpt.register(blockName  + "_glass_trapdoor", new TrapdoorBlock(bST, AbstractBlock.Settings.copy(trapdoor)));
        Block GLASS_DOOR = BlockFactoryUpt.register(blockName  + "_glass_trapdoor", new DoorBlock(bST, AbstractBlock.Settings.copy(door)));

    }

}
