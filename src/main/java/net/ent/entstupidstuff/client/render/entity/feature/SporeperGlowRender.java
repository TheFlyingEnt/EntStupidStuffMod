package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.util.Identifier;

public class SporeperGlowRender extends EyesFeatureRenderer<CreeperEntityRenderState, SporeperModel> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_e.png"));

    public SporeperGlowRender(FeatureRendererContext<CreeperEntityRenderState, SporeperModel> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }

}
