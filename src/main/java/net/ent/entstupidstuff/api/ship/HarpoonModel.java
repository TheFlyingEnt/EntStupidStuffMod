package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class HarpoonModel extends EntityModel<AnchorRenderState> {
 
    public HarpoonModel(ModelPart root) {
        super(root);
    }
 
    @Override
    public void setupAnim(AnchorRenderState state) {
        // static for now
    }
 
    public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition harpoon = partdefinition.addOrReplaceChild("harpoon", CubeListBuilder.create().texOffs(20, 9).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-1.0F, 14.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-1.0F, 18.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-4.0F, -13.0F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition cube_r1 = harpoon.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.5F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}