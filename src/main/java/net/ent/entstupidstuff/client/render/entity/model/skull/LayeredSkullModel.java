package net.ent.entstupidstuff.client.render.entity.model.skull;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class LayeredSkullModel extends SkullModel {
    private final SkullModel outerLayerModel;
    private final ResourceLocation outerTexture;

    public LayeredSkullModel(ModelPart root, ModelPart outerLayerRoot, ResourceLocation outerTexture) {
        super(root);
        this.outerLayerModel = new SkullModel(outerLayerRoot);
        this.outerTexture = outerTexture;
    }

    public static LayerDefinition createSmallMobHeadLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		return LayerDefinition.create(meshDefinition, 32, 32);
	}

    public ResourceLocation getOuterTexture() {
        return this.outerTexture;
    }

    public SkullModel getOuterLayerModel() {
        return this.outerLayerModel;
    }

    @Override
    public void setupAnim(State state) {
        super.setupAnim(state);
        // Also setup the outer layer model
        this.outerLayerModel.setupAnim(state);
    }
}
