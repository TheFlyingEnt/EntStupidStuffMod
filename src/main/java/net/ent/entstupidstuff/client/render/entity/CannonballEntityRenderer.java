package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.ent.entstupidstuff.entity.projectile.CannonballEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CannonballEntityRenderer extends EntityRenderer<CannonballEntity>{

    private final CannonballModel model;
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball.png");
    private static final Identifier TEXTURE_PHANTOM_FIRE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball_soulflame.png");
    private static final Identifier TEXTURE_FIRE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball_flame.png");

    public CannonballEntityRenderer(Context context) {
        super(context);
        this.model = new CannonballModel(context.getPart(ModEntityModelLayers.CANNON_BALL));
    }

    @Override
    public Identifier getTexture(CannonballEntity entity) {
        if (entity.hasFlame()) {
            return TEXTURE_FIRE;
        }
        return TEXTURE;
    }

    @Override
    public void render(CannonballEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        
        matrices.translate(0.0D, -3.0D, 0.0D); // Adjust the translation if needed
        matrices.scale(2, 2, 2);

        this.model.render(matrices, vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(entity))), light, OverlayTexture.DEFAULT_UV);
        
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

}
