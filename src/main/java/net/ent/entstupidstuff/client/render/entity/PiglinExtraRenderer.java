package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.client.render.entity.state.PiglinEntityRenderState;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.util.Identifier;

public class PiglinExtraRenderer extends BipedEntityRenderer<AbstractPiglinEntity, PiglinEntityRenderState, PiglinEntityModel> {

	private static final Identifier PIGLIN_W_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/piglin_warrior.png");
	public static final HeadFeatureRenderer.HeadTransformation HEAD_TRANSFORMATION = new HeadFeatureRenderer.HeadTransformation(0.0F, 0.0F, 1.0019531F);

	@SuppressWarnings("rawtypes")
    public PiglinExtraRenderer(EntityRendererFactory.Context ctx, EntityModelLayer mainLayer, EntityModelLayer babyMainLayer, EquipmentModelData<EntityModelLayer> equipmentModelData, EquipmentModelData<EntityModelLayer> equipmentModelData2) {
		super(ctx, new PiglinEntityModel(ctx.getPart(mainLayer)), new PiglinEntityModel(ctx.getPart(babyMainLayer)), 0.5F, HEAD_TRANSFORMATION);
		this.addFeature(
			new ArmorFeatureRenderer<>(
				this,
				EquipmentModelData.mapToEntityModel(equipmentModelData, ctx.getEntityModels(), PiglinEntityModel::new),
				EquipmentModelData.mapToEntityModel(equipmentModelData2, ctx.getEntityModels(), PiglinEntityModel::new),
				ctx.getEquipmentRenderer()
			)
		);
	}


	public PiglinEntityRenderState createRenderState() {
		return new PiglinEntityRenderState();
	}

	public Identifier getTexture(PiglinEntityRenderState piglinEntityRenderState) {
		return PIGLIN_W_TEXTURE;
	}

	protected boolean isShaking(PiglinEntityRenderState piglinEntityRenderState) {
		return super.isShaking(piglinEntityRenderState) || piglinEntityRenderState.shouldZombify;
	}

}
