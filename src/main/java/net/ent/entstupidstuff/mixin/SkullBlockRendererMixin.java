package net.ent.entstupidstuff.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ent.entstupidstuff.block.ModSkullStype;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.skull.CoralSkeletonSkullModel;
import net.ent.entstupidstuff.client.render.entity.model.skull.LayeredSkullModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {
    @Shadow
    @Final
    private static Map<SkullBlock.Type, ResourceLocation> SKIN_BY_TYPE;
    
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCustomSkullTextures(CallbackInfo ci) {
        // Map skull types to their TEXTURE locations

        SKIN_BY_TYPE.put(ModSkullStype.DROWNED, 
            ResourceLocation.withDefaultNamespace("textures/entity/zombie/drowned.png"));
        SKIN_BY_TYPE.put(ModSkullStype.BLAZE, 
            ResourceLocation.withDefaultNamespace("textures/entity/blaze.png"));
        SKIN_BY_TYPE.put(ModSkullStype.BREEZE, 
            ResourceLocation.withDefaultNamespace("textures/entity/breeze/breeze.png"));
        SKIN_BY_TYPE.put(ModSkullStype.HUSK, 
            ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk.png"));
        SKIN_BY_TYPE.put(ModSkullStype.STRAY, 
            ResourceLocation.withDefaultNamespace("textures/entity/skeleton/stray.png"));
        SKIN_BY_TYPE.put(ModSkullStype.BOGGED, 
            ResourceLocation.withDefaultNamespace("textures/entity/skeleton/bogged.png"));
        SKIN_BY_TYPE.put(ModSkullStype.BLAZE, 
            ResourceLocation.withDefaultNamespace("textures/entity/blaze.png"));
        SKIN_BY_TYPE.put(ModSkullStype.ZOMBIE_FUNGAL, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_fungal.png"));
        SKIN_BY_TYPE.put(ModSkullStype.ZOMBIE_LOBBER, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_lobber.png"));
        SKIN_BY_TYPE.put(ModSkullStype.ZOMBIE_SCORCHED, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_scorched.png"));
        SKIN_BY_TYPE.put(ModSkullStype.ZOMBIE_SLIMED, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_slimed.png"));
        SKIN_BY_TYPE.put(ModSkullStype.ZOMBIE_FROSTBITTEN, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_frostbitten.png"));
        SKIN_BY_TYPE.put(ModSkullStype.SPOREBONE, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/sporebone.png"));
        SKIN_BY_TYPE.put(ModSkullStype.SPOREPER, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/sporeper/sporeper.png"));
        SKIN_BY_TYPE.put(ModSkullStype.SOUL_SKELETON, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/soul_skeleton.png"));


        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_BRAIN, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_brain.png"));
        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_FIRE, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_fire.png"));
        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_HORN, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_horn.png"));
        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_TUBE, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_tube.png"));
        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_BUBBLE, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_bubble.png"));
        SKIN_BY_TYPE.put(ModSkullStype.CORAL_SKELETON_UNUSED, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/coral_skeleton_unused.png"));

        SKIN_BY_TYPE.put(ModSkullStype.METAL_SKELETON_DEFAULT, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/metal_skeleton_1.png"));
        SKIN_BY_TYPE.put(ModSkullStype.METAL_SKELETON_BLUE, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/metal_skeleton_2.png"));
        SKIN_BY_TYPE.put(ModSkullStype.METAL_SKELETON_RED, 
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/metal_skeleton_3.png"));


    }
    
    @Inject(method = "createModel", at = @At("HEAD"), cancellable = true)
    private static void createCustomSkullModel(EntityModelSet entityModelSet, SkullBlock.Type type, CallbackInfoReturnable<SkullModelBase> cir) {
        // Handle custom skull types

        switch (type) {
        case ModSkullStype.DROWNED:
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.DROWNED_HEAD),
                entityModelSet.bakeLayer(ModEntityModelLayers.DROWNED_HEAD_OUTER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/zombie/drowned_outer_layer.png")
            ));
            break;

        case ModSkullStype.BLAZE:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.BLAZE_HEAD)));
            break;

        case ModSkullStype.BREEZE:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.BREEZE_HEAD)));
            break;

        case ModSkullStype.HUSK:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.HUSK_HEAD)));
            break;

        case ModSkullStype.STRAY:
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.STRAY_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.STRAY_SKULL_OUTER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/stray_overlay.png")
            ));
            break;

        case ModSkullStype.BOGGED:
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.BOGGED_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.BOGGED_SKULL_OTHER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/bogged_overlay.png")
            ));
            break;

        case ModSkullStype.ZOMBIE_FUNGAL:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_HEAD)));
            break;

        case ModSkullStype.ZOMBIE_LOBBER:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_LOBBER_HEAD)));
            break;

        case ModSkullStype.ZOMBIE_SCORCHED:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_SCORCHED_HEAD)));
            break;

        case ModSkullStype.ZOMBIE_SLIMED:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED_HEAD)));
            break;

        case ModSkullStype.ZOMBIE_FROSTBITTEN:
            cir.setReturnValue(new LayeredSkullModel(
                entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_HEAD),
                entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_HEAD_OUTER),
                ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_frostbitten_outer_layer.png")
            ));
            break;

        case ModSkullStype.SPOREBONE:
            cir.setReturnValue(new LayeredSkullModel(
                entityModelSet.bakeLayer(ModEntityModelLayers.SPOREBONE_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.SPOREBONE_SKULL_OUTER),
                ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/sporebone_overlay.png")
            ));
            break;

        case ModSkullStype.SPOREPER:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.SPOREPER_HEAD)));
            break;

        case ModSkullStype.SOUL_SKELETON:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.SOUL_SKELETON_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_BRAIN: 
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_BRAIN_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_FIRE:
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_FIRE_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_HORN:
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_HORN_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_TUBE:
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_TUBE_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_BUBBLE:
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_BUBBLE_SKULL)));
            break;

        case ModSkullStype.CORAL_SKELETON_UNUSED:
            cir.setReturnValue(new CoralSkeletonSkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.CORAL_SKELETON_UNUSED_SKULL)));
            break;

        case ModSkullStype.METAL_SKELETON_DEFAULT:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.METAL_SKELETON_DEFAULT_SKULL)));
            break;

        case ModSkullStype.METAL_SKELETON_BLUE:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.METAL_SKELETON_BLUE_SKULL)));
            break;

        case ModSkullStype.METAL_SKELETON_RED:
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.METAL_SKELETON_RED_SKULL)));
            break;

        default:
            // code block
        }



        /*if (type == ModSkullStype.DROWNED) {
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.DROWNED_HEAD),
                entityModelSet.bakeLayer(ModEntityModelLayers.DROWNED_HEAD_OUTER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/zombie/drowned_outer_layer.png")
            ));

        } else if (type == ModSkullStype.BLAZE) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.BLAZE_HEAD)));

        } else if (type == ModSkullStype.BREEZE) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.BREEZE_HEAD)));

        } else if (type == ModSkullStype.HUSK) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.HUSK_HEAD)));

        } else if (type == ModSkullStype.STRAY) {
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.STRAY_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.STRAY_SKULL_OUTER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/stray_overlay.png")
            ));

        } else if (type == ModSkullStype.BOGGED) {
            cir.setReturnValue(new LayeredSkullModel (
                entityModelSet.bakeLayer(ModEntityModelLayers.BOGGED_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.BOGGED_SKULL_OTHER),
                ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/bogged_overlay.png")
            ));

        } else if (type == ModSkullStype.ZOMBIE_FUNGAL) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_HEAD)));

        } else if (type == ModSkullStype.ZOMBIE_LOBBER) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_LOBBER_HEAD)));

        } else if (type == ModSkullStype.ZOMBIE_SCORCHED) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_SCORCHED_HEAD)));

        } else if (type == ModSkullStype.ZOMBIE_SLIMED) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED_HEAD)));

        } else if (type == ModSkullStype.ZOMBIE_FROSTBITTEN) {
            cir.setReturnValue(new LayeredSkullModel(
                entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_HEAD),
                entityModelSet.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_HEAD_OUTER),
                ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/zombie/zombie_frostbitten_outer_layer.png")
            ));

        } else if (type == ModSkullStype.SPOREBONE) {
            cir.setReturnValue(new LayeredSkullModel(
                entityModelSet.bakeLayer(ModEntityModelLayers.SPOREBONE_SKULL),
                entityModelSet.bakeLayer(ModEntityModelLayers.SPOREBONE_SKULL_OUTER),
                ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/skeleton/sporebone_overlay.png")
            ));
            
        } else if (type == ModSkullStype.SPOREPER) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.SPOREPER_HEAD)));

        } else if (type == ModSkullStype.SOUL_SKELETON) {
            cir.setReturnValue(new SkullModel(entityModelSet.bakeLayer(ModEntityModelLayers.SOUL_SKELETON_SKULL)));

        }*/
    }
}
