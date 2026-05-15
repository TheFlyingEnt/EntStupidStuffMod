package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
 
/**
 * 2023 Ford Mustang Shelby GT500 — 5.2L Supercharged V8, 760 hp, RWD.
 *
 * The sledgehammer. 760 hp in a 1,900 kg muscle car with the highest
 * peakDriveForce in the garage. The traction circle is almost always
 * fully saturated in 1st through 3rd gear — power oversteer is trivially
 * easy to trigger and hard to control. Heavy weight makes slides slower
 * to initiate but momentum carries them a long way once rolling.
 *
 * 7-speed dual-clutch transmission shifts nearly instantly. The supercharged
 * torque curve is flat and massive from 2800 RPM — unlike naturally aspirated
 * cars there is no "building into the powerband". It just hits hard.
 *
 * Good for stress-testing:
 *   - 7-gear drivetrain chain
 *   - High-power burnout system (BURN_RPM_RISE hits redline fast)
 *   - Extreme oversteer correction (yawMax = 6.5, highest in garage)
 *
 * Character vs other cars:
 *   vs Viper:   More power, heavier, 7 gears, supercharged plateau vs V10 peak.
 *               Less nimble, more point-and-shoot brutal.
 *   vs GT3:     Half the grip, twice the drama.
 *   vs GR86:    Like comparing a sledgehammer to a scalpel.
 *
 * Shift points: 1st 23 | 2nd 33 | 3rd 44 | 4th 56 | 5th 67 | 6th 86 | 7th 112 km/h
 */

public class ShelbyGT500Entity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (5.2L Predator V8 + Roots supercharger, 760 hp @ 7300 RPM)
    //  Supercharged torque curve: massive flat plateau from 2800–4200 RPM.
    //  Far more accessible than the GT3's high-rev NA delivery.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  750f; }
    @Override protected float redlineRpm()      { return 7500f; }
    @Override protected float maxReverseRpm()   { return 2000f; }
    @Override protected float maxReverseSpeed() { return 0.32f; }
    @Override protected float downshiftRpm()    { return 2000f; }
    @Override protected int   clutchTicks()     { return 3; }
    @Override protected int   maxGear()         { return 7; }
 
    // Supercharged curve — strong from 1500, plateau 2800–4200, then falls.
    // Compare to GR86's gentle mountain or GT3's high-rev climb.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 750, 1500, 2200, 2800, 3500, 4200, 5000, 5800, 6500, 7000, 7500 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .40f, .68f, .88f, .97f, 1.0f, 1.0f, .98f, .92f, .80f, .65f, .48f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (Tremec TR-9007 7-speed DCT + 3.73:1 final drive)
    //
    //  TYRE_CIRC calibrated: redline in 7th = 1.556 bl/tick (112 km/h)
    //  7 gears stress-tests the full drivetrain chain.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 3.700f, 2.600f, 1.949f, 1.551f, 1.280f, 1.000f, 0.769f }; }
    @Override protected float   finalDrive()    { return 3.730f; }
    @Override protected float   tyreCirc()      { return 0.7139f; }
    @Override protected float   peakDriveForce(){ return 0.95f; }  // highest in the garage — brutal
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (long muscle car, front-heavy despite RWD)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.53f; } // front-heavy for a RWD car
    @Override protected float  rearBias()   { return 0.47f; }
    @Override protected float  frontDist()  { return 1.50f; }
    @Override protected float  rearDist()   { return 2.00f; }
    @Override protected double trackHalf()  { return 0.92; }  // wide track
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (305/30ZR20 Michelin Pilot Sport 4S)
    //  Good tyres but massively overpowered relative to grip.
    //  In 1st–3rd gear: traction circle almost always fully saturated.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.145f; }
    @Override protected float rearGripMax()    { return 0.170f; }
    @Override protected float gripStiffness()  { return 0.85f; }
    @Override protected float slipThreshold()  { return 0.10f; }
    @Override protected float slipFalloff()    { return 0.58f; }
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (muscle car — powerful but heavy and blunt)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.86f; } // heavy mass = slower bleed
    @Override protected float  longTransfer()     { return 0.30f; }
    @Override protected float  latTransfer()      { return 0.10f; }
    @Override protected float  maxSteerDeg()      { return 4.0f; }
    @Override protected double peakSteerSpeed()   { return 0.34; }
    @Override protected double highSteerFraction(){ return 0.55; }
    @Override protected float  driftSteerBoost()  { return 1.7f; }
    @Override protected float  handbrakeRearGrip(){ return 0.04f; }
    @Override protected float  yawMomentScale()   { return 300f; }
    @Override protected float  yawDamping()       { return 0.82f; }
    @Override protected float  yawMax()           { return 6.5f; }  // highest yawMax — scary spins
    @Override protected float  driftThreshold()   { return 0.032f; }
    @Override protected float  throttleRampOn()   { return 0.09f; }  // power arrives fast
    @Override protected float  throttleRampOff()  { return 0.18f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (heavy, high frontal area, moderate aero)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.976; } // heavier than all other cars
    @Override protected double aeroDragStart() { return 0.90;  }
    @Override protected double aeroDragK()     { return 0.0045; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return true; }
    @Override protected float realisticSpeedScale() { return 2.589f; }
    @Override protected float surfacePenaltyScale() { return 1.4f; }  // max-performance PS4S street tyres
    @Override protected float crashResistance() { return 0.25f; }  // heavy muscle car — tanks through
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (Supercharged V8 — deep rumble, loud)
    // ═══════════════════════════════════════════════════════════
 
    @Override
    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.americanV8(
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP,
            SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

 
    public ShelbyGT500Entity(EntityType<?> type, Level level) {
        super(type, level);
    }
}
