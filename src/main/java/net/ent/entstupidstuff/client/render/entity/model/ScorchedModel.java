package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;

public class ScorchedModel extends ZombieEntityModel<ZombieEntityRenderState>{

    public ScorchedModel(ModelPart root) {
        super(root);
        this.leftArm.setOrigin(this.leftArm.originX, this.leftArm.originY, this.leftArm.originZ);
        this.leftLeg.setOrigin(this.leftLeg.originX, this.leftLeg.originY, this.leftLeg.originZ);
        this.leftLeg.setOrigin(this.leftLeg.originX, this.leftLeg.originY, this.leftLeg.originZ);

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = ZombieEntityModel.getModelData(Dilation.NONE, 0.0F);
    
        ModelPartBuilder rightArm = ModelPartBuilder.create() //Right Arm
            .uv(40, 16)
            .cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        ModelPartBuilder leftArm = ModelPartBuilder.create() //Left Arm
            .uv(32, 48)
            .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);

        ModelPartBuilder leftLeg = ModelPartBuilder.create() //Left Leg
            .uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
    
        modelData.getRoot().addChild("right_arm", rightArm, ModelTransform.origin(-5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_arm", leftArm, ModelTransform.origin(5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_leg", leftLeg, ModelTransform.origin(2f, 2F, 2F));
    
        return TexturedModelData.of(modelData, 64, 64);
    }

    
}
