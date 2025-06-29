package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.HoveringInfernoModel;
import net.ent.entstupidstuff.entity.mob.HoveringInfernoEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class HoveringInfernoRenderer extends MobEntityRenderer<HoveringInfernoEntity, HoveringInfernoModel<HoveringInfernoEntity>>{

    public HoveringInfernoRenderer(Context context) {
        super(context, new HoveringInfernoModel<>(context.getPart(ModModelLayers.HOVERING_INFERNO)), 1.1F);
    }

    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/hovering_inferno.png");

    @Override
    public Identifier getTexture(HoveringInfernoEntity entity) {
        return TEXTURE;
    }



    
}
