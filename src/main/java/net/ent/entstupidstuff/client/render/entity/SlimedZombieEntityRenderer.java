package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.entity.mob.SlimedZombieEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SlimedZombieEntityRenderer extends ZombieBaseEntityRenderer<SlimedZombieEntity, SlimedZombieModel<SlimedZombieEntity>>{
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed.png");

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public SlimedZombieEntityRenderer(EntityRendererFactory.Context context) {
      super(context, new SlimedZombieModel(context.getPart(ModModelLayers.ZOMBIE_SLIMED)), new SlimedZombieModel(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new SlimedZombieModel(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)));
      this.addFeature(new SlimedZombieOverlayFeatureRenderer(this, context.getModelLoader()));
   }

   public Identifier getTexture(ZombieEntity zombieEntity) {
      return TEXTURE;
   }

   protected void setupTransforms(SlimedZombieEntity drownedEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
      super.setupTransforms(drownedEntity, matrixStack, f, g, h, i);
      float j = drownedEntity.getLeaningPitch(h);
      if (j > 0.0F) {
         float k = -10.0F - drownedEntity.getPitch();
         float l = MathHelper.lerp(j, 0.0F, k);
         matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(l), 0.0F, drownedEntity.getHeight() / 2.0F / i, 0.0F);
      }
   }

   @Override
   protected RenderLayer getRenderLayer(SlimedZombieEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
      return RenderLayer.getEntityTranslucent(this.getTexture(entity));
   }
}
