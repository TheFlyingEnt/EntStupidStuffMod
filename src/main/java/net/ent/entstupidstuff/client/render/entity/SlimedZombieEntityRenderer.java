package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SlimedZombieEntity;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class SlimedZombieEntityRenderer extends ZombieBaseEntityRenderer<SlimedZombieEntity, ZombieEntityRenderState, SlimedZombieModel>{

   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed.png");

   protected SlimedZombieEntityRenderer(Context context, SlimedZombieModel mainModel, SlimedZombieModel babyMainModel, EquipmentModelData<SlimedZombieModel> equipmentModelData, EquipmentModelData<SlimedZombieModel> equipmentModelData2) {
      super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
   }

   public SlimedZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new SlimedZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_SLIMED)),
            new SlimedZombieModel(context.getPart(ModEntityModelLayers.ZOMBIE_SLIMED_BABY)),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), SlimedZombieModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), SlimedZombieModel::new)
		);
	}

   


   @Override
    public Identifier getTexture(ZombieEntityRenderState state) {
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
