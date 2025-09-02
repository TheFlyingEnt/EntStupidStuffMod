package net.ent.entstupidstuff.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils.Continentalness;
import terrablender.api.ParameterUtils.Depth;
import terrablender.api.ParameterUtils.Erosion;
import terrablender.api.ParameterUtils.Humidity;
import terrablender.api.ParameterUtils.ParameterPointListBuilder;
import terrablender.api.ParameterUtils.Temperature;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {

        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.ICY, Temperature.ICY))
            .humidity(Humidity.span(Humidity.ARID, Humidity.ARID))
            .continentalness(Continentalness.INLAND)
            .erosion(Erosion.EROSION_1, Erosion.EROSION_1)
            .depth(Depth.UNDERGROUND, Depth.UNDERGROUND)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.ICY_CAVES));

        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.COOL, Temperature.WARM)) // mild, seasonal
            .humidity(Humidity.span(Humidity.HUMID, Humidity.WET))             // humid climate
            .continentalness(Continentalness.span(Continentalness.COAST, Continentalness.MID_INLAND)) // coastal to inland valleys
            .erosion(Erosion.EROSION_2, Erosion.EROSION_4)                     // rolling hills / valleys
            .depth(MultiNoiseUtil.ParameterRange.of(-1.0F, 0.2F))              // surface + airspace, not caves
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_DESCENDING) // normal → slightly hilly
        .build().forEach(point -> builder.add(point, ModBiomes.MAPLE_FOREST));*/





        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.ICY, Temperature.ICY))
            .humidity(Humidity.span(Humidity.HUMID, Humidity.HUMID))
            .continentalness(Continentalness.FAR_INLAND)
            .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
            .depth(Depth.SURFACE, Depth.FLOOR)
            //.depth(MultiNoiseUtil.ParameterRange.of(-1.0F, 0.0F))
            .depth(MultiNoiseUtil.ParameterRange.of(-1.0F, 0.2F))
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL)
        .build().forEach(point -> builder.add(point, ModBiomes.MAPLE_FOREST));




        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.ICY, Temperature.ICY))
            .humidity(Humidity.span(Humidity.HUMID, Humidity.HUMID))
            .continentalness(Continentalness.FAR_INLAND)
            .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
            .depth(Depth.SURFACE, Depth.FLOOR)
            //.depth(MultiNoiseUtil.ParameterRange.of(-1.0F, 0.0F))
            //.depth(MultiNoiseUtil.ParameterRange.of(-1.0F, 1.0F))
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL)
        .build().forEach(point -> builder.add(point, ModBiomes.MAPLE_FOREST));*/

        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.ICY, Temperature.ICY))
            .humidity(Humidity.span(Humidity.HUMID, Humidity.HUMID))
            .continentalness(Continentalness.INLAND)
            .erosion(Erosion.EROSION_1, Erosion.EROSION_1)
            .depth(Depth.SURFACE, Depth.SURFACE)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.MAPLE_FOREST));*/


        builder.build().forEach(mapper);

        

        
        
        


        /*this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.FOREST, ModBiomes.MAPLE_FOREST);
        });*/
    }
}
