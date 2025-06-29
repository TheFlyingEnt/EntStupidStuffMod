package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.ent.entstupidstuff.entity.mob.LobberZombieEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.util.Identifier;

//Clean-up
public class LobberModel<T extends LobberZombieEntity> extends ZombieEntityModel<T> {

    public final float ARM_EXTENSION = 1f;
    //public final float leftPivotX = 

    public LobberModel(ModelPart root) {
        super(root);
        this.rightArm.setPivot(this.rightArm.pivotX, this.rightArm.pivotY, this.rightArm.pivotZ/* - ARM_EXTENSION */);
        this.leftArm.setPivot(this.leftArm.pivotX, this.leftArm.pivotY, this.leftArm.pivotZ);
        this.leftLeg.setPivot(this.leftLeg.pivotX, this.leftLeg.pivotY, this.leftLeg.pivotZ);

    }

    public Identifier getTexture(T entity) {
        return Identifier.of("entstupidstuff", "textures/entity/zombie_lobber.png"); // Replace with your modid and texture path
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = ZombieEntityModel.getModelData(Dilation.NONE, 0.0F);
    
        // Define parts using ModelPartBuilder
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create()
            .uv(32, 46) // Example: Set UV coordinates
            .cuboid(-3.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F); // Example: Define cuboid for right arm

        ModelPartBuilder modelPartBuilderLeft = ModelPartBuilder.create()
            .uv(40, 16) // Example: Set UV coordinates
            //.cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F);
            .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F);

        ModelPartBuilder modelPartBuilderLeg = ModelPartBuilder.create()
            .uv(16, 48) // Example: Set UV coordinates
            .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
    
        modelData.getRoot().addChild("right_arm", modelPartBuilder, ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_arm", modelPartBuilderLeft, ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        modelData.getRoot().addChild("left_leg", modelPartBuilderLeg, ModelTransform.pivot(2f, 2F, 2F));
    
        return TexturedModelData.of(modelData, 64, 64);
    }

}
