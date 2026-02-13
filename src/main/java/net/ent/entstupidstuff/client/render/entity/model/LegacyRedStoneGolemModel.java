package net.ent.entstupidstuff.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;




@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public class LegacyRedStoneGolemModel extends EntityModel<LivingEntityRenderState>{
    
    private final ModelPart upperbody;
	private final ModelPart head;
	private final ModelPart leftarm;
	private final ModelPart lefthand;
	private final ModelPart rightarm;
	private final ModelPart righthand;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart root;


	public LegacyRedStoneGolemModel(ModelPart root) {
		super(root);
		this.root = root;
		this.upperbody = root.getChild("upperbody");
		this.head = root.getChild("upperbody").getChild("head");
		this.leftarm = root.getChild("upperbody").getChild("leftarm");
		this.lefthand = root.getChild("upperbody").getChild("leftarm").getChild("lefthand");
		this.rightarm = root.getChild("upperbody").getChild("rightarm");
		this.righthand = root.getChild("upperbody").getChild("rightarm").getChild("righthand");
		this.leftleg = root.getChild("leftleg");
		this.rightleg = root.getChild("rightleg");
	}

    public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition upperbody = modelPartData.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -37.9848F, -11.8264F, 40.0F, 31.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(49, 90).addBox(-8.0F, -25.9848F, 0.1736F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(120, 36).addBox(-11.0F, -7.9848F, -6.8264F, 22.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition head = upperbody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(124, 8).addBox(-8.0F, -10.6236F, -12.5579F, 16.0F, 15.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.3612F, -11.2685F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 52).addBox(-0.1896F, -4.5601F, -6.3104F, 14.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, -31.3612F, -0.2685F, -0.1731F, -0.0227F, -0.1289F));

		PartDefinition lefthand = leftarm.addOrReplaceChild("lefthand", CubeListBuilder.create().texOffs(97, 55).addBox(2.0F, 25.5212F, -6.3806F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(81, 57).addBox(-4.0F, 25.5212F, -10.3806F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(97, 55).addBox(2.0F, 25.5212F, -12.3806F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(3, 88).addBox(-5.0F, 6.5212F, -13.3806F, 11.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.3325F, 15.4742F, 6.6896F, -0.3054F, 0.0F, 0.1309F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-13.8331F, -4.7322F, -5.3256F, 14.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.0F, -31.3612F, -1.2685F, -0.1731F, 0.0227F, 0.1289F));

		PartDefinition righthand = rightarm.addOrReplaceChild("righthand", CubeListBuilder.create().texOffs(97, 55).mirror().addBox(-5.0F, 25.5212F, -6.3806F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(81, 57).mirror().addBox(1.0F, 25.5212F, -10.3806F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(97, 55).mirror().addBox(-5.0F, 25.5212F, -12.3806F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 88).mirror().addBox(-6.0F, 6.5212F, -13.3806F, 11.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.3552F, 15.302F, 7.6744F, -0.3054F, 0.0F, -0.1309F));

		PartDefinition leftleg = modelPartData.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(113, 58).addBox(-5.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, 1.0F));

		PartDefinition rightleg = modelPartData.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(113, 58).mirror().addBox(-7.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 4.0F, 1.0F));
		return LayerDefinition.create(modelData, 256, 256);
	}

}
