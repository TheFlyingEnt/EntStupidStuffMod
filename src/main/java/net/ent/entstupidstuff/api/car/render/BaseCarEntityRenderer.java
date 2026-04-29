package net.ent.entstupidstuff.api.car.render;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.models.BaseCarEntityModel;
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

public abstract class BaseCarEntityRenderer extends EntityRenderer<BaseCarEntity, CarRenderState> {
 
    /** Main texture for this car's model. Supplied by subclass. */
    protected abstract ResourceLocation texture();
    /** Glow texture shown when driving forward (headlights on). */
    protected abstract ResourceLocation glowTexture();
    /** Glow texture shown when in reverse (reverse lights). */
    protected abstract ResourceLocation glowBackupTexture();
 
    protected final List<RenderLayer<CarRenderState, BaseCarEntityModel>> layers = Lists.<RenderLayer<CarRenderState, BaseCarEntityModel>>newArrayList();
 
    private static final float MODEL_SCALE = 1.0f;
 
    // ── Animation tuning (mirrors BaseCarEntityModel constants for the lerp calcs) ──
    private static final float STEERING_WHEEL_MULTIPLIER = (float)(Math.PI * 3.5f);
    private static final float SHIFTER_MAX_TILT          = 0.35f;
    private static final float BODY_ROLL_MAX             = 0.15f;
    private static final float BODY_ROLL_LERP            = 0.15f;
    private static final float STEER_WHEEL_LERP          = 0.20f;
    private static final float SHIFTER_LERP              = 0.10f;
    private final BaseCarEntityModel model;
 
    public BaseCarEntityRenderer(EntityRendererProvider.Context context, BaseCarEntityModel model) {
        super(context);
        this.model = model;
        this.shadowRadius = 1.6f;
    }
 
    protected final boolean addLayer(RenderLayer<CarRenderState, BaseCarEntityModel> renderLayer) {
		return this.layers.add(renderLayer);
	}
 
    @Override
    public CarRenderState createRenderState() {
        return new CarRenderState();
    }
 
    @Override
    public void extractRenderState(BaseCarEntity entity, CarRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
 
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
        // carried over from the last frame). BaseCarEntityModel.setupAnim() then just
        // reads the already-interpolated values — no persistent model state.
 
        // Steering wheel
        float targetSteerWheel = state.steerInput * STEERING_WHEEL_MULTIPLIER;
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
    public void submit(CarRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();
 
        poseStack.mulPose(Axis.YP.rotationDegrees(180f - state.yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
 
        poseStack.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
        poseStack.translate(0, -1.35F, 0);
        
 
        model.setupAnim(state);
        
        collector.submitModel(
			this.model(), state, poseStack, this.renderType(), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
		);
 
        for (RenderLayer<CarRenderState, BaseCarEntityModel> renderLayer : this.layers) {
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
 
	protected RenderType renderType() {
		return this.model.renderType(texture());
	}
 
    protected EntityModel<CarRenderState> model() {
        return this.model;
    }
 
}
 