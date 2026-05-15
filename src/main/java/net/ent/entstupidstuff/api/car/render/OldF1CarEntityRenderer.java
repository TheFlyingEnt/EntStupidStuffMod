package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.F1CarEntity;
import net.ent.entstupidstuff.api.car.models.F1CarEntityModel;
import net.ent.entstupidstuff.api.car.models.NissanZEntityModel;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.render.entity.state.BassRenderState;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;


public class OldF1CarEntityRenderer extends EntityRenderer<F1CarEntity, F1CarRenderState> {

    private static final ResourceLocation AUDI = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_audi.png");
    private static final ResourceLocation REDBULL_JAPAN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_redbull_japan.png");
    private static final ResourceLocation CAMEL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_camel.png");
    private static final ResourceLocation DEMON_SLAYER = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_demonslayer.png");
    private static final ResourceLocation SENNA = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_senna.png");
    private static final ResourceLocation REDBULL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_redbull.png");
    private static final ResourceLocation BASE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_base_updated.png");

    private static final ResourceLocation JURASSICSTUDIO = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_jurassic_studios.png");
    private static final ResourceLocation ENTITY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_entity.png");
    private static final ResourceLocation LEXUS = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_lexus.png");
    private static final ResourceLocation STAKE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_stake.png");
    private static final ResourceLocation ASTON = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_aston.png");

    private static final ResourceLocation MCLAREN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_mclaren.png");
    private static final ResourceLocation FERRARI24 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_base_ferrari_sf24.png");
    private static final ResourceLocation FERRARI26 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_base_ferrari_sf26.png");
    private static final ResourceLocation CADILLAC = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_cadillac.png");
    private static final ResourceLocation HAAS24 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_haas_vf24.png");
    private static final ResourceLocation MERCADES = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_mercades.png");
    private static final ResourceLocation VCARB = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_vcarb.png");
    private static final ResourceLocation BLAST = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_blast.png");
    private static final ResourceLocation BEAMY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_beamy.png");
    private static final ResourceLocation FORD = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_ford.png");
    private static final ResourceLocation HAAS26 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_haas_vf26.png");
 
    private static final ResourceLocation GLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light.png");
    private static final ResourceLocation GLOW_BACKUP = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light_backup.png");
    private static final ResourceLocation GLOW_REVERSE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light_backup.png");

    public  ResourceLocation texture(F1CarRenderState state) {
        return switch (state.variant) {
			case AUDI -> AUDI; //5
			case REDBULL_JAPAN -> REDBULL_JAPAN; //1
            case CAMEL -> CAMEL; //7
            case DEMON_SLAYER -> DEMON_SLAYER; //8
            case SENNA -> SENNA; // - N/A
            case REDBULL -> REDBULL; //1
            case JURASSICSTUDIO -> JURASSICSTUDIO; //9
            case ENTITY -> ENTITY; //9
            case LEXUS -> LEXUS; //9
            case STAKE -> STAKE; //4
            case ASTON -> ASTON; //14
            case MCLAREN -> MCLAREN; // - NA
            case FERRARI24 -> FERRARI24; //15
            case FERRARI26 -> FERRARI26; //16
            case CADILLAC -> CADILLAC; //11
            case HAAS24 -> HAAS24; //10
            case MERCADES -> MERCADES; //44
            case VCARB -> VCARB; //3
            case BLAST -> BLAST; //10
            case BEAMY -> BEAMY;
            case FORD -> FORD; //2
            case HAAS26 -> HAAS26; //17
			default -> BASE;

            /*
            REDBULL           1
            REDBULL_JAPAN     1
            FORD              2
            VCARB             3
            STAKE             4
            AUDI              5
            TBA               6 - TBA....... Alpine?
            TBA               7 - TBA....... Williams?
            CAMEL             7 - SPECIAL
            DEMON_SLAYER      8 - SPECIAL
            TBA               8 - TBA
            TBA               9 - TBA
            ENTITY            9 - SPECIAL
            LEXUS             9 - SPECIAL
            JURASSICSTUDIO    9 - SPECIAL
            HAAS24            10
            BLAST             10 - SPECIAL
            CADILLAC          11
            ASTON             14
            FERRARI24         15
            FERRARI26         16
            HAAS26            17
            MERCADES          44
            */
		};
    }

    public ResourceLocation glowTexture() {
        return GLOW;
    }

    public ResourceLocation glowBackupTexture() {
        return GLOW_BACKUP;
    }
 
    protected final List<RenderLayer<F1CarRenderState, F1CarEntityModel>> layers = Lists.<RenderLayer<F1CarRenderState, F1CarEntityModel>>newArrayList();
 
    private static final float MODEL_SCALE = 1.0f;
 
