package net.ent.entstupidstuff.api.car;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * 2025 Honda Civic Type R — 2.0L Turbo K20C1, simulated automatic, FWD.
 *
 * Real specs: 315 hp @ 6500 RPM, 310 lb-ft @ 2600–4000 RPM, 6-speed manual.
 * Implemented as automatic (CLUTCH_TICKS = 2, fast computer-shifted gears).
 *
 * Character vs Dodge Viper GTS:
 *
 *   Torque delivery:  Turbo flat plateau 2600–4000 RPM (vs Viper's sharp 3700 peak).
 *                     Strong and linear through the mid-range, falls off high.
 *   Drive type:       FWD — throttle loads front, understeer under power.
 *   Top speed:        ~108 km/h (vs Viper ~110) — stickier tyres offset less power.
 *   Shift feel:       Fast 2-tick automatic shifts (real Type R is 6-speed manual,
 *                     but simulated as DCT-style for game playability).
 *   Drifting:         Handbrake slides only — rear snaps out then self-corrects.
 *   Tyre grip:        265/30ZR20 Michelin Pilot Sport 4S — high front grip.
 *
 * Shift points: 1st 25.6 | 2nd 37.1 | 3rd 50.3 | 4th 65.9 | 5th 83.3 | 6th 105 km/h
 */
public class HondaCivicTypeREntity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (K20C1 2.0L Turbo, 315 hp @ 6500 RPM)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  750f; }
    @Override protected float redlineRpm()      { return 7000f; }
    @Override protected float maxReverseRpm()   { return 1800f; }
    @Override protected float maxReverseSpeed() { return 0.28f; }
    @Override protected float downshiftRpm()    { return 1800f; }
    @Override protected int   clutchTicks()     { return 2; }   // simulated automatic — fast shifts
    @Override protected int   maxGear()         { return 6; }
 
    // Turbo torque curve — broad plateau 2600–4000 RPM due to turbocharger.
    // Much flatter than the Viper's naturally aspirated mountain peak.
    // Falls off above 4000 as the engine approaches its cam breathing limit.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 750, 1500, 2000, 2600, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .35f, .60f, .82f, 1.0f, 1.0f, 1.0f, .99f, .95f, .88f, .78f, .65f, .50f, .38f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (6-speed manual ratios, 4.765:1 final drive)
    //
    //  TYRE_CIRC calibrated: redline in 6th = 1.458 bl/tick (105 km/h).
    //  True terminal ~108 km/h (front grip limit, not engine limit).
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 2.647f, 1.826f, 1.346f, 1.028f, 0.813f, 0.645f }; }
    @Override protected float   finalDrive()    { return 4.765f; }
    @Override protected float   tyreCirc()      { return 0.768f; } // calibrated for 105 km/h at redline in 6th
    @Override protected float   peakDriveForce(){ return 0.60f;  } // 315 hp vs Viper's ~450 hp
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (compact FWD, front-heavy layout)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.63f; } // FWD, engine over front axle
    @Override protected float  rearBias()   { return 0.37f; }
    @Override protected float  frontDist()  { return 1.20f; }
    @Override protected float  rearDist()   { return 1.65f; }
    @Override protected double trackHalf()  { return 0.75;  }
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (265/30ZR20 Michelin Pilot Sport 4S)
    //  Significantly stickier than the Civic Hybrid's touring tyres.
    //  Front grip is high — Type R is tuned for cornering, not drifting.
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.155f; } // PS4S front — very high
    @Override protected float rearGripMax()    { return 0.155f; } // matched rear rubber
    @Override protected float gripStiffness()  { return 0.88f;  } // more precise than Hybrid
    @Override protected float slipThreshold()  { return 0.08f;  } // PS4S grips harder before sliding
    @Override protected float slipFalloff()    { return 0.62f;  }
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (sport-tuned, adaptive dampers)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.87f; } // quick lateral bleed (sporty)
    @Override protected float  longTransfer()     { return 0.28f; }
    @Override protected float  latTransfer()      { return 0.09f; }
    @Override protected float  maxSteerDeg()      { return 4.5f;  } // quicker rack than Hybrid
    @Override protected double peakSteerSpeed()   { return 0.32;  }
    @Override protected double highSteerFraction(){ return 0.58;  }
    @Override protected float  driftSteerBoost()  { return 1.4f;  } // FWD, limited slide authority
    @Override protected float  handbrakeRearGrip(){ return 0.04f; }
    @Override protected float  yawMomentScale()   { return 200f;  } // more rotation than Hybrid, less than Viper
    @Override protected float  yawDamping()       { return 0.82f; }
    @Override protected float  yawMax()           { return 4.0f;  }
    @Override protected float  driftThreshold()   { return 0.028f;} // PS4S grips hard but lets go suddenly
    @Override protected float  throttleRampOn()   { return 0.11f; } // turbo builds quickly
    @Override protected float  throttleRampOff()  { return 0.22f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (aggressive front splitter and rear wing add downforce
    //         but also increase aero drag above ~80 km/h)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.979; } // similar to Viper
    @Override protected double aeroDragStart() { return 0.85;  } // wing loads up earlier
    @Override protected double aeroDragK()     { return 0.005; } // steeper aero curve than Viper
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return false; } // FWD
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public HondaCivicTypeREntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override protected double seatHeight()  { return 0.35; }
    @Override protected double seatSide()    { return 0.55; }
    @Override protected double seatForward() { return -0.25; }

}
