package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class RedStoneGolemRenderer extends MobRenderer<RedStoneGolemEntity, LivingEntityRenderState, RedStoneGolemModel>{

    public RedStoneGolemRenderer(Context context) {
        super(context, new RedStoneGolemModel(context.bakeLayer(ModEntityModelLayers.RSGolem)), 1.1F);
    }

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/redstone_golem.png");

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState entity) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }




}
