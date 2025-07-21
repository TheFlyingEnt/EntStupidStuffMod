package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.MackerelModel;
import net.ent.entstupidstuff.entity.mob.MackerelEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class MackerelRenderer extends MobEntityRenderer<MackerelEntity, MackerelModel<MackerelEntity>> {
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/mackerel.png");

   public MackerelRenderer(EntityRendererFactory.Context context) {
      super(context, new MackerelModel(context.getPart(ModModelLayers.MACKEREL)), 0.3F);
   }

   public Identifier getTexture(MackerelEntity fishEntity) {
      return TEXTURE;
   }

   protected void setupTransforms(MackerelEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
      super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
      float j = 4.3F * MathHelper.sin(0.6F * f);
      matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
      if (!fishEntity.isTouchingWater()) {
         matrixStack.translate(0.1F, 0.1F, -0.1F);
         matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
      }

   }
}
