package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.api.car.render.CarRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class BaseCarEntityModel extends EntityModel<CarRenderState> {
 
    /** Maximum physical steering angle of the front wheels (radians). */
    private static final float MAX_WHEEL_STEER_RAD = 0.4f;
 
    // ═══════════════════════════════════════════════════════════
    //  ABSTRACT PART ACCESSORS
    //  Each subclass returns its own ModelPart fields.
    //  Returning null from optional parts (steering wheel, shifter)
    //  is allowed — setupAnim() skips null parts gracefully.
    // ═══════════════════════════════════════════════════════════
 
    /** The root body part. Body roll is applied here. */
    protected abstract ModelPart body();
 
    protected abstract ModelPart frontLeftWheel();
    protected abstract ModelPart frontRightWheel();
    protected abstract ModelPart backLeftWheel();
    protected abstract ModelPart backRightWheel();
 
    /** Interior steering wheel. Return null if model has none. */
    protected abstract ModelPart steeringWheel();
 
    /** Gear shifter. Return null if model has none. */
    protected abstract ModelPart shifter();
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public BaseCarEntityModel(ModelPart root) {
        super(root);
    }
 
    // ═══════════════════════════════════════════════════════════
    //  ANIMATION  (shared by all car models)
    // ═══════════════════════════════════════════════════════════
 
    @Override
    public void setupAnim(CarRenderState state) {
 
        // ── 1. Wheel spin ─────────────────────────────────────────────────
        // Front wheels track actual forward speed direction.
        float frontSpinRad = (float) Math.toRadians(
            state.forwardSpeed > 0.01f ? state.wheelSpin : -state.wheelSpin);
        frontLeftWheel().xRot  = frontSpinRad;
        frontRightWheel().xRot = frontSpinRad;
 
        // Rear wheels: normalise to 0–360 before converting so Minecraft
        // never wraps xRot through ±180° (would make asymmetric rims flip).
        // Forward/burnout → positive spin; reversing → negative.
        float rearSpinNorm = state.rearWheelSpin % 360f;
        float rearSpinRad  = (float) Math.toRadians(
            state.forwardSpeed > 0.01f ? rearSpinNorm : -rearSpinNorm);
        backLeftWheel().xRot  = rearSpinRad;
        backRightWheel().xRot = rearSpinRad;
 
        // ── 2. Front wheel steering ───────────────────────────────────────
        float wheelSteer = state.steerInput * MAX_WHEEL_STEER_RAD;
        frontLeftWheel().yRot  = wheelSteer;
        frontRightWheel().yRot = wheelSteer;
 
        // ── 3. Steering wheel ─────────────────────────────────────────────
        // Pre-lerped per-entity in CarEntityRenderer.extractRenderState()
        if (steeringWheel() != null) {
            steeringWheel().zRot = state.steerWheelRot;
        }
 
        // ── 4. Gear shifter ───────────────────────────────────────────────
        // Pre-lerped per-entity in CarEntityRenderer.extractRenderState()
        if (shifter() != null) {
            shifter().xRot = state.shifterRot;
        }
 
        // ── 5. Body roll ──────────────────────────────────────────────────
        // Pre-lerped + drift oscillation in CarEntityRenderer.extractRenderState()
        //body().yRot = state.bodyRoll;
        body().zRot = state.bodyRoll;
    }
}