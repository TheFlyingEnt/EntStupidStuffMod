package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.client.entity.passive.KoiEntity;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.client.render.entity.model.fish.KoiModel;
import net.ent.entstupidstuff.client.render.entity.state.KoiEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class KoiRenderer extends MobRenderer<KoiEntity, KoiEntityRenderState, KoiModel> {
    //private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi.png");

    private static final Map<KoiBaseColor, ResourceLocation> BASE_TEXTURES = Map.of(
        KoiBaseColor.WHITE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/koi/base_white.png"),
        KoiBaseColor.RED, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/koi/base_red.png"),
        KoiBaseColor.YELLOW, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/koi/base_yellow.png")
    );

    public KoiRenderer(EntityRendererProvider.Context context) {
       super(context, new KoiModel(context.bakeLayer(ModEntityModelLayers.KOI)), 0.3F);
       this.addLayer(new KoiPatternFeatureRenderer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(KoiEntityRenderState state) {
        //LegacyKoiVariant variant = entity.getVariantObject();
        KoiVariant variant = state.variant;
        return BASE_TEXTURES.get(variant.getBaseColor());
    }

    @Override
	protected void setupRotations(KoiEntityRenderState state, PoseStack matrices, float bodyYaw, float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

    @Override
	public void extractRenderState(KoiEntity entity, KoiEntityRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

    @Override
    public KoiEntityRenderState createRenderState() {
        return new KoiEntityRenderState();
    }


    
}
