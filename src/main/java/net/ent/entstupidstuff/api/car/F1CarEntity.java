package net.ent.entstupidstuff.api.car;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * 2024 Formula 1 Car — 1.6L Turbo-Hybrid V6 + MGU-K + MGU-H, ~1000 hp combined, RWD.
 *
 * Real specs:
 *   Engine  : 1.6L single-turbo V6 + dual electric motors (MGU-K, MGU-H)
 *   Power   : ~1000 hp combined (ICE ~850 hp + ERS ~160 hp)
 *   Torque  : ~500 Nm ICE + electric fill across full rev range
 *   Trans   : 8-speed seamless-shift sequential (paddle, ~50 ms upshift)
 *   Drive   : RWD
 *   Weight  : 798 kg minimum (with driver)
 *   Dist    : 45% front / 55% rear (rear-biased, engine behind driver)
 *   Tyres   : Pirelli P Zero slicks — 305/670-13 front, 405/670-13 rear
 *   WB      : 3,600 mm
 *   Track   : ~1,600 mm front / ~1,550 mm rear
 *   Top spd : ~370 km/h (Monza spec, low downforce)
 *   0–100   : ~2.6 s
 *   0–200   : ~4.7 s
 *   0–300   : ~10.6 s
 *   Redline : 15,000 RPM (FIA limited)
 *   Idle    : ~4,000 RPM (no traditional idle — MGU-H keeps turbo spooled)
 *
 * In-game character:
 *   Fastest car in a straight line by a wide margin (~200 km/h vs ~120 km/h for road cars).
 *   Massive grip from ground-effect downforce — cornering speeds far exceed any road car.
 *   Almost impossible to drift — tyres hang on until they suddenly don't, then snap oversteer
 *   from the rear-biased weight catches you immediately. Very high yawMomentScale combined
 *   with low slipFalloff means loss of control is violent and unrecoverable.
 *   Rewards clean, precise driving. Punishes overdriving.
 *
 *   Single-seat open-wheel cockpit — driver sits centred and very low.
 *   No passenger seat. canAddPassenger() capped at 1.
 *
 * Shift points: 1st 49 | 2nd 68 | 3rd 87 | 4th 106 | 5th 126 | 6th 148 | 7th 170 | 8th 200 km/h
 */
