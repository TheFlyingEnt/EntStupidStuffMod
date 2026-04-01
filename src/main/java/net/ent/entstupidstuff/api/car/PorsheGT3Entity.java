package net.ent.entstupidstuff.api.car;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
 
/**
 * 2023 Porsche 911 GT3 — 4.0L Naturally Aspirated Flat-6, 502 hp, RWD.
 *
 * The precision instrument. Rear-heavy (41/59 weight distribution), extremely
 * high grip Michelin Pilot Sport Cup 2 tyres, and a 9000 RPM screaming flat-6.
 * Corners very flat with almost no body roll. Demands respect — push too hard
 * and it snaps suddenly with little warning due to the high gripStiffness and
 * low slipFalloff. The yawMomentScale is the highest in the garage.
 *
 * Character vs other cars:
 *   vs GR86:     Twice the grip, twice the power. Much harder to drift —
 *                tyres hang on much longer then let go all at once.
 *   vs Viper:    Similar power but lighter, rear-biased, more precise.
 *                Drifts require proper technique, not just throttle.
 *   vs GT500:    More refined, less brutal. Snaps rather than slides.
 *
 * Shift points: 1st 29 | 2nd 45 | 3rd 61 | 4th 78 | 5th 97 | 6th 118 km/h
 */

public class PorsheGT3Entity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (4.0L Mezger flat-6, 502 hp @ 8400 RPM)
    //  High-revving NA — torque keeps climbing all the way to 7000 RPM.
    //  Falls sharply above that as valvetrain breathing limits kick in.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  900f; }
    @Override protected float redlineRpm()      { return 9000f; }
    @Override protected float maxReverseRpm()   { return 2000f; }
    @Override protected float maxReverseSpeed() { return 0.30f; }
    @Override protected float downshiftRpm()    { return 2500f; }
    @Override protected int   clutchTicks()     { return 2; }  // PDK-style fast shifts
    @Override protected int   maxGear()         { return 6; }
 
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 900, 2000, 3000, 4000, 5000, 6000, 7000, 7800, 8500, 9000 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .28f, .55f, .72f, .85f, .94f, 1.0f, 1.0f, .96f, .82f, .55f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (7-speed PDK + 3.579:1 final drive)
    //
    //  TYRE_CIRC calibrated: redline in 6th = 1.639 bl/tick (118 km/h)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 3.909f, 2.529f, 1.857f, 1.448f, 1.172f, 0.960f }; }
    @Override protected float   finalDrive()    { return 3.579f; }
    @Override protected float   tyreCirc()      { return 0.7508f; }
    @Override protected float   peakDriveForce(){ return 0.82f; }  // 502 hp, high but controlled
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (rear-heavy 911 layout, engine over rear axle)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.41f; } // 911 is rear-heavy by design
    @Override protected float  rearBias()   { return 0.59f; }
    @Override protected float  frontDist()  { return 1.25f; }
    @Override protected float  rearDist()   { return 1.80f; }
    @Override protected double trackHalf()  { return 0.88; }
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (265/35ZR20 Michelin Pilot Sport Cup 2 — semi-slick)
    //  Highest grip in the garage. Hangs on then snaps suddenly.
    //  Low slipFalloff = cliff-edge grip loss, not progressive.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.175f; }
    @Override protected float rearGripMax()    { return 0.205f; }
    @Override protected float gripStiffness()  { return 0.90f; }  // very responsive
    @Override protected float slipThreshold()  { return 0.07f; }  // grips hard before sliding
    @Override protected float slipFalloff()    { return 0.55f; }  // drops off suddenly — snap oversteer
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (race-derived, extremely precise)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.85f; } // slides bleed off quickly
    @Override protected float  longTransfer()     { return 0.32f; }
    @Override protected float  latTransfer()      { return 0.11f; }
    @Override protected float  maxSteerDeg()      { return 3.8f; }  // precise, not twitchy
    @Override protected double peakSteerSpeed()   { return 0.38; }
    @Override protected double highSteerFraction(){ return 0.50; }
    @Override protected float  driftSteerBoost()  { return 1.6f; }  // less counter-steer needed
    @Override protected float  handbrakeRearGrip(){ return 0.04f; }
    @Override protected float  yawMomentScale()   { return 320f; }  // highest — rear-heavy snaps hard
    @Override protected float  yawDamping()       { return 0.78f; } // less damping — rotation builds fast
    @Override protected float  yawMax()           { return 6.0f; }
    @Override protected float  driftThreshold()   { return 0.035f; } // harder to break traction
    @Override protected float  throttleRampOn()   { return 0.09f; }
    @Override protected float  throttleRampOff()  { return 0.20f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (aggressive GT aero — wing creates downforce but also drag)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.979; }
    @Override protected double aeroDragStart() { return 0.88;  } // aero loads up early
    @Override protected double aeroDragK()     { return 0.0038; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return true; }
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public PorsheGT3Entity(EntityType<?> type, Level level) {
        super(type, level);
    }
}
