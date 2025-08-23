package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.entity.mob.FrostbittenZombieEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FrostbittenZombieOverlay <T extends FrostbittenZombieEntity> extends FeatureRenderer<T, DrownedEntityModel<T>> {
    
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite_o.png");
   private final DrownedEntityModel<T> model;

   @SuppressWarnings({ "unchecked", "rawtypes" })
   public FrostbittenZombieOverlay(FeatureRendererContext<T, DrownedEntityModel<T>> context, EntityModelLoader loader) {
      super(context);
      this.model = new DrownedEntityModel(loader.getModelPart(ModModelLayers.ZOMBIE_FROSTBITTEN_OUTER));
   }

   public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T drownedEntity, float f, float g, float h, float j, float k, float l) {
      render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, drownedEntity, f, g, j, k, l, h, -1);
   }
}