package net.ent.entstupidstuff.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;




@Environment(EnvType.CLIENT)
public class RedStoneGolemModel extends EntityModel<LivingEntityRenderState>{
    
    private final ModelPart upperbody;
	private final ModelPart head;
	@SuppressWarnings("unused")
	private final ModelPart leftarm;
	@SuppressWarnings("unused")
	private final ModelPart lefthand;
	@SuppressWarnings("unused")
	private final ModelPart rightarm;
	@SuppressWarnings("unused")
	private final ModelPart righthand;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart root;


	public RedStoneGolemModel(ModelPart root) {
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


	@SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData upperbody = modelPartData.addChild("upperbody", ModelPartBuilder.create().uv(0, 0).cuboid(-20.0F, -37.9848F, -11.8264F, 40.0F, 31.0F, 20.0F, new Dilation(0.0F))
		.uv(49, 90).cuboid(-8.0F, -25.9848F, 0.1736F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
		.uv(120, 36).cuboid(-11.0F, -7.9848F, -6.8264F, 22.0F, 7.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData head = upperbody.addChild("head", ModelPartBuilder.create().uv(124, 8).cuboid(-8.0F, -10.6236F, -12.5579F, 16.0F, 15.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -26.3612F, -11.2685F));

		ModelPartData leftarm = upperbody.addChild("leftarm", ModelPartBuilder.create().uv(0, 52).cuboid(-0.1896F, -4.5601F, -6.3104F, 14.0F, 24.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(19.0F, -31.3612F, -0.2685F, -0.1731F, -0.0227F, -0.1289F));

		ModelPartData lefthand = leftarm.addChild("lefthand", ModelPartBuilder.create().uv(97, 55).cuboid(2.0F, 25.5212F, -6.3806F, 3.0F, 10.0F, 5.0F, new Dilation(0.0F))
		.uv(81, 57).cuboid(-4.0F, 25.5212F, -10.3806F, 3.0F, 8.0F, 5.0F, new Dilation(0.0F))
		.uv(97, 55).cuboid(2.0F, 25.5212F, -12.3806F, 3.0F, 10.0F, 5.0F, new Dilation(0.0F))
		.uv(3, 88).cuboid(-5.0F, 6.5212F, -13.3806F, 11.0F, 22.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(10.3325F, 15.4742F, 6.6896F, -0.3054F, 0.0F, 0.1309F));

		ModelPartData rightarm = upperbody.addChild("rightarm", ModelPartBuilder.create().uv(0, 52).mirrored().cuboid(-13.8331F, -4.7322F, -5.3256F, 14.0F, 24.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-19.0F, -31.3612F, -1.2685F, -0.1731F, 0.0227F, 0.1289F));

		ModelPartData righthand = rightarm.addChild("righthand", ModelPartBuilder.create().uv(97, 55).mirrored().cuboid(-5.0F, 25.5212F, -6.3806F, 3.0F, 10.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(81, 57).mirrored().cuboid(1.0F, 25.5212F, -10.3806F, 3.0F, 8.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(97, 55).mirrored().cuboid(-5.0F, 25.5212F, -12.3806F, 3.0F, 10.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(3, 88).mirrored().cuboid(-6.0F, 6.5212F, -13.3806F, 11.0F, 22.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.3552F, 15.302F, 7.6744F, -0.3054F, 0.0F, -0.1309F));

		ModelPartData leftleg = modelPartData.addChild("leftleg", ModelPartBuilder.create().uv(113, 58).cuboid(-5.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(9.0F, 4.0F, 1.0F));

		ModelPartData rightleg = modelPartData.addChild("rightleg", ModelPartBuilder.create().uv(113, 58).mirrored().cuboid(-7.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-9.0F, 4.0F, 1.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}

}
