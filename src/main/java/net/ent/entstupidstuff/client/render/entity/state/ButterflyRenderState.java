package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ButterflyRenderState extends LivingEntityRenderState {
    public ButterflyEntity.Variant variant = ButterflyEntity.Variant.YELLOW;
    public float bodyPitch;
	public boolean stoppedOnGround;

}
