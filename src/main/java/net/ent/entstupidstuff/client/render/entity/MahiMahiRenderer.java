package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.client.render.entity.model.fish.MahiMahiModel;
import net.ent.entstupidstuff.client.render.entity.state.MahiMahiRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MahiMahiRenderer extends MobRenderer<MahiMahiEntity, MahiMahiRenderState, MahiMahiModel>{
    private static final ResourceLocation TEXTURE_1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/mahimahi_blue.png");
    private static final ResourceLocation TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/mahimahi_green.png");

    public MahiMahiRenderer(EntityRendererProvider.Context context) {
        super(context, new MahiMahiModel(context.bakeLayer(ModEntityModelLayers.MAHIMAHI)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(MahiMahiRenderState state) {
        return switch (state.variant) {
			case BLUE -> TEXTURE_1;
			case GREEN -> TEXTURE_2;
			default -> TEXTURE_2;
		};
    }

    @Override
	protected void setupRotations(MahiMahiRenderState state, PoseStack matrices, float bodyYaw,
			float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

    @Override
	public void extractRenderState(MahiMahiEntity entity, MahiMahiRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

    @Override
    public MahiMahiRenderState createRenderState() {
        return new MahiMahiRenderState();
    } 
}
