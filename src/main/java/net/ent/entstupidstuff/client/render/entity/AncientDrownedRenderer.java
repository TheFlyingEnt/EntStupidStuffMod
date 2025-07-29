package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class AncientDrownedRenderer extends ZombieBaseEntityRenderer<DrownedEntity, AncientDrownedModel<DrownedEntity>>{
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned.png");
   private static final Identifier GLOW_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned_e.png");

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public AncientDrownedRenderer(EntityRendererFactory.Context context) {
      super(context, new AncientDrownedModel(context.getPart(ModModelLayers.ANCIENT_DROWNED)), new AncientDrownedModel(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new AncientDrownedModel(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)));
      this.addFeature(new AncientDrownedOverlayFeatureRenderer(this, context.getModelLoader()));
   }

   public Identifier getTexture(ZombieEntity zombieEntity) {
      return TEXTURE;
   }

   protected void setupTransforms(DrownedEntity drownedEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
      super.setupTransforms(drownedEntity, matrixStack, f, g, h, i);
      float j = drownedEntity.getLeaningPitch(h);
      if (j > 0.0F) {
         float k = -10.0F - drownedEntity.getPitch();
         float l = MathHelper.lerp(j, 0.0F, k);
         matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(l), 0.0F, drownedEntity.getHeight() / 2.0F / i, 0.0F);
      }
   }

   /*@Override
   public void render(DrownedEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
      VertexConsumer glowConsumer = vertexConsumers.getBuffer(RenderLayer.getEyes(GLOW_TEXTURE));
        this.model.render(matrices, glowConsumer, 15728640, OverlayTexture.DEFAULT_UV); // 15728640 = maximum light
      super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
   }*/
}
