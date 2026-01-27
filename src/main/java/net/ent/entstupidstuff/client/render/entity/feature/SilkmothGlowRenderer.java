package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SilkmothModel;
import net.ent.entstupidstuff.client.render.entity.state.SilkmothRenderstate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SilkmothGlowRenderer extends EyesLayer<SilkmothRenderstate, SilkmothModel> {
    private static final RenderType SKIN = RenderType.eyes(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/silkmoth/silkmoth_mushroom_e.png")
    );

    public SilkmothGlowRenderer(RenderLayerParent<SilkmothRenderstate, SilkmothModel> context) {
        super(context);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
    
}