public class F1CarEntity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  SEAT  (single-seat, centred, very low open cockpit)
    //
    //  seatSide = 0 puts the driver on the car's centreline.
    //  seatHeight is low — driver sits in the car, not on it.
    //  seatForward nudged back slightly — cockpit is behind front axle.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double seatHeight()  { return -0.10; }
    @Override protected double seatSide()    { return 0.00; }
    @Override protected double seatForward() { return 0.30; }
 
    /** F1 cars carry one driver. No passenger seat. */
    @Override protected boolean canAddPassenger(Entity p) {
        return this.getPassengers().isEmpty();
    }
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (1.6L Turbo V6 + ERS, ~1000 hp combined)
    //
    //  Extremely high redline (15,000 RPM FIA limit).
    //  Idle at 4,000 — the MGU-H keeps the turbo lit, there is no
    //  traditional "lugging" below 4k. The torque curve is broad and
    //  flat thanks to electric fill: near-peak from 6,000–12,000 RPM,
    //  then falls as ICE breathing limits hit above 13k.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return 4000f; }
    @Override protected float redlineRpm()      { return 15000f; }
    @Override protected float maxReverseRpm()   { return 5000f; }
    @Override protected float maxReverseSpeed() { return 0.20f; }
    @Override protected float downshiftRpm()    { return 6000f; }
    @Override protected int   clutchTicks()     { return 1; }   // seamless-shift — near-instant
    @Override protected int   maxGear()         { return 8; }
 
    // Hybrid torque curve — electric motors fill the low end, ICE dominates
    // mid-to-high range. Remarkably flat plateau from 6,000–12,000 RPM.
    // Falls off above 13,000 as valvetrain and breathing limits bite.
    // The low-RPM electric boost means there's usable torque even at 4k idle.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{
            4000f, 5000f, 6000f, 7000f, 8000f, 9000f, 10000f,
            11000f, 12000f, 13000f, 14000f, 15000f
        };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{
            .55f, .78f, .92f, .97f, 1.0f, 1.0f, .99f,
            .98f, .96f, .90f, .76f, .58f
        };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (8-speed seamless-shift sequential + 3.00:1 final)
    //
    //  Gear ratios approximate real F1 spacing — very close together
    //  since the engine's power band is narrow in absolute terms
    //  (6,000–15,000 RPM, but the useful window is 9,000–14,000).
    //
    //  TYRE_CIRC calibrated: redline in 8th = 2.78 bl/tick (200 km/h).
    //  This makes the F1 car ~67% faster than the Viper (120 km/h)
    //  and ~70% faster than the GT3 (118 km/h) in a straight line.
    //
    //  Shift points (redline in each gear):
    //    1st  49 | 2nd  68 | 3rd  87 | 4th 106 |
    //    5th 126 | 6th 148 | 7th 170 | 8th 200 km/h
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios() {
        return new float[]{ 0f, 3.50f, 2.50f, 1.95f, 1.60f, 1.35f, 1.15f, 1.00f, 0.85f };
    }
    @Override protected float   finalDrive()    { return 3.00f; }
    @Override protected float   tyreCirc()      { return 0.567f; }
    @Override protected float   peakDriveForce(){ return 1.10f; }  // highest in the garage — ~1000 hp in 798 kg
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (long wheelbase open-wheeler, rear-biased)
    //
    //  3,600 mm wheelbase — longer than any road car in the garage.
    //  Engine and gearbox sit behind the driver, pushing 55% weight
    //  to the rear. This is the opposite of most front-engine cars.
    //  Track is wide (~1,575 mm average) for cornering stability.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.45f; }
    @Override protected float  rearBias()   { return 0.55f; }
    @Override protected float  frontDist()  { return 1.80f; }
    @Override protected float  rearDist()   { return 2.40f; }
    @Override protected double trackHalf()  { return 0.80; }
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (Pirelli P Zero slicks + ground-effect downforce)
    //
    //  Highest grip values in the entire garage by a large margin.
    //  At speed, downforce pushes the car into the track with 3–5×
    //  its own weight, giving cornering forces no road tyre can match.
    //
    //  gripStiffness is very high — the tyres respond instantly to
    //  slip angle. slipThreshold is low — they grip hard right up to
    //  the edge. slipFalloff is the lowest in the garage — when grip
    //  is lost, it drops off a cliff. No progressive slide, just snap.
    //
    //  Rear grip slightly higher than front (wider rear tyres: 405 vs 305).
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.250f; }
    @Override protected float rearGripMax()    { return 0.280f; }
    @Override protected float gripStiffness()  { return 0.95f; }
    @Override protected float slipThreshold()  { return 0.06f; }
    @Override protected float slipFalloff()    { return 0.45f; }  // cliff-edge — snap oversteer
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (race-bred, razor-sharp, unforgiving)
    //
    //  Steering is the most direct in the garage — tiny inputs produce
    //  big direction changes. At speed the car turns in immediately.
    //  driftSteerBoost is low because you're not meant to drift an F1
    //  car — if the rear steps out, you've already lost it.
    //
    //  yawMomentScale is the highest in the garage (350). Combined with
    //  55% rear weight and low slipFalloff, any rear grip loss instantly
    //  spins the car. This is authentic — real F1 spins are sudden and
    //  violent, not the long lazy slides of a muscle car.
    //
    //  yawDamping is low (0.72) — once rotation starts, it builds fast.
    //  yawMax is high (7.0) — if you spin, you spin hard.
    //
    //  Coast decay is aggressive — the car settles very quickly when
    //  inputs are released, simulating the massive aerodynamic stability.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.82f; }  // aggressive lateral bleed
    @Override protected float  longTransfer()     { return 0.35f; }  // strong weight transfer
    @Override protected float  latTransfer()      { return 0.12f; }
    @Override protected float  maxSteerDeg()      { return 5.5f; }   // most direct rack in the garage
    @Override protected double peakSteerSpeed()   { return 0.40; }
    @Override protected double highSteerFraction(){ return 0.45; }   // retains good authority at speed
    @Override protected float  driftSteerBoost()  { return 1.2f; }   // minimal — not a drift car
    @Override protected float  handbrakeRearGrip(){ return 0.03f; }
    @Override protected float  yawMomentScale()   { return 350f; }   // highest — rear-engine snap
    @Override protected float  yawDamping()       { return 0.72f; }  // lowest — rotation builds fast
    @Override protected float  yawMax()           { return 7.0f; }   // highest — violent spins
    @Override protected float  driftThreshold()   { return 0.040f; } // very hard to break traction
    @Override protected float  throttleRampOn()   { return 0.07f; }  // instant throttle response
    @Override protected float  throttleRampOff()  { return 0.12f; }  // fast off too — no turbo lag (MGU-H)
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (high-downforce aero package)
    //
    //  F1 cars generate enormous drag from their wings and diffuser.
    //  rollingDrag is very low (lightweight, low-friction bearings).
    //  aeroDragStart kicks in early — at ~140 km/h (~1.94 bl/tick)
    //  the wings are already generating meaningful drag.
    //  aeroDragK is moderate — the car is fast enough that aero drag
    //  is the primary speed limiter, not engine power.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.988; }
    @Override protected double aeroDragStart() { return 1.94;  }
    @Override protected double aeroDragK()     { return 0.008; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return true; }
    @Override protected float realisticSpeedScale() { return 1.875f; }
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public F1CarEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
}
