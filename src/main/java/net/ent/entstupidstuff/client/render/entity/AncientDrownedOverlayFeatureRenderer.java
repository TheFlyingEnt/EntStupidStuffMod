package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.util.Identifier;

public class AncientDrownedOverlayFeatureRenderer <T extends DrownedEntity> extends FeatureRenderer<T, AncientDrownedModel<T>> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned_outer_layer.png");
   private final AncientDrownedModel<T> model;

   @SuppressWarnings({ "unchecked", "rawtypes" })
   public AncientDrownedOverlayFeatureRenderer(FeatureRendererContext<T, AncientDrownedModel<T>> context, EntityModelLoader loader) {
      super(context);
      this.model = new AncientDrownedModel(loader.getModelPart(ModModelLayers.ANCIENT_DROWNED_OUTER));
   }

   public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T drownedEntity, float f, float g, float h, float j, float k, float l) {
      render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, drownedEntity, f, g, j, k, l, h, -1);
   }
}