    // ── Animation tuning (mirrors F1CarEntityModel constants for the lerp calcs) ──
    private static final float STEERING_WHEEL_MULTIPLIER = (float)(Math.PI * 3.5f);
    private static final float SHIFTER_MAX_TILT          = 0.35f;
    private static final float BODY_ROLL_MAX             = 0.15f;
    private static final float BODY_ROLL_LERP            = 0.15f;
    private static final float STEER_WHEEL_LERP          = 0.20f;
    private static final float SHIFTER_LERP              = 0.10f;
    private final F1CarEntityModel model;
 
    public OldF1CarEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new F1CarEntityModel(context.bakeLayer(F1CarEntityModel.LAYER_LOCATION));
        this.shadowRadius = 1.6f;
    }
 
    protected final boolean addLayer(RenderLayer<F1CarRenderState, F1CarEntityModel> renderLayer) {
		return this.layers.add(renderLayer);
	}
 
    @Override
    public F1CarRenderState createRenderState() {
        return new F1CarRenderState();
    }
 
    @Override
    public void extractRenderState(F1CarEntity entity, F1CarRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        state.variant = entity.getVariant();
 
        state.isDrifting    = entity.isDrifting();
        state.isBurningOut  = entity.isBurningOut();
        state.wheelSpin     = entity.getWheelSpin();
        state.rearWheelSpin = entity.getRearWheelSpin();
        state.steerInput   = entity.getSteerInput();
        state.forwardSpeed = entity.getForwardSpeed();
        state.yRot         = Mth.rotLerp(partialTick, entity.yRotO, entity.getYRot());
 
        Vec3 vel = entity.getDeltaMovement();
        double yRad = Math.toRadians(entity.getYRot());
        state.lateralVelocity = (float)(vel.x * Math.cos(yRad) + vel.z * Math.sin(yRad));
 
        // ── Per-entity lerp — runs once per entity per frame ───────────────
        //
        // This is the key fix: lerp happens here in extractRenderState() where
        // we have access to the PREVIOUS state values (state.steerWheelRot etc.
        // carried over from the last frame). F1CarEntityModel.setupAnim() then just
        // reads the already-interpolated values — no persistent model state.
 
        // Steering wheel
        float targetSteerWheel = -state.steerInput * STEERING_WHEEL_MULTIPLIER;
        state.steerWheelRot = Mth.lerp(STEER_WHEEL_LERP,
                                        state.steerWheelRot, targetSteerWheel);
 
        // Gear shifter
        float targetShifter;
        if (state.forwardSpeed > 0.01f) {
            targetShifter = -SHIFTER_MAX_TILT * Mth.clamp(state.forwardSpeed / 0.3f, 0f, 1f);
        } else if (state.forwardSpeed < -0.01f) {
            targetShifter = SHIFTER_MAX_TILT;
        } else {
            targetShifter = 0f;
        }
        state.shifterRot = Mth.lerp(SHIFTER_LERP, state.shifterRot, targetShifter);
 
        // Body roll
        /*float targetRoll = Mth.clamp(state.lateralVelocity * -0.8f,
                                     -BODY_ROLL_MAX, BODY_ROLL_MAX);
        state.bodyRoll = Mth.lerp(BODY_ROLL_LERP, state.bodyRoll, targetRoll);
        if (state.isDrifting) {
            state.bodyRoll += Mth.sin(state.ageInTicks * 0.3f) * 0.025f;
        }*/

        float rollScale = state.isDrifting ? 1.8f : 1.0f;
        float targetRoll = Mth.clamp(state.lateralVelocity * -0.8f * rollScale,
                                     -BODY_ROLL_MAX, BODY_ROLL_MAX);
        state.bodyRoll = Mth.lerp(BODY_ROLL_LERP, state.bodyRoll, targetRoll);
        if (state.isDrifting) {
            state.bodyRoll += Mth.sin(state.ageInTicks * 0.3f) * 0.025f;
        }
    }
 
    @Override
    public void submit(F1CarRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();
 
        poseStack.mulPose(Axis.YP.rotationDegrees(180f - state.yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
 
        poseStack.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
        poseStack.translate(0, -1.35F, 0);
        
 
        model.setupAnim(state);
        
        collector.submitModel(
			this.model(), state, poseStack, this.renderType(state), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
		);
 
        for (RenderLayer<F1CarRenderState, F1CarEntityModel> renderLayer : this.layers) {
			renderLayer.submit(
				poseStack, collector, state.lightCoords, state, state.yRot, state.xRot
			);
		}
 
        int alphaByte = Math.round(1 * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;
 
        if (state.forwardSpeed < 0) {
            collector.order(1)
			.submitModel(
				this.model(), state, poseStack, RenderType.eyes(glowBackupTexture()), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);
 
        } else {
            collector.order(1)
			.submitModel(
				this.model(), state, poseStack, RenderType.eyes(glowTexture()), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);
 
        }
 
        
 
        poseStack.popPose();
 
        super.submit(state, poseStack, collector, cameraState);
    }
 
	protected RenderType renderType(F1CarRenderState state) {
		return this.model.renderType(texture(state));
	}
 
    protected EntityModel<CarRenderState> model() {
        return this.model;
    }
 
}
