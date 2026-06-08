package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.api.car.render.BaseCarRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class BaseCarEntityModel<S extends BaseCarRenderState> extends EntityModel<S> {
 
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

    /** BodyKits. */
    protected abstract ModelPart bodykits();

    /** Doors */
    protected abstract ModelPart leftDoor();
    protected abstract ModelPart rightDoor();
    protected abstract ModelPart hood();

    private boolean wheelHomeCaptured = false;
    private float flHomeX, frHomeX, rlHomeX, rrHomeX;
 
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
    public void setupAnim(S state) {

        if (!wheelHomeCaptured) {
            flHomeX = frontLeftWheel().x;
            frHomeX = frontRightWheel().x;
            rlHomeX = backLeftWheel().x;
            rrHomeX = backRightWheel().x;
            wheelHomeCaptured = true;
        }
 
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
        //body().zRot = state.bodyRoll;
        body().zRot = state.bodyRoll;
        body().xRot = 0f; // baseline pitch — tire-install tip adds onto this

        // ── 6. Body kits ────────────────────────────────────────────
        // Hide ALL kits first, then show only the active one.
        // This prevents multiple kits being visible simultaneously.

        if (this.bodykits() != null) { // Legacy Car Support
            ModelPart bodykits = this.bodykits();

            // Hide all kits
            for (ModelPart part : bodykits.getAllParts()) {
                if (part != bodykits) {
                    part.visible = false;
                }
            }

            // Show active kit
            if (state.bodyKit != null && (!state.bodyKit.equals("stock") || !state.bodyKit.equals("none")) ) {


                ModelPart activeKit = null;;
                try {
                    activeKit = bodykits.getChild(state.bodyKit);

                } catch (Exception e) {}

                if (activeKit != null) {

                    for (ModelPart part : activeKit.getAllParts()) {
                        if (part != bodykits) {
                            part.visible = true;
                        }
                    }

                    activeKit.visible = true;

                    // ----------------------------------------------------
                    // Bodykit animated parts
                    // ----------------------------------------------------

                    String kitName = state.bodyKit;

                    //ModelPart kitLeftDoor =
                    //    activeKit.getChild(kitName + "_left_door");

                    //ModelPart kitRightDoor =
                    //    activeKit.getChild(kitName + "_right_door");

                    //ModelPart kitHood =
                    //    activeKit.getChild(kitName + "_hood");

                    try {
                        if (activeKit.getChild(kitName + "_left_door") != null) {
                            activeKit.getChild(kitName + "_left_door").yRot = state.leftDoorAngle;
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (activeKit.getChild(kitName + "_right_door") != null) {
                            activeKit.getChild(kitName + "_right_door").yRot = state.rightDoorAngle;
                        }
                    } catch (Exception e) {
  
                    }

                    try {
                        if (activeKit.getChild(kitName + "_hood") != null) {
                            activeKit.getChild(kitName + "_hood").xRot = -state.hoodAngle;
                        }
                    } catch (Exception e) {
      
                    }

                }
            }
        }

        // ── Door animation ───────────────────────────────────────────
        // leftDoorAngle/rightDoorAngle are pre-lerped in the renderer's
        // extractRenderState() — smooth swing, no snapping.
        ModelPart leftDoor  = this.leftDoor();
        ModelPart rightDoor = this.rightDoor();
        ModelPart hood      = this.hood();
 
        if (leftDoor != null) {
            leftDoor.yRot = state.leftDoorAngle;
        }
        if (rightDoor != null) {
            rightDoor.yRot = state.rightDoorAngle;
        }
 
        // ── Hood animation ───────────────────────────────────────────
        // Opens upward (tilts toward windshield) when GUI is accessed.
        if (hood != null) {
            hood.xRot = -state.hoodAngle;
        }


        // ── Tire-install animation ────────────────────────────────────
        // Car tips up toward the corner being fitted (sine bump), the wheel
        // slides in from outboard, then the body settles back to level.
        frontLeftWheel().x  = flHomeX;
        frontRightWheel().x = frHomeX;
        backLeftWheel().x   = rlHomeX;
        backRightWheel().x  = rrHomeX;
 
        float MAX_TIP    = 0.05f; //0.13f;  // radians (~7.5°)
        float SLIDE_DIST = 14.0f;  // model units the wheel starts outboard
        float tipZ = 0f, tipX = 0f;
 
        for (int corner = 0; corner < 4; corner++) {
            float prog = state.tireAnimProgress[corner];
            if (prog < 0f) continue;                       // this wheel idle
 
            float tip = (float) Math.sin(prog * Math.PI) * MAX_TIP; // up then back
 
            // Signs that RAISE this corner (tip UP toward the wheel).
            float rollSign, pitchSign;
            switch (corner) {
                case 0 -> { rollSign = -1f; pitchSign = -1f; } // FL
                case 1 -> { rollSign = +1f; pitchSign = -1f; } // FR
                case 2 -> { rollSign = -1f; pitchSign = +1f; } // RL
                default -> { rollSign = +1f; pitchSign = +1f; } // RR
            }
            tipZ += rollSign  * tip;
            tipX += pitchSign * tip * 0.8f;
 
            // Wheel slides from OUTSIDE → home during 15%–60% of the anim.
            float wheelSlide = net.minecraft.util.Mth.clamp((prog - 0.15f) / 0.45f, 0f, 1f);
            float lateral = (1f - wheelSlide) * SLIDE_DIST;
 
            // "Outside" = same direction as the wheel's home offset from
            // center, so it always starts outboard regardless of axis sign.
            float homeX = switch (corner) {
                case 0 -> flHomeX; case 1 -> frHomeX;
                case 2 -> rlHomeX; default -> rrHomeX;
            };
            float outward = Math.signum(homeX);
            if (outward == 0f) outward = (corner == 0 || corner == 2) ? -1f : 1f;
            float x = homeX + outward * lateral;
 
            switch (corner) {
                case 0 -> frontLeftWheel().x  = x;
                case 1 -> frontRightWheel().x = x;
                case 2 -> backLeftWheel().x   = x;
                default -> backRightWheel().x = x;
            }
        }
 
        // Add the combined tip onto the body (zRot already = bodyRoll, xRot = 0).
        body().zRot += tipZ;
        body().xRot += tipX;






        /*if (this.bodykits() != null && state.bodyKit != "stock") {
            for (ModelPart kit : this.bodykits().getAllParts()) {

                if (this.bodykits().getChild(state.bodyKit) == kit ) {
                    try {
                        for (ModelPart kit2 : kit.getAllParts()) {
                            kit2.visible = true;
                        }
                    } catch (Exception ignored) {
                        // Kit name doesn't match any ModelPart — silently ignore
                    }

                } else kit.visible = false;
            }


        } else if (state.bodyKit == "stock" || state.bodyKit == "base"){
            this.bodykits().visible = false;
        }


        // Show the active kit (if any)
        /*if (state.bodyKit != null && !state.bodyKit.equals("stock")) {
            try {
                this.bodykits().getChild(state.bodyKit).visible = true;
            } catch (Exception ignored) {
                // Kit name doesn't match any ModelPart — silently ignore
            }
        }*/


    }
}