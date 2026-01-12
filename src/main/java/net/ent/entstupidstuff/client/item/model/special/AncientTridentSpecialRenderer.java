package net.ent.entstupidstuff.client.item.model.special;

import java.util.Set;

import org.joml.Vector3f;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.TridentSpecialRenderer;
import net.minecraft.world.item.ItemDisplayContext;

@Environment(EnvType.CLIENT)
public class AncientTridentSpecialRenderer implements NoDataSpecialModelRenderer {
	private final AncientTridentModel model;

	public AncientTridentSpecialRenderer(AncientTridentModel model) {
		this.model = model;
	}

	@Override
	public void submit(ItemDisplayContext displayContext, PoseStack matrices, SubmitNodeCollector queue, int light, int overlay, boolean glint, int i) {
		matrices.pushPose();
		matrices.scale(1.0F, -1.0F, -1.0F);
		queue.submitModelPart(this.model.root(), matrices, this.model.renderType(AncientTridentModel.TEXTURE), light, overlay, null, false, glint, -1, null, i);
		matrices.popPose();
	}

	@Override
	public void getExtents(Set<Vector3f> vertices) {
		PoseStack matrixStack = new PoseStack();
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		this.model.root().getExtentsForGui(matrixStack, vertices);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TridentSpecialRenderer.Unbaked> CODEC = MapCodec.unit(new TridentSpecialRenderer.Unbaked());

		@Override
		public MapCodec<TridentSpecialRenderer.Unbaked> type() {
			return CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
			return new AncientTridentSpecialRenderer(new AncientTridentModel(context.entityModelSet().bakeLayer(ModEntityModelLayers.ANCIENT_TRIDENT)));
		}
	}
}
