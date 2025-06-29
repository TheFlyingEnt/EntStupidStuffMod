package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.entity.mob.ArmoredPillagerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;

public class ArmoredPillagerEntityRenderer extends IllagerEntityRenderer<ArmoredPillagerEntity> /*MobEntityRenderer<ArmoredPillagerEntity, IllagerEntityModel<ArmoredPillagerEntity>>*/{

    // Textures for Varients
    private static final Identifier DIAMOND_TEXTURE = Identifier.of("entstupidstuff", "textures/entity/pillager_diamond_armored.png");
    private static final Identifier GOLD_TEXTURE = Identifier.of("entstupidstuff", "textures/entity/pillager_gold_armored.png");
    
    public ArmoredPillagerEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new IllagerEntityModel<>(context.getPart(EntityModelLayers.PILLAGER)), 0.5F);
		this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
	}

    @Override
    public Identifier getTexture(ArmoredPillagerEntity entity) {
        return entity.getVariant() == ArmoredPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }

    

}
