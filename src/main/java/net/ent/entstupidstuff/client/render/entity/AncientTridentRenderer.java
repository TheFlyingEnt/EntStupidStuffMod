package net.ent.entstupidstuff.client.render.entity;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.entity.projectile.AncientTridentEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.TridentEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class AncientTridentRenderer extends EntityRenderer<AncientTridentEntity, TridentEntityRenderState> {
   public static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident.png");
   public static final Identifier GLOW_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident_e.png");
   private final AncientTridentModel model;

   public AncientTridentRenderer(EntityRendererFactory.Context context) {
      super(context);
      this.model = new AncientTridentModel(context.getPart(ModEntityModelLayers.ANCIENT_TRIDENT));
   }

   @Override
   public void render(
         TridentEntityRenderState state,
         MatrixStack matrices,
         OrderedRenderCommandQueue renderQueue,
         CameraRenderState camera
   ) {
      matrices.push();

      // Apply rotation like before
      matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0F));
      matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch + 90.0F));

      // === Base texture (with optional glint) ===
      List<RenderLayer> layers = ItemRenderer.getGlintRenderLayers(
               this.model.getLayer(TEXTURE),
               false,
               state.enchanted
      );

      for (int i = 0; i < layers.size(); i++) {
         renderQueue.getBatchingQueue(i).submitModel(
                  this.model,
                  Unit.INSTANCE,
                  matrices,
                  layers.get(i),
                  state.light,
                  OverlayTexture.DEFAULT_UV,
                  -1,
                  null,
                  state.outlineColor,
                  null
         );
      }

      // === Glow texture (like RenderLayer.getEyes(GLOW_TEXTURE)) ===
      renderQueue.getBatchingQueue(0).submitModel(
               this.model,
               Unit.INSTANCE,
               matrices,
               RenderLayer.getEyes(GLOW_TEXTURE),
               15728640, // Max light (same as before)
               OverlayTexture.DEFAULT_UV,
               -1,
               null,
               state.outlineColor,
               null
      );

      matrices.pop();

      super.render(state, matrices, renderQueue, camera);
   }

   @Override
   public TridentEntityRenderState createRenderState() {
      return new TridentEntityRenderState();
   }

   @Override
   public void updateRenderState(AncientTridentEntity entity, TridentEntityRenderState state, float tickDelta) {
      super.updateRenderState(entity, state, tickDelta);
      state.yaw = entity.getLerpedYaw(tickDelta);
      state.pitch = entity.getLerpedPitch(tickDelta);
      state.enchanted = entity.isEnchanted();
   }
   
}
