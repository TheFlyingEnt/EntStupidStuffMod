package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.entity.mob.ScorchedZombieEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ScorchedEntityRenderer extends ZombieBaseEntityRenderer<ScorchedZombieEntity, ScorchedModel<ScorchedZombieEntity>>{

    public ScorchedEntityRenderer(EntityRendererFactory.Context context) {
        this(context, ModEntityModelLayers.ZOMBIE_SCORCHED, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
        this.addFeature(new ScorchedGlowRenderer<>(this));
    }

    public ScorchedEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new ScorchedModel<>(ctx.getPart(layer)), new ScorchedModel<>(ctx.getPart(legsArmorLayer)), new ScorchedModel<>(ctx.getPart(bodyArmorLayer)));
        this.addFeature(new ScorchedGlowRenderer<>(this));
	}

    @Override //Render Logic
    public void render(ScorchedZombieEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override //Getting Texture of Mob
    public Identifier getTexture(ScorchedZombieEntity entity) {
        return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched.png"); // Path to your texture
    }

}


//this.addFeature(new ScorchedGlowRenderer<>(this));