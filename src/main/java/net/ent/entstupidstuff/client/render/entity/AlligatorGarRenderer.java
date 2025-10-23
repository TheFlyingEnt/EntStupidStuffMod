package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AlligatorGarModel;
import net.ent.entstupidstuff.entity.passive.AlligatorGarEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class AlligatorGarRenderer extends MobEntityRenderer<AlligatorGarEntity, LivingEntityRenderState, AlligatorGarModel> {
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/alligator_gar.png");

   public AlligatorGarRenderer(EntityRendererFactory.Context context) {
      super(context, new AlligatorGarModel(context.getPart(ModEntityModelLayers.ALLIGATOR_GAR)), 0.3F);
   }

   public Identifier getTexture(LivingEntityRenderState fishEntity) {
      return TEXTURE;
   }

   @Override
	protected void setupTransforms(LivingEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}

   @Override
   public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
