package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModelNew;
import net.ent.entstupidstuff.client.render.entity.state.RedStoneGolemRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class RedStoneGolemGlowEyeRenderer extends EyesLayer<RedStoneGolemRenderState, RedStoneGolemModelNew> {

    private static final RenderType SKIN = RenderType.eyes(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/redstone_golem_glow_eyes.png")
    );

    public RedStoneGolemGlowEyeRenderer(RenderLayerParent<RedStoneGolemRenderState, RedStoneGolemModelNew> context) {
        super(context);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
}
