package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.ent.entstupidstuff.entity.projectile.CannonBallEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CannonballEntityRenderer extends EntityRenderer<CannonBallEntity>{

    public CannonballEntityRenderer(Context context) {
        super(context);
        this.model = new CannonballModel(context.getPart(ModModelLayers.CANNON_BALL));
    }

    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball.png");

     private final CannonballModel model;

    @Override
    public Identifier getTexture(CannonBallEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(CannonBallEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        
        matrices.translate(0.0D, -3.0D, 0.0D); // Adjust the translation if needed
        matrices.scale(2, 2, 2);

        this.model.render(matrices, vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(entity))), light, OverlayTexture.DEFAULT_UV);
        
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

}
