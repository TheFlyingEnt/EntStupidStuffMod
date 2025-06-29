package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.LobberModel;
import net.ent.entstupidstuff.entity.mob.LobberZombieEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LobberEntityRenderer extends ZombieBaseEntityRenderer<LobberZombieEntity, LobberModel<LobberZombieEntity>>{

    public LobberEntityRenderer(EntityRendererFactory.Context context) {
        this(context, ModModelLayers.LOBBER_ZOMBIE, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public LobberEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new LobberModel<>(ctx.getPart(layer)), new LobberModel<>(ctx.getPart(legsArmorLayer)), new LobberModel<>(ctx.getPart(bodyArmorLayer)));

	}

    @Override //Render Logic
    public void render(LobberZombieEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override //Getting Texture of Mob
    public Identifier getTexture(LobberZombieEntity entity) {
        return Identifier.of("entstupidstuff", "textures/entity/zombie_lobber.png"); // Path to your texture
    }

}