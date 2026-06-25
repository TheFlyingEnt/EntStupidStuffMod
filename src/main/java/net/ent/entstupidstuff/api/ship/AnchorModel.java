package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
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
        root.addOrReplaceChild("anchor", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -5.74F, -0.5F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-5.0F, -4.74F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 14).addBox(1.0F, -4.74F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 19).addBox(-3.0F, -8.74F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 19).addBox(1.0F, -8.74F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-2.0F, -9.74F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 12).addBox(-1.0F, -10.74F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 17).addBox(-2.0F, -6.74F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 14).addBox(-2.0F, 4.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 22).addBox(-3.0F, 4.26F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-5.0F, 2.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-6.0F, -0.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 22).addBox(-5.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-8.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(-7.0F, -1.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-4.0F, 3.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(1.0F, 4.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 22).addBox(2.0F, 4.26F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 22).addBox(3.0F, 3.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 8).addBox(4.0F, 2.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 24).addBox(4.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 18).addBox(5.0F, -0.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 6).addBox(6.0F, -1.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 12).addBox(7.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.74F, 0.5F, 0.0F, 0.0F, -3.1416F));
        return LayerDefinition.create(mesh, 32, 32);
    }
}

