package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FungalZombieOverlay extends FeatureRenderer<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_fungal_overlay.png");
   private final ZombieEntityModel<ZombieEntityRenderState> model;
   private final ZombieEntityModel<ZombieEntityRenderState> babyModel;

   public FungalZombieOverlay(FeatureRendererContext<ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> context, LoadedEntityModels loader) {
      super(context);
      this.model = new ZombieEntityModel<ZombieEntityRenderState>(loader.getModelPart(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER));
      this.babyModel = new ZombieEntityModel<ZombieEntityRenderState>(loader.getModelPart(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER_BABY));

   }

   @Override
   public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, ZombieEntityRenderState state, float limbAngle, float limbDistance) {
      ZombieEntityModel<ZombieEntityRenderState> drownedEntityModel = state.baby ? this.babyModel : this.model;
		render(drownedEntityModel, SKIN, matrices, queue, light, state, -1, 1);
   }
    
}
