package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.PhantomSkeletonEntity.PhantomSkeletonVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class PhantomSkeletonEntityRenderer extends SkeletonEntityRenderer<PhantomSkeletonEntity>{

    private static final Identifier TEXTURE3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final Identifier TEXTURE2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
	private static final Identifier TEXTURE1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/phantom_skeleton/phantom_skeleton_01.png");
    
    public PhantomSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		super(context, ModModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
	}

	public PhantomSkeletonEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, layer, legArmorLayer, bodyArmorLayer);
	}

	@Override
    public Identifier getTexture(PhantomSkeletonEntity ent) {

		if (ent.getVariant() == PhantomSkeletonVariant.CROSSBOW) {
			return TEXTURE2;
		} else if (ent.getVariant() == PhantomSkeletonVariant.FLINTLOCK) {
			return TEXTURE3;
		} else {
			return TEXTURE1;
		}
 
	}

    @Override
    protected RenderLayer getRenderLayer(PhantomSkeletonEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }
}

