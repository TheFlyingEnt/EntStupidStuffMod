package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;

//Clean-up
public class LobberModel extends ZombieEntityModel<ZombieEntityRenderState> {

    public final float ARM_EXTENSION = 1f;
    //public final float leftoriginX = 

    public LobberModel(ModelPart root) {
        super(root);
        this.rightArm.setOrigin(this.rightArm.originX, this.rightArm.originY, this.rightArm.originZ/* - ARM_EXTENSION */);
        this.leftArm.setOrigin(this.leftArm.originX, this.leftArm.originY, this.leftArm.originZ);
        this.leftLeg.setOrigin(this.leftLeg.originX, this.leftLeg.originY, this.leftLeg.originZ);

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = ZombieEntityModel.getModelData(Dilation.NONE, 0.0F);
    
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create()
            .uv(32, 46)
            .cuboid(-3.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F);

        ModelPartBuilder modelPartBuilderLeft = ModelPartBuilder.create()
            .uv(40, 16)
            .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F);

        ModelPartBuilder leftLeg = ModelPartBuilder.create() //Left Leg
           .uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
    
        modelData.getRoot().addChild("right_arm", modelPartBuilder, ModelTransform.origin(-5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_arm", modelPartBuilderLeft, ModelTransform.origin(5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_leg", leftLeg, ModelTransform.origin(1.9F, 12.0F, 0.0F));
    
        return TexturedModelData.of(modelData, 64, 64);
    }

}
