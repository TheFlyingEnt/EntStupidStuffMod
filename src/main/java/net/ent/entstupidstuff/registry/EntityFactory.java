package net.ent.entstupidstuff.registry;

import java.util.function.Supplier;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.AncientDrownedEntity;
import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.client.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.HoveringInfernoEntity;
import net.ent.entstupidstuff.client.entity.mob.LobberZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.MetalSkeletonEntity;
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
import net.ent.entstupidstuff.client.entity.passive.AlligatorGarEntity;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.entity.passive.CustomBoatEntity;
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

    public static final EntityType<SoulSkeletonEntity> SOUL_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "soul_skeleton"),
        EntityType.Builder.of(SoulSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"soul_skeleton")))
    );

    public static final EntityType<FungalSkeletonEntity> FUNGAL_SKELETON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "fungal_skeleton"),
        EntityType.Builder.of(FungalSkeletonEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.99F)
        .eyeHeight(1.74F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"fungal_skeleton")))
    );

    public static final EntityType<SporeperEntity> SPOREPER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporeper"),
		EntityType.Builder.of(SporeperEntity::new, MobCategory.MONSTER)
        .sized(0.6F, 1.7F)
        .clientTrackingRange(8).
        notInPeaceful()
        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sporeper")))
	);

    public static final EntityType<RedStoneGolemEntity> RSGolem = Registry.register(BuiltInRegistries.ENTITY_TYPE,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "redstone_golem"),
        EntityType.Builder.of(RedStoneGolemEntity::new, MobCategory.MONSTER)
        .sized(2.0f, 3.5F)
        .eyeHeight(2.60F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
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
        .sized(0.5f, 0.5F)
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


    private static EntityType.EntityFactory<CustomBoatEntity> getBigBoatFactory(Supplier<Item> itemSupplier) {
		return (type, world) -> new CustomBoatEntity(type, world, itemSupplier);
	}





    public static void onInitialize() {
        
        FabricDefaultAttributeRegistry.register(ZOMBIE_LOBBER, LobberZombieEntity.createLobberZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_SCORCHED, ScorchedZombieEntity.createScorchedZombieAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_SLIMED, SlimedZombieEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_FROSTBITTEN, FrostbittenZombieEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ANCIENT_DROWNED, AncientDrownedEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_FUNGAL, FungalZombieEntity.createAttributes());

        FabricDefaultAttributeRegistry.register(FUNGAL_SKELETON, FungalSkeletonEntity.createAttributes());


        FabricDefaultAttributeRegistry.register(ARMORED_PILLAGER, ArmoredPillagerEntity.createArmoredPillagerAttributes/*createPillagerAttributes*/());
        FabricDefaultAttributeRegistry.register(SOUL_SKELETON, SoulSkeletonEntity.createSoulSkeletonAttributes/*createPillagerAttributes*/());
        FabricDefaultAttributeRegistry.register(RSGolem, RedStoneGolemEntity.createVindicatorAttributes()/*createPillagerAttributes*/);

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
        

       
        


    }
}
