package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.entity.mob.ZebraFishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ZebraFishRenderer extends MobEntityRenderer<ZebraFishEntity, ZebraFishModel<ZebraFishEntity>> {
   private static final Identifier TEXTURE_NAVY = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_2.png");
   private static final Identifier TEXTURE_BLUE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_3.png");
   private static final Identifier TEXTURE_NAVY_LEPORD = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_2_leopard.png");
   private static final Identifier TEXTURE_BLUE_LEPORD  = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_3_leopard.png");

   public ZebraFishRenderer(EntityRendererFactory.Context context) {
      super(context, new ZebraFishModel(context.getPart(ModModelLayers.ZEBRA_FISH)), 0.3F);
   }

   public Identifier getTexture(ZebraFishEntity fishEntity) {
      //return fishEntity.getVariant() == ZebraFishEntity.Variant.NAVY ? TEXTURE_NAVY : TEXTURE_BLUE;
      if (fishEntity.getVariant() == ZebraFishEntity.Variant.NAVY) {
			return TEXTURE_NAVY;
		} else if (fishEntity.getVariant() == ZebraFishEntity.Variant.BLUE) {
			return TEXTURE_BLUE;
		} else if (fishEntity.getVariant() == ZebraFishEntity.Variant.LEPORD_NAVY) {
			return TEXTURE_NAVY_LEPORD;
		} else {
			return TEXTURE_BLUE_LEPORD;
		}
   }

   protected void setupTransforms(ZebraFishEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
      super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
      float j = 4.3F * MathHelper.sin(0.6F * f);
      matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
      if (!fishEntity.isTouchingWater()) {
         matrixStack.translate(0.1F, 0.1F, -0.1F);
         matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
      }

   }
}
