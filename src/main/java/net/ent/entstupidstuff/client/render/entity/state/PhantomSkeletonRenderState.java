package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity.PhantomSkeletonVariant;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class PhantomSkeletonRenderState extends SkeletonRenderState{
    public PhantomSkeletonEntity.PhantomSkeletonVariant variant = PhantomSkeletonVariant.MELEE;
    
}
