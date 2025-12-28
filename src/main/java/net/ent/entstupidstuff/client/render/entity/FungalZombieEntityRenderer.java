package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.FungalZombieEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FungalZombieGlowRenderer;
import net.ent.entstupidstuff.client.render.entity.feature.FungalZombieOverlay;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class FungalZombieEntityRenderer extends ZombieBaseEntityRenderer<FungalZombieEntity, ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_fungal.png");

	public ZombieEntityRenderState createRenderState() {
		return new ZombieEntityRenderState();
	}

    public FungalZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new ZombieEntityModel<>(context.getPart(ModEntityModelLayers.ZOMBIE_FUNGAL)),
            new ZombieEntityModel<>(context.getPart(ModEntityModelLayers.ZOMBIE_FUNGAL_BABY)),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), ZombieEntityModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), ZombieEntityModel::new)
		);
		this.addFeature(new FungalZombieGlowRenderer(this));
		this.addFeature(new FungalZombieOverlay(this, context.getEntityModels()));
	}

    @Override
    public Identifier getTexture(ZombieEntityRenderState state) {
        return TEXTURE;
    }


}
