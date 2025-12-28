package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class FungalZombieGlowRenderer extends EyesFeatureRenderer<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(
        Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_fungal_e.png")
    );

    public FungalZombieGlowRenderer(FeatureRendererContext<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
    
}
