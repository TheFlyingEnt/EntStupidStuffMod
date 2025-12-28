package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity.PhantomSkeletonVariant;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;

public class PhantomSkeletonRenderState extends SkeletonEntityRenderState{
    public PhantomSkeletonEntity.PhantomSkeletonVariant variant = PhantomSkeletonVariant.MELEE;
    
}
