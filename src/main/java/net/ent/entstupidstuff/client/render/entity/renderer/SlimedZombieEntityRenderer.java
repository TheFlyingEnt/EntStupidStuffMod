package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SlimedZombieEntity;
import net.ent.entstupidstuff.client.render.entity.model.zombie.SlimedZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class SlimedZombieEntityRenderer extends AbstractZombieRenderer<SlimedZombieEntity, ZombieRenderState, SlimedZombieModel>{

   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_slimed.png");

   protected SlimedZombieEntityRenderer(Context context, SlimedZombieModel mainModel, SlimedZombieModel babyMainModel, ArmorModelSet<SlimedZombieModel> equipmentModelData, ArmorModelSet<SlimedZombieModel> equipmentModelData2) {
      super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
   }

   public SlimedZombieEntityRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new SlimedZombieModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED)),
            new SlimedZombieModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), SlimedZombieModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), SlimedZombieModel::new)
		);
	}

   


   @Override
    public ResourceLocation getTextureLocation(ZombieRenderState state) {
        return TEXTURE;
    }

   @Override
   protected RenderType getRenderType(ZombieRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
      return RenderType.entityTranslucent(this.getTextureLocation(entity));
   }

   @Override
   public ZombieRenderState createRenderState() {
      return new ZombieRenderState();
   }
}
