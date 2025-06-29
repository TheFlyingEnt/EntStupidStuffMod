package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
public class PrismerineArrowRenderer extends ProjectileEntityRenderer<UnderwaterArrowEntity> {
	public static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/projectiles/prismerine_arrow.png");

	@Override
	public void render(UnderwaterArrowEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

		//i = 15728640;
		i = 0xF000F0;
		renderGlowLayer(persistentProjectileEntity, matrixStack, vertexConsumerProvider, i);
		super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);

	}

	private void renderGlowLayer(UnderwaterArrowEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        VertexConsumerProvider.Immediate immediate = (VertexConsumerProvider.Immediate) vertexConsumers;
        
        // Render the glow texture at full brightness
        super.render(entity, 0, 0, matrices, immediate, light);
        
        // Make sure to flush after rendering
        immediate.draw();
    }


	public PrismerineArrowRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	public Identifier getTexture(UnderwaterArrowEntity arrowEntity) {
		return TEXTURE;
	}
}
