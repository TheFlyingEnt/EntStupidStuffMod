package net.ent.entstupidstuff.client.item.model.special;

import java.util.Objects;
import java.util.Set;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.client.render.HorizontalBannerRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.BannerSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class HorizontalBannerSpecialRenderer implements SpecialModelRenderer<BannerPatternLayers> {
	private final HorizontalBannerRenderer HorizontalBannerRenderer;
	private final DyeColor baseColor;

	public HorizontalBannerSpecialRenderer(DyeColor dyeColor, HorizontalBannerRenderer HorizontalBannerRenderer) {
		this.HorizontalBannerRenderer = HorizontalBannerRenderer;
		this.baseColor = dyeColor;
	}

	@Nullable
	public BannerPatternLayers extractArgument(ItemStack itemStack) {
		return itemStack.get(DataComponents.BANNER_PATTERNS);
	}

	public void submit(
		@Nullable BannerPatternLayers bannerPatternLayers,
		ItemDisplayContext itemDisplayContext,
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int i,
		int j,
		boolean bl,
		int k
	) {
        Minecraft mc = Minecraft.getInstance();
        float ageInTicks = (mc.level != null ? (float) mc.level.getGameTime() : 0.0F) + mc.getDeltaTracker().getGameTimeDeltaPartialTick(true);

		this.HorizontalBannerRenderer.submitSpecial(
            poseStack, submitNodeCollector, i, j, this.baseColor,
            (BannerPatternLayers) Objects.requireNonNullElse(bannerPatternLayers, BannerPatternLayers.EMPTY),
            k, ageInTicks // pass raw ticks, division happens in submitSpecial
        );
	}

	@Override
	public void getExtents(Set<Vector3f> set) {
		this.HorizontalBannerRenderer.getExtents(set);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked(DyeColor baseColor) implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<HorizontalBannerSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(DyeColor.CODEC.fieldOf("color").forGetter(HorizontalBannerSpecialRenderer.Unbaked::baseColor))
				.apply(instance, HorizontalBannerSpecialRenderer.Unbaked::new)
		);

		@Override
		public MapCodec<HorizontalBannerSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext bakingContext) {
			return new HorizontalBannerSpecialRenderer(this.baseColor, new HorizontalBannerRenderer(bakingContext));
		}
	}
}
