package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.state.PhantomSkeletonRenderState;
import net.ent.entstupidstuff.entity.mob.PhantomSkeletonEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class PhantomSkeletonEntityRenderer extends AbstractSkeletonEntityRenderer<PhantomSkeletonEntity, PhantomSkeletonRenderState>{

    private static final Identifier TEXTURE3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final Identifier TEXTURE2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final Identifier TEXTURE1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
    
    public PhantomSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		super(context, ModEntityModelLayers.PHANTOM_SKELETON, EntityModelLayers.SKELETON_EQUIPMENT);
	}

    @Override
    protected RenderLayer getRenderLayer(PhantomSkeletonRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }

	@Override
	public Identifier getTexture(PhantomSkeletonRenderState state) {
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

