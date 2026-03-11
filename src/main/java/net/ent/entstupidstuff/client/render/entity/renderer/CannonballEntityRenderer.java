package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.client.render.entity.model.CannonballModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CannonballEntityRenderer extends EntityRenderer<CannonballEntity, ArrowRenderState> {
	private final CannonballModel model;

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/projectiles/cannon_ball.png");
    private static final ResourceLocation TEXTURE_PHANTOM_FIRE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/projectiles/cannon_ball_soulflame.png");
    private static final ResourceLocation TEXTURE_FIRE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/projectiles/cannon_ball_flame.png");

	public CannonballEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new CannonballModel(context.bakeLayer(ModEntityModelLayers.CANNON_BALL));
	}

	public void submit(ArrowRenderState projectileEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState cameraRenderState) 
    {
		matrixStack.pushPose();
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
			RenderType.entityCutout(this.getTexture(projectileEntityRenderState)),
			projectileEntityRenderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
			projectileEntityRenderState.outlineColor,
			null
		);
        
		matrixStack.popPose();
		super.submit(projectileEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

    
	protected ResourceLocation getTexture(ArrowRenderState state) {
        return TEXTURE;
    }

    @Override
	public void extractRenderState(CannonballEntity persistentProjectileEntity, ArrowRenderState projectileEntityRenderState, float f) {
		super.extractRenderState(persistentProjectileEntity, projectileEntityRenderState, f);
		projectileEntityRenderState.xRot = persistentProjectileEntity.getXRot(f);
		projectileEntityRenderState.yRot = persistentProjectileEntity.getYRot(f);
		projectileEntityRenderState.shake = persistentProjectileEntity.shakeTime - f;
	}

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }
}
