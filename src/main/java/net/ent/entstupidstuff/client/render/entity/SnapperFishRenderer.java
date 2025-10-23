package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SnapperFishModel;
import net.ent.entstupidstuff.entity.passive.SnapperFishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SnapperFishRenderer extends MobEntityRenderer<SnapperFishEntity, LivingEntityRenderState, SnapperFishModel>{
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/red_snapper.png");

    public SnapperFishRenderer(EntityRendererFactory.Context context) {
        super(context, new SnapperFishModel(context.getPart(ModEntityModelLayers.SNAPPER)), 0.3F);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
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
    
}
