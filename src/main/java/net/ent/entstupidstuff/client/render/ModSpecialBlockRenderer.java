package net.ent.entstupidstuff.client.render;

import java.util.Map;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;

public class ModSpecialBlockRenderer{
	public static final ModSpecialBlockRenderer EMPTY = new ModSpecialBlockRenderer(Map.of());
	private final Map<Block, SpecialModelRenderer<?>> renderers;

	public ModSpecialBlockRenderer(Map<Block, SpecialModelRenderer<?>> map) {
		this.renderers = map;
	}

	public static ModSpecialBlockRenderer vanilla(SpecialModelRenderer.BakingContext bakingContext) {
		return new ModSpecialBlockRenderer(ModSpecialModelTypes.createBlockRenderers(bakingContext));
	}

	public void renderByBlock(
		Block block, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, int k
	) {
		SpecialModelRenderer<?> specialModelRenderer = (SpecialModelRenderer<?>)this.renderers.get(block);
		if (specialModelRenderer != null) {
			specialModelRenderer.submit(null, itemDisplayContext, poseStack, submitNodeCollector, i, j, false, k);
		}
	}
}
