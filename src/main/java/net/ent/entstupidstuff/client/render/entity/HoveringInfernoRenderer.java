package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.HoveringInfernoEntity;
import net.ent.entstupidstuff.client.render.entity.model.HoveringInfernoModel;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class HoveringInfernoRenderer extends MobEntityRenderer<HoveringInfernoEntity, LivingEntityRenderState, HoveringInfernoModel> {
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/hovering_inferno.png");

    public HoveringInfernoRenderer(Context context) {
        super(context, new HoveringInfernoModel(context.getPart(ModEntityModelLayers.HOVERING_INFERNO)), 1.1F);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }



    
}
