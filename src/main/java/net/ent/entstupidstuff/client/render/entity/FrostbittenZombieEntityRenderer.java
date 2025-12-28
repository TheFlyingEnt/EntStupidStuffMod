package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FrostbittenZombieOverlay;
import net.ent.entstupidstuff.client.render.entity.model.FrostbittenZombieModel;
import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.util.Identifier;

public class FrostbittenZombieEntityRenderer extends ZombieBaseEntityRenderer<FrostbittenZombieEntity, FrostbittenEntityRenderState, FrostbittenZombieModel> {
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite_chilled.png");
    private static final Identifier TEXTURE_B = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite.png");

    public FrostbittenZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new FrostbittenZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_FROSTBITTEN)),
            new FrostbittenZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_BABY)),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), FrostbittenZombieModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), FrostbittenZombieModel::new)
		);
		this.addFeature(new FrostbittenZombieOverlay(this, context.getEntityModels()));
	}

    @Override
    public Identifier getTexture(FrostbittenEntityRenderState state) {
        return switch (state.variant) {
			case FROSTBITTEN -> TEXTURE;
			case NORMAL -> TEXTURE_B;
			default -> TEXTURE_B;
		};
    }

    @Override
    public FrostbittenEntityRenderState createRenderState() {
		return new FrostbittenEntityRenderState();
	}
    
}
