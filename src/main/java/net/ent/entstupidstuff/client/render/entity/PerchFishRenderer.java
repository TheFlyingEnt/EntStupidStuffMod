package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
import net.ent.entstupidstuff.client.render.entity.model.fish.PerchFishModel;
import net.ent.entstupidstuff.client.render.entity.state.PerchFishRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PerchFishRenderer extends MobRenderer<PerchFishEntity, PerchFishRenderState, PerchFishModel>{
    private static final ResourceLocation TEXTURE_1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/perch_dark.png");
    private static final ResourceLocation TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/perch_lighter.png");

    public PerchFishRenderer(EntityRendererProvider.Context context) {
        super(context, new PerchFishModel(context.bakeLayer(ModEntityModelLayers.PERCH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(PerchFishRenderState state) {
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
	protected void setupRotations(PerchFishRenderState state, PoseStack matrices, float bodyYaw, float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	public void extractRenderState(PerchFishEntity entity, PerchFishRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
