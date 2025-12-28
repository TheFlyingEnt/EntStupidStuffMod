package net.ent.entstupidstuff.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class CrystalSpikeFeatureConfig implements FeatureConfig {

    public static final Codec<CrystalSpikeFeatureConfig> CODEC =
        RecordCodecBuilder.create(instance -> instance.group(

            // Which block the spike is made of
            BlockState.CODEC
                .fieldOf("crystal_block")
                .forGetter(config -> config.crystalBlock),

            // Blocks the spike can replace (stone, deepslate, abyssal, etc.)
            TagKey.codec(net.minecraft.registry.RegistryKeys.BLOCK)
                .fieldOf("replaceable")
                .forGetter(config -> config.replaceable),

            // Length of the spike
            IntProvider.createValidatingCodec(1, 64)
                .fieldOf("length")
                .forGetter(config -> config.length),

            // Thickness at base
            IntProvider.createValidatingCodec(1, 6)
                .fieldOf("base_radius")
                .forGetter(config -> config.baseRadius),

            // How quickly it tapers
            FloatProvider.createValidatedCodec(0.0F, 1.0F)
                .fieldOf("taper_chance")
                .forGetter(config -> config.taperChance)

        ).apply(instance, CrystalSpikeFeatureConfig::new));

    public final BlockState crystalBlock;
    public final TagKey<Block> replaceable;
    public final IntProvider length;
    public final IntProvider baseRadius;
    public final FloatProvider taperChance;

    public CrystalSpikeFeatureConfig(
        BlockState crystalBlock,
        TagKey<Block> replaceable,
        IntProvider length,
        IntProvider baseRadius,
        FloatProvider taperChance
    ) {
        this.crystalBlock = crystalBlock;
        this.replaceable = replaceable;
        this.length = length;
        this.baseRadius = baseRadius;
        this.taperChance = taperChance;
    }
}