package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class RedStoneGolemRenderer extends MobEntityRenderer<RedStoneGolemEntity, LivingEntityRenderState, RedStoneGolemModel>{

    public RedStoneGolemRenderer(Context context) {
        super(context, new RedStoneGolemModel(context.getPart(ModEntityModelLayers.RSGolem)), 1.1F);
    }

    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/redstone_golem.png");

    @Override
    public Identifier getTexture(LivingEntityRenderState entity) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }




}
