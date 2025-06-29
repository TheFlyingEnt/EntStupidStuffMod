package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.ent.entstupidstuff.entity.mob.ScorchedZombieEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class ScorchedGlowRenderer<T extends ScorchedZombieEntity> extends EyesFeatureRenderer<T, ScorchedModel<T>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched_e.png"));

	public ScorchedGlowRenderer(FeatureRendererContext<T, ScorchedModel<T>> featureRendererContext) {
		super(featureRendererContext);
		//System.out.println("Glowing Ran!!");
	}

	@Override
	public RenderLayer getEyesTexture() {
		//System.out.println("Glowing Ran2!!");
		return SKIN;
	}

}
