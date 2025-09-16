package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.BassModel;
import net.ent.entstupidstuff.entity.passive.BassEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class BassRenderer extends MobEntityRenderer<BassEntity, BassModel<BassEntity>> {
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass.png");

   private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_1.png");
   private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_2.png");
   private static final Identifier TEXTURE_3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_3.png");

   //TODO: New Model/Textures based on Bass

   public BassRenderer(EntityRendererFactory.Context context) {
      super(context, new BassModel(context.getPart(ModEntityModelLayers.BASS)), 0.3F);
   }

   public Identifier getTexture(BassEntity fishEntity) {
      if (fishEntity.getVariant() == BassEntity.Variant.MOUTH) {
			return TEXTURE_1;
		} else if (fishEntity.getVariant() == BassEntity.Variant.RIVER) {
			return TEXTURE_2;
		} else {
			return TEXTURE_3;
		}
   }

   protected void setupTransforms(BassEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
      super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
      float j = 4.3F * MathHelper.sin(0.6F * f);
      matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
      if (!fishEntity.isTouchingWater()) {
         matrixStack.translate(0.1F, 0.1F, -0.1F);
         matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
      }

   }
}
