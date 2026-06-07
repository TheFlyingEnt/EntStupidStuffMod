package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * 1989 Nissan Silvia K's (S13) — 1.8L CA18DET turbo I4, ~169 hp, RWD.
 *
 * The drift icon. Light (≈1180 kg), perfectly balanced, and just powerful
 * enough to break the rear loose when the turbo spools — the S13 is the car
 * that taught a generation to slide. It is NOT fast in a straight line; its
 * magic is how willing it is to rotate. Lift off mid-corner or stab the
 * throttle as boost hits and the tail steps out, then it holds a long,
 * controllable slide thanks to its low weight and neutral balance.
 *
 * Character vs the garage:
 *   vs GT500:  A tenth of the power, a third of the drama-from-brute-force,
 *              but FAR more willing and controllable when sliding. The GT500
 *              spins you; the Silvia drifts WITH you.
 *   vs GR86:   Similar philosophy (light, balanced, RWD), but turbocharged —
 *              power arrives in a surge instead of a linear NA build.
 *   vs Type R: Opposite layout — the Silvia oversteers where the Type R
 *              understeers. RWD vs FWD in a nutshell.
 *
 * Turbo lag is real: below ~3200 RPM it's sleepy, then boost slams in
 * around 3800–4500 and the rear lights up. Drive it on boost.
 *
 * Shift points (internal): 1st 47 | 2nd 82 | 3rd 119 | 4th 155 | 5th 205 km/h
 * (displayed, realisticSpeed ON — × 2.35 scale)
 */
public class NissanSilviaS13Entity extends BaseCarEntity {

    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (CA18DET — 1.8L turbo I4, ~169 hp @ 6400, 166 lb-ft @ 4000)
    //  Turbo curve: lazy off-boost, hard surge once spooled, falls past peak.
    // ═══════════════════════════════════════════════════════════

    @Override protected float idleRpm()        {  return  800f; }
    @Override protected float redlineRpm()      {  return 7500f; }
    @Override protected float maxReverseRpm()   {  return 2000f; }
    @Override protected float maxReverseSpeed() {  return 0.30f; }
    @Override protected float downshiftRpm()    {  return 2200f; }
    @Override protected int   clutchTicks()     {  return 4; }
    @Override protected int   maxGear()         {  return 5; }

