package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FrostbittenZombieOverlay;
import net.ent.entstupidstuff.client.render.entity.model.FrostbittenZombieModel;
import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FrostbittenZombieEntityRenderer extends AbstractZombieRenderer<FrostbittenZombieEntity, FrostbittenEntityRenderState, FrostbittenZombieModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_frostbitten_chilled.png");
    private static final ResourceLocation TEXTURE_B = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/zombie_frostbitten.png");

    public FrostbittenZombieEntityRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new FrostbittenZombieModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN)),
            new FrostbittenZombieModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_FROSTBITTEN_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), FrostbittenZombieModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), FrostbittenZombieModel::new)
		);
		this.addLayer(new FrostbittenZombieOverlay(this, context.getModelSet()));
	}

    @Override
    public ResourceLocation getTextureLocation(FrostbittenEntityRenderState state) {
        return switch (state.variant) {
			case FROSTBITTEN -> TEXTURE;
			case NORMAL -> TEXTURE_B;
			default -> TEXTURE_B;
		};
    }

    @Override
    public FrostbittenEntityRenderState createRenderState() {
		return new FrostbittenEntityRenderState();
	}
    
}
