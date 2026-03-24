package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class CarGlowRenderer extends EyesLayer<CarRenderState, DMCModel> {

    private static final RenderType SKIN = RenderType.eyes(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13_glow.png")
    );

    public CarGlowRenderer(RenderLayerParent<CarRenderState, DMCModel> context) {
        super(context);
    }

    /*public CarGlowRenderer(CarEntityRenderer carEntityRenderer) {
        //TODO Auto-generated constructor stub
    }*/

    @Override
    public RenderType renderType() {
        return SKIN;
    }
}
