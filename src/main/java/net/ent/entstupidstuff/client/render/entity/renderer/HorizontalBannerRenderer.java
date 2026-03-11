package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Set;

import net.ent.entstupidstuff.block.base.HorizontalBannerBlock;
import net.ent.entstupidstuff.block.base.HorizontalWallBannerBlock;
import net.ent.entstupidstuff.block.blockentity.HorizontalBannerBlockEntity;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.WarBannerFlagModel;
import net.ent.entstupidstuff.client.render.entity.model.WarBannerModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BannerRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class HorizontalBannerRenderer implements BlockEntityRenderer<HorizontalBannerBlockEntity, BannerRenderState> {
	private static final int MAX_PATTERNS = 16;
	private static final float SIZE = 0.6666667F;
	private final MaterialSet materials;
	private final WarBannerModel standingModel;
	private final WarBannerModel wallModel;
	private final WarBannerFlagModel standingFlagModel;
	private final WarBannerFlagModel wallFlagModel;

	public HorizontalBannerRenderer(BlockEntityRendererProvider.Context context) {
		this(context.entityModelSet(), context.materials());
	}

	public HorizontalBannerRenderer(SpecialModelRenderer.BakingContext bakingContext) {
		this(bakingContext.entityModelSet(), bakingContext.materials());
	}

	public HorizontalBannerRenderer(EntityModelSet entityModelSet, MaterialSet materialSet) {
		this.materials = materialSet;
		this.standingModel = new WarBannerModel(entityModelSet.bakeLayer(ModEntityModelLayers.WAR_STANDING_BANNER));
		this.wallModel = new WarBannerModel(entityModelSet.bakeLayer(ModEntityModelLayers.WAR_WALL_BANNER));
		this.standingFlagModel = new WarBannerFlagModel(entityModelSet.bakeLayer(ModEntityModelLayers.WAR_STANDING_BANNER_FLAG));
		this.wallFlagModel = new WarBannerFlagModel(entityModelSet.bakeLayer(ModEntityModelLayers.WAR_WALL_BANNER_FLAG));
	}

	public BannerRenderState createRenderState() {
		return new BannerRenderState();
	}

	public void extractRenderState(
		HorizontalBannerBlockEntity HorizontalBannerBlockEntity,
		BannerRenderState bannerRenderState,
		float f,
		Vec3 vec3,
		@Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay
	) {
		BlockEntityRenderer.super.extractRenderState(HorizontalBannerBlockEntity, bannerRenderState, f, vec3, crumblingOverlay);
		bannerRenderState.baseColor = HorizontalBannerBlockEntity.getBaseColor();
		bannerRenderState.patterns = HorizontalBannerBlockEntity.getPatterns();
		BlockState blockState = HorizontalBannerBlockEntity.getBlockState();
		if (blockState.getBlock() instanceof HorizontalBannerBlock) {
			bannerRenderState.angle = -RotationSegment.convertToDegrees((Integer)blockState.getValue(HorizontalBannerBlock.ROTATION));
			bannerRenderState.standing = true;
		} else {
			bannerRenderState.angle = -((Direction)blockState.getValue(HorizontalWallBannerBlock.FACING)).toYRot();
			bannerRenderState.standing = false;
		}

		long l = HorizontalBannerBlockEntity.getLevel() != null ? HorizontalBannerBlockEntity.getLevel().getGameTime() : 0L;
		BlockPos blockPos = HorizontalBannerBlockEntity.getBlockPos();
		bannerRenderState.phase = ((float)Math.floorMod(blockPos.getX() * 7 + blockPos.getY() * 9 + blockPos.getZ() * 13 + l, 100L) + f) / 100.0F;
	}

	public void submit(BannerRenderState bannerRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		WarBannerModel WarBannerModel;
		WarBannerFlagModel WarBannerFlagModel;
		if (bannerRenderState.standing) {
			WarBannerModel = this.standingModel;
			WarBannerFlagModel = this.standingFlagModel;
		} else {
			WarBannerModel = this.wallModel;
			WarBannerFlagModel = this.wallFlagModel;
		}

		submitBanner(
			this.materials,
			poseStack,
			submitNodeCollector,
			bannerRenderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
			bannerRenderState.angle,
			WarBannerModel,
			WarBannerFlagModel,
			bannerRenderState.phase,
			bannerRenderState.baseColor,
			bannerRenderState.patterns,
			bannerRenderState.breakProgress,
			0
		);
	}

	public void submitSpecial(
		PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, DyeColor dyeColor, BannerPatternLayers bannerPatternLayers, int k, float animTime
	) {
        float phase = (animTime % 20.0F) / 20.0F; // always 0.0 - 1.0
        //this.standingFlagModel.setupAnim(phase);

        

        //poseStack.pushPose();
        //poseStack.mulPose(Axis.YP.rotationDegrees(180));
        //poseStack.scale(2.0F, 2.0F, 2.0F);
        //poseStack.mulPose(Axis.XP.rotationDegrees(5));
        //poseStack.mulPose(Axis.ZP.rotationDegrees(-5));
        //poseStack.translate(-0.2, 0, -0.2);


		submitBanner(
			this.materials, poseStack, submitNodeCollector, i, j, 0.0F, this.standingModel, this.standingFlagModel, phase /*0.0F*/, dyeColor, bannerPatternLayers, null, k
		);

        //poseStack.popPose();

	}

    public void submitSpecial(
		PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, DyeColor dyeColor, BannerPatternLayers bannerPatternLayers, int k
	) {
		submitBanner(
			this.materials, poseStack, submitNodeCollector, i, j, 0.0F, this.standingModel, this.standingFlagModel, 0.0F, dyeColor, bannerPatternLayers, null, k
		);
	}

	private static void submitBanner(
		MaterialSet materialSet,
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int i,
		int j,
		float f,
		WarBannerModel WarBannerModel,
		WarBannerFlagModel WarBannerFlagModel,
		float g,
		DyeColor dyeColor,
		BannerPatternLayers bannerPatternLayers,
		@Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
		int k
	) {
		poseStack.pushPose();
		poseStack.translate(0.5F, 0.0F, 0.5F);
		poseStack.mulPose(Axis.YP.rotationDegrees(f));
		poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
		Material material = ModelBakery.BANNER_BASE;
		submitNodeCollector.submitModel(
			WarBannerModel, Unit.INSTANCE, poseStack, material.renderType(RenderType::entitySolid), i, j, -1, materialSet.get(material), k, crumblingOverlay
		);
		submitPatterns(
			materialSet, poseStack, submitNodeCollector, i, j, WarBannerFlagModel, g, material, true, dyeColor, bannerPatternLayers, false, crumblingOverlay, k
		);
		poseStack.popPose();
	}

	public static <S> void submitPatterns(
		MaterialSet materialSet,
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int i,
		int j,
		Model<S> model,
		S object,
		Material material,
		boolean bl,
		DyeColor dyeColor,
		BannerPatternLayers bannerPatternLayers,
		boolean bl2,
		@Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
		int k
	) {
		submitNodeCollector.submitModel(
			model, object, poseStack, material.renderType(RenderType::entitySolid), i, j, -1, materialSet.get(material), k, crumblingOverlay
		);
		if (bl2) {
			submitNodeCollector.submitModel(model, object, poseStack, RenderType.entityGlint(), i, j, -1, materialSet.get(material), 0, crumblingOverlay);
		}

		submitPatternLayer(materialSet, poseStack, submitNodeCollector, i, j, model, object, bl ? Sheets.BANNER_BASE : Sheets.SHIELD_BASE, dyeColor, crumblingOverlay);

		for (int l = 0; l < 16 && l < bannerPatternLayers.layers().size(); l++) {
			BannerPatternLayers.Layer layer = (BannerPatternLayers.Layer)bannerPatternLayers.layers().get(l);
			Material material2 = bl ? Sheets.getBannerMaterial(layer.pattern()) : Sheets.getShieldMaterial(layer.pattern());
			submitPatternLayer(materialSet, poseStack, submitNodeCollector, i, j, model, object, material2, layer.color(), null);
		}
	}

	private static <S> void submitPatternLayer(
		MaterialSet materialSet,
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int i,
		int j,
		Model<S> model,
		S object,
		Material material,
		DyeColor dyeColor,
		@Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay
	) {
		int k = dyeColor.getTextureDiffuseColor();
		submitNodeCollector.submitModel(
			model, object, poseStack, material.renderType(RenderType::entityNoOutline), i, j, k, materialSet.get(material), 0, crumblingOverlay
		);
	}

	public void getExtents(Set<Vector3f> set) {
		PoseStack poseStack = new PoseStack();
		poseStack.translate(0.5F, 0.0F, 0.5F);
		poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
		this.standingModel.root().getExtentsForGui(poseStack, set);
		this.standingFlagModel.setupAnim(0.0F);
		this.standingFlagModel.root().getExtentsForGui(poseStack, set);
	}
}