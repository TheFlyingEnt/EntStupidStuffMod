package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.entity.mob.SlimedZombieEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class SlimedZombieEntityRenderer extends ZombieBaseEntityRenderer<SlimedZombieEntity, ZombieEntityRenderState, SlimedZombieModel>{
   protected SlimedZombieEntityRenderer(Context context, SlimedZombieModel mainModel, SlimedZombieModel babyMainModel, EquipmentModelData<SlimedZombieModel> equipmentModelData, EquipmentModelData<SlimedZombieModel> equipmentModelData2) {
      super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
   }

   public SlimedZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new SlimedZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_SLIMED)),
            new SlimedZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_SLIMED)),//TODO 1.21.10 - Add baby Models
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), SlimedZombieModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), SlimedZombieModel::new)
		);
	}

   

   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed.png");

   public Identifier getTexture(ZombieEntity zombieEntity) {
      return TEXTURE;
   }

   @Override
   protected RenderLayer getRenderLayer(ZombieEntityRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
      return RenderLayer.getEntityTranslucent(this.getTexture(entity));
   }

   @Override
   public ZombieEntityRenderState createRenderState() {
      return new ZombieEntityRenderState();
   }
}
