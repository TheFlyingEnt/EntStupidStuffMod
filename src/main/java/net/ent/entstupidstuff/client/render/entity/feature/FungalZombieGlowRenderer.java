package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class FungalZombieGlowRenderer extends EyesLayer<ZombieRenderState, ZombieModel<ZombieRenderState>> {

    private static final RenderType SKIN = RenderType.eyes(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_fungal_glow.png")
    );

    public FungalZombieGlowRenderer(RenderLayerParent<ZombieRenderState, ZombieModel<ZombieRenderState>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
    
}
