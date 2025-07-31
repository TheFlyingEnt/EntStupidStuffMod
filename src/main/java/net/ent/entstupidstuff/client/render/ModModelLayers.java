package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.AlligatorGarRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientDrownedRenderer;
import net.ent.entstupidstuff.client.render.entity.AncientTridentRenderer;
import net.ent.entstupidstuff.client.render.entity.ArmoredPillagerEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.ButterflyRenderer;
import net.ent.entstupidstuff.client.render.entity.CannonballEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.FrostbittenZombieEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.HoveringInfernoRenderer;
import net.ent.entstupidstuff.client.render.entity.LobberEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.MackerelRenderer;
import net.ent.entstupidstuff.client.render.entity.PiglinExtraRenderer;
import net.ent.entstupidstuff.client.render.entity.PrismerineArrowRenderer;
import net.ent.entstupidstuff.client.render.entity.RedStoneGolemRenderer;
import net.ent.entstupidstuff.client.render.entity.ScorchedEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SkeletonGoldRenderer;
import net.ent.entstupidstuff.client.render.entity.SkeletonPirateCaptainEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.SoulSkeletonEntityRender;
import net.ent.entstupidstuff.client.render.entity.SunkenSkeletonEntityRenderer;
import net.ent.entstupidstuff.client.render.entity.ZebraFishRenderer;
import net.ent.entstupidstuff.client.render.entity.model.AlligatorGarModel;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.client.render.entity.model.ButterflyModel;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;
import net.ent.entstupidstuff.client.render.entity.model.HoveringInfernoModel;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.ent.entstupidstuff.client.render.entity.model.MackerelModel;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.client.render.entity.model.SkeletonGoldModel;
import net.ent.entstupidstuff.client.render.entity.model.SkeletonPirateCaptainModel;
import net.ent.entstupidstuff.client.render.entity.model.SunkenSkeletonModel;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.util.Identifier;

public class ModModelLayers {

