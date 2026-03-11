package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.FungalZombieEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FungalZombieGlowRenderer;
import net.ent.entstupidstuff.client.render.entity.feature.FungalZombieOverlay;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class FungalZombieEntityRenderer extends AbstractZombieRenderer<FungalZombieEntity, ZombieRenderState, ZombieModel<ZombieRenderState>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_fungal.png");

	public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

    public FungalZombieEntityRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new ZombieModel<>(context.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL)),
            new ZombieModel<>(context.bakeLayer(ModEntityModelLayers.ZOMBIE_FUNGAL_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), ZombieModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), ZombieModel::new)
		);
		this.addLayer(new FungalZombieGlowRenderer(this));
		this.addLayer(new FungalZombieOverlay(this, context.getModelSet()));
	}

    @Override
    public ResourceLocation getTextureLocation(ZombieRenderState state) {
        return TEXTURE;
    }


}
