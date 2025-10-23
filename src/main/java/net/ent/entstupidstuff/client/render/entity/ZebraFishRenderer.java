package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.client.render.entity.state.ZebraFishEntityRenderState;
import net.ent.entstupidstuff.entity.passive.ZebraFishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ZebraFishRenderer extends MobEntityRenderer<ZebraFishEntity, ZebraFishEntityRenderState, ZebraFishModel> {
   private static final Identifier TEXTURE_NAVY = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_2.png");
   private static final Identifier TEXTURE_BLUE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_3.png");
   private static final Identifier TEXTURE_NAVY_LEPORD = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_2_leopard.png");
   private static final Identifier TEXTURE_BLUE_LEPORD  = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zebra_fish/zebra_fish_3_leopard.png");

   public ZebraFishRenderer(EntityRendererFactory.Context context) {
      super(context, new ZebraFishModel(context.getPart(ModEntityModelLayers.ZEBRA_FISH)), 0.3F);
   }

   @Override
	protected void setupTransforms(ZebraFishEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}

	@Override
	public Identifier getTexture(ZebraFishEntityRenderState state) {
		return switch (state.variant) {
			case STRIPED_NAVY -> TEXTURE_NAVY;
			case STRIPED_BLUE -> TEXTURE_BLUE;
			case LEPORD_NAVY -> TEXTURE_NAVY_LEPORD;
			case LEPORD_BLUE -> TEXTURE_BLUE_LEPORD;
			default -> TEXTURE_BLUE_LEPORD;
		};
	}

	@Override
	public ZebraFishEntityRenderState createRenderState() {
		return new ZebraFishEntityRenderState();
	}

	@Override
	public void updateRenderState(ZebraFishEntity entity, ZebraFishEntityRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
