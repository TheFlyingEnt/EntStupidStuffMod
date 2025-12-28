package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.render.entity.model.BassModel;
import net.ent.entstupidstuff.client.render.entity.state.BassRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class BassRenderer extends MobEntityRenderer<BassEntity, BassRenderState, BassModel> {
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass.png");

	private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_1.png");
	private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_2.png");
	private static final Identifier TEXTURE_3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/bass/bass_3.png");

	// TODO: New Model/Textures based on Bass

	public BassRenderer(EntityRendererFactory.Context context) {
		super(context, new BassModel(context.getPart(ModEntityModelLayers.BASS)), 0.3F);
	}

	@Override
	public Identifier getTexture(BassRenderState state) {
		return switch (state.variant) {
			case MOUTH -> TEXTURE_1;
			case RIVER -> TEXTURE_2;
			default -> TEXTURE_3;
		};
	}

	@Override
	public BassRenderState createRenderState() {
		return new BassRenderState();
	}

	@Override
	public void updateRenderState(BassEntity entity, BassRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

	@Override
	protected void setupTransforms(BassRenderState state, MatrixStack matrices, float bodyYaw,
			float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}
}
