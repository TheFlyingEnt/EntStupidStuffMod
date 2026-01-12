package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

@SuppressWarnings("unused")
public class StrongShieldEntityModel extends ShieldModel{

    /**
	 * The key of the plate model part, whose value is {@value}.
	 */
	private static final String PLATE = "plate";
	/**
	 * The key of the handle model part, whose value is {@value}.
	 */
	private static final String HANDLE = "handle";
	private static final int field_32551 = 10;
	private static final int field_32552 = 20;
	private final ModelPart root;
	private final ModelPart plate;
	private final ModelPart handle;
	

	public StrongShieldEntityModel(ModelPart root) {
		super(root);
		this.root = root;
		this.plate = root.getChild("plate");
		this.handle = root.getChild("handle");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F), PartPose.ZERO);

        modelPartData.addOrReplaceChild("side_1", CubeListBuilder.create().texOffs(54, 0).mirror().addBox(0.0F, -11.0F, 0.0F, 3.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.5F, 0.0F, -1.1F, 0.0F, 22.5F, 0.0F));
        modelPartData.addOrReplaceChild("side_2", CubeListBuilder.create().texOffs(54, 0).addBox(-3.0F, -11.0F, 0.0F, 3.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 0.0F, -1.1F, 0.0F, -22.5F, 0.0F));
        
		modelPartData.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), PartPose.ZERO);
		return LayerDefinition.create(modelData, 64, 64);
	}

	public ModelPart plate() {
		return this.plate;
	}

	public ModelPart handle() {
		return this.handle;
	}

	public ModelPart getSide(){
		return root.getChild("side_1");
	}

	public ModelPart getSide2(){
		return root.getChild("side_2");
	}
    
}
