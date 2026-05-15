package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * 2023 Toyota GR86 — 2.4L Naturally Aspirated Flat-4, 228 hp, RWD.
 *
 * The benchmark test car. Lightweight (1,272 kg), balanced 53/47 weight
 * distribution, and a progressive torque curve that peaks smoothly at
 * 4500 RPM. Drifts are easy to initiate and forgiving to catch — if the
 * physics system feels right in this car it is tuned well.
 *
 * Character vs other cars in the garage:
 *   vs Viper:    Much less power, shorter gear bands, but more nimble.
 *                Drifts are gradual rather than violent.
 *   vs GT3:      Less grip, easier to break traction, more playful.
 *   vs GT500:    Less than a third of the power, much lighter, far more
 *                predictable — a learner's drift car.
 *
 * Shift points: 1st 20 | 2nd 33 | 3rd 47 | 4th 60 | 5th 73 | 6th 95 km/h
 */

public class GR86Entity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (2.4L FA24 Boxer-4, 228 hp @ 7000 RPM)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  700f; }
    @Override protected float redlineRpm()      { return 7500f; }
    @Override protected float maxReverseRpm()   { return 1800f; }
    @Override protected float maxReverseSpeed() { return 0.28f; }
    @Override protected float downshiftRpm()    { return 2000f; }
    @Override protected int   clutchTicks()     { return 3; }
    @Override protected int   maxGear()         { return 6; }
 
    // NA curve — smooth climb to 4500 RPM peak, gentle falloff.
    // No turbo plateau — power builds and falls naturally.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 700, 1500, 2200, 3000, 3800, 4500, 5200, 6000, 6800, 7200, 7500 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .32f, .55f, .75f, .90f, .97f, 1.0f, .99f, .95f, .82f, .65f, .45f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (6-speed manual + 4.10:1 final drive)
    //
    //  TYRE_CIRC calibrated: redline in 6th = 1.319 bl/tick (95 km/h)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 3.626f, 2.188f, 1.541f, 1.213f, 1.000f, 0.767f }; }
    @Override protected float   finalDrive()    { return 4.100f; }
    @Override protected float   tyreCirc()      { return 0.6639f; }
    @Override protected float   peakDriveForce(){ return 0.48f; }  // 228 hp — modest
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (compact sports car, 53/47 balance)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.53f; }
    @Override protected float  rearBias()   { return 0.47f; }
    @Override protected float  frontDist()  { return 1.30f; }
    @Override protected float  rearDist()   { return 1.85f; }
    @Override protected double trackHalf()  { return 0.80; }
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (215/40R18 Michelin Pilot Sport 4 — stock)
    //  Lower than the GT3 and Viper — easier to break traction.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.120f; }
    @Override protected float rearGripMax()    { return 0.145f; }
    @Override protected float gripStiffness()  { return 0.85f; }
    @Override protected float slipThreshold()  { return 0.10f; }
    @Override protected float slipFalloff()    { return 0.62f; } // forgiving — progressive slide
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (sport-tuned but friendly)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.87f; }
    @Override protected float  longTransfer()     { return 0.28f; }
    @Override protected float  latTransfer()      { return 0.09f; }
    @Override protected float  maxSteerDeg()      { return 4.4f; }  // direct rack
    @Override protected double peakSteerSpeed()   { return 0.33; }
    @Override protected double highSteerFraction(){ return 0.57; }
    @Override protected float  driftSteerBoost()  { return 1.8f; }  // generous counter-steer window
    @Override protected float  handbrakeRearGrip(){ return 0.04f; }
    @Override protected float  yawMomentScale()   { return 240f; }  // moderate rotation
    @Override protected float  yawDamping()       { return 0.82f; }
    @Override protected float  yawMax()           { return 5.0f; }
    @Override protected float  driftThreshold()   { return 0.028f; } // breaks traction easily
    @Override protected float  throttleRampOn()   { return 0.11f; }
    @Override protected float  throttleRampOff()  { return 0.22f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (lightweight, good aero for its class)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.980; }
    @Override protected double aeroDragStart() { return 0.95;  }
    @Override protected double aeroDragK()     { return 0.0035; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return true; }
    @Override protected float realisticSpeedScale() { return 2.404f; }
    @Override protected float surfacePenaltyScale() { return 1.2f; }  // performance summer tyres
    @Override protected float crashResistance() { return 0.12f; }  // lightweight — poor crash structure
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (NA Boxer-4 — high-rev, progressive)
    // ═══════════════════════════════════════════════════════════
 
    @Override
    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.highRevNA(
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP,
            SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

 
    public GR86Entity(EntityType<?> type, Level level) {
        super(type, level);
    }
}
