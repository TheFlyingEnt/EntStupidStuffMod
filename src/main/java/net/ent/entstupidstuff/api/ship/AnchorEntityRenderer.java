package net.ent.entstupidstuff.api.ship;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.phys.Vec3;

public class AnchorEntityRenderer extends EntityRenderer<AnchorEntity, AnchorRenderState> {

    private static final ResourceLocation ANCHOR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/anchor.png");

    // Use the same texture as the anchor — known to load correctly.
    // Block textures (minecraft:textures/block/chain.png) don't work for entity rendering
    // because they live in the block atlas, not the entity texture system.
    
    private static final ResourceLocation CHAIN_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/item/iron_chain.png");

    private final AnchorModel model;
    private final Model.Simple chainLinkModel;
 
    /** Scale of each chain link model. */
    private static final float LINK_SCALE = 1.0f;
 
    public AnchorEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new AnchorModel(ctx.bakeLayer(ModEntityModelLayers.ANCHOR));
        this.chainLinkModel = new Model.Simple(
            ctx.bakeLayer(ModEntityModelLayers.CHAIN_LINK),
            id -> RenderType.entityCutoutNoCull(CHAIN_TEXTURE));
    }
 
    @Override
    public AnchorRenderState createRenderState() {
        return new AnchorRenderState();
    }
 
    @Override
    public void extractRenderState(AnchorEntity e, AnchorRenderState st, float partialTick) {
        super.extractRenderState(e, st, partialTick);
 
        CustomBoatEntity ship = e.getShip();
        if (ship != null) {
            // Interpolated positions for smooth chain rendering
            double ax = Mth.lerp(partialTick, e.xo, e.getX());
            double ay = Mth.lerp(partialTick, e.yo, e.getY());
            double az = Mth.lerp(partialTick, e.zo, e.getZ());
            double sx = Mth.lerp(partialTick, ship.xo, ship.getX());
            double sy = Mth.lerp(partialTick, ship.yo, ship.getY());
            double sz = Mth.lerp(partialTick, ship.zo, ship.getZ());
 
            // Vector from anchor (+ small offset up) to ship's chain attach point
            st.toShip = new Vec3(sx - ax, (sy + 0.8) - (ay + 0.3), sz - az);
 
            double dist = st.toShip.length();
            st.chainLinks = Mth.clamp((int)(dist * 4), 2, 60);
 
            // Droop: taut chain still sags noticeably, slack chain hangs deep
            double slack = Math.max(0, AnchorEntity.CHAIN_LENGTH - dist);
            st.chainDroop = 1.5 + slack * 0.3;
        } else {
            st.toShip = Vec3.ZERO;
            st.chainLinks = 0;
        }
    }
 
    @Override
    public void submit(AnchorRenderState st, PoseStack pose, SubmitNodeCollector collector, CameraRenderState cam) {
        // ── Render the anchor model ────────────────────────────────────
        pose.pushPose();
        //pose.scale(0.9f, 0.9f, 0.9f);
        pose.scale(1.25f, 1.25f, 1.25f);
        this.model.setupAnim(st);
        collector.submitModel(
            this.model, st, pose,
            RenderType.entityCutoutNoCull(ANCHOR_TEXTURE),
            st.lightCoords, OverlayTexture.NO_OVERLAY, st.outlineColor, null);
        pose.popPose();
 
        // ── Render the chain links ─────────────────────────────────────
        if (st.chainLinks <= 0) return;
 
        Vec3 to = st.toShip;
        int links = st.chainLinks;
        double droop = st.chainDroop;
 
        // Yaw: horizontal direction from anchor to ship
        float yaw = (float) Math.atan2(to.x, to.z);
 
        RenderType chainType = this.chainLinkModel.renderType(CHAIN_TEXTURE);
 
        for (int i = 0; i <= links; i++) {
            double t = i / (double) links;
 
            // Position along the catenary
            double cx = t * to.x;
            double cz = t * to.z;
            double cy = t * to.y - Math.sin(t * Math.PI) * droop;
 
            // Direction to next point (for orientation)
            double t1 = Math.min(1.0, (i + 1) / (double) links);
            double nx = t1 * to.x;
            double nz = t1 * to.z;
            double ny = t1 * to.y - Math.sin(t1 * Math.PI) * droop;
            double dx = nx - cx, dy = ny - cy, dz = nz - cz;
 
            // Yaw: horizontal direction of the chain at this point
            yaw = (float) Math.atan2(dx, dz);
 
            // Pitch: tilt from vertical. 0 = hanging straight down, π/2 = horizontal
            double dh = Math.sqrt(dx * dx + dz * dz);
            float pitch = -(float) Math.atan2(dh, -dy);
 
            pose.pushPose();
            pose.translate(cx, cy + 0.3, cz);   // +0.3 = anchor attach offset
 
            // Face along the chain direction
            pose.mulPose(Axis.YP.rotation(yaw));
            pose.mulPose(Axis.XP.rotation(pitch));
 
            // Alternate links 90° for the interlocking chain pattern
            if (i % 2 == 1) {
                pose.mulPose(Axis.YP.rotationDegrees(90f));
            }
 
            pose.scale(LINK_SCALE, LINK_SCALE, LINK_SCALE);
 
            collector.submitModel(
                this.chainLinkModel, Unit.INSTANCE, pose,
                chainType, st.lightCoords, OverlayTexture.NO_OVERLAY,
                st.outlineColor, null);
 
            pose.popPose();
        }
    }
}
