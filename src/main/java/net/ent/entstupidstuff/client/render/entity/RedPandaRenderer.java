package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.RedPandaModel;
import net.ent.entstupidstuff.entity.passive.RedPandaEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RedPandaRenderer extends MobEntityRenderer<RedPandaEntity, RedPandaModel<RedPandaEntity>>{
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/red_panda.png");

    public RedPandaRenderer(EntityRendererFactory.Context context) {
		super(context, new RedPandaModel<>(context.getPart(ModModelLayers.RED_PANDA)), 0.4F);
		//this.addFeature(new FoxHeldItemFeatureRenderer(this, context.getHeldItemRenderer()));
	}

	protected void setupTransforms(RedPandaEntity redPandaEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
		super.setupTransforms(redPandaEntity, matrixStack, f, g, h, i);
		/*if (redPandaEntity.isWalking()) {
			float j = -MathHelper.lerp(h, redPandaEntity.prevPitch, redPandaEntity.getPitch());
			matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(j));
		}*/
	}

	public Identifier getTexture(RedPandaEntity redPandaEntity) {
		return TEXTURE;
	}
}
