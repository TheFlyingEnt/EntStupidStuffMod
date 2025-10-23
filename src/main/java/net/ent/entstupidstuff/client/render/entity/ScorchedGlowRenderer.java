package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class ScorchedGlowRenderer extends EyesFeatureRenderer<ZombieEntityRenderState, ScorchedModel> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(
        Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched_e.png")
    );

    public ScorchedGlowRenderer(FeatureRendererContext<ZombieEntityRenderState, ScorchedModel> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
