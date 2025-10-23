package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.ArrowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.ArrowEntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
public class PrismerineArrowRenderer extends ProjectileEntityRenderer<UnderwaterArrowEntity, ProjectileEntityRenderState> {
	public static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/projectiles/prismerine_arrow.png");
	public final ArrowEntityModel model;

	public PrismerineArrowRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new ArrowEntityModel(context.getPart(EntityModelLayers.ARROW));
	}

	public Identifier getTexture(ProjectileEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public void render(ProjectileEntityRenderState projectileEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {

		matrixStack.push();
		super.render(projectileEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
		//this.model = new AncientTridentModel(context.getPart(ModEntityModelLayers.ANCIENT_TRIDENT));

	}

	private void renderGlowLayer(ProjectileEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue commandQueue) {
        commandQueue.submitCustom(matrices, RenderLayer.getEntityTranslucentEmissive(TEXTURE), (entry, vertexConsumer) -> {
            int light = 0xF000F0;
            int overlay = 0;
            int outlineColor = 0;

            commandQueue.submitModelPart(
                    this.model.getRootPart(),
                    matrices,
                    RenderLayer.getEntityTranslucentEmissive(TEXTURE),
                    light,
                    overlay,
                    null,
                    false,
                    false,
                    -1,
                    null,
                    outlineColor
            );
        });
    }

	@Override
	public ProjectileEntityRenderState createRenderState() {
		return new ProjectileEntityRenderState();
	}

	public void updateRenderState(UnderwaterArrowEntity arrowEntity, ArrowEntityRenderState arrowEntityRenderState, float f) {
		super.updateRenderState(arrowEntity, arrowEntityRenderState, f);
	}

}
