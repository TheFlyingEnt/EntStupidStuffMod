package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.PerchFishModel;
import net.ent.entstupidstuff.entity.passive.PerchFishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class PerchFishRenderer extends MobEntityRenderer<PerchFishEntity, PerchFishModel<PerchFishEntity>>{
    private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/perch/perch_dark.png");
    private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/perch/perch_lighter.png");

    public PerchFishRenderer(EntityRendererFactory.Context context) {
        super(context, new PerchFishModel(context.getPart(ModEntityModelLayers.PERCH)), 0.3F);
    }

    @Override
    public Identifier getTexture(PerchFishEntity fishEntity) {
      if (fishEntity.getVariant() == PerchFishEntity.Variant.DARK) {
			return TEXTURE_1;
		} else {
			return TEXTURE_2;
		} 
    }

    protected void setupTransforms(PerchFishEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
        float j = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
        if (!fishEntity.isTouchingWater()) {
            matrixStack.translate(0.1F, 0.1F, -0.1F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }

    }  
}
