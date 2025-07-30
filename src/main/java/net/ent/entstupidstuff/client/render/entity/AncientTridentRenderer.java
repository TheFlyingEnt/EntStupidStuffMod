package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.entity.projectile.AncientTridentEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class AncientTridentRenderer extends EntityRenderer<AncientTridentEntity> {
   public static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident.png");
   public static final Identifier GLOW_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident_e.png");
   private final AncientTridentModel model;

   public AncientTridentRenderer(EntityRendererFactory.Context context) {
      super(context);
      this.model = new AncientTridentModel(context.getPart(ModModelLayers.ANCIENT_TRIDENT));
   }

   @Override
   public void render(AncientTridentEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) { //This Does Work
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));

        // Base texture
        VertexConsumer baseConsumer = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumers, this.model.getLayer(TEXTURE), false, entity.isEnchanted()
        );
        this.model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV);

        // Glow texture
        VertexConsumer glowConsumer = vertexConsumers.getBuffer(RenderLayer.getEyes(GLOW_TEXTURE));
        this.model.render(matrices, glowConsumer, 15728640, OverlayTexture.DEFAULT_UV); // 15728640 = maximum light

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

   @Override
   public Identifier getTexture(AncientTridentEntity tridentEntity) {
      return TEXTURE;
   }
}
