package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

public class FungalSkeletonGlowRender extends EyesFeatureRenderer<SkeletonEntityRenderState, SkeletonEntityModel<SkeletonEntityRenderState>> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/fungal_skeleton_e.png"));

    public FungalSkeletonGlowRender(FeatureRendererContext<SkeletonEntityRenderState, SkeletonEntityModel<SkeletonEntityRenderState>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }

}
