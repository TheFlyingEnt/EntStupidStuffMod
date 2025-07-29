package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AncientTridentModel extends Model {
   public static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_trident.png");
   private final ModelPart root;

   public AncientTridentModel(ModelPart root) {
      //super(RenderLayer::getEntitySolid);
      super(texture -> RenderLayer.getEntityTranslucent(texture));
      this.root = root;
   }

   public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData pole = modelPartData.addChild("pole", ModelPartBuilder.create().uv(0, 6).cuboid(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData seaweed = pole.addChild("seaweed", ModelPartBuilder.create().uv(8, 6).cuboid(-0.5F, -27.0F, -0.5F, 1.0F, 25.0F, 1.0F, new Dilation(0.125F)), ModelTransform.pivot(0.0F, 29.0F, 0.0F));

		ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create().uv(4, 0).cuboid(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 0).cuboid(-3.5F, -7.0F, 0.0F, 7.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_spike = modelPartData.addChild("left_spike", ModelPartBuilder.create().uv(4, 3).cuboid(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_spike = modelPartData.addChild("right_spike", ModelPartBuilder.create().uv(4, 3).mirrored().cuboid(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData middle_spike = modelPartData.addChild("middle_spike", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
   }

   public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.root.render(matrices, vertices, light, overlay, color);
   }
}