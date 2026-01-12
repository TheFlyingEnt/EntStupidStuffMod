package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class SlimedZombieOverlayFeatureRenderer extends RenderLayer<ZombieRenderState, SlimedZombieModel> {
   private static final ResourceLocation SKIN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed_outer_layer.png");
   private final SlimedZombieModel babyModel;
   private final SlimedZombieModel model;

   public SlimedZombieOverlayFeatureRenderer( RenderLayerParent<ZombieRenderState, SlimedZombieModel> context, EntityModelSet loader) {
      super(context);
      this.model = new SlimedZombieModel(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER));
      this.babyModel = new SlimedZombieModel(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER)); //TODO: 1.21.10 Please Fix
   }

   @Override
   public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, ZombieRenderState state, float limbAngle, float limbDistance) {
      SlimedZombieModel entity = state.isBaby ? this.babyModel : this.model;
		coloredCutoutModelCopyLayerRender(entity, SKIN, matrices, queue, light, state, -1, 1);
   }

}
