package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * 2025 Nissan Z — 3.0L Twin-Turbo V6 (VR30DDTT)
 *
 * Real specs:
 *   Engine  : 3.0L twin-turbo V6
 *   Power   : 400 hp @ 6,400 rpm
 *   Torque  : 350 lb-ft flat from 1,600–5,600 rpm
 *   Trans   : 6-speed manual (Aisin)
 *   Drive   : RWD
 *   0–60    : ~4.5 s
 *   Top spd : ~155 mph (250 km/h)
 *   Weight  : ~3,600 lb (1,633 kg)
 *   Dist    : 54% front / 46% rear
 *   Tyres   : 245/40R19 front, 255/40R19 rear
 *   WB      : 2,550 mm
 */
public class NissanZEntity extends BaseCarEntity {
 
    public NissanZEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
 
    // ── Seat ─────────────────────────────────────────────────────────────────
    // Low-slung coupe — tight cockpit, driver sits close to centre-line
    //@Override protected double seatHeight()  { return 0.30; }
    //@Override protected double seatSide()    { return 0.28; }
    //@Override protected double seatForward() { return -0.15; }
 
    // ── Drivetrain type ───────────────────────────────────────────────────────
    @Override protected boolean defaultIsRWD() { return true; }
    @Override protected float realisticSpeedScale() { return 2.358f; }
    @Override protected float surfacePenaltyScale() { return 1.4f; }  // max-performance street tyres
    @Override protected float crashResistance() { return 0.15f; }  // sports coupe — moderate
 
    // ── Engine ────────────────────────────────────────────────────────────────
    // VR30DDTT: wide idle, flat power band, 7,200 rpm rev limiter
    @Override protected float idleRpm()        { return  800f; }
    @Override protected float redlineRpm()     { return 7200f; }
    @Override protected float maxReverseRpm()  { return 2800f; }
    @Override protected float maxReverseSpeed(){ return 0.22f; }
    @Override protected float downshiftRpm()   { return 1900f; }
    @Override protected int   clutchTicks()    { return 8;     }
    @Override protected int   maxGear()        { return 6;     }
 
    // ── Torque curve (normalised 0–1) ─────────────────────────────────────────
    // Twin turbos spool fast — near-full torque by 1,600 rpm, plateau to 5,600.
    // Slight dip at peak boost (boost stacking), then clean taper to redline.
    @Override
    protected float[] torqueRpmPoints() {
        return new float[]{ 800f, 1600f, 2800f, 4000f, 5600f, 6400f, 7000f, 7200f };
    }
    @Override
    protected float[] torqueCurve() {
        return new float[]{  0.12f, 0.88f, 1.00f, 0.98f,  0.96f,  0.88f,  0.62f,  0.40f };
    }
 
    // ── Gearbox — Aisin 6-speed manual ───────────────────────────────────────
    // index 0 unused; 1–6 match real ratios.
    // Final drive 3.700; 255/40R19 ≈ 2.156 m circumference.
    // tyreCirc scaled so 250 km/h (3.47 b/t) ≈ 6,400 rpm in 6th.
    @Override
    protected float[] gearRatios() {
        return new float[]{ 0f, 3.821f, 2.360f, 1.685f, 1.312f, 1.000f, 0.794f };
    }
    @Override protected float finalDrive() { return 3.700f; }
    @Override protected float tyreCirc()   { return 0.72f;  }
 
    // ── Drive force ───────────────────────────────────────────────────────────
    // 400 hp RWD — punchy but not traction-limited in all gears like a Viper.
    // Slightly less than a GT86 equivalent to reflect real-world weight penalty.
    @Override protected float peakDriveForce() { return 0.42f; }
 
    // ── Weight distribution ───────────────────────────────────────────────────
    // Front engine pushes 54% weight forward — mild understeer tendency
    // that the 400hp overrides under hard throttle.
    @Override protected float frontBias() { return 0.54f; }
    @Override protected float rearBias()  { return 0.46f; }
 
