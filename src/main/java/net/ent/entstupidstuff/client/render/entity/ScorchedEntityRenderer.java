package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.entity.mob.ScorchedZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class ScorchedEntityRenderer extends ZombieBaseEntityRenderer<ScorchedZombieEntity, ZombieEntityRenderState, ScorchedModel>{

    protected ScorchedEntityRenderer(Context context, ScorchedModel mainModel, ScorchedModel babyMainModel, EquipmentModelData<ScorchedModel> equipmentModelData, EquipmentModelData<ScorchedModel> equipmentModelData2) {
        super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
        this.addFeature(new ScorchedGlowRenderer(this));
    }

    public ScorchedEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new ScorchedModel(context.getPart(ModEntityModelLayers.ZOMBIE_SCORCHED)),
            new ScorchedModel(context.getPart(ModEntityModelLayers.ZOMBIE_SCORCHED)),//TODO 1.21.10 - Add baby Models
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), ScorchedModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), ScorchedModel::new)
		);
        this.addFeature(new ScorchedGlowRenderer(this));
	}

    @Override //Getting Texture of Mob
    public Identifier getTexture(ZombieEntityRenderState entity) {
        return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched.png"); // Path to your texture
    }

    @Override
    public ZombieEntityRenderState createRenderState() {
        return new ZombieEntityRenderState();
    }

}


//this.addFeature(new ScorchedGlowRenderer<>(this));