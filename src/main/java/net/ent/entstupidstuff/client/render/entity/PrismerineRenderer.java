package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

public class PrismerineRenderer extends ProjectileEntityRenderer<UnderwaterArrowEntity, ProjectileEntityRenderState> {
	//public static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/arrow.png");
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID,"textures/entity/projectiles/prismerine_arrow.png");

	public PrismerineRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	public Identifier getTexture(ProjectileEntityRenderState arrowEntity) {
		return TEXTURE;
	}

	@Override
	public ProjectileEntityRenderState createRenderState() {
		return new ProjectileEntityRenderState();
	}
}
