package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.ZebraFishEntity;
import net.ent.entstupidstuff.client.render.entity.model.ZebraFishModel;
import net.ent.entstupidstuff.client.render.entity.state.ZebraFishEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ZebraFishRenderer extends MobRenderer<ZebraFishEntity, ZebraFishEntityRenderState, ZebraFishModel> {
   private static final ResourceLocation TEXTURE_NAVY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/zebra_fish_2.png");
   private static final ResourceLocation TEXTURE_BLUE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/zebra_fish_3.png");
   private static final ResourceLocation TEXTURE_NAVY_LEPORD = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/zebra_fish_2_leopard.png");
   private static final ResourceLocation TEXTURE_BLUE_LEPORD  = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/fish/zebra_fish_3_leopard.png");

   public ZebraFishRenderer(EntityRendererProvider.Context context) {
      super(context, new ZebraFishModel(context.bakeLayer(ModEntityModelLayers.ZEBRA_FISH)), 0.3F);
   }

   @Override
	protected void setupRotations(ZebraFishEntityRenderState state, PoseStack matrices, float bodyYaw, float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(ZebraFishEntityRenderState state) {
		return switch (state.variant) {
			case STRIPED_NAVY -> TEXTURE_NAVY;
			case STRIPED_BLUE -> TEXTURE_BLUE;
			case LEPORD_NAVY -> TEXTURE_NAVY_LEPORD;
			case LEPORD_BLUE -> TEXTURE_BLUE_LEPORD;
			default -> TEXTURE_BLUE_LEPORD;
		};
	}

	@Override
	public ZebraFishEntityRenderState createRenderState() {
		return new ZebraFishEntityRenderState();
	}

	@Override
	public void extractRenderState(ZebraFishEntity entity, ZebraFishEntityRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
