package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.render.entity.model.ButterflyModel;
import net.ent.entstupidstuff.client.render.entity.state.ButterflyRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ButterflyRenderer extends MobRenderer<ButterflyEntity, ButterflyRenderState, ButterflyModel> {

    private static final ResourceLocation BIRCH = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_birch.png");
    private static final ResourceLocation EMPEROR = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_emperor.png");
    private static final ResourceLocation MONARCH = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_monarch.png");
    private static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_yellow.png");
    private static final ResourceLocation LUMINOUS = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_luminous.png");
    private static final ResourceLocation REDWOOD = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_redwood.png");
    private static final ResourceLocation BLUE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_blue.png");
    private static final ResourceLocation SEELE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_seele.png");
    private static final ResourceLocation CREEPER = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_creeper.png");

    public ButterflyRenderer(EntityRendererProvider.Context context) {
		super(context, new ButterflyModel(context.bakeLayer(ModEntityModelLayers.BUTTERFLY)), 0.15F);
	}

	public ResourceLocation getTextureLocation(ButterflyRenderState state) {
        return switch (state.variant) {
			case BIRCH -> BIRCH;
			case EMPEROR -> EMPEROR;
			case MONARCH -> MONARCH;
			case YELLOW -> YELLOW;
			case LUMINOUS -> LUMINOUS;
			case REDWOOD -> REDWOOD;
			case BLUE -> BLUE;
			case SEELE -> SEELE;
			case CREEPER -> CREEPER;
			default -> EMPEROR;
		};
	}

    @Override
	public ButterflyRenderState createRenderState() {
		return new ButterflyRenderState();
	}

	@Override
	public void extractRenderState(ButterflyEntity entity, ButterflyRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
