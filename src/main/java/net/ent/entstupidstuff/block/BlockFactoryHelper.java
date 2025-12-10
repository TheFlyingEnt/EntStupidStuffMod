package net.ent.entstupidstuff.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TintedParticleLeavesBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.UntintedParticleLeavesBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

@SuppressWarnings("unused")
public class BlockFactoryHelper {

    public static void NatureFamily(String blockName, String suffix, MapColor mapColor, MapColor mapColor2, Boolean flamable, Boolean withLeaves){ //1.21.10 Tracker - Completed

        Block LOG = BlockFactory.register3(
            blockName + "_log" + suffix,
            (settings) -> new PillarBlock(settings),
            Blocks.createLogSettings(mapColor, mapColor, BlockSoundGroup.WOOD)
        );

        Block STRIPPED_LOG = BlockFactory.register3(
            "stripped_" + blockName + "_log" + suffix,
            (settings) -> new PillarBlock(settings),
            Blocks.createLogSettings(mapColor, mapColor, BlockSoundGroup.WOOD)
        );

        Block WOOD = BlockFactory.register3(
            blockName + "_wood" + suffix,
            (settings) -> new PillarBlock(settings),
            AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
        );

        Block STRIPPED_WOOD = BlockFactory.register3(
            "stripped_" + blockName + "_wood" + suffix,
            (settings) -> new PillarBlock(settings),
            AbstractBlock.Settings.create()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
        );

        if (withLeaves) {

            boolean tint = false;
            Block LEAVES;

            if (tint) { //Updated
                LEAVES = BlockFactory.register3(
                    blockName + "_leaves" + suffix, (settings) -> new TintedParticleLeavesBlock(0.01F, settings),
                    AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
                );
            }
            else {
                LEAVES = BlockFactory.register3( //TODO: 1.21.10 Updating
                    blockName + "_leaves" + suffix,
                    (settings) -> new UntintedParticleLeavesBlock(
                        0.1F,
                        ParticleTypes.CHERRY_LEAVES,
                        settings
                    ),
                    AbstractBlock.Settings.create()
                        .mapColor(MapColor.PINK)
                        .strength(0.2F)
                        .ticksRandomly()
                        .sounds(BlockSoundGroup.CHERRY_LEAVES)
                        .nonOpaque()
                        .allowsSpawning(Blocks::canSpawnOnLeaves)
                        .suffocates(Blocks::never)
                        .blockVision(Blocks::never)
                        .burnable()
                        .pistonBehavior(PistonBehavior.DESTROY)
                        .solidBlock(Blocks::never)
                );

            }

            //ModGroup.addToNatural(blockName + "_leaves" + suffix);
            //ModGroup.addToDeco(blockName + "_leaves" + suffix);
        }

        //ModGroup.addToNatural(blockName + "_log" + suffix);
        //ModGroup.addToNatural("stripped_" + blockName + "_log" + suffix);
        //ModGroup.addToNatural(blockName + "_wood" + suffix);
        //ModGroup.addToNatural("stripped_" + blockName + "_wood" + suffix);
        
        //ModGroup.addToDeco(blockName + "_log" + suffix);
        //ModGroup.addToDeco("stripped_" + blockName + "_log" + suffix);
        //ModGroup.addToDeco(blockName + "_wood" + suffix);
        //ModGroup.addToDeco("stripped_" + blockName + "_wood" + suffix);        

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);

