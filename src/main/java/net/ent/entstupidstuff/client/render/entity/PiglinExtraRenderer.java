package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;

public class PiglinExtraRenderer extends HumanoidMobRenderer<AbstractPiglin, PiglinRenderState, PiglinModel> {

	private static final ResourceLocation PIGLIN_WARRIOR_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/piglin/piglin_warrior.png");
	public static final CustomHeadLayer.Transforms HEAD_TRANSFORMATION = new CustomHeadLayer.Transforms(0.0F, 0.0F, 1.0019531F);

    public PiglinExtraRenderer(EntityRendererProvider.Context ctx, ModelLayerLocation mainLayer, ModelLayerLocation babyMainLayer, ArmorModelSet<ModelLayerLocation> equipmentModelData, ArmorModelSet<ModelLayerLocation> equipmentModelData2) {
		super(ctx, new PiglinModel(ctx.bakeLayer(mainLayer)), new PiglinModel(ctx.bakeLayer(babyMainLayer)), 0.5F, HEAD_TRANSFORMATION);
		this.addLayer(
			new HumanoidArmorLayer<>(
				this,
				ArmorModelSet.bake(equipmentModelData, ctx.getModelSet(), PiglinModel::new),
				ArmorModelSet.bake(equipmentModelData2, ctx.getModelSet(), PiglinModel::new),
				ctx.getEquipmentRenderer()
			)
		);
	}


	public PiglinRenderState createRenderState() {
		return new PiglinRenderState();
	}

	public ResourceLocation getTextureLocation(PiglinRenderState piglinEntityRenderState) {
		return PIGLIN_WARRIOR_TEXTURE;
	}

	protected boolean isShaking(PiglinRenderState piglinEntityRenderState) {
		return super.isShaking(piglinEntityRenderState) || piglinEntityRenderState.isConverting;
	}

}
