package net.ent.entstupidstuff.world.biome;

import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import com.mojang.datafixers.util.Pair;
import terrablender.api.ParameterUtils.Continentalness;
import terrablender.api.ParameterUtils.Erosion;
import terrablender.api.ParameterUtils.Humidity;
import terrablender.api.ParameterUtils.ParameterPointListBuilder;
import terrablender.api.ParameterUtils.Temperature;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

//https://minecraft.wiki/w/World_generation#Biomes
//OverworldBiomeBuilder.class

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        // ## Sunken Seas
        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.COOL, Temperature.NEUTRAL))
            .humidity(Humidity.span(Humidity.WET, Humidity.HUMID))
            .continentalness(Continentalness.span(
                Continentalness.DEEP_OCEAN,
                Continentalness.OCEAN
            ))
            .erosion(Erosion.span(
                    Erosion.EROSION_2,
                    Erosion.EROSION_2
            ))
            .depth(Parameter.span(0.2F, 0.5F), Parameter.span(0.2F, 0.5F))
            .weirdness(Weirdness.span(
                Weirdness.MID_SLICE_NORMAL_ASCENDING, 
                Weirdness.MID_SLICE_NORMAL_DESCENDING //PEAK_NORMAL
            ))
        .build().forEach(point -> builder.add(point, ModBiomes.SUNKEN_SEA)); //0.6f)*/

        // I need mushroom biomes to spawn in cheese caves
        
        //Mushroom
        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.FULL_RANGE, Temperature.FULL_RANGE))
            .humidity(Humidity.span(Humidity.FULL_RANGE, Humidity.FULL_RANGE)) //How Hot a Biome is
            .continentalness(Continentalness.INLAND) //TBA
            .erosion(Erosion.EROSION_3, Erosion.EROSION_4)
            //.depth(Depth.UNDERGROUND, Depth.FLOOR)
            .depth(MultiNoiseUtil.ParameterRange.of(0.8F, 0.9F))  // Between Vanilla Caves (Included) and DeepDark (Exclusive)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL)//MID_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.UNDERGROUND_BLUE_MUSHROOM));*/


        // ## UNDERGROUND_BLUE_MUSHROOM - ChatGPT
        new ParameterPointListBuilder()
            // Temperature/humidity: keep full range so biome can appear under many surface biomes
            .temperature(Temperature.span(Temperature.FULL_RANGE, Temperature.FULL_RANGE))
            .humidity(Humidity.span(Humidity.FULL_RANGE, Humidity.FULL_RANGE))

            // Continentalness: inland / mid-continental areas (not ocean). Cheese caves form in wide pockets,
            // and INLAND is appropriate if you want them under land biomes rather than ocean.
            .continentalness(Continentalness.INLAND)

            // Erosion: lower erosion encourages large, smooth chambers (cheese style).
            // Choose a low-to-mid erosion span so the biome covers smooth big pockets.
            .erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_2))

            // Depth: bias toward the “mid-to-deep” underground slice where big caverns commonly appear.
            // If you prefer exact control, you can use a ParameterRange like your old example.
            .depth(Climate.Parameter.span(0.7F, 0.92F))

            // Weirdness: use the MID_SLICE ascending weirdness — this is the zone where Minecraft's
            // noise caves often produce large pocket/cheese-style cavities.
            .weirdness(Weirdness.span(
                Weirdness.MID_SLICE_NORMAL_ASCENDING,
                Weirdness.MID_SLICE_NORMAL_ASCENDING
            ))

        .build().forEach(point -> builder.add(point, ModBiomes.UNDERGROUND_BLUE_MUSHROOM));

        /*

        This verison spawn exactly in Deep Dark areas
        
        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.FULL_RANGE, Temperature.FULL_RANGE))
            .humidity(Humidity.span(Humidity.FULL_RANGE, Humidity.FULL_RANGE)) //How Hot a Biome is
            .continentalness(Continentalness.INLAND) //TBA
            .erosion(Erosion.EROSION_0, Erosion.EROSION_0) //Deep Dark Values
            //.depth(Depth.UNDERGROUND, Depth.FLOOR)
            .depth(MultiNoiseUtil.ParameterRange.of(0.7F, 1.1F))  
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.UNDERGROUND_BLUE_MUSHROOM));

        
        */

        /*
        | -1 = SKY
        |
        |
        |
        |
        | 0 = Surfurace
        |
        |
        |
        |
        | 1 = Floor
        */

        /*
        D = 0: Surface Biomes
        D = 0.2~0.9 + Continentalness=0.8~1.0: Dripstone Caves
        D = 0.2~0.9 + Humidity=0.7~1.0 : Dripstone Caves
        D = 1.0: Surface biomes 
        D = 1.1 + Erosion=-1.0~-0.375: Deep Dark
        */

        //ChatGPT:
        new ParameterPointListBuilder()
            // Cold-only biome
            .temperature(Temperature.span(Temperature.FROZEN, Temperature.FROZEN))
            .humidity(Humidity.span(Humidity.ARID, Humidity.NEUTRAL))

            // Dripstone caves typically appear inland / mid-continental
            .continentalness(Continentalness.INLAND)

            // Lower erosion encourages large, smooth caverns (cheese-style),
            // similar to how dripstone caves form
            .erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_2))

            // Bias toward mid-to-deep underground where dripstone caves live
            // This mirrors vanilla behavior much better than Depth.UNDERGROUND
            //.depth(MultiNoiseUtil.ParameterRange.of(0.65F, 0.9F))
            .depth(Climate.Parameter.span(0.45F, 0.9F))

            // Mid-slice weirdness = large caverns (cheese caves)
            .weirdness(Weirdness.span(
                Weirdness.MID_SLICE_NORMAL_ASCENDING,
                Weirdness.MID_SLICE_NORMAL_ASCENDING
            ))

        .build().forEach(point -> builder.add(point, ModBiomes.ICY_CAVES));


        //IceSpikes
        /*new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.ICY, Temperature.ICY))
            .humidity(Humidity.span(Humidity.ARID, Humidity.ARID))
            .continentalness(Continentalness.INLAND)
            .erosion(Erosion.EROSION_1, Erosion.EROSION_1)
            .depth(Depth.UNDERGROUND, Depth.UNDERGROUND)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.ICY_CAVES));*/

        /*
         new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.FULL_RANGE, Temperature.FULL_RANGE))
            .humidity(Humidity.span(Humidity.FULL_RANGE, Humidity.FULL_RANGE))
            .continentalness(Continentalness.INLAND)
            .erosion(Erosion.EROSION_1, Erosion.EROSION_1)
            //.depth(Depth.UNDERGROUND, Depth.FLOOR)
            .depth(MultiNoiseUtil.ParameterRange.of(0.5F, 0.9F))  
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
        .build().forEach(point -> builder.add(point, ModBiomes.UNDERGROUND_BLUE_MUSHROOM));
         */



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