            if (withLeaves) {
                FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_leaves" + suffix)), 30, 60);
            }
        }
        
        
    }

    public static void BaseFamily(String blockName, String varient, Block baseBlock, Boolean flamable){

        Block STAIRS = BlockFactory.register3(blockName + "_stairs" + varient, 
            (settings) -> new StairsBlock(baseBlock.getDefaultState(), settings),
            AbstractBlock.Settings.copy(baseBlock)); 

        Block SLAB = BlockFactory.register3(blockName + "_slab" + varient, 
            SlabBlock::new,
            AbstractBlock.Settings.copy(baseBlock));

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add(baseBlock, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(STAIRS, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(SLAB, 5, 20);
        }

        ////ModGroup.addToDeco(blockName + "_planks" + varient);
        //ModGroup.addToDeco(blockName + "_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_slab" + varient);
    }

    public static void MosicFamily(String blockName, String varient, Block baseBlock, Boolean flamable){

        Block MOSAIC  = BlockFactory.register3(blockName + "_mosaic" + varient,
            Block::new,
            AbstractBlock.Settings.copy(baseBlock));

        Block MOSAIC_STAIRS = BlockFactory.register3(blockName + "_mosaic_stairs" + varient, 
            (settings) -> new StairsBlock(baseBlock.getDefaultState(), settings),
            AbstractBlock.Settings.copy(baseBlock)); 

        Block MOSAIC_SLAB = BlockFactory.register3(blockName + "_mosaic_slab" + varient, 
            SlabBlock::new,
            AbstractBlock.Settings.copy(baseBlock));

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);
        }

        //ModGroup.addToDeco(blockName + "_mosaic" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }
    
    public static void InteractionFamily(String blockName, String suffix, Block baseBlock, MapColor mapColor, Boolean flamable, Boolean IsWooden){

        Block FENCE = BlockFactory.register3(blockName + "_fence" + suffix, 
            FenceBlock::new,
            AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor((mapColor))); 

        Block FENCE_GATE = BlockFactory.register3(blockName + "_fence_gate" + suffix, 
            (settings) -> new FenceGateBlock(WoodType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor((mapColor))); 

        Block DOOR = BlockFactory.register3(blockName + "_door" + suffix, 
            (settings) -> new DoorBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor)));

        Block GLASS_DOOR = BlockFactory.register3(blockName + "_glass_door" + suffix, 
            (settings) -> new DoorBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor))); 

        Block TRAP_DOOR = BlockFactory.register3(blockName + "_trapdoor" + suffix, 
            (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)));

        Block GLASS_TRAP_DOOR = BlockFactory.register3(blockName + "_glass_trapdoor" + suffix, 
            (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)));

        Block P_PLATE = BlockFactory.register3(blockName + "_pressure_plate" + suffix, 
            (settings) -> new PressurePlateBlock(BlockSetType.OAK, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor))); 

        Block BUTTON = BlockFactory.register3(blockName + "_button" + suffix, 
            (settings) -> new ButtonBlock(BlockSetType.OAK, 30, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor((mapColor))); 

        //ModGroup.addToDeco(blockName + "_fence" + suffix);
        //ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        //ModGroup.addToDeco(blockName + "_door" + suffix);
        //ModGroup.addToDeco(blockName + "_glass_door" + suffix);
        //ModGroup.addToDeco(blockName + "_trapdoor" + suffix);
        //ModGroup.addToDeco(blockName + "_glass_trapdoor" + suffix);
        //ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        //ModGroup.addToDeco(blockName + "_button" + suffix);

        if (flamable) {
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_button" + suffix)), 5, 20);
        }
    }

    public static void StoneFamily(String blockName, String varient, Block baseBlock, Boolean onlyWall) {
        Block Wall = BlockFactory.register3(blockName + "_wall" + varient, 
            WallBlock::new,
            AbstractBlock.Settings.copy(baseBlock));
            
        if (!onlyWall) {
            Block Chizeled = BlockFactory.register3(blockName + "chiseled" + varient, 
                Block::new,
                AbstractBlock.Settings.copy(baseBlock));
            Block Cracked = BlockFactory.register3("cracked_" + blockName + "_" + varient, 
                Block::new,
                AbstractBlock.Settings.copy(baseBlock));
        }
    }

    public static void VanillaAdditions_Wood(String blockName, Block baseBlock, BlockSetType bST, Block trapdoor, Block door, Boolean flamable){
        MosicFamily(blockName, "", baseBlock, flamable);
        Block GLASS_TRAPDOOR = BlockFactory.register3(blockName  + "_glass_trapdoor", 
            (settings) -> new TrapdoorBlock(bST, settings),
            AbstractBlock.Settings.copy(trapdoor));
        Block GLASS_DOOR = BlockFactory.register3(blockName  + "_glass_door", 
            (settings) -> new DoorBlock(bST, settings),
            AbstractBlock.Settings.copy(door));
    }

    public static void addSaplings(String blockName, SaplingGenerator generator) {

        Block SAPLING = BlockFactory.register3(
            blockName + "_sapling",
            (settings) -> new SaplingBlock(generator, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)
        );

        Block POT_SAPLING = BlockFactory.register3(
            "potted_" + blockName + "_sapling",
            (settings) -> new FlowerPotBlock(SAPLING, settings),
            Blocks.createFlowerPotSettings()
        );
    }
}
