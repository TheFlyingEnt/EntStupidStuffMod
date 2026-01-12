package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

public class ScorchedModel extends ZombieModel<ZombieRenderState>{

    public ScorchedModel(ModelPart root) {
        super(root);
        this.leftArm.setPos(this.leftArm.x, this.leftArm.y, this.leftArm.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);

    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = ZombieModel.createMesh(CubeDeformation.NONE, 0.0F);
    
        CubeListBuilder rightArm = CubeListBuilder.create() //Right Arm
            .texOffs(40, 16)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        CubeListBuilder leftArm = CubeListBuilder.create() //Left Arm
            .texOffs(32, 48)
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        CubeListBuilder leftLeg = CubeListBuilder.create() //Left Leg
           .texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
    
        modelData.getRoot().addOrReplaceChild("right_arm", rightArm, PartPose.offset(-5.0F, 2.0F, 0.0F));
        modelData.getRoot().addOrReplaceChild("left_arm", leftArm, PartPose.offset(5.0F, 2.0F, 0.0F));
        modelData.getRoot().addOrReplaceChild("left_leg", leftLeg, PartPose.offset(1.9F, 12.0F, 0.0F));
    
        return LayerDefinition.create(modelData, 64, 64);
    }

    
}