    // ── Geometry (Minecraft block scale) ─────────────────────────────────────
    // Wheelbase 2,550 mm. CoM sits slightly ahead of mid-point (front-engine).
    // Track avg 1,535 mm.
    @Override protected float  frontDist()  { return 1.75f; }
    @Override protected float  rearDist()   { return 2f; }
    @Override protected double trackHalf()  { return 0.76;  }
 
    // ── Tyre grip ─────────────────────────────────────────────────────────────
    // Wide 255 rear rubber; decent front 245s. Not a track-day special —
    // grip is high enough for spirited driving but slides are accessible.
    @Override protected float frontGripMax()  { return 0.170f; }
    @Override protected float rearGripMax()   { return 0.162f; }
    @Override protected float gripStiffness() { return 0.84f;  }
    @Override protected float slipThreshold() { return 0.12f;  }
    @Override protected float slipFalloff()   { return 0.74f;  }
 
    // ── Lateral dynamics ─────────────────────────────────────────────────────
    @Override protected float latDecay()      { return 0.87f; }
    @Override protected float longTransfer()  { return 0.08f; }
    @Override protected float latTransfer()   { return 0.09f; }
 
    // ── Steering ─────────────────────────────────────────────────────────────
    // Quick, precise rack — characteristic Z feel. Boost applied during drift.
    @Override protected float  maxSteerDeg()       { return 3.0f;  }
    @Override protected double peakSteerSpeed()    { return 0.35;  }
    @Override protected double highSteerFraction() { return 0.35;  }
    @Override protected float  driftSteerBoost()   { return 1.28f; }
 
    // ── Handbrake ─────────────────────────────────────────────────────────────
    // Mechanical rear e-brake — good for flicking tail in corners.
    @Override protected float handbrakeRearGrip() { return 0.10f; }
 
    // ── Yaw / oversteer character ─────────────────────────────────────────────
    // Nimble and eager to rotate, but not snap-oversteer. Recoverable drifts.
    // yawMax kept moderate — the Z can hold a slide, not spin forever.
    @Override protected float yawMomentScale() { return 180f; }
    @Override protected float yawDamping()     { return 0.88f; }
    @Override protected float yawMax()         { return 5.5f;  }
    @Override protected float driftThreshold() { return 0.028f;}
 
    // ── Throttle response ─────────────────────────────────────────────────────
    // Twin-turbo: ramp-on feels slightly delayed vs NA, but pull is strong.
    @Override protected float throttleRampOn()  { return 0.11f; }
    @Override protected float throttleRampOff() { return 0.16f; }
 
    // ── Drag ─────────────────────────────────────────────────────────────────
    // Cd 0.33 — reasonable coupe aero. Drag kicks in meaningfully above 200 km/h.
    @Override protected double rollingDrag()   { return 0.988; }
    @Override protected double aeroDragStart() { return 2.78;  } // ~200 km/h
    @Override protected double aeroDragK()     { return 0.013; }
 
    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (Twin-turbo VR30 — mid grunt, turbo spool)
    // ═══════════════════════════════════════════════════════════
 
    @Override
    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.twinTurboV6(
            SoundFactory.ENTITY_VEHICLE_AUDI_IDLE,
            SoundFactory.ENTITY_VEHICLE_AUDI_GEAR_1,
            SoundFactory.ENTITY_VEHICLE_AUDI_BREAK,
            SoundFactory.ENTITY_VEHICLE_AUDI_GEAR_TOP,
            SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

    @Override
    public String[] availableWraps() {
        return new String[]{ "default", "gold", "green", "silver" };
    }
 
    @Override public String getCarTypeId() { return "nissan_z"; }
    @Override public Vec3 licensePlateOffset() { return new Vec3(0, 0.72, 2.98); }

    @Override protected float carMass() { return 0.80f; }

    @Override public float carLength() { return 3.5f; }
    @Override public float carWidth()  { return 1.8f; }
    @Override public float cameraWeight() { return 0.55f; }



}