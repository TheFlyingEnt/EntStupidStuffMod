package net.ent.entstupidstuff.client.render.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class RedStoneGolemRenderState extends LivingEntityRenderState {
    public int attackType = 0;
    public int attackTick = 0;
    
    // Animation states
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState sweepAttackAnimationState = new AnimationState();
    public final AnimationState normalAttackAnimationState = new AnimationState();
}

