package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
import net.ent.entstupidstuff.client.render.entity.model.PerchFishModel;
import net.ent.entstupidstuff.client.render.entity.state.PerchFishRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class PerchFishRenderer extends MobEntityRenderer<PerchFishEntity, PerchFishRenderState, PerchFishModel>{
    private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/perch/perch_dark.png");
    private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/perch/perch_lighter.png");

    public PerchFishRenderer(EntityRendererFactory.Context context) {
        super(context, new PerchFishModel(context.getPart(ModEntityModelLayers.PERCH)), 0.3F);
    }

    @Override
    public Identifier getTexture(PerchFishRenderState state) {
      	return switch (state.variant) {
			case DARK -> TEXTURE_1;
			case LIGHT -> TEXTURE_2;
			default -> TEXTURE_2;
		};
    }

	@Override
	public PerchFishRenderState createRenderState() {
		return new PerchFishRenderState();
	}

    @Override
	protected void setupTransforms(PerchFishRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}

	@Override
	public void updateRenderState(PerchFishEntity entity, PerchFishRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
