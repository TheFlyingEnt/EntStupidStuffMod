package net.ent.entstupidstuff.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

@SuppressWarnings("unused")
public class BlockFactoryHelper {

    public static void NatureFamily(String blockName, String suffix, MapColor mapColor, MapColor mapColor2, Boolean flamable, Boolean withLeaves){ //1.21.10 Tracker - Completed

        Block LOG = BlockFactory.register(
            blockName + "_log" + suffix,
            (settings) -> new RotatedPillarBlock(settings),
            Blocks.logProperties(mapColor, mapColor, SoundType.WOOD)
        );

        Block STRIPPED_LOG = BlockFactory.register(
            "stripped_" + blockName + "_log" + suffix,
            (settings) -> new RotatedPillarBlock(settings),
            Blocks.logProperties(mapColor, mapColor, SoundType.WOOD)
        );

        Block WOOD = BlockFactory.register(
            blockName + "_wood" + suffix,
            (settings) -> new RotatedPillarBlock(settings),
            BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava()
        );

        Block STRIPPED_WOOD = BlockFactory.register(
            "stripped_" + blockName + "_wood" + suffix,
            (settings) -> new RotatedPillarBlock(settings),
            BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava()
        );

        if (withLeaves) {

            boolean tint = false;
            Block LEAVES;

            if (tint) { //Updated
                LEAVES = BlockFactory.register(
                    blockName + "_leaves" + suffix, (settings) -> new TintedParticleLeavesBlock(0.01F, settings),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                );
            }
            else {
                LEAVES = BlockFactory.register( //TODO: 1.21.10 Updating
                    blockName + "_leaves" + suffix,
                    (settings) -> new UntintedParticleLeavesBlock(
                        0.1F,
                        ParticleTypes.CHERRY_LEAVES,
                        settings
                    ),
                    BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PINK)
                        .strength(0.2F)
                        .randomTicks()
                        .sound(SoundType.CHERRY_LEAVES)
                        .noOcclusion()
                        .isValidSpawn(Blocks::ocelotOrParrot)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never)
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY)
                        .isRedstoneConductor(Blocks::never)
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

        Block STAIRS = BlockFactory.register(blockName + "_stairs" + varient, 
            (settings) -> new StairBlock(baseBlock.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(baseBlock)); 

        Block SLAB = BlockFactory.register(blockName + "_slab" + varient, 
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(baseBlock));

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

        Block MOSAIC  = BlockFactory.register(blockName + "_mosaic" + varient,
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(baseBlock));

        Block MOSAIC_STAIRS = BlockFactory.register(blockName + "_mosaic_stairs" + varient, 
            (settings) -> new StairBlock(baseBlock.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(baseBlock)); 

        Block MOSAIC_SLAB = BlockFactory.register(blockName + "_mosaic_slab" + varient, 
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(baseBlock));

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

        Block FENCE = BlockFactory.register(blockName + "_fence" + suffix, 
            FenceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor((mapColor))); 

        Block FENCE_GATE = BlockFactory.register(blockName + "_fence_gate" + suffix, 
            (settings) -> new FenceGateBlock(WoodType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).mapColor((mapColor))); 

        Block DOOR = BlockFactory.register(blockName + "_door" + suffix, 
            (settings) -> new DoorBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion().mapColor((mapColor)));

        Block GLASS_DOOR = BlockFactory.register(blockName + "_glass_door" + suffix, 
            (settings) -> new DoorBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion().mapColor((mapColor))); 

        Block TRAP_DOOR = BlockFactory.register(blockName + "_trapdoor" + suffix, 
            (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)));

        Block GLASS_TRAP_DOOR = BlockFactory.register(blockName + "_glass_trapdoor" + suffix, 
            (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)));

        Block P_PLATE = BlockFactory.register(blockName + "_pressure_plate" + suffix, 
            (settings) -> new PressurePlateBlock(BlockSetType.OAK, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor))); 

        Block BUTTON = BlockFactory.register(blockName + "_button" + suffix, 
            (settings) -> new ButtonBlock(BlockSetType.OAK, 30, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).mapColor((mapColor))); 

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
        Block Wall = BlockFactory.register(blockName + "_wall" + varient, 
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(baseBlock));
            
        if (!onlyWall) {
            Block Chizeled = BlockFactory.register(blockName + "chiseled" + varient, 
                Block::new,
                BlockBehaviour.Properties.ofFullCopy(baseBlock));
            Block Cracked = BlockFactory.register("cracked_" + blockName + "_" + varient, 
                Block::new,
                BlockBehaviour.Properties.ofFullCopy(baseBlock));
        }
    }

    public static void VanillaAdditions_Wood(String blockName, Block baseBlock, BlockSetType bST, Block trapdoor, Block door, Boolean flamable){
        MosicFamily(blockName, "", baseBlock, flamable);
        Block GLASS_TRAPDOOR = BlockFactory.register(blockName  + "_glass_trapdoor", 
            (settings) -> new TrapDoorBlock(bST, settings),
            BlockBehaviour.Properties.ofFullCopy(trapdoor));
        Block GLASS_DOOR = BlockFactory.register(blockName  + "_glass_door", 
            (settings) -> new DoorBlock(bST, settings),
            BlockBehaviour.Properties.ofFullCopy(door));
    }

    public static void addSaplings(String blockName, TreeGrower generator) {

        Block SAPLING = BlockFactory.register(
            blockName + "_sapling",
            (settings) -> new SaplingBlock(generator, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
        );

        Block POT_SAPLING = BlockFactory.register(
            "potted_" + blockName + "_sapling",
            (settings) -> new FlowerPotBlock(SAPLING, settings),
            Blocks.flowerPotProperties()
        );
    }
}
