package net.ent.entstupidstuff.api.car;

import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class CarRenderState extends EntityRenderState {
    public boolean isDrifting = false;
    public float wheelSpin = 0f;
    public float steerInput = 0f;
    public float forwardSpeed = 0f;
 
    // # The car's yaw in degrees, interpolated between the previous and current tick values for smooth rendering
    public float yRot = 0f;
    public float xRot = 0f;
 
    // # Lateral velocity component — used for body-roll effect
    public float lateralVelocity = 0f;
}
