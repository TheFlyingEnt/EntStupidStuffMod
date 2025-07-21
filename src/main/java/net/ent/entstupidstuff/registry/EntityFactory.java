package net.ent.entstupidstuff.registry;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.mob.AlligatorGarEntity;
import net.ent.entstupidstuff.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.entity.mob.HoveringInfernoEntity;
import net.ent.entstupidstuff.entity.mob.LobberZombieEntity;
import net.ent.entstupidstuff.entity.mob.MetalSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.PiglinFungalEntity;
import net.ent.entstupidstuff.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.entity.mob.ScorchedZombieEntity;
import net.ent.entstupidstuff.entity.mob.SkeletonCrossbowEntity;
import net.ent.entstupidstuff.entity.mob.SkeletonPirateCaptainEntity;
import net.ent.entstupidstuff.entity.mob.SoulSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.ZebraFishEntity;
import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.entity.passive.CustomBoatEntity;
import net.ent.entstupidstuff.entity.projectile.CannonBallEntity;
import net.ent.entstupidstuff.entity.projectile.UnderwaterArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class EntityFactory {

    public static final EntityType<LobberZombieEntity> LOBBER_ZOMBIE = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "zombie_lobber"),
        EntityType.Builder.create(LobberZombieEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.vehicleAttachment(-0.7F)
		.maxTrackingRange(8)
        .build()
    );

    public static final EntityType<ScorchedZombieEntity> ZOMBIE_SCORCHED = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "zombie_scorched"),
        EntityType.Builder.create(ScorchedZombieEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.vehicleAttachment(-0.7F)
		.maxTrackingRange(8)
        .build()
    );

    public static final EntityType<FrostbittenZombieEntity> ZOMBIE_FROSTBITTEN = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten"),
        EntityType.Builder.create(FrostbittenZombieEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.vehicleAttachment(-0.7F)
		.maxTrackingRange(8)
        .build()
    );

    public static final EntityType<ArmoredPillagerEntity> ARMORED_PILLAGER = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "armored_pillager"),
        EntityType.Builder.create(ArmoredPillagerEntity::new, SpawnGroup.MONSTER)
        .spawnableFarFromPlayer()
		.dimensions(0.6F, 1.95F)
		.passengerAttachments(2.0F)
		.vehicleAttachment(-0.6F)
		.maxTrackingRange(8)
        .build()
    );

    public static final EntityType<SoulSkeletonEntity> SOUL_SKELETON = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "soul_skeleton"),
        EntityType.Builder.create(SoulSkeletonEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<RedStoneGolemEntity> RSGolem = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "redstone_golem"),
        EntityType.Builder.create(RedStoneGolemEntity::new, SpawnGroup.MONSTER)
        .dimensions(2.0f, 3.5F)
        .eyeHeight(2.60F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<ButterflyEntity> BUTTERFLY = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "butterfly"),
        EntityType.Builder.create(ButterflyEntity::new, SpawnGroup.AMBIENT)
        .dimensions(0.5f, 0.5F)
        .eyeHeight(0.25F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<CustomBoatEntity> CUSTOMBOAT = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "customboat"),
        EntityType.Builder.<CustomBoatEntity>create(CustomBoatEntity::new, SpawnGroup.MISC)
        //.dimensions(0.98F, 0.7F)
        .dimensions(5.5F, 0.7F)
		.passengerAttachments(0.1875F)
		.maxTrackingRange(8)
        .build()
    );

    public static final EntityType<AlligatorGarEntity> ALLIGATOR_GAR = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "alligator_gar"),
        EntityType.Builder.create(AlligatorGarEntity::new, SpawnGroup.WATER_AMBIENT)
        .dimensions(0.5F, 0.3F)
        .eyeHeight(0.195F).maxTrackingRange(4)
        .build()
    );

    public static final EntityType<ZebraFishEntity> ZEBRA_FISH = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "zebra_fish"),
        EntityType.Builder.create(ZebraFishEntity::new, SpawnGroup.WATER_AMBIENT)
        .dimensions(0.5F, 0.3F)
        .eyeHeight(0.195F).maxTrackingRange(4)
        .maxTrackingRange(8)
        .build()
    );



    /*
    *       The Fire of the Hunt Update
    */
    // Adds Piglin Warior, Hovering Inferno, Fox Hound, Piglin Guard
    // Soul Skeleton, Wisps, Skeleton Wolves and Ghoals

    // Wither Bones, Hunt Armor Trim, Golden Armor Trim, Blaze Rod Trim Material

    public static final EntityType<PiglinWarriorEntity> PIGLIN_WARRIOR = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "piglin_warrior"),
        EntityType.Builder.create(PiglinWarriorEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.95F)
        .eyeHeight(1.79F)
        .passengerAttachments(2.0125F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<PiglinFungalEntity> PIGLIN_FUNGAL = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "piglin_fungal"),
        EntityType.Builder.create(PiglinFungalEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.95F)
        .eyeHeight(1.79F)
        .passengerAttachments(2.0125F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
    .build()
    );

    public static final EntityType<HoveringInfernoEntity> HOVERING_INFERNO = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "hovering_inferno"),
        EntityType.Builder.create(HoveringInfernoEntity::new, SpawnGroup.MONSTER)
        .makeFireImmune()
        .dimensions(0.6F, 1.8F)
        .maxTrackingRange(8)
        .build()
    );

    /*
    *       Tales of the Seas
    */

    public static final EntityType<UnderwaterArrowEntity> UARROW = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "underwater_arrow"),
		EntityType.Builder.create((EntityType<UnderwaterArrowEntity> type, World world) -> new UnderwaterArrowEntity(type, world), SpawnGroup.MISC)
		    .dimensions(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.maxTrackingRange(4)
			.trackingTickInterval(20)
            .build()
	);

    public static final EntityType<CannonBallEntity> CANNON_BALL = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "cannon_ball"),
		EntityType.Builder.create((EntityType<CannonBallEntity> type, World world) -> new CannonBallEntity(type, world), SpawnGroup.MISC)
		    .dimensions(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.maxTrackingRange(4)
			.trackingTickInterval(20)
            .build()
	);

    public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton"),
        EntityType.Builder.create(SunkenSkeletonEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<SkeletonPirateCaptainEntity> SKELETON_PIRATE_CAPTAIN = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "skeleton_pirate_captain_concept"),
        EntityType.Builder.create(SkeletonPirateCaptainEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<SkeletonCrossbowEntity> SUNKEN_SKELETON_CROSSBOW = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton_crossbow"),
        EntityType.Builder.create(SkeletonCrossbowEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<MetalSkeletonEntity> METAL_SKELETON = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "metal_skeleton"),
        EntityType.Builder.create(MetalSkeletonEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );

    public static final EntityType<PhantomSkeletonEntity> PHANTOM_SKELETON = Registry.register(Registries.ENTITY_TYPE,
        Identifier.of(EntStupidStuff.MOD_ID, "phantom_skeleton"),
        EntityType.Builder.create(PhantomSkeletonEntity::new, SpawnGroup.MONSTER)
        .dimensions(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .vehicleAttachment(-0.7F)
        .maxTrackingRange(8)
        .build()
    );








    public static void onInitialize() {
        
        FabricDefaultAttributeRegistry.register(LOBBER_ZOMBIE, LobberZombieEntity.createLobberZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_SCORCHED, ScorchedZombieEntity.createScorchedZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_FROSTBITTEN, FrostbittenZombieEntity.createZombieAttributes());
        FabricDefaultAttributeRegistry.register(ARMORED_PILLAGER, ArmoredPillagerEntity.createArmoredPillagerAttributes/*createPillagerAttributes*/());
        FabricDefaultAttributeRegistry.register(SOUL_SKELETON, SoulSkeletonEntity.createSoulSkeletonAttributes/*createPillagerAttributes*/());
        FabricDefaultAttributeRegistry.register(RSGolem, RedStoneGolemEntity.createVindicatorAttributes()/*createPillagerAttributes*/);

        //The Fire of the Hunt Update
        FabricDefaultAttributeRegistry.register(PIGLIN_WARRIOR, PiglinWarriorEntity.createPiglinBruteAttributes());
        FabricDefaultAttributeRegistry.register(PIGLIN_FUNGAL, PiglinFungalEntity.createPiglinAttributes());

        FabricDefaultAttributeRegistry.register(HOVERING_INFERNO, HoveringInfernoEntity.createBlazeAttributes());

        FabricDefaultAttributeRegistry.register(SUNKEN_SKELETON, SunkenSkeletonEntity.createAbstractSkeletonAttributes());

        FabricDefaultAttributeRegistry.register(SKELETON_PIRATE_CAPTAIN, SkeletonEntity.createAbstractSkeletonAttributes());

        FabricDefaultAttributeRegistry.register(SUNKEN_SKELETON_CROSSBOW, SkeletonCrossbowEntity.createGenericSkeletonCrossbow());

        FabricDefaultAttributeRegistry.register(BUTTERFLY, ButterflyEntity.createButterflyAttributes());

        FabricDefaultAttributeRegistry.register(ALLIGATOR_GAR, AlligatorGarEntity.createFishAttributes());
        FabricDefaultAttributeRegistry.register(ZEBRA_FISH, ZebraFishEntity.createFishAttributes());

        //FabricDefaultAttributeRegistry.register(CUSTOMBOAT, CustomBoatEntity.());
        

        FabricDefaultAttributeRegistry.register(METAL_SKELETON, SkeletonEntity.createAbstractSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(PHANTOM_SKELETON, SkeletonEntity.createAbstractSkeletonAttributes());
        

       
        


    }
}
