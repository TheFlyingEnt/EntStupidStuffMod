package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@SuppressWarnings("unused")
public class StrongShieldEntityModel extends ShieldEntityModel{

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

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("plate", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F), ModelTransform.NONE);

        modelPartData.addChild("side_1", ModelPartBuilder.create().uv(54, 0).mirrored().cuboid(0.0F, -11.0F, 0.0F, 3.0F, 22.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.5F, 0.0F, -1.1F, 0.0F, 22.5F, 0.0F));
        modelPartData.addChild("side_2", ModelPartBuilder.create().uv(54, 0).cuboid(-3.0F, -11.0F, 0.0F, 3.0F, 22.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.5F, 0.0F, -1.1F, 0.0F, -22.5F, 0.0F));
        
		modelPartData.addChild("handle", ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), ModelTransform.NONE);
		return TexturedModelData.of(modelData, 64, 64);
	}

	public ModelPart getPlate() {
		return this.plate;
	}

	public ModelPart getHandle() {
		return this.handle;
	}

	public ModelPart getSide(){
		return root.getChild("side_1");
	}

	public ModelPart getSide2(){
		return root.getChild("side_2");
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.root.render(matrices, vertices, light, overlay, color);
	}
    
}
