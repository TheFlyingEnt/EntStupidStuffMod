package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class WarBannerFlagModel extends Model<Float> {
	private final ModelPart flag;
    private final ModelPart flag_middle;
    private final ModelPart flag_end;

	public WarBannerFlagModel(ModelPart modelPart) {
		super(modelPart, RenderType::entitySolid);
		this.flag = modelPart.getChild("flag");
        this.flag_middle = modelPart.getChild("flag").getChild("flag_middle");
        this.flag_end = modelPart.getChild("flag").getChild("flag_middle").getChild("flag_end");
	}

	public static LayerDefinition createFlagLayer(boolean bl) {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		/*if (bl) { //Standing
		    partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -0.5F, 20.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
        } else {
            partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 9.0F, -1.5708F, 0.0F, -1.5708F));
        }*/

        if (bl) { //Standing
		    //partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -0.5F, 20.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
            PartDefinition flag = partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -0.5F, 20.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -32.0F, 0.0F, 0.0F, 0.0F, -1.5708F));
		    PartDefinition flag_middle = flag.addOrReplaceChild("flag_middle", CubeListBuilder.create().texOffs(0, 14).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 14.0F, 0.0F));
		    flag_middle.addOrReplaceChild("flag_end", CubeListBuilder.create().texOffs(0, 28).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));
            
        } else {
            //partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 9.0F, -1.5708F, 0.0F, -1.5708F));
            PartDefinition flag = partDefinition.addOrReplaceChild("flag", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 9.0F, -1.5708F, 0.0F, -1.5708F));
		    PartDefinition flag_middle = flag.addOrReplaceChild("flag_middle", CubeListBuilder.create().texOffs(0, 14).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));
		    flag_middle.addOrReplaceChild("flag_end", CubeListBuilder.create().texOffs(0, 28).addBox(-10.0F, 0.0F, -0.5F, 20.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));
        }

        




		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	/*public void setupAnim(Float float_) {
		super.setupAnim(float_);
		this.flag.xRot = (-0.0125F + 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
        this.flag_middle.xRot = (0.0125F - 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
        this.flag_end.xRot = (-0.0125F + 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
	}*/

    public void setupAnim(Float float_) {
		super.setupAnim(float_);
		this.flag.xRot = (-0.0125F + 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
        this.flag_middle.xRot = (0.0125F - 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
        this.flag_end.xRot = (-0.0125F + 0.01F * Mth.cos((float) (Math.PI * 2) * float_)) * (float) Math.PI;
	}



}
