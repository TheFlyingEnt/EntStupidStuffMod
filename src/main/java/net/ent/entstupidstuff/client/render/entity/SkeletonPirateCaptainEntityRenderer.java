package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SkeletonPirateCaptainEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SoulSkeletonGlowRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SkeletonPirateCaptainEntityRenderer extends AbstractSkeletonRenderer<SkeletonPirateCaptainEntity, SkeletonRenderState>  {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/toadd/skeleton_pirate_captain.png");


    public SkeletonPirateCaptainEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, ModelLayers.SKELETON_ARMOR);
		this.addLayer(new SoulSkeletonGlowRender(this));
	}

	@Override
	public ResourceLocation getTextureLocation(SkeletonRenderState state) {
		return TEXTURE;
	}

	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}

}
