package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.client.render.entity.feature.RedStoneGolemGlowEyeRenderer;
import net.ent.entstupidstuff.client.render.entity.feature.RedstoneGolemGlowRenderer;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModelNew;
import net.ent.entstupidstuff.client.render.entity.state.RedStoneGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedStoneGolemRenderer extends MobRenderer<RedStoneGolemEntity, RedStoneGolemRenderState, RedStoneGolemModelNew>{

    public RedStoneGolemRenderer(Context context) {
        super(context, new RedStoneGolemModelNew(context.bakeLayer(ModEntityModelLayers.REDSTONE_GOLEM)), 1.1F);
        this.addLayer(new RedStoneGolemGlowEyeRenderer(this));
        this.addLayer(new RedstoneGolemGlowRenderer(this));
    }

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/redstone_golem.png");

    @Override
    public ResourceLocation getTextureLocation(RedStoneGolemRenderState entity) {
        return TEXTURE;
    }

    @Override
    public RedStoneGolemRenderState createRenderState() {
        return new RedStoneGolemRenderState();
    }

    @Override
    public void extractRenderState(RedStoneGolemEntity entity, RedStoneGolemRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        
        // Extract attack state
        state.attackType = entity.getAttackType();
        state.attackTick = entity.getAttackTick();
        
        // Copy animation states - this is how Sniffer does it
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.walkAnimationState.copyFrom(entity.walkAnimationState);
        state.sweepAttackAnimationState.copyFrom(entity.sweepAttackAnimationState);
        state.normalAttackAnimationState.copyFrom(entity.normalAttackAnimationState);
    }

}
