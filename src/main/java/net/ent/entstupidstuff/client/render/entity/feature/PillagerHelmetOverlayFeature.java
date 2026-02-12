package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.client.render.entity.state.ArmoredPillagerRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class PillagerHelmetOverlayFeature extends RenderLayer<ArmoredPillagerRenderState, IllagerModel<ArmoredPillagerRenderState>>{
    //private static final ResourceLocation HELMET_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/pillager_diamond_armored.png");

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/pillager_diamond_armored.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/pillager_gold_armored.png");


    //private final PillagerHelmetModel model;

    public PillagerHelmetOverlayFeature(RenderLayerParent<ArmoredPillagerRenderState, IllagerModel<ArmoredPillagerRenderState>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        //this.model = new PillagerHelmetModel(entityModelSet.bakeLayer(ModEntityModelLayers.PILLAGER_ARMORED_OUTER));
    }



    public ResourceLocation getTextureLocation(ArmoredPillagerRenderState state) {
        return state.variant == ArmoredPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }
    

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, ArmoredPillagerRenderState renderState, float tickDelta, float bob) {
        // Sync the model state from parent
        /*this.model.setupAnim(renderState);
        

        if (renderState.isCaptain) {
            coloredCutoutModelCopyLayerRender(
                this.model,
                getTextureLocation(renderState),
                poseStack,
                submitNodeCollector,
                packedLight,
                renderState,
                -1,  // white color (no tint)
                0    // render order
            );
        }

        // Render the helmet overlay using the helper method
        /*coloredCutoutModelCopyLayerRender(
            this.model,
            getTextureLocation(renderState),
            poseStack,
            submitNodeCollector,
            packedLight,
            renderState,
            -1,  // white color (no tint)
            0    // render order
        );*/
    }
    
}
