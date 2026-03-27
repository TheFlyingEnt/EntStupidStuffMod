package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.CarRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CarEntityModel extends EntityModel<CarRenderState> {
 
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "car"), "main"
    );
 
    // ── Root parts ──
    private final ModelPart body;
 
    // ── Wheel parts (children of body) ──
    private final ModelPart wheelFrontLeft;
    private final ModelPart wheelFrontRight;
    private final ModelPart wheelRearLeft;
    private final ModelPart wheelRearRight;
 
    // ── Interior ──
    private final ModelPart steeringWheel;
 
    public CarEntityModel(ModelPart root) {
        super(root);
        this.body            = root.getChild("body");
        this.wheelFrontLeft  = this.body.getChild("wheel_front_left");
        this.wheelFrontRight = this.body.getChild("wheel_front_right");
        this.wheelRearLeft   = this.body.getChild("wheel_rear_left");
        this.wheelRearRight  = this.body.getChild("wheel_rear_right");
        this.steeringWheel   = this.body.getChild("cabin").getChild("steering_wheel");
    }
 
    // ═══════════════════════════════════════════════════════════
    //  LAYER DEFINITION
    // ═══════════════════════════════════════════════════════════
 
    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
 
        // Body — origin at entity centre
        PartDefinition body = root.addOrReplaceChild("body",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4f, -3f, -12f, 8, 4, 24),
            PartPose.offset(0f, 0f, 0f));
 
        // Cabin
        PartDefinition cabin = body.addOrReplaceChild("cabin",
            CubeListBuilder.create()
                .texOffs(0, 28)
                .addBox(-3f, -8f, -5f, 6, 5, 10),
            PartPose.offset(0f, 0f, 0f));
 
        // Steering wheel
        cabin.addOrReplaceChild("steering_wheel",
            CubeListBuilder.create()
                .texOffs(64, 0)
                .addBox(-2f, -2f, 0f, 4, 4, 1),
            PartPose.offset(0f, -5f, -3f));
 
        // Front bumper
        body.addOrReplaceChild("bumper_front",
            CubeListBuilder.create()
                .texOffs(64, 10)
                .addBox(-3f, -1f, 0f, 6, 2, 1),
            PartPose.offset(0f, 0f, -12f));
 
        // Rear bumper
        body.addOrReplaceChild("bumper_rear",
            CubeListBuilder.create()
                .texOffs(64, 16)
                .addBox(-3f, -1f, -1f, 6, 2, 1),
            PartPose.offset(0f, 0f, 12f));
 
        // Front left wheel
        body.addOrReplaceChild("wheel_front_left",
            CubeListBuilder.create()
                .texOffs(0, 44)
                .addBox(-4f, -4f, -2f, 4, 8, 4),
            PartPose.offset(4f, 0f, -8f));
 
        // Front right wheel
        body.addOrReplaceChild("wheel_front_right",
            CubeListBuilder.create()
                .texOffs(16, 44)
                .addBox(0f, -4f, -2f, 4, 8, 4),
            PartPose.offset(-4f, 0f, -8f));
 
        // Rear left wheel
        body.addOrReplaceChild("wheel_rear_left",
            CubeListBuilder.create()
                .texOffs(32, 44)
                .addBox(-4f, -4f, -2f, 4, 8, 4),
            PartPose.offset(4f, 0f, 8f));
 
        // Rear right wheel
        body.addOrReplaceChild("wheel_rear_right",
            CubeListBuilder.create()
                .texOffs(48, 44)
                .addBox(0f, -4f, -2f, 4, 8, 4),
            PartPose.offset(-4f, 0f, 8f));
 
        return LayerDefinition.create(mesh, 128, 64);
    }
 
    // ═══════════════════════════════════════════════════════════
    //  ANIMATION
    //
    //  setupAnim() now receives CarRenderState (not CarEntity).
    //  All data needed for animation must be in the render state.
    // ═══════════════════════════════════════════════════════════
 
    @Override
    public void setupAnim(CarRenderState state) {
        // ── Wheel spin ──
        float spinRad = (float) Math.toRadians(state.wheelSpin);
        this.wheelFrontLeft.xRot  = spinRad;
        this.wheelFrontRight.xRot = spinRad;
        this.wheelRearLeft.xRot   = spinRad;
        this.wheelRearRight.xRot  = spinRad;
 
        // ── Front-wheel steering lean ──
        // Derive a steer lean from the car's lateral velocity.
        // Positive lateral = sliding right → lean front wheels right.
        float steerLean = Mth.clamp(state.lateralVelocity * -2.0f, -0.45f, 0.45f);
        this.wheelFrontLeft.yRot  = steerLean;
        this.wheelFrontRight.yRot = steerLean;
 
        // ── Steering wheel rotates opposite to steer lean ──
        this.steeringWheel.zRot = -steerLean * 3f;
 
        // ── Body roll from lateral velocity ──
        float targetRoll = Mth.clamp(state.lateralVelocity * -0.8f, -0.15f, 0.15f);
        this.body.zRot = Mth.lerp(0.2f, this.body.zRot, targetRoll);
 
        // ── Drift oscillation on body ──
        if (state.isDrifting) {
            this.body.zRot += Mth.sin(state.ageInTicks * 0.3f) * 0.04f;
        }
    }
 
    // ═══════════════════════════════════════════════════════════
    //  RENDER
    // ═══════════════════════════════════════════════════════════
 
    /*@Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay, int color) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, color);
    }*/
}
