package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SoulJackOLanternModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class SoulOLanternRenderer extends AbstractZombieRenderer<Zombie, ZombieRenderState, SoulJackOLanternModel>{

   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/texture.png");
   private static final ResourceLocation GLOW_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/texture.png");

   protected SoulOLanternRenderer(Context context, SoulJackOLanternModel mainModel, SoulJackOLanternModel  babyMainModel, ArmorModelSet<SoulJackOLanternModel > equipmentModelData, ArmorModelSet<SoulJackOLanternModel > equipmentModelData2) {
      super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
   }

    public SoulOLanternRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new SoulJackOLanternModel (context.bakeLayer(ModEntityModelLayers.SOULJACKOLANTERN)),
            new SoulJackOLanternModel (context.bakeLayer(ModEntityModelLayers.SOULJACKOLANTERN)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), SoulJackOLanternModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), SoulJackOLanternModel::new)
		);
	}

    @Override
    public void submit(ZombieRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {

        super.submit(state, poseStack, collector, cameraState);

        int alphaByte = Math.round(1 * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;

        collector.order(1)
			.submitModel(
				this.getModel(), state, poseStack, RenderType.eyes(GLOW_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);


    }

   


    @Override
    public ResourceLocation getTextureLocation(ZombieRenderState state) {
        return TEXTURE;
    }

   @Override
   protected RenderType getRenderType(ZombieRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
      return RenderType.entityTranslucent(this.getTextureLocation(entity));
   }

   @Override
   public ZombieRenderState createRenderState() {
      return new ZombieRenderState();
   }
}
