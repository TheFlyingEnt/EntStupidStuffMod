package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.EntityTypeTags;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider{

    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {

        this.valueLookupBuilder(EntityTypeTags.ZOMBIES)
        .add(
            EntityFactory.ZOMBIE_LOBBER,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SLIMED,
            EntityFactory.ANCIENT_DROWNED,
            EntityFactory.ZOMBIE_FROSTBITTEN,
            EntityFactory.ZOMBIE_FUNGAL,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SCORCHED
        );

        this.valueLookupBuilder(EntityTypeTags.RAIDERS)
        .add(
            EntityFactory.ARMORED_PILLAGER
        );

        this.valueLookupBuilder(EntityTypeTags.SKELETONS)
        .add(
            EntityFactory.SOUL_SKELETON,
            EntityFactory.SPORE_BONE,
            EntityFactory.SUNKEN_SKELETON_CROSSBOW,
            EntityFactory.METAL_SKELETON,
            EntityFactory.PHANTOM_SKELETON
        );

        this.valueLookupBuilder(EntityTypeTags.ILLAGER)
        .add(
            EntityFactory.ARMORED_PILLAGER
        );

        this.valueLookupBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE)
        .add(
            EntityFactory.BUTTERFLY,
            EntityFactory.SILKMOTH
        );

        this.valueLookupBuilder(EntityTypeTags.BOAT)
        .add(
            EntityFactory.CUSTOMBOAT
        );

        this.valueLookupBuilder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
        .add(
            EntityFactory.ALLIGATOR_GAR,
            EntityFactory.ZEBRA_FISH,
            EntityFactory.MACKEREL,
            EntityFactory.BASS,
            EntityFactory.KOI,
            EntityFactory.FURTROUT,
            EntityFactory.PERCH,
            EntityFactory.SNAPPER,
            EntityFactory.MAHIMAHI
        );

        this.valueLookupBuilder(EntityTypeTags.AXOLOTL_HUNT_TARGETS)
        .add(
            EntityFactory.CUSTOMBOAT
        );

        this.valueLookupBuilder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES)
        .add(
            EntityFactory.PHANTOM_SKELETON
        );

        this.valueLookupBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER) //Already adds Undead
        .add(
            EntityFactory.ALLIGATOR_GAR,
            EntityFactory.ZEBRA_FISH,
            EntityFactory.MACKEREL,
            EntityFactory.BASS,
            EntityFactory.KOI,
            EntityFactory.FURTROUT,
            EntityFactory.PERCH,
            EntityFactory.SNAPPER,
            EntityFactory.MAHIMAHI
        );

        this.valueLookupBuilder(EntityTypeTags.AQUATIC)
        .add(
            EntityFactory.ALLIGATOR_GAR,
            EntityFactory.ZEBRA_FISH,
            EntityFactory.MACKEREL,
            EntityFactory.BASS,
            EntityFactory.KOI,
            EntityFactory.FURTROUT,
            EntityFactory.PERCH,
            EntityFactory.SNAPPER,
            EntityFactory.MAHIMAHI,
            EntityFactory.ANCIENT_DROWNED
        );

        this.valueLookupBuilder(EntityTypeTags.ARTHROPOD)
        .add(
            EntityFactory.BUTTERFLY,
            EntityFactory.SILKMOTH
        );

        this.valueLookupBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
        .add(
            EntityFactory.ZOMBIE_LOBBER,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SLIMED,
            EntityFactory.ANCIENT_DROWNED,
            EntityFactory.ZOMBIE_FROSTBITTEN,
            EntityFactory.ZOMBIE_FUNGAL,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.ZOMBIE_SCORCHED,
            EntityFactory.SOUL_SKELETON,
            EntityFactory.SPORE_BONE,
            EntityFactory.SUNKEN_SKELETON_CROSSBOW,
            EntityFactory.METAL_SKELETON,
            EntityFactory.PHANTOM_SKELETON
        );

        this.valueLookupBuilder(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS)
        .add(
            EntityFactory.BUTTERFLY,
            EntityFactory.SILKMOTH
        );

        this.valueLookupBuilder(EntityTypeTags.CANNOT_BE_PUSHED_ONTO_BOATS)
        .add(
            EntityFactory.ALLIGATOR_GAR,
            EntityFactory.ZEBRA_FISH,
            EntityFactory.MACKEREL,
            EntityFactory.BASS,
            EntityFactory.KOI,
            EntityFactory.FURTROUT,
            EntityFactory.PERCH,
            EntityFactory.SNAPPER,
            EntityFactory.MAHIMAHI
        );

        this.valueLookupBuilder(EntityTypeTags.IMPACT_PROJECTILES)
        .add(
            EntityFactory.CANNON_BALL,
            EntityFactory.ANCIENT_TRIDENT,
            EntityFactory.UARROW
        );


    }
    
}
