package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.render.entity.model.ButterflyModel;
import net.ent.entstupidstuff.client.render.entity.state.ButterflyRenderState;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ButterflyRenderer extends MobEntityRenderer<ButterflyEntity, ButterflyRenderState, ButterflyModel> {

    private static final Identifier BIRCH = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_birch.png");
    private static final Identifier EMPEROR = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_emperor.png");
    private static final Identifier MONARCH = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_monarch.png");
    private static final Identifier YELLOW = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_yellow.png");
    private static final Identifier LUMINOUS = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_luminous.png");
    private static final Identifier REDWOOD = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_redwood.png");
    private static final Identifier BLUE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_blue.png");
    private static final Identifier SEELE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_seele.png");
    private static final Identifier CREEPER = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/butterfly/butterfly_creeper.png");

    public ButterflyRenderer(EntityRendererFactory.Context context) {
		super(context, new ButterflyModel(context.getPart(ModEntityModelLayers.BUTTERFLY)), 0.15F);
	}

	public Identifier getTexture(ButterflyRenderState state) {
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
	public void updateRenderState(ButterflyEntity entity, ButterflyRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}
}
