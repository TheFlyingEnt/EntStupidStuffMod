package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.renderer.entity.state.BoatRenderState;

/**
 * Per-ship render state — carries ALL ship-specific visual data.
 *
 * This fixes the "sail level changes for all ships" bug: previously,
 * ship data was stored on the shared Model instance. If multiple ships
 * rendered in the same frame, the last one's data won. Now each ship
 * gets its own state object populated in extractRenderState().
 */
public class CustomBoatRenderState extends BoatRenderState {
    public int   sailLevel      = 3;
    public float forwardSpeed   = 0f;
    public float sinkProgress   = 0f;
    public float waveTime       = 0f;
    public float rudderTurn     = 0f;
    public boolean anchorDeployed = false;
    public float boatSpeed      = 0f;
    public boolean hasBanner    = false;

    // ── Attachment rendering ──
    /** 0 = none, 1 = harpoon, 2 = cannon */
    public int   attachmentType = 0;
    /** Bow gunner's yaw RELATIVE to ship yaw (radians). Drives the attachment swivel. */
    public float bowRelativeYaw = 0f;
    /** Whether the harpoon/cannon has ammo loaded (for the loaded model part) */
    public boolean hasAmmo      = false;
    /** Whether a harpoon is currently deployed (fired and tethered) */
    public boolean harpoonDeployed = false;

    /** Bow gunner's pitch (elevation), in radians. Negative = aiming up. */
    public float bowPitch = 0f;

    public boolean bowOccupied = false;
    public float sinkRoll  = 0f;

    // ── Wind (for the burgee flag) ──
    /** Global wind blow-toward direction relative to the ship's heading (radians). */
    public float windRelativeYaw = 0f;
    /** Wind strength 0..1 (drives flutter intensity). */
    public float windStrength    = 0.8f;
    public float sailFill        = 1f;

}
