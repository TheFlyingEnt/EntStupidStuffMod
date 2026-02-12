package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.zombie.AncientDrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class AncientDrownedOverlayFeatureRenderer extends RenderLayer<ZombieRenderState, AncientDrownedModel> {
   private static final ResourceLocation SKIN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/ancient_drowned_outer_layer.png");
   private final AncientDrownedModel model;
   private final AncientDrownedModel babyModel;

   public AncientDrownedOverlayFeatureRenderer(RenderLayerParent<ZombieRenderState, AncientDrownedModel> context, EntityModelSet loader) {
      super(context);
      this.model = new AncientDrownedModel(loader.bakeLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER));
      this.babyModel = new AncientDrownedModel(loader.bakeLayer(ModEntityModelLayers.ANCIENT_DROWNED_OUTER_BABY));
      //this.babyModel = new DrownedEntityModel(loader.getModelPart(EntityModelLayers.DROWNED_BABY_OUTER)); // TODO: 1.21.10 Addition
   }

   public void submit(
		PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, int i, ZombieRenderState zombieEntityRenderState, float f, float g
	) {
		AncientDrownedModel drownedEntityModel = zombieEntityRenderState.isBaby ? this.babyModel : this.model;
		coloredCutoutModelCopyLayerRender(drownedEntityModel, SKIN, matrixStack, orderedRenderCommandQueue, i, zombieEntityRenderState, -1, 1);
	}
}
