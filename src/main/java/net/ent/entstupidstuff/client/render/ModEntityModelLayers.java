package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.AlligatorGarRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientDrownedRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientTridentRenderer;
import net.ent.entstupidstuff.client.render.entity.ArmoredPillagerEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.BassRenderer;
import net.ent.entstupidstuff.client.render.entity.ButterflyRenderer;
import net.ent.entstupidstuff.client.render.entity.CannonballEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.FrostbittenZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.FurTroutRenderer;
import net.ent.entstupidstuff.client.render.entity.HoveringInfernoRenderer;
import net.ent.entstupidstuff.client.render.entity.KoiRenderer;
import net.ent.entstupidstuff.client.render.entity.LobberEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MackerelRenderer;
import net.ent.entstupidstuff.client.render.entity.MahiMahiRenderer;
import net.ent.entstupidstuff.client.render.entity.PerchFishRenderer;
import net.ent.entstupidstuff.client.render.entity.PiglinExtraRenderer;
import net.ent.entstupidstuff.client.render.entity.PrismerineArrowRenderer;
import net.ent.entstupidstuff.client.render.entity.RedPandaRenderer;
import net.ent.entstupidstuff.client.render.entity.RedStoneGolemRenderer;
import net.ent.entstupidstuff.client.render.entity.ScorchedEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MetalSkeletonRenderer;
import net.ent.entstupidstuff.client.render.entity.SkeletonPirateCaptainEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SlimedZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SnapperFishRenderer;
import net.ent.entstupidstuff.client.render.entity.SoulSkeletonEntityRender;
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
import net.ent.entstupidstuff.client.render.entity.model.KoiModel;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.ent.entstupidstuff.client.render.entity.model.MackerelModel;
import net.ent.entstupidstuff.client.render.entity.model.MahiMahiModel;
import net.ent.entstupidstuff.client.render.entity.model.PerchFishModel;
import net.ent.entstupidstuff.client.render.entity.model.RedPandaModel;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.client.render.entity.model.MetalSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.SkeletonPirateCaptainModel;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.client.render.entity.model.SnapperFishModel;
import net.ent.entstupidstuff.client.render.entity.model.SunkenSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.util.Identifier;

public class ModEntityModelLayers {

    public static final EntityModelLayer ZOMBIE_LOBBER =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_lobber"), "main");

    public static final EntityModelLayer ZOMBIE_LOBBER_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_lobber_baby"), "main");

    public static final EntityModelLayer ZOMBIE_SCORCHED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_scorched"), "main");

    public static final EntityModelLayer ZOMBIE_SCORCHED_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_scorched_baby"), "main");

    public static final EntityModelLayer ZOMBIE_DEEPCRAWLE =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_deepcrawle"), "main"); //Not Added

    public static final EntityModelLayer ZOMBIE_ROTSPAWN =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_rotspawn"), "main"); //Not Added

