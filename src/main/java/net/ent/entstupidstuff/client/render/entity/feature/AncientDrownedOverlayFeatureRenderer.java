package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AncientDrownedOverlayFeatureRenderer extends FeatureRenderer<ZombieEntityRenderState, AncientDrownedModel> {
   private static final Identifier SKIN = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned_outer_layer.png");
   private final AncientDrownedModel model;
   private final AncientDrownedModel babyModel;

   public AncientDrownedOverlayFeatureRenderer(FeatureRendererContext<ZombieEntityRenderState, AncientDrownedModel> context, LoadedEntityModels loader) {
      super(context);
      this.model = new AncientDrownedModel(loader.getModelPart(ModEntityModelLayers.ANCIENT_DROWNED_OUTER));
      this.babyModel = new AncientDrownedModel(loader.getModelPart(ModEntityModelLayers.ANCIENT_DROWNED_OUTER_BABY));
      //this.babyModel = new DrownedEntityModel(loader.getModelPart(EntityModelLayers.DROWNED_BABY_OUTER)); // TODO: 1.21.10 Addition
   }

   public void render(
		MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int i, ZombieEntityRenderState zombieEntityRenderState, float f, float g
	) {
		AncientDrownedModel drownedEntityModel = zombieEntityRenderState.baby ? this.babyModel : this.model;
		render(drownedEntityModel, SKIN, matrixStack, orderedRenderCommandQueue, i, zombieEntityRenderState, -1, 1);
	}
}
