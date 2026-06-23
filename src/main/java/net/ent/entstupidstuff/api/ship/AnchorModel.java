package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
 
/** Simple anchor shape: shank + stock + flukes. */
public class AnchorModel extends EntityModel<AnchorRenderState> {
 
    public AnchorModel(ModelPart root) {
        super(root);
    }
 
    @Override
    public void setupAnim(AnchorRenderState state) {
        // static for now
    }
 
    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("shank",
            CubeListBuilder.create().texOffs(0, 0).addBox(-1f, -6f, -1f, 2f, 12f, 2f), PartPose.ZERO);
        root.addOrReplaceChild("stock",
            CubeListBuilder.create().texOffs(0, 0).addBox(-5f, -5f, -1f, 10f, 2f, 2f), PartPose.ZERO);
        root.addOrReplaceChild("flukes",
            CubeListBuilder.create().texOffs(0, 0).addBox(-6f, 4f, -1f, 12f, 2f, 2f), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }
}

