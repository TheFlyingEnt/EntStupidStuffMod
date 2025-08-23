package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.entity.mob.SlimedZombieEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SlimedZombieOverlayFeatureRenderer<T extends SlimedZombieEntity> extends FeatureRenderer<T, SlimedZombieModel<T>> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed_outer_layer.png");
   private final SlimedZombieModel<T> model;

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public SlimedZombieOverlayFeatureRenderer(FeatureRendererContext<T, SlimedZombieModel<T>> context, EntityModelLoader loader) {
      super(context);
      this.model = new SlimedZombieModel(loader.getModelPart(ModModelLayers.ZOMBIE_SLIMED_OUTER));
   }

   @Override
   public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
      //render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, OverlayTexture.DEFAULT_UV);
      RenderLayer layer = RenderLayer.getEntityTranslucentCull(SKIN);
      render(this.getContextModel(), this.model, SKIN, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
   }

}
