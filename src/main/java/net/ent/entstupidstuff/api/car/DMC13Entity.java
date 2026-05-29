package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.item.util.CarWrapHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Dodge Viper GTS — 8.0L V10, Tremec T56, RWD.
 *
 * Extends BaseCarEntity and supplies all car-specific physics constants.
 * The physics engine lives entirely in the base class.
 */

public class DMC13Entity extends BaseCarEntity {
 
    // ═══════════════════════════════════════════════════════════
    //  ENGINE  (8.0L V10, 450 hp @ 5200 RPM, 490 lb-ft @ 3700 RPM)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float idleRpm()        { return  800f; }
    @Override protected float redlineRpm()      { return 7400f; }
    @Override protected float maxReverseRpm()   { return 2000f; }
    @Override protected float maxReverseSpeed() { return 0.35f; }
    @Override protected float downshiftRpm()    { return 2200f; }
    @Override protected int   clutchTicks()     { return 4; }
    @Override protected int   maxGear()         { return 6; }
 
    // V10 torque curve — peaks at 3700 RPM, falls sharply at high RPM
    @Override protected float[] torqueRpmPoints() {
        return new float[]{ 800, 1500, 2000, 2500, 3000, 3700, 4500, 5200, 6000, 6500, 7000, 7400 };
    }
    @Override protected float[] torqueCurve() {
        return new float[]{ .30f, .60f, .78f, .91f, .96f, 1.0f, .98f, .94f, .82f, .72f, .58f, .44f };
    }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVETRAIN  (Tremec T56 + 3.07:1 final drive)
    //
    //  TYRE_CIRC calibrated: redline in 6th = 1.667 bl/tick (120 km/h)
    //  Shift points: 1st 22.6 | 2nd 33.7 | 3rd 46.2 | 4th 60.0 | 5th 81.1 | 6th 120 km/h
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float[] gearRatios()    { return new float[]{ 0f, 2.66f, 1.78f, 1.30f, 1.00f, 0.74f, 0.50f }; }
    @Override protected float   finalDrive()    { return 3.07f; }
    @Override protected float   tyreCirc()      { return 0.415f; } // game-calibrated
    @Override protected float   peakDriveForce(){ return 0.75f; }
 
    // ═══════════════════════════════════════════════════════════
    //  GEOMETRY
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  frontBias()  { return 0.49f; }
    @Override protected float  rearBias()   { return 0.51f; }
    @Override protected float  frontDist()  { return 1.80f; }
    @Override protected float  rearDist()   { return 2.24f; }
    @Override protected double trackHalf()  { return 0.90; }
 
    // ═══════════════════════════════════════════════════════════
    //  GRIP  (335/35ZR17 rear — massive contact patch)
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float frontGripMax()   { return 0.130f; }
    @Override protected float rearGripMax()    { return 0.175f; }
    @Override protected float gripStiffness()  { return 0.85f;  }
    @Override protected float slipThreshold()  { return 0.10f;  }
    @Override protected float slipFalloff()    { return 0.60f;  }
 
    // ═══════════════════════════════════════════════════════════
    //  HANDLING
    // ═══════════════════════════════════════════════════════════
 
    @Override protected float  latDecay()        { return 0.88f; }
    @Override protected float  longTransfer()    { return 0.30f; }
    @Override protected float  latTransfer()     { return 0.10f; }
    @Override protected float  maxSteerDeg()     { return 4.2f;  }
    @Override protected double peakSteerSpeed()  { return 0.35;  }
    @Override protected double highSteerFraction(){ return 0.55; }
    @Override protected float  driftSteerBoost() { return 1.7f;  }
    @Override protected float  handbrakeRearGrip(){ return 0.04f;}
    @Override protected float  yawMomentScale()  { return 280f;  }
    @Override protected float  yawDamping()      { return 0.80f; }
    @Override protected float  yawMax()          { return 5.5f;  }
    @Override protected float  driftThreshold()  { return 0.030f;}
    @Override protected float  throttleRampOn()  { return 0.10f; }
    @Override protected float  throttleRampOff() { return 0.20f; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRAG
    // ═══════════════════════════════════════════════════════════
 
    @Override protected double rollingDrag()   { return 0.978; }
    @Override protected double aeroDragStart() { return 0.90;  }
    @Override protected double aeroDragK()     { return 0.004; }
 
    // ═══════════════════════════════════════════════════════════
    //  DRIVE TYPE
    // ═══════════════════════════════════════════════════════════
 
    @Override protected boolean defaultIsRWD() { return true; }
    @Override protected float realisticSpeedScale() { return 2.636f; }
    @Override protected float surfacePenaltyScale() { return 1.4f; }  // max-performance street tyres
    @Override protected float crashResistance() { return 0.20f; }
 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public DMC13Entity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override public Vec3 licensePlateOffset() { return new Vec3(0, 0.25, 3.35); }

    public String[] availableWraps() {
        return CarWrapHelper.visableDMC13();
    }

    @Override public String getCarTypeId() { return "dmc_13"; }

    @Override protected float carMass() { return 0.90f; }

}
