package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.zombie.FrostbittenZombieModel;
import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class FrostbittenZombieOverlay extends RenderLayer<FrostbittenEntityRenderState, FrostbittenZombieModel> {
   private static final ResourceLocation SKIN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_frostbitten_outer_layer.png");
   private final FrostbittenZombieModel model;
   private final FrostbittenZombieModel babyModel;

   public FrostbittenZombieOverlay(RenderLayerParent<FrostbittenEntityRenderState, FrostbittenZombieModel> context, EntityModelSet loader) {
      super(context);
      this.model = new FrostbittenZombieModel(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER));
      this.babyModel = new FrostbittenZombieModel(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_OUTER_BABY));
   }

   @Override
   public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, FrostbittenEntityRenderState state, float limbAngle, float limbDistance) {
      FrostbittenZombieModel drownedEntityModel = state.isBaby ? this.babyModel : this.model;
		coloredCutoutModelCopyLayerRender(drownedEntityModel, SKIN, matrices, queue, light, state, -1, 1);
   }
}