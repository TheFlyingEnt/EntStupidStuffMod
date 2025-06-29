package net.ent.entstupidstuff.client.render.entity;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class PiglinExtraRenderer extends BipedEntityRenderer<MobEntity, PiglinEntityModel<MobEntity>>{

    private static final Map<EntityType<?>, Identifier> TEXTURES = ImmutableMap.of(
		EntityFactory.PIGLIN_WARRIOR,
		Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/piglin_warrior.png"),
		EntityFactory.PIGLIN_FUNGAL,
		Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/piglin_fungusthrower.png")
	);
    
    private static final float HORIZONTAL_SCALE = 1.0019531F;

	@SuppressWarnings("rawtypes")
    public PiglinExtraRenderer(
		EntityRendererFactory.Context ctx, EntityModelLayer mainLayer, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer, boolean zombie
	) {
		super(ctx, getPiglinModel(ctx.getModelLoader(), mainLayer, zombie), 0.5F, HORIZONTAL_SCALE, 1.0F, HORIZONTAL_SCALE);
		this.addFeature(
			new ArmorFeatureRenderer<>(
				this, new ArmorEntityModel(ctx.getPart(innerArmorLayer)), new ArmorEntityModel(ctx.getPart(outerArmorLayer)), ctx.getModelManager()
			)
		);
	}

	private static PiglinEntityModel<MobEntity> getPiglinModel(EntityModelLoader modelLoader, EntityModelLayer layer, boolean zombie) {
		PiglinEntityModel<MobEntity> piglinEntityModel = new PiglinEntityModel<>(modelLoader.getModelPart(layer));
		if (zombie) {
			piglinEntityModel.rightEar.visible = false;
		}

		return piglinEntityModel;
	}

	public Identifier getTexture(MobEntity mobEntity) {
		Identifier identifier = (Identifier)TEXTURES.get(mobEntity.getType());
		if (identifier == null) {
			throw new IllegalArgumentException("I don't know what texture to use for " + mobEntity.getType());
		} else {
			return identifier;
		}
	}

	protected boolean isShaking(MobEntity mobEntity) {
		return super.isShaking(mobEntity) || mobEntity instanceof AbstractPiglinEntity && ((AbstractPiglinEntity)mobEntity).shouldZombify();
	}

}
