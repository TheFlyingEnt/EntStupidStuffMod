package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.ScorchedZombieEntity;
import net.ent.entstupidstuff.client.render.entity.feature.ScorchedGlowRenderer;
import net.ent.entstupidstuff.client.render.entity.model.ScorchedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class ScorchedEntityRenderer extends AbstractZombieRenderer<ScorchedZombieEntity, ZombieRenderState, ScorchedModel>{

    protected ScorchedEntityRenderer(Context context, ScorchedModel mainModel, ScorchedModel babyMainModel, ArmorModelSet<ScorchedModel> equipmentModelData, ArmorModelSet<ScorchedModel> equipmentModelData2) {
        super(context, mainModel, babyMainModel, equipmentModelData, equipmentModelData2);
        this.addLayer(new ScorchedGlowRenderer(this));
    }

    public ScorchedEntityRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new ScorchedModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_SCORCHED)),
            new ScorchedModel(context.bakeLayer(ModEntityModelLayers.ZOMBIE_SCORCHED_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), ScorchedModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), ScorchedModel::new)
		);
        this.addLayer(new ScorchedGlowRenderer(this));
	}

    @Override //Getting Texture of Mob
    public ResourceLocation getTextureLocation(ZombieRenderState entity) {
        return ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie_scorched.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

}
