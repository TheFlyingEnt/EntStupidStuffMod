package net.ent.entstupidstuff.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class CrystalSpikeFeatureConfig implements FeatureConfiguration {

    public static final Codec<CrystalSpikeFeatureConfig> CODEC =
        RecordCodecBuilder.create(instance -> instance.group(

            // Which block the spike is made of
            BlockState.CODEC
                .fieldOf("blue_crystal_block")
                .forGetter(config -> config.crystalBlock),

            // Blocks the spike can replace (stone, deepslate, abyssal, etc.)
            TagKey.hashedCodec(net.minecraft.core.registries.Registries.BLOCK)
                .fieldOf("replaceable")
                .forGetter(config -> config.replaceable),

            // Length of the spike
            IntProvider.codec(1, 64)
                .fieldOf("length")
                .forGetter(config -> config.length),

            // Thickness at base
            IntProvider.codec(1, 6)
                .fieldOf("base_radius")
                .forGetter(config -> config.baseRadius),

            // How quickly it tapers
            FloatProvider.codec(0.0F, 1.0F)
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