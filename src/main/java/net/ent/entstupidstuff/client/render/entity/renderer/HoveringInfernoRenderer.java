package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.HoveringInfernoEntity;
import net.ent.entstupidstuff.client.render.entity.model.HoveringInfernoModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class HoveringInfernoRenderer extends MobRenderer<HoveringInfernoEntity, LivingEntityRenderState, HoveringInfernoModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/hovering_inferno.png");

    public HoveringInfernoRenderer(Context context) {
        super(context, new HoveringInfernoModel(context.bakeLayer(ModEntityModelLayers.HOVERING_INFERNO)), 1.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }



    
}
