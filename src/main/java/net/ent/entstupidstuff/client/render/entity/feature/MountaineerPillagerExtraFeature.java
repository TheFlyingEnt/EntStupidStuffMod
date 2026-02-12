package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.MountaineerPillagerEntity;
import net.ent.entstupidstuff.client.render.entity.model.illager.MountaineerPillagerModel;
import net.ent.entstupidstuff.client.render.entity.state.MountaineerPillagerRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MountaineerPillagerExtraFeature extends RenderLayer<MountaineerPillagerRenderState, IllagerModel<MountaineerPillagerRenderState>>{
    //private static final ResourceLocation HELMET_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/pillager_diamond_armored.png");

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_pillager.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_pillager.png");


    private final IllagerModel<MountaineerPillagerRenderState> model;

    public MountaineerPillagerExtraFeature(RenderLayerParent<MountaineerPillagerRenderState, IllagerModel<MountaineerPillagerRenderState>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new MountaineerPillagerModel<>(entityModelSet.bakeLayer(ModEntityModelLayers.MOUNTAINEER_PILLAGER));
    }



    public ResourceLocation getTextureLocation(MountaineerPillagerRenderState state) {
        return state.variant == MountaineerPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }
    

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, MountaineerPillagerRenderState renderState, float tickDelta, float bob) {
        // Sync the model state from parent
        this.model.setupAnim(renderState);
        

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
