package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.state.PhantomSkeletonRenderState;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PhantomSkeletonEntityRenderer extends AbstractSkeletonRenderer<PhantomSkeletonEntity, PhantomSkeletonRenderState>{

    private static final ResourceLocation TEXTURE3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final ResourceLocation TEXTURE2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
    
    public PhantomSkeletonEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModEntityModelLayers.PHANTOM_SKELETON, ModelLayers.SKELETON_ARMOR);
	}

    @Override
    protected RenderType getRenderType(PhantomSkeletonRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

	@Override
	public ResourceLocation getTextureLocation(PhantomSkeletonRenderState state) {
		return switch (state.variant) {
			case MELEE -> TEXTURE1;
			case CROSSBOW -> TEXTURE2;
			default -> TEXTURE3;
		};
	}

	@Override
	public PhantomSkeletonRenderState createRenderState() {
		return new PhantomSkeletonRenderState();
	}
}

