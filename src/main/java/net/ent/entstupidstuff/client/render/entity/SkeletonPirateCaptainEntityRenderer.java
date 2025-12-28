package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SkeletonPirateCaptainEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SoulSkeletonGlowRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SkeletonPirateCaptainEntityRenderer extends AbstractSkeletonEntityRenderer<SkeletonPirateCaptainEntity, SkeletonEntityRenderState>  {
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/toadd/skeleton_pirate_captain.png");


    public SkeletonPirateCaptainEntityRenderer(EntityRendererFactory.Context context) {
		super(context, ModEntityModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_EQUIPMENT);
		this.addFeature(new SoulSkeletonGlowRender(this));
	}

	@Override
	public Identifier getTexture(SkeletonEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public SkeletonEntityRenderState createRenderState() {
		return new SkeletonEntityRenderState();
	}

}
