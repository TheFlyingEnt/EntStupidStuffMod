package net.ent.entstupidstuff.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.ent.entstupidstuff.EntStupidStuff;
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


public class SunkenSeaRegion extends Region {

    public SunkenSeaRegion() {
        super(Identifier.of(EntStupidStuff.MOD_ID, "sunken_sea"), RegionType.OVERWORLD, 5);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {

        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.COOL, Temperature.ICY))
            .humidity(Humidity.span(Humidity.WET, Humidity.HUMID))
            .continentalness(Continentalness.span(
                Continentalness.DEEP_OCEAN,
                Continentalness.OCEAN
            ))
            .erosion(Erosion.span(
                    Erosion.EROSION_0,
                    Erosion.EROSION_2
            ))
            .depth(Depth.UNDERGROUND, Depth.UNDERGROUND)
            .weirdness(Weirdness.span(
                Weirdness.MID_SLICE_NORMAL_ASCENDING,
                Weirdness.MID_SLICE_NORMAL_DESCENDING
            ))
        .build().forEach(point -> builder.add(point, ModBiomes.SUNKEN_SEA)); //0.6f)

    }
}

