package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class SoulSkeletonGlowRender extends EyesLayer<SkeletonRenderState, SkeletonModel<SkeletonRenderState>> {

    private static final RenderType SKIN = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/soul_skeleton_e.png"));

    public SoulSkeletonGlowRender(RenderLayerParent<SkeletonRenderState, SkeletonModel<SkeletonRenderState>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }

}
