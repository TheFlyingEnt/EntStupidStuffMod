package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.FrostbittenZombieModel;
import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FrostbittenZombieOverlay extends FeatureRenderer<FrostbittenEntityRenderState, FrostbittenZombieModel> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite_o.png");
   private final FrostbittenZombieModel model;
   private final FrostbittenZombieModel babyModel;

   @SuppressWarnings({ "unchecked", "rawtypes" })
   public FrostbittenZombieOverlay(FeatureRendererContext<FrostbittenEntityRenderState, FrostbittenZombieModel> context, LoadedEntityModels loader) {
      super(context);
      this.model = new FrostbittenZombieModel(loader.getModelPart(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER));
      this.babyModel = new FrostbittenZombieModel(loader.getModelPart(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER));  // TODO: 1.21.10 Addition
   }

   @Override
   public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, FrostbittenEntityRenderState state, float limbAngle, float limbDistance) {
      FrostbittenZombieModel drownedEntityModel = state.baby ? this.babyModel : this.model;
		render(drownedEntityModel, SKIN, matrices, queue, light, state, -1, 1);
   }
}