package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class PrismerineRenderer extends ArrowRenderer<UnderwaterArrowEntity, ArrowRenderState> {
	//public static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/arrow.png");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/projectiles/prismerine_arrow.png");

	public PrismerineRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public ResourceLocation getTextureLocation(ArrowRenderState arrowEntity) {
		return TEXTURE;
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
