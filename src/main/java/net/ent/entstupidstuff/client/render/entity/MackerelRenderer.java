package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.MackerelEntity;
import net.ent.entstupidstuff.client.render.entity.model.fish.MackerelModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MackerelRenderer extends MobRenderer<MackerelEntity, LivingEntityRenderState, MackerelModel> {
   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/mackerel.png");

   public MackerelRenderer(EntityRendererProvider.Context context) {
      super(context, new MackerelModel(context.bakeLayer(ModEntityModelLayers.MACKEREL)), 0.3F);
   }

   @Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack matrices, float bodyYaw,
			float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

   @Override
   public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
      return TEXTURE;
   }

   @Override
   public LivingEntityRenderState createRenderState() {
      return new LivingEntityRenderState();
   }
}
