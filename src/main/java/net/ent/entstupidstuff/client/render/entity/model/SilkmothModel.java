package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.render.entity.state.SilkmothRenderstate;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class SilkmothModel extends EntityModel<SilkmothRenderstate>{

    private final ModelPart left_wing;
	private final ModelPart right_wing;

    public SilkmothModel(ModelPart root) {
        super(root);
		root.getChild("head");
		root.getChild("body");
		this.left_wing = root.getChild("left_wing");
		this.right_wing = root.getChild("right_wing");
	}

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 24).addBox(1.5F, -3.0F, -4.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 24).addBox(-1.5F, -3.0F, -4.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, -4.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(19, 1).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-1.5F, -2.5F, -8.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(22, 14).addBox(1.5F, 0.5F, -8.0F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(22, 22).addBox(-0.5F, 0.5F, -8.0F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 22.5F, 4.0F));

		PartDefinition left_wing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -3.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 22.0F, -0.5F));

		PartDefinition right_wing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 7).addBox(-6.0F, 0.0F, -3.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.0F, -0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    public void setupAnim(SilkmothRenderstate SilkmothRenderstate) {
        super.setupAnim(SilkmothRenderstate);
        if (!SilkmothRenderstate.isOnGround) {
			float f = SilkmothRenderstate.ageInTicks * 120.32113F * (float) (Math.PI / 180.0);
			this.right_wing.yRot = 0.0F;
			this.right_wing.zRot = Mth.cos(f) * (float) Math.PI * 0.15F;
			this.left_wing.xRot = this.right_wing.xRot;
			this.left_wing.yRot = this.right_wing.yRot;
			this.left_wing.zRot = -this.right_wing.zRot;
		}
        
    }
    
}
