package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.ButterflyModel;
import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ButterflyRenderer extends MobEntityRenderer<ButterflyEntity, ButterflyModel<ButterflyEntity>>{

    private static final Identifier BIRCH = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_birch.png");
    private static final Identifier EMPEROR = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_emperor.png");
    private static final Identifier MONARCH = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_monarch.png");
    private static final Identifier YELLOW = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_yellow.png");
    private static final Identifier LUMINOUS = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_luminous.png");
    private static final Identifier REDWOOD = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_redwood.png");
    private static final Identifier BLUE = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_blue.png");
    private static final Identifier SEELE = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_blue.png");
    private static final Identifier CREEPER = Identifier.of("entstupidstuff", "textures/entity/butterfly/butterfly_blue.png");

    public ButterflyRenderer(EntityRendererFactory.Context context) {
		super(context, new ButterflyModel<>(context.getPart(ModModelLayers.BUTTERFLY)), 0.15F);
	}

	public Identifier getTexture(ButterflyEntity entity) {
        if (entity.getVariant() == ButterflyEntity.Variant.BIRCH)
            return BIRCH;
        else if (entity.getVariant() == ButterflyEntity.Variant.EMPEROR)
            return EMPEROR;
        else if (entity.getVariant() == ButterflyEntity.Variant.MONARCH)
            return MONARCH;
        else if (entity.getVariant() == ButterflyEntity.Variant.YELLOW)
            return YELLOW;
        else if (entity.getVariant() == ButterflyEntity.Variant.LUMINOUS)
            return LUMINOUS;
        else if (entity.getVariant() == ButterflyEntity.Variant.REDWOOD)
            return REDWOOD;
        else if (entity.getVariant() == ButterflyEntity.Variant.BLUE)
            return BLUE;
        else if (entity.getVariant() == ButterflyEntity.Variant.SEELE)
            return SEELE;
        else if (entity.getVariant() == ButterflyEntity.Variant.CREEPER)
            return CREEPER;

        return BIRCH;
	}
}