    public static final EntityModelLayer PILLAGER_ARMORED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "pillager_armored"), "main");

    public static final EntityModelLayer RSGolem =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "redstone_golem"), "main");

    public static final EntityModelLayer ZOMBIE_SLIMED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_slimed"), "main");

    public static final EntityModelLayer ZOMBIE_SLIMED_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_slimed_baby"), "main");

    public static final EntityModelLayer ZOMBIE_SLIMED_OUTER  =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_slimed"), "outer");

    public static final EntityModelLayer ZOMBIE_SLIMED_OUTER_BABY  =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_slimed_baby"), "outer");

    public static final EntityModelLayer ZOMBIE_FROSTBITTEN =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten"), "main");

    public static final EntityModelLayer ZOMBIE_FROSTBITTEN_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten_baby"), "main");

    public static final EntityModelLayer ZOMBIE_FROSTBITTEN_OUTER =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten"), "outer");

    public static final EntityModelLayer ZOMBIE_FROSTBITTEN_OUTER_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten_baby"), "outer");

    // Fires of the Hunt Update:

    public static final EntityModelLayer PIGLIN_WARRIOR =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "piglin_warrior"), "main");

    public static final EntityModelLayer HOVERING_INFERNO =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "hovering_inferno"), "main");

    // Tale of the Seas Update:

    public static final EntityModelLayer SUNKEN_SKELTON =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton"), "main");

    public static final EntityModelLayer CANNON_BALL =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "cannon_ball"), "main");

    public static final EntityModelLayer SKELETON_PIRATE_CAPTAIN =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "skeleton_pirate_captain"), "main");

    public static final EntityModelLayer SUNKEN_SKELETON_CROSSBOW =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton_crossbow"), "main");

    public static final EntityModelLayer METAL_SKELETON =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "metal_skeleton"), "main");

    public static final EntityModelLayer PHANTOM_SKELETON =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "phantom_skeleton"), "main");

    public static final EntityModelLayer ANCIENT_DROWNED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned"), "main");

    public static final EntityModelLayer ANCIENT_DROWNED_BABY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned_baby"), "main");

    public static final EntityModelLayer ANCIENT_DROWNED_OUTER  =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned"), "outer");

    public static final EntityModelLayer ANCIENT_DROWNED_OUTER_BABY  =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned_baby"), "outer");

    public static final EntityModelLayer ANCIENT_TRIDENT = 
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_trident"), "main");

    // Butterfly

    public static final EntityModelLayer BUTTERFLY =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "butterfly"), "main");

    public static final EntityModelLayer ZEBRA_FISH =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zebra_fish"), "main");

    public static final EntityModelLayer ALLIGATOR_GAR =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "alligator_gar"), "main");

    public static final EntityModelLayer MACKEREL =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "mackerel"), "main");

    public static final EntityModelLayer BASS =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "bass"), "main");

    public static final EntityModelLayer KOI =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "koi"), "main");

    public static final EntityModelLayer FURTROUT =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "furtrout"), "main");

    public static final EntityModelLayer PERCH =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "perch"), "main");

    public static final EntityModelLayer SNAPPER =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "snapper"), "main");

    public static final EntityModelLayer MAHIMAHI =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "mahimahi"), "main");

    public static final EntityModelLayer RED_PANDA =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "red_panda"), "main");

    // Work in Progress

    public static final EntityModelLayer CUSTOMBOAT =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "customboat"), "main");

    public static final EntityModelLayer WOODEN_OAK_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_oak_shield"), "main");
    public static final EntityModelLayer WOODEN_SPRUCE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_spruce_shield"), "main");
    public static final EntityModelLayer WOODEN_BIRCH_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_birch_shield"), "main");
    public static final EntityModelLayer WOODEN_JUNGLE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_jungle_shield"), "main");
    public static final EntityModelLayer WOODEN_ACACIA_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_acacia_shield"), "main");
    public static final EntityModelLayer WOODEN_DARK_OAK_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_dark_oak_shield"), "main");
    public static final EntityModelLayer WOODEN_MANGROVE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_mangrove_shield"), "main");
    public static final EntityModelLayer WOODEN_CHERRY_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_cherry_shield"), "main");
    public static final EntityModelLayer WOODEN_BAMBOO_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "wooden_bamboo_shield"), "main");

    public static final EntityModelLayer STONE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "stone_shield"), "main");
    public static final EntityModelLayer STONE_DEEPSLATE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "stone_deepslate_shield"), "main");
    public static final EntityModelLayer STONE_BLACKSTONE_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "stone_blackstone_shield"), "main");

    public static final EntityModelLayer GOLDEN_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "golden_shield"), "main");
    
    public static final EntityModelLayer DIAMOND_SHIELD = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "diamond_shield"), "main");


    public static void onInitialize() {
        
        EntityRendererFactories.register(EntityFactory.ZOMBIE_LOBBER, (EntityRendererFactory.Context context) -> new LobberEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_LOBBER, LobberModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_LOBBER_BABY, () -> LobberModel.getTexturedModelData().transform(BipedEntityModel.BABY_TRANSFORMER));

        EntityRendererFactories.register(EntityFactory.ZOMBIE_SCORCHED, (EntityRendererFactory.Context context) -> new ScorchedEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SCORCHED, ScorchedModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SCORCHED_BABY, () -> ScorchedModel.getTexturedModelData().transform(BipedEntityModel.BABY_TRANSFORMER));

        EntityRendererFactories.register(EntityFactory.ZOMBIE_SLIMED, (EntityRendererFactory.Context context) -> new SlimedZombieEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED, () -> SlimedZombieModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER, () -> SlimedZombieModel.getTexturedModelData(new Dilation(0.25F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_BABY, () -> SlimedZombieModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER_BABY, () -> SlimedZombieModel.getTexturedModelData(new Dilation(0.25F)));

        EntityRendererFactories.register(EntityFactory.ANCIENT_DROWNED, (EntityRendererFactory.Context context) -> new AncientDrownedRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED, () -> AncientDrownedModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER, () -> AncientDrownedModel.getTexturedModelData(new Dilation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_BABY, () -> ScorchedModel.getTexturedModelData().transform(BipedEntityModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER_BABY, () -> ScorchedModel.getTexturedModelData().transform(BipedEntityModel.BABY_TRANSFORMER));

        EntityRendererFactories.register(EntityFactory.ZOMBIE_FROSTBITTEN, (EntityRendererFactory.Context context) -> new FrostbittenZombieEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN, () -> DrownedEntityModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER, () -> DrownedEntityModel.getTexturedModelData(new Dilation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_BABY, () -> DrownedEntityModel.getTexturedModelData(Dilation.NONE).transform(BipedEntityModel.BABY_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER_BABY, () -> DrownedEntityModel.getTexturedModelData(new Dilation(0.5F)).transform(BipedEntityModel.BABY_TRANSFORMER));
        

        EntityRendererFactories.register(EntityFactory.ARMORED_PILLAGER, (EntityRendererFactory.Context context) -> new ArmoredPillagerEntityRenderer(context));

        EntityRendererFactories.register(EntityFactory.RSGolem, (EntityRendererFactory.Context context) -> new RedStoneGolemRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.RSGolem, RedStoneGolemModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.RED_PANDA, (EntityRendererFactory.Context context) -> new RedPandaRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.RED_PANDA, RedPandaModel::getTexturedModelData);

        //The Fire of the Hunt Update

        EntityRendererFactories.register(EntityFactory.PIGLIN_WARRIOR, (EntityRendererFactory.Context context) -> new PiglinExtraRenderer(context, EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT, EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT));

        EntityRendererFactories.register(EntityFactory.HOVERING_INFERNO, (EntityRendererFactory.Context context) -> new HoveringInfernoRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.HOVERING_INFERNO, HoveringInfernoModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context));
        //EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context, ModEntityModelLayers.SUNKEN_SKELTON /*EntityModelLayers.SKELETON*/, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SUNKEN_SKELTON, SunkenSkeletonModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.SOUL_SKELETON, (EntityRendererFactory.Context context) -> new SoulSkeletonEntityRender(context));
        EntityRendererFactories.register(EntityFactory.SOUL_SKELETON, (EntityRendererFactory.Context context) -> new SoulSkeletonEntityRender(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_EQUIPMENT));

        // Tale of the Seas Update:
        
        EntityRendererFactories.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererFactory.Context context) -> new SkeletonPirateCaptainEntityRenderer(context));
        //EntityRendererFactories.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererFactory.Context context) -> new SkeletonPirateCaptainEntityRenderer(context, ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, SkeletonPirateCaptainModel::getTexturedModelData);

        //EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context));
        EntityRendererFactories.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context, ModEntityModelLayers.SUNKEN_SKELETON_CROSSBOW /*EntityModelLayers.SKELETON*/, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SUNKEN_SKELETON_CROSSBOW, SunkenSkeletonModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.CANNON_BALL, (EntityRendererFactory.Context context) -> new CannonballEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CANNON_BALL, CannonballModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.UARROW, (EntityRendererFactory.Context context) -> new PrismerineArrowRenderer(context));

        //EntityRendererFactories.register(EntityFactory.METAL_SKELETON, (EntityRendererFactory.Context context) -> new MetalSkeletonRenderer(context));
        EntityRendererFactories.register(EntityFactory.METAL_SKELETON, (EntityRendererFactory.Context context) -> new MetalSkeletonRenderer(context, ModEntityModelLayers.METAL_SKELETON, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.METAL_SKELETON, MetalSkeletonModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.PHANTOM_SKELETON, (EntityRendererFactory.Context context) -> new PhantomSkeletonEntityRenderer(context));
        //EntityRendererFactories.register(EntityFactory.PHANTOM_SKELETON, (EntityRendererFactory.Context context) -> new PhantomSkeletonEntityRenderer(context, ModEntityModelLayers.PHANTOM_SKELETON, EntityModelLayers.SKELETON_EQUIPMENT));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PHANTOM_SKELETON, MetalSkeletonModel::getTexturedModelData);
        
        EntityRendererFactories.register(EntityFactory.ALLIGATOR_GAR, (EntityRendererFactory.Context context) -> new AlligatorGarRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ALLIGATOR_GAR, AlligatorGarModel::getTexturedModelData);
        
        EntityRendererFactories.register(EntityFactory.ZEBRA_FISH, (EntityRendererFactory.Context context) -> new ZebraFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ZEBRA_FISH, ZebraFishModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.MACKEREL, (EntityRendererFactory.Context context) -> new MackerelRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MACKEREL, MackerelModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.BASS, (EntityRendererFactory.Context context) -> new BassRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BASS, BassModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.KOI, (EntityRendererFactory.Context context) -> new KoiRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.KOI, KoiModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.FURTROUT, (EntityRendererFactory.Context context) -> new FurTroutRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.FURTROUT, FurTroutModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.PERCH, (EntityRendererFactory.Context context) -> new PerchFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PERCH, PerchFishModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.SNAPPER, (EntityRendererFactory.Context context) -> new SnapperFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SNAPPER, SnapperFishModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.MAHIMAHI, (EntityRendererFactory.Context context) -> new MahiMahiRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MAHIMAHI, MahiMahiModel::getTexturedModelData);

        // Work in Progress

        EntityRendererFactories.register(EntityFactory.CUSTOMBOAT, (EntityRendererFactory.Context context) -> new CustomBoatEntityRenderer(context, false));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CUSTOMBOAT, CustomBoatModel::getTexturedModelData);

        // Butterfly

        EntityRendererFactories.register(EntityFactory.BUTTERFLY, (EntityRendererFactory.Context context) -> new ButterflyRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BUTTERFLY, ButterflyModel::getTexturedModelData);

        // Shields

        EntityModelLayerRegistry.registerModelLayer(WOODEN_OAK_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_SPRUCE_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_BIRCH_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_JUNGLE_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_ACACIA_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_DARK_OAK_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_MANGROVE_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_CHERRY_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WOODEN_BAMBOO_SHIELD, ShieldEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(STONE_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(STONE_DEEPSLATE_SHIELD, ShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(STONE_BLACKSTONE_SHIELD, ShieldEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(GOLDEN_SHIELD, StrongShieldEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(DIAMOND_SHIELD, StrongShieldEntityModel::getTexturedModelData);

        EntityRendererFactories.register(EntityFactory.ANCIENT_TRIDENT, (EntityRendererFactory.Context context) -> new AncientTridentRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ANCIENT_TRIDENT, AncientTridentModel::getTexturedModelData);
        
    }

}
