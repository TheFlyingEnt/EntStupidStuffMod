package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.AlligatorGarEntity;
import net.ent.entstupidstuff.client.render.entity.model.fish.AlligatorGarModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class AlligatorGarRenderer extends MobRenderer<AlligatorGarEntity, LivingEntityRenderState, AlligatorGarModel> {
   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/alligator_gar.png");

   public AlligatorGarRenderer(EntityRendererProvider.Context context) {
      super(context, new AlligatorGarModel(context.bakeLayer(ModEntityModelLayers.ALLIGATOR_GAR)), 0.3F);
   }

   public ResourceLocation getTextureLocation(LivingEntityRenderState fishEntity) {
      return TEXTURE;
   }

   @Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack matrices, float bodyYaw, float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

   @Override
   public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
