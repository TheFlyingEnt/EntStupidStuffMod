package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.LobberZombieEntity;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class LobberEntityRenderer extends AbstractZombieRenderer<LobberZombieEntity, ZombieRenderState, LobberModel> { //TODO 1.21.10: Please Check
    

    protected LobberEntityRenderer(Context context, LobberModel mainModel, LobberModel babyMainModel, ArmorModelSet<LobberModel> equipmentModelData, ArmorModelSet<LobberModel> equipmentModelData2) {
        super(
            context, new LobberModel(context.bakeLayer(ModelLayers.DROWNED)), 
            babyMainModel, 
            equipmentModelData, 
            equipmentModelData2
        );
    }

    public LobberEntityRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new LobberModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_LOBBER)),
            new LobberModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_LOBBER_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), LobberModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), LobberModel::new)
		);
	}

    //ScorchedEntityRenderer(EntityRendererFactory.Context, EntityModelLayer, EntityModelLayer, EquipmentModelData<EntityModelLayer>, EquipmentModelData<EntityModelLayer>)

    @Override
    public ResourceLocation getTextureLocation(ZombieRenderState zombieEntityRenderState) {
        return ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie_lobber.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

}