package net.ent.entstupidstuff.client.render.entity;

import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.client.entity.passive.KoiEntity;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.client.render.entity.model.KoiModel;
import net.ent.entstupidstuff.client.render.entity.state.KoiEntityRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class KoiRenderer extends MobEntityRenderer<KoiEntity, KoiEntityRenderState, KoiModel> {
    //private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi.png");

    private static final Map<KoiBaseColor, Identifier> BASE_TEXTURES = Map.of(
        KoiBaseColor.WHITE, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_white.png"),
        KoiBaseColor.RED, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_red.png"),
        KoiBaseColor.YELLOW, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_yellow.png")
    );

    public KoiRenderer(EntityRendererFactory.Context context) {
       super(context, new KoiModel(context.getPart(ModEntityModelLayers.KOI)), 0.3F);
       this.addFeature(new KoiPatternFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(KoiEntityRenderState state) {
        //LegacyKoiVariant variant = entity.getVariantObject();
        KoiVariant variant = state.variant;
        return BASE_TEXTURES.get(variant.getBaseColor());
    }

    @Override
	protected void setupTransforms(KoiEntityRenderState state, MatrixStack matrices, float bodyYaw, float baseHeight) {
		super.setupTransforms(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * MathHelper.sin(0.6F * state.age);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f));
		if (!state.touchingWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
		}
	}

    @Override
	public void updateRenderState(KoiEntity entity, KoiEntityRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

    @Override
    public KoiEntityRenderState createRenderState() {
        return new KoiEntityRenderState();
    }


    
}
