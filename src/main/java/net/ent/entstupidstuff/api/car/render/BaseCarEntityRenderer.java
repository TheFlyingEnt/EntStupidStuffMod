package net.ent.entstupidstuff.api.car.render;

import java.util.List;

import org.joml.Matrix4f;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.models.BaseCarEntityModel;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.car.LicensePlateItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public abstract class BaseCarEntityRenderer<E extends BaseCarEntity, S extends CarRenderState> extends EntityRenderer<E, S> {
 
    /** Main texture for this car's model. Supplied by subclass. */
    protected abstract ResourceLocation texture(S state);
    /** Glow texture shown when driving forward (headlights on). */
    protected abstract ResourceLocation glowTexture(S state);
    /** Glow texture shown when in reverse (reverse lights). */
    protected abstract ResourceLocation glowBackupTexture(S state);
 
    protected final List<RenderLayer<S, BaseCarEntityModel>> layers = Lists.<RenderLayer<S, BaseCarEntityModel>>newArrayList();
 
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
 
    protected final boolean addLayer(RenderLayer<S, BaseCarEntityModel> renderLayer) {
		return this.layers.add(renderLayer);
	}
 
    @Override
    public S createRenderState() {
        return (S) new CarRenderState();
    }
 
    @Override
    public void extractRenderState(E entity, S state, float partialTick) {
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

        // ── Wrap + license plate ─────────────────────────────────────────
        state.wrapId      = entity.getCurrentWrap();
        state.carTypeId   = entity.getCarTypeId();
        //state.licensePlate = entity.getLicensePlate();
        state.licensePlate = entity.getSyncedLicensePlate();
        state.plateOffset = entity.licensePlateOffset();

        state.isBreaking = entity.isBraking();

 
        // ── Per-entity lerp — runs once per entity per frame ───────────────
        //
        // This is the key fix: lerp happens here in extractRenderState() where
        // we have access to the PREVIOUS state values (state.steerWheelRot etc.
        // carried over from the last frame). BaseCarEntityModel.setupAnim() then just
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
    public void submit(S state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        poseStack.pushPose();
 
        poseStack.mulPose(Axis.YP.rotationDegrees(180f - state.yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
 
        poseStack.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
        poseStack.translate(0, -1.35F, 0);
        
 
        model.setupAnim(state);
        
        collector.submitModel(
			this.model(), state, poseStack, this.renderType(state), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
		);
 
        for (RenderLayer<S, BaseCarEntityModel> renderLayer : this.layers) {
			renderLayer.submit(
				poseStack, collector, state.lightCoords, state, state.yRot, state.xRot
			);
		}
 
        int alphaByte = Math.round(1 * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;
 
        if (state.isBreaking || state.isBurningOut || state.forwardSpeed < 0) {
            collector.order(1)
			.submitModel(
				this.model(), state, poseStack, RenderType.eyes(glowBackupTexture(state)), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);
 
        } else {
            collector.order(1)
			.submitModel(
				this.model(), state, poseStack, RenderType.eyes(glowTexture(state)), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);
 
        }

        if (state.licensePlate.is(ItemFactory.LICENSE_PLATE)) {
            renderLicensePlate(state, poseStack, collector);
        }
 
        poseStack.popPose();
 
        super.submit(state, poseStack, collector, cameraState);
    }
 
	/*protected RenderType renderType() {
		return this.model.renderType(texture());
	}*/

    protected RenderType renderType(S state) {
        if (state.wrapId != null && !state.wrapId.equals("default")) {
            ResourceLocation wrapTex = CarTextureHelper.getWrapTexture(state.carTypeId, state.wrapId);
            return this.model.renderType(wrapTex);
        }
		return this.model.renderType(texture(state));
	}

 
    protected EntityModel<CarRenderState> model() {
        return this.model;
    }

    private static final ResourceLocation PLATE_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/license_plate.png");

    private void renderLicensePlate(CarRenderState state, PoseStack pose, SubmitNodeCollector collector) {

        if (state.plateOffset == null) return;
        if (state.licensePlate.isEmpty()) return;

        pose.pushPose();

        pose.translate(
                state.plateOffset.x,
                state.plateOffset.y,
                state.plateOffset.z
        );

        ItemStackRenderState itemState = new ItemStackRenderState();

        Minecraft.getInstance()
                .getItemModelResolver()
                .updateForTopItem(
                        itemState,
                        state.licensePlate,
                        ItemDisplayContext.FIXED,
                        Minecraft.getInstance().level,
                        null,
                        0
                );

        itemState.submit(
                pose,
                collector,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                state.outlineColor
        );



        String plateText = LicensePlateItem.getPlateText(state.licensePlate);

        if (!plateText.isEmpty()) {

            Font font = Minecraft.getInstance().font;

            pose.pushPose();

            // Move text slightly OUT from plate surface
            pose.translate(0.0F, 0.0F, 0.02F);

            // Depending on plate orientation,
            // you may need this:
            pose.mulPose(Axis.YP.rotationDegrees(180f));

            // VERY IMPORTANT:
            // Text rendering units are huge.
            float scale = 0.0125f;

            pose.scale(scale, -scale, scale);

            FormattedCharSequence seq =
                    FormattedCharSequence.forward(plateText, Style.EMPTY);

            float x = -font.width(seq) / 2f;

            collector.submitText(
                pose,
                x,
                -4,
                seq,
                false,
                Font.DisplayMode.POLYGON_OFFSET,
                0xFFFFFFFF,           // text color
                0,                    // background
                state.lightCoords,    // light
                0                     // outline
        );

            pose.popPose();
        }
        pose.popPose();
    }
 
}
 