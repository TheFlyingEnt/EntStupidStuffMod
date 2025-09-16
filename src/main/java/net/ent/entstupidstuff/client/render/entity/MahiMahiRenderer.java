package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.MahiMahiModel;
import net.ent.entstupidstuff.entity.passive.MahiMahiEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class MahiMahiRenderer extends MobEntityRenderer<MahiMahiEntity, MahiMahiModel<MahiMahiEntity>>{
    private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/mahimahi/mahimahi_blue.png");
    private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/mahimahi/mahimahi_green.png");

    public MahiMahiRenderer(EntityRendererFactory.Context context) {
        super(context, new MahiMahiModel(context.getPart(ModEntityModelLayers.MAHIMAHI)), 0.3F);
    }

    @Override
    public Identifier getTexture(MahiMahiEntity fishEntity) {
      if (fishEntity.getVariant() == MahiMahiEntity.Variant.BLUE) {
			return TEXTURE_1;
		} else {
			return TEXTURE_2;
		} 
    }

    protected void setupTransforms(MahiMahiEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
        float j = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
        if (!fishEntity.isTouchingWater()) {
            matrixStack.translate(0.1F, 0.1F, -0.1F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }

    }  
}