    // Turbo torque: weak until ~3200, surge to peak at 4500, hold, then taper.
    // This is what makes it "come alive on boost" and break traction suddenly.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 800, 1500, 2500, 3200, 3800, 4500, 5500, 6400, 7000, 7500 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .30f, .42f, .55f, .72f, .92f, 1.0f, .98f, .90f, .76f, .58f };
    }

    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (5-speed manual + 4.083:1 final drive)
    //
    //  Real S13 CA18DET ratios. tyreCirc calibrated so 5th redlines at
    //  1.210 bl/tick internal → 205 km/h displayed (× 2.35 scale).
    // ═══════════════════════════════════════════════════════════

    @Override protected float[] gearRatios()    { return new float[]{ 0f, 3.321f, 1.902f, 1.308f, 1.000f, 0.759f }; }
    @Override protected float   finalDrive()    { return 4.083f; }
    @Override protected float   tyreCirc()      { return 0.60f; }
    @Override protected float   peakDriveForce(){ return 0.45f; }  // modest power — it's 169 hp

    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (near 50/50 — the secret to its drift balance)
    // ═══════════════════════════════════════════════════════════

    @Override protected float  frontBias()  { return 0.53f; }
    @Override protected float  rearBias()   { return 0.47f; }
    @Override protected float  frontDist()  { return 1.35f; }
    @Override protected float  rearDist()   { return 1.55f; }
    @Override protected double trackHalf()  { return 0.84; }

    // ═══════════════════════════════════════════════════════════
    //  GRIP  (period 195-section street/drift tyres)
    //  Moderate grip — low enough that turbo surge breaks the rear easily.
    // ═══════════════════════════════════════════════════════════

    @Override protected float frontGripMax()   { return 0.120f; }
    @Override protected float rearGripMax()    { return 0.135f; }
    @Override protected float gripStiffness()  { return 0.80f; }
    @Override protected float slipThreshold()  { return 0.09f; }
    @Override protected float slipFalloff()    { return 0.55f; }

    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (tail-happy and forgiving — built to drift)
    // ═══════════════════════════════════════════════════════════

    @Override protected float  latDecay()         { return 0.82f; } // light car, bleeds slip fairly quick
    @Override protected float  longTransfer()     { return 0.32f; }
    @Override protected float  latTransfer()      { return 0.11f; }
    @Override protected float  maxSteerDeg()      { return 4.5f; }  // quick rack
    @Override protected double peakSteerSpeed()   { return 0.30; }
    @Override protected double highSteerFraction(){ return 0.55; }
    @Override protected float  driftSteerBoost()  { return 1.9f; }  // generous counter-steer
    @Override protected float  handbrakeRearGrip(){ return 0.03f; } // e-brake locks cleanly for drift
    @Override protected float  yawMomentScale()   { return 340f; }  // eager to rotate
    @Override protected float  yawDamping()       { return 0.85f; }
    @Override protected float  yawMax()           { return 6.0f; }
    @Override protected float  driftThreshold()   { return 0.030f; } // slides at a low threshold
    @Override protected float  throttleRampOn()   { return 0.10f; }
    @Override protected float  throttleRampOff()  { return 0.16f; }

    // ═══════════════════════════════════════════════════════════
    //  DRAG  (light, slippery-ish for its era, low power → drag-limited)
    // ═══════════════════════════════════════════════════════════

    @Override protected double rollingDrag()   { return 0.983; }
    @Override protected double aeroDragStart() { return 0.80;  }
    @Override protected double aeroDragK()     { return 0.0048; }

    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE + SCALING
    // ═══════════════════════════════════════════════════════════

    @Override protected boolean defaultIsRWD()      { return true; }
    @Override public    boolean isTurbo()           { return true; }  // turbo spool sound layer
    @Override protected float realisticSpeedScale() { return 2.35f; } // 87 km/h internal → 205 displayed
    @Override protected float surfacePenaltyScale() { return 1.3f; }  // period performance street tyres
    @Override protected float crashResistance()     { return 0.13f; } // light 80s steel — folds easily

    // Per-car steering: nimble, responsive even at speed
    @Override protected float steerSpeedReference() { return 0.70f; }
    @Override protected float steerMinSensitivity() { return 0.50f; }

    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (turbo four — raspy I4 + spool/flutter)
    //
    //  Uses the turboFour preset. Swap these SoundFactory constants for
    //  CA18DET-specific oggs once you have them (idle / accel / brake /
    //  top-gear / tyre squeal). Placeholder = Viper events so it compiles.
    // ═══════════════════════════════════════════════════════════

    @Override
    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.turboFour(
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE,     // TODO: silvia idle
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1,   // TODO: silvia accel
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK,    // TODO: silvia decel
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP, // TODO: silvia top gear
            SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════

    public NissanSilviaS13Entity(EntityType<?> type, Level level) {
        super(type, level);
    }

    // ═══════════════════════════════════════════════════════════
    //  MODEL / VISUAL  (tune to your Blockbench model)
    // ═══════════════════════════════════════════════════════════

    @Override public String getCarTypeId() { return "silvias13"; }

    // TODO: add a CarWrapHelper.visableSilvia() when wraps are made.
    @Override public String[] availableWraps()    { return new String[]{ "default" }; }
    @Override public String[] availableBodyKits() { return new String[]{ }; }

    @Override public Vec3 licensePlateOffset() { return new Vec3(0, 0.40, 2.85); }

    @Override protected double seatHeight()  { return 0.32; }
    @Override protected double seatSide()    { return 0.55; }
    @Override protected double seatForward() { return 0.05; }

    @Override protected float carMass()      { return 0.58f; } // light
    @Override public    float carLength()    { return 4.2f; }
    @Override public    float carWidth()     { return 1.8f; }
    @Override public    float cameraWeight() { return 0.50f; } // light & nimble — low camera lag
}
