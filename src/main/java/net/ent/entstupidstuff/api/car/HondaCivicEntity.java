package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * 2025 Honda Civic Hybrid Sport Touring — 2.0L Atkinson + electric motor, e-CVT, FWD.
 *
 * Extends BaseCarEntity and supplies all car-specific physics constants.
 * Key character differences from the Viper:
 *
 *   Torque curve:  Flat hybrid plateau (electric fills low-end) vs Viper's sharp peak.
 *   Drive type:    FWD — throttle loads front traction circle → understeer under power.
 *   Shift points:  Lower redline (6000 RPM), higher final drive (4.44) — shorter gears.
 *   Top speed:     ~92 km/h vs Viper ~110 km/h (less power, more aero drag).
 *   Drifting:      Front-heavy (62/38), FWD — much harder to rotate than Viper.
 */

public class HondaCivicEntity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (2.0L Atkinson + e-motor, 200 hp combined)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  700f; }
    @Override protected float redlineRpm()      { return 6000f; }
    @Override protected float maxReverseRpm()   { return 1500f; }
    @Override protected float maxReverseSpeed() { return 0.25f; }
    @Override protected float downshiftRpm()    { return 1500f; }
    @Override protected int   clutchTicks()     { return 2; }   // e-CVT shifts near-instantly
    @Override protected int   maxGear()         { return 6; }
 
    // Hybrid torque curve — broad plateau from 1800–4000 RPM due to electric motor.
    // Compare to Viper: peaks sharply at 3700 then falls hard.
    // Civic: sustained torque then gradual falloff.
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 700, 1200, 1800, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 5800, 6000 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .70f, .90f, .98f, 1.0f, 1.0f, .99f, .95f, .87f, .73f, .55f, .42f, .32f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (e-CVT approximated as 6-speed + 4.44:1 final)
    //
    //  TYRE_CIRC calibrated: redline in 6th = 1.278 bl/tick (92 km/h)
    //  Shift points: 1st 15.2 | 2nd 26.0 | 3rd 38.3 | 4th 54.1 | 5th 76.7 | 6th 92 km/h
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 3.64f, 2.12f, 1.44f, 1.02f, 0.72f, 0.60f }; }
    @Override protected float   finalDrive()    { return 4.44f;  }
    @Override protected float   tyreCirc()      { return 0.681f; } // calibrated for 92 km/h top
    @Override protected float   peakDriveForce(){ return 0.42f;  } // 200 hp vs Viper's ~450 hp
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY  (shorter wheelbase, front-heavy FWD layout)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.62f; } // FWD cars carry more weight up front
    @Override protected float  rearBias()   { return 0.38f; }
    @Override protected float  frontDist()  { return 1.10f; } // shorter wheelbase than Viper
    @Override protected float  rearDist()   { return 1.60f; }
    @Override protected double trackHalf()  { return 0.70;  } // narrower track than Viper
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (235/40R18 — good summer tyres but not sports car spec)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.105f; }
    @Override protected float rearGripMax()    { return 0.095f; }
    @Override protected float gripStiffness()  { return 0.85f;  }
    @Override protected float slipThreshold()  { return 0.10f;  }
    @Override protected float slipFalloff()    { return 0.65f;  } // slightly more forgiving
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING  (comfort-tuned, less agile than Viper)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()         { return 0.90f; } // slightly more stable
    @Override protected float  longTransfer()     { return 0.25f; }
    @Override protected float  latTransfer()      { return 0.08f; }
    @Override protected float  maxSteerDeg()      { return 3.8f;  } // less direct than Viper
    @Override protected double peakSteerSpeed()   { return 0.30;  }
    @Override protected double highSteerFraction(){ return 0.60;  }
    @Override protected float  driftSteerBoost()  { return 1.3f;  } // less drift authority (FWD)
    @Override protected float  handbrakeRearGrip(){ return 0.04f; }
    @Override protected float  yawMomentScale()   { return 180f;  } // FWD yaws less aggressively
    @Override protected float  yawDamping()       { return 0.85f; }
    @Override protected float  yawMax()           { return 3.5f;  }
    @Override protected float  driftThreshold()   { return 0.035f;} // harder to break traction
    @Override protected float  throttleRampOn()   { return 0.12f; } // hybrid torque available sooner
    @Override protected float  throttleRampOff()  { return 0.18f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG  (less powerful, lower terminal velocity)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.980; } // slightly less drag (lighter car)
    @Override protected double aeroDragStart() { return 0.80;  } // aero kicks in earlier
    @Override protected double aeroDragK()     { return 0.005; } // steeper aero curve
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return false; } // FWD
    @Override protected float realisticSpeedScale() { return 2.174f; }
    @Override protected float surfacePenaltyScale() { return 1.0f; }  // touring summer tyres — standard
    @Override protected float crashResistance() { return 0.12f; }  // lightweight economy car
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (Hybrid — quiet, smooth)
    // ═══════════════════════════════════════════════════════════
 
    @Override
    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.hybrid(
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK,
            SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP,
            SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

 
    public HondaCivicEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
}
