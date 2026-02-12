package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.render.entity.model.fish.BassModel;
import net.ent.entstupidstuff.client.render.entity.state.BassRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BassRenderer extends MobRenderer<BassEntity, BassRenderState, BassModel> {
	private static final ResourceLocation TEXTURE_1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/bass_1.png");
	private static final ResourceLocation TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/bass_2.png");
	private static final ResourceLocation TEXTURE_3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/bass_3.png");

	// TODO: New Model/Textures based on Bass

	public BassRenderer(EntityRendererProvider.Context context) {
		super(context, new BassModel(context.bakeLayer(ModEntityModelLayers.BASS)), 0.3F);
	}

	@Override
	public ResourceLocation getTextureLocation(BassRenderState state) {
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
	public void extractRenderState(BassEntity entity, BassRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

	@Override
	protected void setupRotations(BassRenderState state, PoseStack matrices, float bodyYaw,
			float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}
