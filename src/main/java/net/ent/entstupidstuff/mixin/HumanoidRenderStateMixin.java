package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.ent.entstupidstuff.client.render.entity.state.HeldHorizontalBannerRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

@Mixin(HumanoidRenderState.class)
public class HumanoidRenderStateMixin implements HeldHorizontalBannerRenderState {

    @Unique private boolean holdingBannerMainHand = false;
    @Unique private boolean holdingBannerOffHand = false;

    @Override public boolean entstupidstuff$isHoldingBannerMainHand() { return holdingBannerMainHand; }
    @Override public boolean entstupidstuff$isHoldingBannerOffHand()  { return holdingBannerOffHand; }
    @Override public void entstupidstuff$setHoldingBannerMainHand(boolean value) { holdingBannerMainHand = value; }
    @Override public void entstupidstuff$setHoldingBannerOffHand(boolean value)  { holdingBannerOffHand = value; }
}
