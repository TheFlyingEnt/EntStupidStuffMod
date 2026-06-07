package net.ent.entstupidstuff.api.car.render;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;

public class BaseCarRenderState extends EntityRenderState {
 
    /** Whether the car is actively drifting this frame. */
    public boolean isDrifting = false;

    /** Whether the car is burning out (W + brake/SPACE at low speed). */
    public boolean isBurningOut = false;
 
    /** Accumulated front wheel rotation in degrees. */
    public float wheelSpin = 0f;

    /** Accumulated rear wheel rotation — spins independently during burnout. */
    public float rearWheelSpin = 0f;
 
    /** Steering input: -1 = full left, 0 = straight, +1 = full right. */
    public float steerInput = 0f;
 
    /** Forward speed (blocks/tick). Positive = forward, negative = reversing. */
    public float forwardSpeed = 0f;
 
    /** Interpolated yaw in degrees. */
    public float yRot = 0f;
    public float xRot = 0f;
 
    /** Lateral velocity — used for body roll. */
    public float lateralVelocity = 0f;
 
    // ── Per-entity interpolated animation values ──────────────────────────
    //
    // These MUST live in the render state, not in DMCModel.
    // DMCModel is a singleton — one instance shared across ALL car entities.
    // Storing lerp state on the model means car B overwrites car A's
    // steering-wheel rotation on the same frame, causing the shared-animation bug.
    //
    // By putting them here, each entity has its own copy updated every frame
    // in CarEntityRenderer.extractRenderState(), and DMCModel.setupAnim()
    // just reads them — no persistent state in the model at all.
 
    /** Smoothly interpolated steering wheel Y rotation (radians). */
    public float steerWheelRot = 0f;
 
    /** Smoothly interpolated gear shifter X rotation (radians). */
    public float shifterRot = 0f;
 
    /** Smoothly interpolated body roll Z rotation (radians). */
    public float bodyRoll = 0f;

    // ── Car customization state ──────────────────────────────────────
    /** Current wrap ID (e.g. "default", "gold", "fone_senna"). */
    public String wrapId = "default";
    public boolean isBreaking = false;
    /** Car type ID for texture path (e.g. "nissan_z", "f1_car"). */
    public String carTypeId = "car";
    /** License plate item — empty if no plate installed. */
    public net.minecraft.world.item.ItemStack licensePlate = net.minecraft.world.item.ItemStack.EMPTY;

    public Vec3 plateOffset = new Vec3(0, -10.65, 1.85);
    public int revLightState = 0;

    public String bodyKit = "stock";

    // ── Door & hood animation state ──────────────────────────────
    /** Whether each door/hood should be open (raw boolean from entity). */
    public boolean leftDoorOpen  = false;
    public boolean rightDoorOpen = false;
    public boolean hoodOpen      = false;
 
    /**
     * Smoothly interpolated door/hood angles (radians).
     * Lerped each frame in extractRenderState() so the door
     * swings open/closed instead of snapping.
     *
     * Door: 0 = closed, ~1.2 rad (~70°) = fully open.
     * Hood: 0 = closed, ~0.8 rad (~45°) = fully open.
     */
    public float leftDoorAngle  = 0f;
    public float rightDoorAngle = 0f;
    public float hoodAngle      = 0f;

    public float carLength = 4.0f;
    public float carWidth  = 1.8f;

 


}