package net.ent.entstupidstuff.registry;

import java.util.function.Supplier;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.DMC13Entity;
import net.ent.entstupidstuff.api.car.F1CarEntity;
import net.ent.entstupidstuff.api.car.GR86Entity;
import net.ent.entstupidstuff.api.car.HondaCivicEntity;
import net.ent.entstupidstuff.api.car.HondaCivicTypeREntity;
import net.ent.entstupidstuff.api.car.NissanZEntity;
import net.ent.entstupidstuff.api.car.PorsheGT3Entity;
import net.ent.entstupidstuff.api.car.ShelbyGT500Entity;
import net.ent.entstupidstuff.api.ship.ShipCollider;
import net.ent.entstupidstuff.api.ship.ShipEntityTest;
import net.ent.entstupidstuff.client.entity.CustomBoatEntity;
import net.ent.entstupidstuff.client.entity.mob.AncientDrownedEntity;
import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.client.entity.mob.ArmoredVindicatorEntity;
import net.ent.entstupidstuff.client.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.HoveringInfernoEntity;
import net.ent.entstupidstuff.client.entity.mob.LobberZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.MetalSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.MountaineerPillagerEntity;
import net.ent.entstupidstuff.client.entity.mob.MountaineerVindicatorEntity;
import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.client.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.client.entity.mob.ScorchedZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.SilkmothEntity;
import net.ent.entstupidstuff.client.entity.mob.SkeletonCrossbowEntity;
import net.ent.entstupidstuff.client.entity.mob.SkeletonPirateCaptainEntity;
import net.ent.entstupidstuff.client.entity.mob.SlimedZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.SoulSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.SporeperEntity;
import net.ent.entstupidstuff.client.entity.mob.SunkenSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.skeleton.CoralSkeletonEntity;
import net.ent.entstupidstuff.client.entity.passive.AlligatorGarEntity;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.entity.passive.FurTroutEntity;
import net.ent.entstupidstuff.client.entity.passive.KoiEntity;
import net.ent.entstupidstuff.client.entity.passive.MackerelEntity;
import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
import net.ent.entstupidstuff.client.entity.passive.RedPandaEntity;
import net.ent.entstupidstuff.client.entity.passive.SnapperFishEntity;
import net.ent.entstupidstuff.client.entity.passive.ZebraFishEntity;
import net.ent.entstupidstuff.client.entity.projectile.AncientTridentEntity;
import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EntityFactory {

    public static final EntityType<LobberZombieEntity> ZOMBIE_LOBBER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_lobber"),
        EntityType.Builder.of(LobberZombieEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_lobber")))
    );

    public static final EntityType<ScorchedZombieEntity> ZOMBIE_SCORCHED = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_scorched"),
        EntityType.Builder.of(ScorchedZombieEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_scorched")))
    );

    public static final EntityType<SlimedZombieEntity> ZOMBIE_SLIMED = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed"),
        EntityType.Builder.of(SlimedZombieEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"zombie_slimed")))
    );

    public static final EntityType<AncientDrownedEntity> ANCIENT_DROWNED = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_drowned"),
        EntityType.Builder.of(AncientDrownedEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"ancient_drowned")))
    );

    public static final EntityType<AncientTridentEntity> ANCIENT_TRIDENT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_trident"),
		EntityType.Builder.of((EntityType<AncientTridentEntity> type, Level world) -> new AncientTridentEntity(type, world), MobCategory.MISC)
		    .sized(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.clientTrackingRange(4)
			.updateInterval(20)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"ancient_trident")))
	);

    public static final EntityType<FrostbittenZombieEntity> ZOMBIE_FROSTBITTEN = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten"),
        EntityType.Builder.of(FrostbittenZombieEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"zombie_frostbitten")))
    );

    public static final EntityType<FungalZombieEntity> ZOMBIE_FUNGAL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal"),
        EntityType.Builder.of(FungalZombieEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
		.eyeHeight(1.74F)
		.passengerAttachments(2.0125F)
		.ridingOffset(-0.7F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"zombie_fungal")))
    );

    public static final EntityType<ArmoredPillagerEntity> ARMORED_PILLAGER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "armored_pillager"),
        EntityType.Builder.of(ArmoredPillagerEntity::new, MobCategory.MONSTER)
        .canSpawnFarFromPlayer()
		.sized(0.6F, 1.95F)
		.passengerAttachments(2.0F)
		.ridingOffset(-0.6F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"armored_pillager")))
    );

    public static final EntityType<ArmoredVindicatorEntity> ARMORED_VINDICATOR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "armored_vindicator"),
        EntityType.Builder.of(ArmoredVindicatorEntity::new, MobCategory.MONSTER)
        .canSpawnFarFromPlayer()
		.sized(0.6F, 1.95F)
		.passengerAttachments(2.0F)
		.ridingOffset(-0.6F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"armored_vindicator")))
    );

    public static final EntityType<MountaineerPillagerEntity> MOUNTAINEER_PILLAGER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mountaineer_pillager"),
        EntityType.Builder.of(MountaineerPillagerEntity::new, MobCategory.MONSTER)
        .canSpawnFarFromPlayer()
		.sized(0.6F, 1.95F)
		.passengerAttachments(2.0F)
		.ridingOffset(-0.6F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"mountaineer_pillager")))
    );

    public static final EntityType<MountaineerVindicatorEntity> MOUNTAINEER_VINDICATOR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mountaineer_vindicator"),
        EntityType.Builder.of(MountaineerVindicatorEntity::new, MobCategory.MONSTER)
        .canSpawnFarFromPlayer()
		.sized(0.6F, 1.95F)
		.passengerAttachments(2.0F)
		.ridingOffset(-0.6F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"mountaineer_vindicator")))
    );

    public static final EntityType<SoulSkeletonEntity> SOUL_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "soul_skeleton"),
        EntityType.Builder.of(SoulSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"soul_skeleton")))
    );

    public static final EntityType<FungalSkeletonEntity> SPORE_BONE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporebone"),
        EntityType.Builder.of(FungalSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sporebone")))
    );

    public static final EntityType<SporeperEntity> SPOREPER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporeper"),
		EntityType.Builder.of(SporeperEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.7F)
        .clientTrackingRange(8).
        notInPeaceful()
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sporeper")))
	);

    public static final EntityType<RedStoneGolemEntity> REDSTONE_GOLEM = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "redstone_golem"),
        EntityType.Builder.of(RedStoneGolemEntity::new, MobCategory.MONSTER)
        .sized(2.0f, 3.5F)
        .eyeHeight(3.00F)
        //.eyeHeight(2.60F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(10)
        .notInPeaceful()
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"redstone_golem")))
    );

    public static final EntityType<ButterflyEntity> BUTTERFLY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "butterfly"),
        EntityType.Builder.of(ButterflyEntity::new, MobCategory.AMBIENT)
        .sized(0.5f, 0.5F)
        .eyeHeight(0.25F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"butterfly")))
    );

    public static final EntityType<RedPandaEntity> RED_PANDA = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "red_panda"),
        EntityType.Builder.of(RedPandaEntity::new, MobCategory.AMBIENT)
        .sized(0.5f, 0.5F)
        .eyeHeight(0.25F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"red_panda")))
    );

    public static final EntityType<SilkmothEntity> SILKMOTH = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "silkmoth"),
        EntityType.Builder.of(SilkmothEntity::new, MobCategory.AMBIENT)
        .sized(0.7F, 0.6F)
        .eyeHeight(0.25F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"silkmoth")))
    );

    public static final EntityType<CustomBoatEntity> CUSTOMBOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "customboat"),
        EntityType.Builder.of(getBigBoatFactory(() -> Items.ACACIA_CHEST_BOAT), MobCategory.MISC)
        //.dimensions(0.98F, 0.7F)
        .sized(3.5F, 0.7F)
		.passengerAttachments(0.1875F)
		.clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"customboat")))
    );

    public static final EntityType<AlligatorGarEntity> ALLIGATOR_GAR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "alligator_gar"),
        EntityType.Builder.of(AlligatorGarEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.65F, 0.25F)
        .eyeHeight(0.2F).clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"alligator_gar")))
    );

    public static final EntityType<ZebraFishEntity> ZEBRA_FISH = Registry.register(BuiltInRegistries.ENTITY_TYPE, //Cod Size
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zebra_fish"),
        EntityType.Builder.of(ZebraFishEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F)
        .clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"zebra_fish")))
    );

    public static final EntityType<MackerelEntity> MACKEREL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mackerel"),
        EntityType.Builder.of(MackerelEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F).clientTrackingRange(4)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"mackerel")))
    );

    public static final EntityType<BassEntity> BASS = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "bass"),
        EntityType.Builder.of(BassEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.65F, 0.375F)
        .eyeHeight(0.195F).clientTrackingRange(4)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"bass")))
    );

    public static final EntityType<KoiEntity> KOI = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "koi"),
        EntityType.Builder.of(KoiEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.75F, 0.43F)
        .eyeHeight(0.195F).clientTrackingRange(4)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"koi")))
    );

    public static final EntityType<FurTroutEntity> FURTROUT = Registry.register(BuiltInRegistries.ENTITY_TYPE, //Cod Size
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "furtrout"),
        EntityType.Builder.of(FurTroutEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F)
        .clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"furtrout")))
    );

    public static final EntityType<PerchFishEntity> PERCH = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "perch"),
        EntityType.Builder.of(PerchFishEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F)
        .clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"perch")))
    );

    public static final EntityType<SnapperFishEntity> SNAPPER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "snapper"),
        EntityType.Builder.of(SnapperFishEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F)
        .clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"snapper")))
    );

    public static final EntityType<MahiMahiEntity> MAHIMAHI = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mahimahi"),
        EntityType.Builder.of(MahiMahiEntity::new, MobCategory.WATER_AMBIENT)
        .sized(0.5F, 0.3F)
        .eyeHeight(0.195F)
        .clientTrackingRange(4)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"mahimahi")))
    );


    /*
    *       The Fire of the Hunt Update
    */
    // Adds Piglin Warior, Hovering Inferno, Fox Hound, Piglin Guard
    // Soul Skeleton, Wisps, Skeleton Wolves and Ghoals

    // Wither Bones, Hunt Armor Trim, Golden Armor Trim, Blaze Rod Trim Material

    public static final EntityType<PiglinWarriorEntity> PIGLIN_WARRIOR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "piglin_warrior"),
        EntityType.Builder.of(PiglinWarriorEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
        .eyeHeight(1.79F)
        .passengerAttachments(2.0125F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"piglin_warrior")))
    );

    public static final EntityType<HoveringInfernoEntity> HOVERING_INFERNO = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hovering_inferno"),
        EntityType.Builder.of(HoveringInfernoEntity::new, MobCategory.MONSTER)
        .fireImmune()
        .sized(0.6F, 1.8F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"hovering_inferno")))
    );

    /*
    *       Tales of the Seas
    */

    public static final EntityType<UnderwaterArrowEntity> UARROW = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "underwater_arrow"),
		EntityType.Builder.of((EntityType<UnderwaterArrowEntity> type, Level world) -> new UnderwaterArrowEntity(type, world), MobCategory.MISC)
		    .sized(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.clientTrackingRange(4)
			.updateInterval(20)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"underwater_arrow")))
	);

    public static final EntityType<CannonballEntity> CANNON_BALL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "cannon_ball"),
		EntityType.Builder.of((EntityType<CannonballEntity> type, Level world) -> new CannonballEntity(type, world), MobCategory.MISC)
		    .sized(0.5F, 0.5F)
			.eyeHeight(0.13F)
			.clientTrackingRange(4)
			.updateInterval(20)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"cannon_ball")))
	);

    public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken_skeleton"),
        EntityType.Builder.of(SunkenSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sunken_skeleton")))
    );

    public static final EntityType<SkeletonPirateCaptainEntity> SKELETON_PIRATE_CAPTAIN = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "skeleton_pirate_captain_concept"),
        EntityType.Builder.of(SkeletonPirateCaptainEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"skeleton_pirate_captain_concept")))
    );

    public static final EntityType<SkeletonCrossbowEntity> SUNKEN_SKELETON_CROSSBOW = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken_skeleton_crossbow"),
        EntityType.Builder.of(SkeletonCrossbowEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sunken_skeleton_crossbow")))
    );

    public static final EntityType<MetalSkeletonEntity> METAL_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "metal_skeleton"),
        EntityType.Builder.of(MetalSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"metal_skeleton")))
    );

    public static final EntityType<PhantomSkeletonEntity> PHANTOM_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "phantom_skeleton"),
        EntityType.Builder.of(PhantomSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"phantom_skeleton")))
    );

    public static final EntityType<CoralSkeletonEntity> CORAL_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton"),
        EntityType.Builder.of(CoralSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"coral_skeleton")))
    );

    ///////////////////////

    public static final EntityType<ShipEntityTest> SHIP = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "shiptest"),
            EntityType.Builder.<ShipEntityTest>of(ShipEntityTest::new, MobCategory.MISC)
                    // Generous outer box for frustum culling. Doesn't affect
                    // collision because canBeCollidedWith() defaults to false.
                    .sized(10.0f, 6.0f)
                    // Track within ~160 blocks. Must be at least as wide as
                    // the SHIP_COLLIDER tracking range so colliders aren't
                    // visible past the ship itself.
                    .clientTrackingRange(10)
                    // Position updates every tick while the ship is moving.
                    .updateInterval(1)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"shiptest")))
    );

    public static final EntityType<ShipCollider> SHIP_COLLIDER  = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ship_collider"),
                        EntityType.Builder.<ShipCollider>of(ShipCollider::new, MobCategory.MISC)
                    // Matches the brick: 1 wide, 1 tall, 1 deep.
                    .sized(1.0f, 1.0f)
                    // Match the parent ship — colliders need to be loaded
                    // wherever the ship is loaded.
                    .clientTrackingRange(10)
                    .updateInterval(1)
                    // Block /summon — these only exist as ship children.
                    .noSummon()
                    // Don't save to disk. The parent Ship re-spawns the full
                    // collider set on its first tick after world load (the
                    // SloopLayout is deterministic, so respawning is fine).
                    // Lets you delete the colliderUuids field + save logic
                    // from Ship.
                    .noSave()
                    // Immune to fire/lava ticks for safety; we override
                    // hurtServer anyway.
                    .fireImmune()

                    .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"ship_collider")))
    );











    ///////////////////////


    private static EntityType.EntityFactory<CustomBoatEntity> getBigBoatFactory(Supplier<Item> itemSupplier) {
		return (type, world) -> new CustomBoatEntity(type, world, itemSupplier);
	}

    public static final EntityType<HondaCivicTypeREntity> CYBERCAR = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "cybercar"),
        EntityType.Builder.<HondaCivicTypeREntity>of(HondaCivicTypeREntity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"cybercar")))
    );

    public static final EntityType<DMC13Entity> DMC13 = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "dmc13"),
        EntityType.Builder.<DMC13Entity>of(DMC13Entity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"dmc13")))
    );

    public static final EntityType<GR86Entity> GR86 = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "gr86"),
        EntityType.Builder.<GR86Entity>of(GR86Entity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"gr86")))
    );

    public static final EntityType<PorsheGT3Entity> P911GT3 = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "gt3"),
        EntityType.Builder.<PorsheGT3Entity>of(PorsheGT3Entity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"gt3")))
    );

    public static final EntityType<ShelbyGT500Entity> SHELBYGT500 = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "gt500"),
        EntityType.Builder.<ShelbyGT500Entity>of(ShelbyGT500Entity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"gt500")))
    );

    public static final EntityType<HondaCivicTypeREntity> HONDACIVICR = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "typer"),
        EntityType.Builder.<HondaCivicTypeREntity>of(HondaCivicTypeREntity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"typer")))
    );

    public static final EntityType<NissanZEntity> NISSANZ = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "nissanz"),
        EntityType.Builder.<NissanZEntity>of(NissanZEntity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"nissanz")))
    );

    public static final EntityType<F1CarEntity> F1CAR = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "f1car"),
        EntityType.Builder.<F1CarEntity>of(F1CarEntity::new, MobCategory.MISC)
            .sized(2.5f, 1.5f)   // hitbox: 2.5 wide, 1.5 tall
            .clientTrackingRange(10)
            .updateInterval(1)   // update every tick for smooth physics sync
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"f1car")))
    );






    public static void onInitialize() {
        
        FabricDefaultAttributeRegistry.register(ZOMBIE_LOBBER, LobberZombieEntity.createLobberZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_SCORCHED, ScorchedZombieEntity.createScorchedZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_SLIMED, SlimedZombieEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_FROSTBITTEN, FrostbittenZombieEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ANCIENT_DROWNED, AncientDrownedEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_FUNGAL, FungalZombieEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(SPORE_BONE, FungalSkeletonEntity.createAttributes());


        FabricDefaultAttributeRegistry.register(ARMORED_PILLAGER, ArmoredPillagerEntity.createArmoredPillagerAttributes());
        FabricDefaultAttributeRegistry.register(MOUNTAINEER_PILLAGER, MountaineerPillagerEntity.createMountaineerPillagerAttributes());
        FabricDefaultAttributeRegistry.register(ARMORED_VINDICATOR, ArmoredVindicatorEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MOUNTAINEER_VINDICATOR, MountaineerVindicatorEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SOUL_SKELETON, SoulSkeletonEntity.createSoulSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(REDSTONE_GOLEM, RedStoneGolemEntity.createAttributes());

        //The Fire of the Hunt Update
        FabricDefaultAttributeRegistry.register(PIGLIN_WARRIOR, PiglinWarriorEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(HOVERING_INFERNO, HoveringInfernoEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(SUNKEN_SKELETON, SunkenSkeletonEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(SKELETON_PIRATE_CAPTAIN, Skeleton.createAttributes());

        FabricDefaultAttributeRegistry.register(SUNKEN_SKELETON_CROSSBOW, SkeletonCrossbowEntity.createGenericSkeletonCrossbow());

        FabricDefaultAttributeRegistry.register(BUTTERFLY, ButterflyEntity.createButterflyAttributes());

        FabricDefaultAttributeRegistry.register(ALLIGATOR_GAR, AlligatorGarEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZEBRA_FISH, ZebraFishEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MACKEREL, MackerelEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BASS, BassEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(KOI, KoiEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FURTROUT, Cod.createAttributes());
        FabricDefaultAttributeRegistry.register(PERCH, PerchFishEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SNAPPER, SnapperFishEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MAHIMAHI, MahiMahiEntity.createAttributes());

        //FabricDefaultAttributeRegistry.register(CUSTOMBOAT, CustomBoatEntity.());
        FabricDefaultAttributeRegistry.register(RED_PANDA, RedPandaEntity.createRedPandaAttributes());


        FabricDefaultAttributeRegistry.register(METAL_SKELETON, Skeleton.createAttributes());
        FabricDefaultAttributeRegistry.register(PHANTOM_SKELETON, Skeleton.createAttributes());

        FabricDefaultAttributeRegistry.register(SPOREPER, SporeperEntity.createCreeperAttributes());

        FabricDefaultAttributeRegistry.register(SILKMOTH, SilkmothEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(CORAL_SKELETON, CoralSkeletonEntity.createAttributes());

        //FabricDefaultAttributeRegistry.register(CAR, CarEntity.createAttributes());

        

       
        


    }
}
