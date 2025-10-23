package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SlimedZombieModel;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SlimedZombieOverlayFeatureRenderer extends FeatureRenderer<ZombieEntityRenderState, SlimedZombieModel> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_slimed_outer_layer.png");
   private final SlimedZombieModel babyModel;
   private final SlimedZombieModel model;

   public SlimedZombieOverlayFeatureRenderer( FeatureRendererContext<ZombieEntityRenderState, SlimedZombieModel> context, LoadedEntityModels loader) {
      super(context);
      this.model = new SlimedZombieModel(loader.getModelPart(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER));
      this.babyModel = new SlimedZombieModel(loader.getModelPart(ModEntityModelLayers.ZOMBIE_SLIMED_OUTER)); //TODO: 1.21.10 Please Fix
   }

   @Override
   public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, ZombieEntityRenderState state, float limbAngle, float limbDistance) {
      SlimedZombieModel entity = state.baby ? this.babyModel : this.model;
		render(entity, SKIN, matrices, queue, light, state, -1, 1);
   }

}
