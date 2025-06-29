package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.ent.entstupidstuff.entity.mob.RedStoneGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class RedStoneGolemRenderer extends MobEntityRenderer<RedStoneGolemEntity, RedStoneGolemModel<RedStoneGolemEntity>>{

    public RedStoneGolemRenderer(Context context) {
        super(context, new RedStoneGolemModel<>(context.getPart(ModModelLayers.RSGolem)), 1.1F);
    }

    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/redstone_golem.png");

    @Override
    public Identifier getTexture(RedStoneGolemEntity entity) {
        return TEXTURE;
    }




}
