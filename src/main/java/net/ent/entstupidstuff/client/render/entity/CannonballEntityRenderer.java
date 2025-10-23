package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.ent.entstupidstuff.entity.projectile.CannonballEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class CannonballEntityRenderer extends EntityRenderer<CannonballEntity, ProjectileEntityRenderState> {
	private final CannonballModel model;

    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball.png");
    private static final Identifier TEXTURE_PHANTOM_FIRE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball_soulflame.png");
    private static final Identifier TEXTURE_FIRE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/cannon_ball_flame.png");

	public CannonballEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new CannonballModel(context.getPart(ModEntityModelLayers.CANNON_BALL));
	}

	public void render(ProjectileEntityRenderState projectileEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) 
    {
		matrixStack.push();
		/*matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(projectileEntityRenderState.yaw - 90.0F));
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(projectileEntityRenderState.pitch));
		orderedRenderCommandQueue.submitModel(
			this.model,
			projectileEntityRenderState,
			matrixStack,
			RenderLayer.getEntityCutout(this.getTexture(projectileEntityRenderState)),
			projectileEntityRenderState.light,
			OverlayTexture.DEFAULT_UV,
			projectileEntityRenderState.outlineColor,
			null
		);*/
        matrixStack.translate(0.0D, -3.0D, 0.0D);
        matrixStack.scale(2.0F, 2.0F, 2.0F);

        orderedRenderCommandQueue.submitModel(
			this.model,
			projectileEntityRenderState,
			matrixStack,
			RenderLayer.getEntityCutout(this.getTexture(projectileEntityRenderState)),
			projectileEntityRenderState.light,
			OverlayTexture.DEFAULT_UV,
			projectileEntityRenderState.outlineColor,
			null
		);
        
		matrixStack.pop();
		super.render(projectileEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

    
	protected Identifier getTexture(ProjectileEntityRenderState state) {
        return TEXTURE;
    }

    @Override
	public void updateRenderState(CannonballEntity persistentProjectileEntity, ProjectileEntityRenderState projectileEntityRenderState, float f) {
		super.updateRenderState(persistentProjectileEntity, projectileEntityRenderState, f);
		projectileEntityRenderState.pitch = persistentProjectileEntity.getLerpedPitch(f);
		projectileEntityRenderState.yaw = persistentProjectileEntity.getLerpedYaw(f);
		projectileEntityRenderState.shake = persistentProjectileEntity.shake - f;
	}

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }
}
