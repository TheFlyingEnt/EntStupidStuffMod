package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;

public class SporeperGlowRender extends EyesLayer<CreeperRenderState, SporeperModel> {

    private static final RenderType SKIN = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_e.png"));

    public SporeperGlowRender(RenderLayerParent<CreeperRenderState, SporeperModel> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }

}