    public static final EntityModelLayer LOBBER_ZOMBIE =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "lobber_zombie"), "main");

    public static final EntityModelLayer ZOMBIE_SCORCHED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_scorched"), "main");

    public static final EntityModelLayer ZOMBIE_DEEPCRAWLE =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_deepcrawle"), "main");

    public static final EntityModelLayer ZOMBIE_ROTSPAWN =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_rotspawn"), "main");

    public static final EntityModelLayer PILLAGER_ARMORED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "pillager_armored"), "main");

    public static final EntityModelLayer RSGolem =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "redstone_golem"), "main");

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

    public static final EntityModelLayer ZOMBIE_FROSTBITTEN =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "zombie_frostbitten"), "main");

    public static final EntityModelLayer ANCIENT_DROWNED =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned"), "main");

    public static final EntityModelLayer ANCIENT_DROWNED_OUTER  =
    new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "ancient_drowned"), "outer");

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
        
        EntityRendererRegistry.register(EntityFactory.LOBBER_ZOMBIE, (EntityRendererFactory.Context context) -> new LobberEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.LOBBER_ZOMBIE, (EntityRendererFactory.Context context) -> new LobberEntityRenderer(context, ModModelLayers.LOBBER_ZOMBIE, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LOBBER_ZOMBIE, LobberModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.ZOMBIE_SCORCHED, (EntityRendererFactory.Context context) -> new ScorchedEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.ZOMBIE_SCORCHED, (EntityRendererFactory.Context context) -> new ScorchedEntityRenderer(context, ModModelLayers.ZOMBIE_SCORCHED, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ZOMBIE_SCORCHED, ScorchedModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.ANCIENT_DROWNED, (EntityRendererFactory.Context context) -> new AncientDrownedRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANCIENT_DROWNED, () -> AncientDrownedModel.getTexturedModelData(Dilation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANCIENT_DROWNED_OUTER, () -> AncientDrownedModel.getTexturedModelData(new Dilation(0.5F)));

        EntityRendererRegistry.register(EntityFactory.ZOMBIE_FROSTBITTEN, (EntityRendererFactory.Context context) -> new FrostbittenZombieEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.ZOMBIE_FROSTBITTEN, (EntityRendererFactory.Context context) -> new FrostbittenZombieEntityRenderer(context, ModModelLayers.ZOMBIE_FROSTBITTEN, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ZOMBIE_FROSTBITTEN, () -> DrownedEntityModel.getTexturedModelData(Dilation.NONE));

        EntityRendererRegistry.register(EntityFactory.ARMORED_PILLAGER, (EntityRendererFactory.Context context) -> new ArmoredPillagerEntityRenderer(context));

        EntityRendererRegistry.register(EntityFactory.RSGolem, (EntityRendererFactory.Context context) -> new RedStoneGolemRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RSGolem, RedStoneGolemModel::getTexturedModelData);

        //The Fire of the Hunt Update

        EntityRendererRegistry.register(EntityFactory.PIGLIN_WARRIOR, (EntityRendererFactory.Context context) -> new PiglinExtraRenderer(context, EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE_INNER_ARMOR, EntityModelLayers.PIGLIN_BRUTE_OUTER_ARMOR, false));
        EntityRendererRegistry.register(EntityFactory.PIGLIN_FUNGAL, (EntityRendererFactory.Context context) -> new PiglinExtraRenderer(context, EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE_INNER_ARMOR, EntityModelLayers.PIGLIN_BRUTE_OUTER_ARMOR, false));

        EntityRendererRegistry.register(EntityFactory.HOVERING_INFERNO, (EntityRendererFactory.Context context) -> new HoveringInfernoRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.HOVERING_INFERNO, HoveringInfernoModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.SUNKEN_SKELETON, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context, ModModelLayers.SUNKEN_SKELTON /*EntityModelLayers.SKELETON*/, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SUNKEN_SKELTON, SunkenSkeletonModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.SOUL_SKELETON, (EntityRendererFactory.Context context) -> new SoulSkeletonEntityRender(context));
        EntityRendererRegistry.register(EntityFactory.SOUL_SKELETON, (EntityRendererFactory.Context context) -> new SoulSkeletonEntityRender(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));

        // Tale of the Seas Update:
        
        EntityRendererRegistry.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererFactory.Context context) -> new SkeletonPirateCaptainEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.SKELETON_PIRATE_CAPTAIN, (EntityRendererFactory.Context context) -> new SkeletonPirateCaptainEntityRenderer(context, ModModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SKELETON_PIRATE_CAPTAIN, SkeletonPirateCaptainModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.SUNKEN_SKELETON_CROSSBOW, (EntityRendererFactory.Context context) -> new SunkenSkeletonEntityRenderer(context, ModModelLayers.SUNKEN_SKELETON_CROSSBOW /*EntityModelLayers.SKELETON*/, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SUNKEN_SKELETON_CROSSBOW, SunkenSkeletonModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.CANNON_BALL, (EntityRendererFactory.Context context) -> new CannonballEntityRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CANNON_BALL, CannonballModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.UARROW, (EntityRendererFactory.Context context) -> new PrismerineArrowRenderer(context));

        EntityRendererRegistry.register(EntityFactory.METAL_SKELETON, (EntityRendererFactory.Context context) -> new SkeletonGoldRenderer(context));
        EntityRendererRegistry.register(EntityFactory.METAL_SKELETON, (EntityRendererFactory.Context context) -> new SkeletonGoldRenderer(context, ModModelLayers.METAL_SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.METAL_SKELETON, SkeletonGoldModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.PHANTOM_SKELETON, (EntityRendererFactory.Context context) -> new PhantomSkeletonEntityRenderer(context));
        EntityRendererRegistry.register(EntityFactory.PHANTOM_SKELETON, (EntityRendererFactory.Context context) -> new PhantomSkeletonEntityRenderer(context, ModModelLayers.PHANTOM_SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PHANTOM_SKELETON, SkeletonGoldModel::getTexturedModelData);
        
        EntityRendererRegistry.register(EntityFactory.ALLIGATOR_GAR, (EntityRendererFactory.Context context) -> new AlligatorGarRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ALLIGATOR_GAR, AlligatorGarModel::getTexturedModelData);
        
        EntityRendererRegistry.register(EntityFactory.ZEBRA_FISH, (EntityRendererFactory.Context context) -> new ZebraFishRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ZEBRA_FISH, ZebraFishModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityFactory.MACKEREL, (EntityRendererFactory.Context context) -> new MackerelRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MACKEREL, MackerelModel::getTexturedModelData);

        // Work in Progress

        EntityRendererRegistry.register(EntityFactory.CUSTOMBOAT, (EntityRendererFactory.Context context) -> new CustomBoatEntityRenderer(context, false));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOMBOAT, CustomBoatModel::getTexturedModelData);

        // Butterfly

        EntityRendererRegistry.register(EntityFactory.BUTTERFLY, (EntityRendererFactory.Context context) -> new ButterflyRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BUTTERFLY, ButterflyModel::getTexturedModelData);

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

        EntityRendererRegistry.register(EntityFactory.ANCIENT_TRIDENT, (EntityRendererFactory.Context context) -> new AncientTridentRenderer(context));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ANCIENT_TRIDENT, AncientTridentModel::getTexturedModelData);
        
    }

}
