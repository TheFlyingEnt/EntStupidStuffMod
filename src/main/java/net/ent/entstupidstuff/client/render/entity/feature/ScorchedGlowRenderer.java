package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class ScorchedGlowRenderer extends EyesLayer<ZombieRenderState, ScorchedModel> {

    private static final RenderType SKIN = RenderType.eyes(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched_e.png")
    );

    public ScorchedGlowRenderer(RenderLayerParent<ZombieRenderState, ScorchedModel> context) {
        super(context);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
}
