package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.projectile.AncientTridentEntity;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

@Environment(EnvType.CLIENT)
public class AncientTridentRenderer extends EntityRenderer<AncientTridentEntity, ThrownTridentRenderState> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident.png");
    public static final ResourceLocation GLOW_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident_glow.png");
    private final AncientTridentModel model;

    public AncientTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new AncientTridentModel(context.bakeLayer(ModEntityModelLayers.ANCIENT_TRIDENT));
    }

    @Override
    public void submit(
            ThrownTridentRenderState state,
            PoseStack matrices,
            SubmitNodeCollector renderQueue,
            CameraRenderState camera
    ) {
        matrices.pushPose();

        // Apply rotation like before
        matrices.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        matrices.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));

        // === Base texture (with optional glint) ===
        List<RenderType> layers = ItemRenderer.getFoilRenderTypes(
                this.model.renderType(TEXTURE),
                false,
                state.isFoil
        );

        for (int i = 0; i < layers.size(); i++) {
            renderQueue.order(i).submitModel(
                    this.model,
                    Unit.INSTANCE,
                    matrices,
                    layers.get(i),
                    state.lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    -1,
                    null,
                    state.outlineColor,
                    null
            );
        }

        // === Glow texture (like RenderLayer.getEyes(GLOW_TEXTURE)) ===
        renderQueue.order(0).submitModel(
                this.model,
                Unit.INSTANCE,
                matrices,
                RenderType.eyes(GLOW_TEXTURE),
                15728640, // Max light (same as before)
                OverlayTexture.NO_OVERLAY,
                -1,
                null,
                state.outlineColor,
                null
        );

        matrices.popPose();

        super.submit(state, matrices, renderQueue, camera);
    }

    @Override
    public ThrownTridentRenderState createRenderState() {
        return new ThrownTridentRenderState();
    }

    @Override
    public void extractRenderState(AncientTridentEntity entity, ThrownTridentRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.yRot = entity.getYRot(tickDelta);
        state.xRot = entity.getXRot(tickDelta);
        state.isFoil = entity.isEnchanted();
    }
   
}
