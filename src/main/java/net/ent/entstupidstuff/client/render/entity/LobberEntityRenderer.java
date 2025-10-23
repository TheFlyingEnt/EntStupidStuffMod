package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.ent.entstupidstuff.entity.mob.LobberZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class LobberEntityRenderer extends ZombieBaseEntityRenderer<LobberZombieEntity, ZombieEntityRenderState, LobberModel> { //TODO 1.21.10: Please Check
    

    protected LobberEntityRenderer(Context context, LobberModel mainModel, LobberModel babyMainModel, EquipmentModelData<LobberModel> equipmentModelData, EquipmentModelData<LobberModel> equipmentModelData2) {
        super(
            context, new LobberModel(context.getPart(EntityModelLayers.DROWNED)), 
            babyMainModel, 
            equipmentModelData, 
            equipmentModelData2
        );
    }

    public LobberEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new LobberModel(context.getPart(ModEntityModelLayers.LOBBER_ZOMBIE)),
            new LobberModel(context.getPart(ModEntityModelLayers.LOBBER_ZOMBIE)),//TODO 1.21.10 - Add baby Models
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), LobberModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), LobberModel::new)
		);
	}

    //ScorchedEntityRenderer(EntityRendererFactory.Context, EntityModelLayer, EntityModelLayer, EquipmentModelData<EntityModelLayer>, EquipmentModelData<EntityModelLayer>)

    @Override
    public Identifier getTexture(ZombieEntityRenderState zombieEntityRenderState) {
        return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_lobber.png");
    }

    @Override
    public ZombieEntityRenderState createRenderState() {
        return new ZombieEntityRenderState();
    }

}