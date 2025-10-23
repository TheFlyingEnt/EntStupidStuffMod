package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class ButterflyRenderState extends LivingEntityRenderState {
    public ButterflyEntity.Variant variant = ButterflyEntity.Variant.YELLOW;
    public float bodyPitch;
	public boolean stoppedOnGround;

}
