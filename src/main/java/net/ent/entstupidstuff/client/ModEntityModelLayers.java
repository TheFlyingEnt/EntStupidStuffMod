package net.ent.entstupidstuff.client;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.CustomBoatEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.AlligatorGarRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientDrownedRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientTridentRenderer;
import net.ent.entstupidstuff.client.render.entity.ArmoredPillagerEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.ArmoredVindicatorEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.BassRenderer;
import net.ent.entstupidstuff.client.render.entity.ButterflyRenderer;
import net.ent.entstupidstuff.client.render.entity.CannonballEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.CoralSkeletonRenderer;
import net.ent.entstupidstuff.client.render.entity.FrostbittenZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.FungalSkeletonEntityRender;
import net.ent.entstupidstuff.client.render.entity.FungalZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.FurTroutRenderer;
import net.ent.entstupidstuff.client.render.entity.HoveringInfernoRenderer;
import net.ent.entstupidstuff.client.render.entity.KoiRenderer;
import net.ent.entstupidstuff.client.render.entity.LobberEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MackerelRenderer;
import net.ent.entstupidstuff.client.render.entity.MahiMahiRenderer;
import net.ent.entstupidstuff.client.render.entity.PerchFishRenderer;
import net.ent.entstupidstuff.client.render.entity.PhantomSkeletonEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.PiglinExtraRenderer;
import net.ent.entstupidstuff.client.render.entity.PrismerineArrowRenderer;
import net.ent.entstupidstuff.client.render.entity.RedPandaRenderer;
import net.ent.entstupidstuff.client.render.entity.RedStoneGolemRenderer;
import net.ent.entstupidstuff.client.render.entity.ScorchedEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SilkmothRenderer;
import net.ent.entstupidstuff.client.render.entity.MetalSkeletonEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MountaineerPillagerEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MountaineerVindicatorEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SkeletonPirateCaptainEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SlimedZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SnapperFishRenderer;
import net.ent.entstupidstuff.client.render.entity.SoulSkeletonEntityRender;
import net.ent.entstupidstuff.client.render.entity.SporeperRenderer;
import net.ent.entstupidstuff.client.render.entity.SunkenSkeletonEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.ZebraFishRenderer;
import net.ent.entstupidstuff.client.render.entity.model.AlligatorGarModel;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.client.render.entity.model.BassModel;
import net.ent.entstupidstuff.client.render.entity.model.ButterflyModel;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
import net.ent.entstupidstuff.client.render.entity.model.FurTroutModel;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;
import net.ent.entstupidstuff.client.render.entity.model.HoveringInfernoModel;
import net.ent.entstupidstuff.client.render.entity.model.PillagerHelmetModel;
import net.ent.entstupidstuff.client.render.entity.model.KoiModel;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.ent.entstupidstuff.client.render.entity.model.MackerelModel;
import net.ent.entstupidstuff.client.render.entity.model.MahiMahiModel;
import net.ent.entstupidstuff.client.render.entity.model.PerchFishModel;
import net.ent.entstupidstuff.client.render.entity.model.RedPandaModel;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.client.render.entity.model.SilkmothModel;
import net.ent.entstupidstuff.client.render.entity.model.MountaineerPillagerModel;
import net.ent.entstupidstuff.client.render.entity.model.MountaineerVindicatorModel;
import net.ent.entstupidstuff.client.render.entity.model.SkeletonPirateCaptainModel;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.client.render.entity.model.SnapperFishModel;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.ent.entstupidstuff.client.render.entity.model.SunkenSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.client.render.entity.model.skeleton.CoralSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.skeleton.MetalSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.skull.BoggedHeadModel;
import net.ent.entstupidstuff.client.render.entity.model.skull.CoralSkeletonSkullModel;
import net.ent.entstupidstuff.client.render.entity.model.skull.LayeredSkullModel;
import net.ent.entstupidstuff.client.render.entity.model.skull.SmallHeadModel;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {

    public static final ModelLayerLocation ZOMBIE_LOBBER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_lobber"), "main");

    public static final ModelLayerLocation ZOMBIE_LOBBER_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_lobber_baby"), "main");

    public static final ModelLayerLocation ZOMBIE_SCORCHED =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_scorched"), "main");

    public static final ModelLayerLocation ZOMBIE_SCORCHED_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_scorched_baby"), "main");

    public static final ModelLayerLocation ZOMBIE_DEEPCRAWLE =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_deepcrawle"), "main"); //Not Added

    public static final ModelLayerLocation ZOMBIE_ROTSPAWN =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_rotspawn"), "main"); //Not Added

    public static final ModelLayerLocation PILLAGER_ARMORED =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "pillager_armored"), "main");

    public static final ModelLayerLocation VINDICATOR_ARMORED =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "vindicator_armored"), "main");

    public static final ModelLayerLocation MOUNTAINEER_PILLAGER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mountaineer_pillager"), "main");

    public static final ModelLayerLocation MOUNTAINEER_VINDICATOR =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mountaineer_vindicator"), "main");

    public static final ModelLayerLocation RSGolem =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "redstone_golem"), "main");

    public static final ModelLayerLocation ZOMBIE_SLIMED =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed"), "main");

    public static final ModelLayerLocation ZOMBIE_SLIMED_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed_baby"), "main");

    public static final ModelLayerLocation ZOMBIE_SLIMED_OUTER  =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed"), "outer");

    public static final ModelLayerLocation ZOMBIE_SLIMED_OUTER_BABY  =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed_baby"), "outer");

    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten"), "main");

    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten_baby"), "main");

    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN_OUTER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten"), "outer");

    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN_OUTER_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten_baby"), "outer");

    public static final ModelLayerLocation ZOMBIE_FUNGAL =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal"), "main");

    public static final ModelLayerLocation ZOMBIE_FUNGAL_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal_baby"), "main");

    public static final ModelLayerLocation ZOMBIE_FUNGAL_OUTER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal"), "outer");

    public static final ModelLayerLocation ZOMBIE_FUNGAL_OUTER_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal_baby"), "outer");

    public static final ModelLayerLocation SPOREBONE =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporebone"), "main");

    public static final ModelLayerLocation SPOREBONE_OUTER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporebone"), "outer");

    public static final ModelLayerLocation SPOREPER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporeper"), "main");

    public static final ModelLayerLocation SILKMOTH =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "silkmoth"), "main");

    // Fires of the Hunt Update:

    public static final ModelLayerLocation PIGLIN_WARRIOR =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "piglin_warrior"), "main");

    public static final ModelLayerLocation HOVERING_INFERNO =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hovering_inferno"), "main");

    // Tale of the Seas Update:

    public static final ModelLayerLocation SUNKEN_SKELTON =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken_skeleton"), "main");

    public static final ModelLayerLocation CANNON_BALL =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "cannon_ball"), "main");

    public static final ModelLayerLocation SKELETON_PIRATE_CAPTAIN =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "skeleton_pirate_captain"), "main");

    public static final ModelLayerLocation SUNKEN_SKELETON_CROSSBOW =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken_skeleton_crossbow"), "main");

    public static final ModelLayerLocation METAL_SKELETON =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "metal_skeleton"), "main");

    public static final ModelLayerLocation PHANTOM_SKELETON =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "phantom_skeleton"), "main");

    public static final ModelLayerLocation ANCIENT_DROWNED =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_drowned"), "main");

    public static final ModelLayerLocation ANCIENT_DROWNED_BABY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_drowned_baby"), "main");

    public static final ModelLayerLocation ANCIENT_DROWNED_OUTER  =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_drowned"), "outer");

    public static final ModelLayerLocation ANCIENT_DROWNED_OUTER_BABY  =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_drowned_baby"), "outer");

    public static final ModelLayerLocation ANCIENT_TRIDENT = 
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ancient_trident"), "main");

    // Butterfly

    public static final ModelLayerLocation BUTTERFLY =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "butterfly"), "main");

    public static final ModelLayerLocation ZEBRA_FISH =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zebra_fish"), "main");

    public static final ModelLayerLocation ALLIGATOR_GAR =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "alligator_gar"), "main");

    public static final ModelLayerLocation MACKEREL =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mackerel"), "main");

    public static final ModelLayerLocation BASS =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "bass"), "main");

    public static final ModelLayerLocation KOI =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "koi"), "main");

    public static final ModelLayerLocation FURTROUT =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "furtrout"), "main");

    public static final ModelLayerLocation PERCH =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "perch"), "main");

    public static final ModelLayerLocation SNAPPER =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "snapper"), "main");

    public static final ModelLayerLocation MAHIMAHI =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mahimahi"), "main");

    public static final ModelLayerLocation RED_PANDA =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "red_panda"), "main");

    public static final ModelLayerLocation CORAL_SKELETON =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton"), "main");

    // Work in Progress

    public static final ModelLayerLocation CUSTOMBOAT =
    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "customboat"), "main");

    public static final ModelLayerLocation WOODEN_OAK_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_oak_shield"), "main");
    public static final ModelLayerLocation WOODEN_SPRUCE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_spruce_shield"), "main");
    public static final ModelLayerLocation WOODEN_BIRCH_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_birch_shield"), "main");
    public static final ModelLayerLocation WOODEN_JUNGLE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_jungle_shield"), "main");
    public static final ModelLayerLocation WOODEN_ACACIA_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_acacia_shield"), "main");
    public static final ModelLayerLocation WOODEN_DARK_OAK_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_dark_oak_shield"), "main");
    public static final ModelLayerLocation WOODEN_MANGROVE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_mangrove_shield"), "main");
    public static final ModelLayerLocation WOODEN_CHERRY_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_cherry_shield"), "main");
    public static final ModelLayerLocation WOODEN_BAMBOO_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wooden_bamboo_shield"), "main");

    public static final ModelLayerLocation STONE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stone_shield"), "main");
    public static final ModelLayerLocation STONE_DEEPSLATE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stone_deepslate_shield"), "main");
    public static final ModelLayerLocation STONE_BLACKSTONE_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stone_blackstone_shield"), "main");

    public static final ModelLayerLocation GOLDEN_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "golden_shield"), "main");
    
    public static final ModelLayerLocation DIAMOND_SHIELD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "diamond_shield"), "main");

    //Mob Heads

    public static final ModelLayerLocation DROWNED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "drowned_head"), "main");
    public static final ModelLayerLocation DROWNED_HEAD_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "drowned_head_outer"), "main");
    public static final ModelLayerLocation BLAZE_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "blaze_head"), "main");
    public static final ModelLayerLocation BREEZE_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "breeze_head"), "main");
    public static final ModelLayerLocation HUSK_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "husk_head"), "main");
    public static final ModelLayerLocation STRAY_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stray_skull"), "main");
    public static final ModelLayerLocation STRAY_SKULL_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stray_skull_outer"), "main");
    public static final ModelLayerLocation BOGGED_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "bogged_skull"), "main");
    public static final ModelLayerLocation BOGGED_SKULL_OTHER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "bogged_skull_outer"), "main");

    public static final ModelLayerLocation CORAL_SKELETON_BRAIN_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_brain_skull"), "main");
    public static final ModelLayerLocation CORAL_SKELETON_FIRE_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_fire_skull"), "main");
    public static final ModelLayerLocation CORAL_SKELETON_HORN_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_horn_skull"), "main");
    public static final ModelLayerLocation CORAL_SKELETON_TUBE_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_tube_skull"), "main");
    public static final ModelLayerLocation CORAL_SKELETON_BUBBLE_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_bubble_skull"), "main");
    public static final ModelLayerLocation CORAL_SKELETON_UNUSED_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "coral_skeleton_unused_skull"), "main");
    
    public static final ModelLayerLocation METAL_SKELETON_DEFAULT_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "metal_skeleton_default_skull"), "main");
    public static final ModelLayerLocation METAL_SKELETON_BLUE_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "metal_skeleton_red_skull"), "main");
    public static final ModelLayerLocation METAL_SKELETON_RED_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "metal_skeleton_blue_skull"), "main");

    public static final ModelLayerLocation ZOMBIE_LOBBER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_lobber_head"), "main");
    public static final ModelLayerLocation ZOMBIE_SCORCHED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_scorched_head"), "main");
    public static final ModelLayerLocation ZOMBIE_SLIMED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_slimed_head"), "main");
    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten_head"), "main");
    public static final ModelLayerLocation ZOMBIE_FROSTBITTEN_HEAD_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_frostbitten_head_outer"), "main");
    public static final ModelLayerLocation ZOMBIE_FUNGAL_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "zombie_fungal_head"), "main");

    public static final ModelLayerLocation SPOREBONE_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporebone_skull"), "main");
    public static final ModelLayerLocation SPOREBONE_SKULL_OUTER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporebone_skull_outer"), "main");
    public static final ModelLayerLocation SPOREPER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporeper_head"), "main");
    public static final ModelLayerLocation SOUL_SKELETON_SKULL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "soul_skeleton_skull"), "main");




    /*
        Golden Skeleton
        Sunken Skeleton/Coral Skeleton
        Plant/Seaweed Skeleton

        Gold Hoarder Skeleton

        Ashen Skeleton

        Ancient Skeleton

        Skeleton Captain

        Mini Bosses:
        Skeleton Lords

        Ent Added: Phantom Skeleton's


        Random Spawn:
        Skeleton Crews:
        - Skeleton Captain
        + Skeleton Type

        Sunken Skeleton/Coral Skeleton
        - Pirate and Normal


        metal_skeleton
        coral_skeleton
        seaweed_skeleton
        ashen_skeleton
        ancient_skeleton

        Skeleton:
        - double_barrel
        - flint_lock
        - crossbow
        - cutlass
        - cannon
    
        double barrel and flint_lock work under water?


        Extras:
        Dripstone Skeleton (Miner style)
        Bamboo Skeleton (Mossy Skeleton + Bamboo Piece)
    
    */




    public static void onInitialize() {
        
        EntityRenderers.register(EntityFactory.ZOMBIE_LOBBER, (EntityRendererProvider.Context context) -> new LobberEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_LOBBER, LobberModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_LOBBER_BABY, () -> LobberModel.getTexturedModelData().apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.ZOMBIE_SCORCHED, (EntityRendererProvider.Context context) -> new ScorchedEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SCORCHED, ScorchedModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SCORCHED_BABY, () -> ScorchedModel.getTexturedModelData().apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.ZOMBIE_SLIMED, (EntityRendererProvider.Context context) -> new SlimedZombieEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED, () -> SlimedZombieModel.getTexturedModelData(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER, () -> SlimedZombieModel.getTexturedModelData(new CubeDeformation(0.25F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_BABY, () -> SlimedZombieModel.getTexturedModelData(CubeDeformation.NONE).apply(HumanoidModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER_BABY, () -> SlimedZombieModel.getTexturedModelData(new CubeDeformation(0.25F)).apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.ANCIENT_DROWNED, (EntityRendererProvider.Context context) -> new AncientDrownedRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED, () -> AncientDrownedModel.getTexturedModelData(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER, () -> AncientDrownedModel.getTexturedModelData(new CubeDeformation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_BABY, () -> ScorchedModel.getTexturedModelData().apply(HumanoidModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER_BABY, () -> ScorchedModel.getTexturedModelData().apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.ZOMBIE_FROSTBITTEN, (EntityRendererProvider.Context context) -> new FrostbittenZombieEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN, () -> DrownedModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER, () -> DrownedModel.createBodyLayer(new CubeDeformation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_BABY, () -> DrownedModel.createBodyLayer(CubeDeformation.NONE).apply(HumanoidModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER_BABY, () -> DrownedModel.createBodyLayer(new CubeDeformation(0.5F)).apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.ZOMBIE_FUNGAL, (EntityRendererProvider.Context context) -> new FungalZombieEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FUNGAL, () -> DrownedModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER, () -> DrownedModel.createBodyLayer(new CubeDeformation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_BABY, () -> DrownedModel.createBodyLayer(CubeDeformation.NONE).apply(HumanoidModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER_BABY, () -> DrownedModel.createBodyLayer(new CubeDeformation(0.5F)).apply(HumanoidModel.BABY_TRANSFORMER));

        EntityRenderers.register(EntityFactory.SPORE_BONE, (EntityRendererProvider.Context context) -> new FungalSkeletonEntityRender(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SPOREBONE, () -> SkeletonModel.createBodyLayer());
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SPOREBONE_OUTER, () -> SkeletonModel.createBodyLayer().create(HumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F), 64, 32));
        //TexturedModelData.of(BipedEntityModel.getModelData(new Dilation(0.25F), 0.0F), 64, 32));
        
        EntityRenderers.register(EntityFactory.ARMORED_PILLAGER, (EntityRendererProvider.Context context) -> new ArmoredPillagerEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PILLAGER_ARMORED, () -> PillagerHelmetModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));

        EntityRenderers.register(EntityFactory.ARMORED_VINDICATOR, (EntityRendererProvider.Context context) -> new ArmoredVindicatorEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.VINDICATOR_ARMORED, () -> IllagerModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));

        EntityRenderers.register(EntityFactory.MOUNTAINEER_PILLAGER, (EntityRendererProvider.Context context) -> new MountaineerPillagerEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MOUNTAINEER_PILLAGER, () -> MountaineerPillagerModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));

        EntityRenderers.register(EntityFactory.MOUNTAINEER_VINDICATOR, (EntityRendererProvider.Context context) -> new MountaineerVindicatorEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MOUNTAINEER_VINDICATOR, () -> MountaineerVindicatorModel.createBodyLayer().apply(MeshTransformer.scaling(0.9375F)));




        EntityRenderers.register(EntityFactory.RSGolem, (EntityRendererProvider.Context context) -> new RedStoneGolemRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.RSGolem, RedStoneGolemModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.RED_PANDA, (EntityRendererProvider.Context context) -> new RedPandaRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.RED_PANDA, RedPandaModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.SPOREPER, (EntityRendererProvider.Context context) -> new SporeperRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SPOREPER, () ->  SporeperModel.getTexturedModelData(new CubeDeformation(0.5F)));

        EntityRenderers.register(EntityFactory.SILKMOTH, (EntityRendererProvider.Context context) -> new SilkmothRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SILKMOTH, SilkmothModel::createBodyLayer);

        //The Fire of the Hunt Update

        EntityRenderers.register(EntityFactory.PIGLIN_WARRIOR, (EntityRendererProvider.Context context) -> new PiglinExtraRenderer(context, ModelLayers.PIGLIN_BRUTE, ModelLayers.PIGLIN_BRUTE, ModelLayers.PIGLIN_BRUTE_ARMOR, ModelLayers.PIGLIN_BRUTE_ARMOR));

        EntityRenderers.register(EntityFactory.HOVERING_INFERNO, (EntityRendererProvider.Context context) -> new HoveringInfernoRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HOVERING_INFERNO, HoveringInfernoModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererProvider.Context context) -> new SunkenSkeletonEntityRenderer(context));
        //EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context, ModEntityModelLayers.SUNKEN_SKELTON /*EntityModelLayers.SKELETON*/, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SUNKEN_SKELTON, SunkenSkeletonModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.SOUL_SKELETON, (EntityRendererProvider.Context context) -> new SoulSkeletonEntityRender(context));
        EntityRenderers.register(EntityFactory.SOUL_SKELETON, (EntityRendererProvider.Context context) -> new SoulSkeletonEntityRender(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR));

        // Tale of the Seas Update:
        
        EntityRenderers.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererProvider.Context context) -> new SkeletonPirateCaptainEntityRenderer(context));
        //EntityRendererFactories.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererFactory.Context context) -> new SkeletonPirateCaptainEntityRenderer(context, ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, SkeletonPirateCaptainModel::getTexturedModelData);

        //EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context));
        EntityRenderers.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererProvider.Context context) -> new SunkenSkeletonEntityRenderer(context, ModEntityModelLayers.SUNKEN_SKELETON_CROSSBOW /*EntityModelLayers.SKELETON*/, ModelLayers.SKELETON_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SUNKEN_SKELETON_CROSSBOW, SunkenSkeletonModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.CANNON_BALL, (EntityRendererProvider.Context context) -> new CannonballEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CANNON_BALL, CannonballModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.UARROW, (EntityRendererProvider.Context context) -> new PrismerineArrowRenderer(context));

        EntityRenderers.register(EntityFactory.METAL_SKELETON, (EntityRendererProvider.Context context) -> new MetalSkeletonEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.METAL_SKELETON, MetalSkeletonModel::createBodyLayer);

        EntityRenderers.register(EntityFactory.PHANTOM_SKELETON, (EntityRendererProvider.Context context) -> new PhantomSkeletonEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PHANTOM_SKELETON, MetalSkeletonModel::createBodyLayer);
        
        EntityRenderers.register(EntityFactory.ALLIGATOR_GAR, (EntityRendererProvider.Context context) -> new AlligatorGarRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ALLIGATOR_GAR, AlligatorGarModel::getTexturedModelData);
        
        EntityRenderers.register(EntityFactory.ZEBRA_FISH, (EntityRendererProvider.Context context) -> new ZebraFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZEBRA_FISH, ZebraFishModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.MACKEREL, (EntityRendererProvider.Context context) -> new MackerelRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MACKEREL, MackerelModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.BASS, (EntityRendererProvider.Context context) -> new BassRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BASS, BassModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.KOI, (EntityRendererProvider.Context context) -> new KoiRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.KOI, KoiModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.FURTROUT, (EntityRendererProvider.Context context) -> new FurTroutRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.FURTROUT, FurTroutModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.PERCH, (EntityRendererProvider.Context context) -> new PerchFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PERCH, PerchFishModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.SNAPPER, (EntityRendererProvider.Context context) -> new SnapperFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SNAPPER, SnapperFishModel::getTexturedModelData);

        EntityRenderers.register(EntityFactory.MAHIMAHI, (EntityRendererProvider.Context context) -> new MahiMahiRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MAHIMAHI, MahiMahiModel::getTexturedModelData);

        // Work in Progress

        EntityRenderers.register(EntityFactory.CUSTOMBOAT, (EntityRendererProvider.Context context) -> new CustomBoatEntityRenderer(context, false));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CUSTOMBOAT, CustomBoatModel::getTexturedModelData);

        // Butterfly

        EntityRenderers.register(EntityFactory.BUTTERFLY, (EntityRendererProvider.Context context) -> new ButterflyRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BUTTERFLY, ButterflyModel::getTexturedModelData);

        //Coral_Skeleton
        EntityRenderers.register(EntityFactory.CORAL_SKELETON, (EntityRendererProvider.Context context) -> new CoralSkeletonRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CORAL_SKELETON, CoralSkeletonModel::createBodyLayer);



        // Shields

        EntityModelLayerRegistry.registerModelLayer(WOODEN_OAK_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_SPRUCE_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_BIRCH_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_JUNGLE_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_ACACIA_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_DARK_OAK_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_MANGROVE_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_CHERRY_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_BAMBOO_SHIELD, ShieldModel::createLayer);

        EntityModelLayerRegistry.registerModelLayer(STONE_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(STONE_DEEPSLATE_SHIELD, ShieldModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(STONE_BLACKSTONE_SHIELD, ShieldModel::createLayer);

        EntityModelLayerRegistry.registerModelLayer(GOLDEN_SHIELD, StrongShieldEntityModel::createLayer);

        EntityModelLayerRegistry.registerModelLayer(DIAMOND_SHIELD, StrongShieldEntityModel::createLayer);

        EntityRenderers.register(EntityFactory.ANCIENT_TRIDENT, (EntityRendererProvider.Context context) -> new AncientTridentRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_TRIDENT, AncientTridentModel::getTexturedModelData);

        // # Mob Heads

        EntityModelLayerRegistry.registerModelLayer(DROWNED_HEAD, LayeredSkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(DROWNED_HEAD_OUTER, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(BLAZE_HEAD, SmallHeadModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(BREEZE_HEAD, SmallHeadModel::createSmallMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(HUSK_HEAD, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(STRAY_SKULL, LayeredSkullModel::createMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(STRAY_SKULL_OUTER, SkullModel::createMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(BOGGED_SKULL, BoggedHeadModel::createMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(BOGGED_SKULL_OTHER, SkullModel::createMobHeadLayer);

        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_LOBBER_HEAD, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_SCORCHED_HEAD, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_SLIMED_HEAD, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_FROSTBITTEN_HEAD, LayeredSkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_FROSTBITTEN_HEAD_OUTER, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(ZOMBIE_FUNGAL_HEAD, SkullModel::createHumanoidHeadLayer);

        EntityModelLayerRegistry.registerModelLayer(METAL_SKELETON_DEFAULT_SKULL, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(METAL_SKELETON_BLUE_SKULL, SkullModel::createHumanoidHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(METAL_SKELETON_RED_SKULL, SkullModel::createHumanoidHeadLayer);

        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_BRAIN_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);
        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_FIRE_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);
        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_HORN_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);
        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_TUBE_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);
        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_BUBBLE_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);
        EntityModelLayerRegistry.registerModelLayer(CORAL_SKELETON_UNUSED_SKULL, CoralSkeletonSkullModel::createCoralSkeletonSkullLayer);

        EntityModelLayerRegistry.registerModelLayer(SPOREBONE_SKULL, LayeredSkullModel::createMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(SPOREBONE_SKULL_OUTER, SkullModel::createMobHeadLayer);
        EntityModelLayerRegistry.registerModelLayer(SPOREPER_HEAD, SkullModel::createHumanoidHeadLayer);//SporeperHeadModel
        EntityModelLayerRegistry.registerModelLayer(SOUL_SKELETON_SKULL, SkullModel::createMobHeadLayer);

;
        


        
    }

}
