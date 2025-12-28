package net.ent.entstupidstuff.client.item.model.special;

import java.util.Set;

import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.model.special.SimpleSpecialModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.item.model.special.TridentModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;

@Environment(EnvType.CLIENT)
public class AncientTridentSpecialRenderer implements SimpleSpecialModelRenderer {
	private final AncientTridentModel model;

	public AncientTridentSpecialRenderer(AncientTridentModel model) {
		this.model = model;
	}

	@Override
	public void render(ItemDisplayContext displayContext, MatrixStack matrices, OrderedRenderCommandQueue queue, int light, int overlay, boolean glint, int i) {
		matrices.push();
		matrices.scale(1.0F, -1.0F, -1.0F);
		queue.submitModelPart(this.model.getRootPart(), matrices, this.model.getLayer(AncientTridentModel.TEXTURE), light, overlay, null, false, glint, -1, null, i);
		matrices.pop();
	}

	@Override
	public void collectVertices(Set<Vector3f> vertices) {
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		this.model.getRootPart().collectVertices(matrixStack, vertices);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TridentModelRenderer.Unbaked> CODEC = MapCodec.unit(new TridentModelRenderer.Unbaked());

		@Override
		public MapCodec<TridentModelRenderer.Unbaked> getCodec() {
			return CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakeContext context) {
			return new AncientTridentSpecialRenderer(new AncientTridentModel(context.entityModelSet().getModelPart(ModEntityModelLayers.ANCIENT_TRIDENT)));
		}
	}
}
