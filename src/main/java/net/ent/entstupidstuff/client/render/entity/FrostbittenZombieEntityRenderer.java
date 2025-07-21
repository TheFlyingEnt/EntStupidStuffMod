package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.entity.mob.FrostbittenZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class FrostbittenZombieEntityRenderer extends ZombieBaseEntityRenderer<FrostbittenZombieEntity, DrownedEntityModel<FrostbittenZombieEntity>>{
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite_chilled.png");
    private static final Identifier TEXTURE_B = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_frostbite.png");

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FrostbittenZombieEntityRenderer(EntityRendererFactory.Context context) {
      super(context, new DrownedEntityModel(context.getPart(ModModelLayers.ZOMBIE_FROSTBITTEN)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_INNER_ARMOR)), new DrownedEntityModel(context.getPart(EntityModelLayers.DROWNED_OUTER_ARMOR)));
      this.addFeature(new FrostbittenZombieOverlay(this, context.getModelLoader()));
   }

   @SuppressWarnings({ "rawtypes", "unchecked" })
    public FrostbittenZombieEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new DrownedEntityModel<>(ctx.getPart(layer)), new DrownedEntityModel<>(ctx.getPart(legsArmorLayer)), new DrownedEntityModel<>(ctx.getPart(bodyArmorLayer)));
        this.addFeature(new FrostbittenZombieOverlay(this, ctx.getModelLoader()));
	}

    @Override
    public Identifier getTexture(FrostbittenZombieEntity entity) {
        return entity.getVariant() == FrostbittenZombieEntity.Variant.FROSTBITTEN ? TEXTURE : TEXTURE_B;
    }
    
}
