package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.client.render.entity.model.MahiMahiModel;
import net.ent.entstupidstuff.client.render.entity.state.MahiMahiRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class MahiMahiRenderer extends MobEntityRenderer<MahiMahiEntity, MahiMahiRenderState, MahiMahiModel>{
    private static final Identifier TEXTURE_1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/mahimahi/mahimahi_blue.png");
    private static final Identifier TEXTURE_2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/mahimahi/mahimahi_green.png");

    public MahiMahiRenderer(EntityRendererFactory.Context context) {
        super(context, new MahiMahiModel(context.getPart(ModEntityModelLayers.MAHIMAHI)), 0.3F);
    }

    @Override
    public Identifier getTexture(MahiMahiRenderState state) {
        return switch (state.variant) {
			case BLUE -> TEXTURE_1;
			case GREEN -> TEXTURE_2;
			default -> TEXTURE_2;
		};
    }

    @Override
	protected void setupTransforms(MahiMahiRenderState state, MatrixStack matrices, float bodyYaw,
			float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}

    @Override
	public void updateRenderState(MahiMahiEntity entity, MahiMahiRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

    @Override
    public MahiMahiRenderState createRenderState() {
        return new MahiMahiRenderState();
    } 
}
