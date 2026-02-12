package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class FungalZombieOverlay extends RenderLayer<ZombieRenderState, ZombieModel<ZombieRenderState>> {
   private static final ResourceLocation SKIN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_fungal_overlay.png");
   private final ZombieModel<ZombieRenderState> model;
   private final ZombieModel<ZombieRenderState> babyModel;

   public FungalZombieOverlay(RenderLayerParent<ZombieRenderState, ZombieModel<ZombieRenderState>> context, EntityModelSet loader) {
      super(context);
      this.model = new ZombieModel<ZombieRenderState>(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER));
      this.babyModel = new ZombieModel<ZombieRenderState>(loader.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_OUTER_BABY));

   }

   @Override
   public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, ZombieRenderState state, float limbAngle, float limbDistance) {
      ZombieModel<ZombieRenderState> drownedEntityModel = state.isBaby ? this.babyModel : this.model;
		coloredCutoutModelCopyLayerRender(drownedEntityModel, SKIN, matrices, queue, light, state, -1, 1);
   }
    
}
