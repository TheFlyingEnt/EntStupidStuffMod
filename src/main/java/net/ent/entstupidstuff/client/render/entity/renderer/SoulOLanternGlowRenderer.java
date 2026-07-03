package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SoulJackOLanternModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class SoulOLanternGlowRenderer extends EyesLayer<ZombieRenderState, SoulJackOLanternModel> {

    private static final RenderType SKIN = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/texture_glow.png"));

    public SoulOLanternGlowRenderer(RenderLayerParent<ZombieRenderState, SoulJackOLanternModel> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }

}
