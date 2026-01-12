package net.ent.entstupidstuff.world.feature;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

public class LargerSpikedIceFeature extends Feature<LargeDripstoneConfiguration> {
	public LargerSpikedIceFeature(Codec<LargeDripstoneConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<LargeDripstoneConfiguration> context) {
		WorldGenLevel structureWorldAccess = context.level();
		BlockPos blockPos = context.origin();
		LargeDripstoneConfiguration largeDripstoneFeatureConfig = context.config();
		RandomSource random = context.random();
		if (!SpikedIceHelper.canGenerate(structureWorldAccess, blockPos)) {
			return false;
		} else {
			Optional<Column> optional = Column.scan(
				structureWorldAccess, blockPos, largeDripstoneFeatureConfig.floorToCeilingSearchRange, SpikedIceHelper::canGenerate, SpikedIceHelper::canReplaceOrLava
			);
			if (!optional.isEmpty() && optional.get() instanceof Column.Range) {
				Column.Range bounded = (Column.Range)optional.get();
				if (bounded.height() < 4) {
					return false;
				} else {
					int i = (int)((float)bounded.height() * largeDripstoneFeatureConfig.maxColumnRadiusToCaveHeightRatio);
					int j = Mth.clamp(i, largeDripstoneFeatureConfig.columnRadius.getMinValue(), largeDripstoneFeatureConfig.columnRadius.getMaxValue());
					int k = Mth.randomBetweenInclusive(random, largeDripstoneFeatureConfig.columnRadius.getMinValue(), j);
					LargerSpikedIceFeature.DripstoneGenerator dripstoneGenerator = createGenerator(
						blockPos.atY(bounded.ceiling() - 1), false, random, k, largeDripstoneFeatureConfig.stalactiteBluntness, largeDripstoneFeatureConfig.heightScale
					);
					LargerSpikedIceFeature.DripstoneGenerator dripstoneGenerator2 = createGenerator(
						blockPos.atY(bounded.floor() + 1), true, random, k, largeDripstoneFeatureConfig.stalagmiteBluntness, largeDripstoneFeatureConfig.heightScale
					);
					LargerSpikedIceFeature.WindModifier windModifier;
					if (dripstoneGenerator.generateWind(largeDripstoneFeatureConfig) && dripstoneGenerator2.generateWind(largeDripstoneFeatureConfig)) {
						windModifier = new LargerSpikedIceFeature.WindModifier(blockPos.getY(), random, largeDripstoneFeatureConfig.windSpeed);
					} else {
						windModifier = LargerSpikedIceFeature.WindModifier.create();
					}

					boolean bl = dripstoneGenerator.canGenerate(structureWorldAccess, windModifier);
					boolean bl2 = dripstoneGenerator2.canGenerate(structureWorldAccess, windModifier);
					if (bl) {
						dripstoneGenerator.generate(structureWorldAccess, random, windModifier);
					}

					if (bl2) {
						dripstoneGenerator2.generate(structureWorldAccess, random, windModifier);
					}

					return true;
				}
			} else {
				return false;
			}
		}
	}

	private static LargerSpikedIceFeature.DripstoneGenerator createGenerator(
		BlockPos pos, boolean isStalagmite, RandomSource random, int scale, FloatProvider bluntness, FloatProvider heightScale
	) {
		return new LargerSpikedIceFeature.DripstoneGenerator(pos, isStalagmite, scale, (double)bluntness.sample(random), (double)heightScale.sample(random));
	}

	/*private void testGeneration(StructureWorldAccess world, BlockPos pos, CaveSurface.Bounded surface, LargerSpikedIceFeature.WindModifier wind) {
		world.setBlockState(wind.modify(pos.withY(surface.getCeiling() - 1)), Blocks.DIAMOND_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
		world.setBlockState(wind.modify(pos.withY(surface.getFloor() + 1)), Blocks.GOLD_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);

		for (BlockPos.Mutable mutable = pos.withY(surface.getFloor() + 2).mutableCopy(); mutable.getY() < surface.getCeiling() - 1; mutable.move(Direction.UP)) {
			BlockPos blockPos = wind.modify(mutable);
			if (SpikedIceHelper.canGenerate(world, blockPos) || world.getBlockState(blockPos).isOf(Blocks.PACKED_ICE)) {
				world.setBlockState(blockPos, Blocks.CREEPER_HEAD.getDefaultState(), Block.NOTIFY_LISTENERS);
			}
		}
	}*/

	static final class DripstoneGenerator {
		private BlockPos pos;
		private final boolean isStalagmite;
		private int scale;
		private final double bluntness;
		private final double heightScale;

		DripstoneGenerator(BlockPos pos, boolean isStalagmite, int scale, double bluntness, double heightScale) {
			this.pos = pos;
			this.isStalagmite = isStalagmite;
			this.scale = scale;
			this.bluntness = bluntness;
			this.heightScale = heightScale;
		}

		private int getBaseScale() {
			return this.scale(0.0F);
		}

		/*private int getBottomY() {
			return this.isStalagmite ? this.pos.getY() : this.pos.getY() - this.getBaseScale();
		}

		private int getTopY() {
			return !this.isStalagmite ? this.pos.getY() : this.pos.getY() + this.getBaseScale();
		}*/

		boolean canGenerate(WorldGenLevel world, LargerSpikedIceFeature.WindModifier wind) {
			while (this.scale > 1) {
				BlockPos.MutableBlockPos mutable = this.pos.mutable();
				int i = Math.min(10, this.getBaseScale());

				for (int j = 0; j < i; j++) {
					if (world.getBlockState(mutable).is(Blocks.LAVA)) {
						return false;
					}

					if (SpikedIceHelper.canGenerateBase(world, wind.modify(mutable), this.scale)) {
						this.pos = mutable;
						return true;
					}

					mutable.move(this.isStalagmite ? Direction.DOWN : Direction.UP);
				}

				this.scale /= 2;
			}

			return false;
		}

		private int scale(float height) {
			return (int)SpikedIceHelper.scaleHeightFromRadius((double)height, (double)this.scale, this.heightScale, this.bluntness);
		}

		void generate(WorldGenLevel world, RandomSource random, LargerSpikedIceFeature.WindModifier wind) {
			for (int i = -this.scale; i <= this.scale; i++) {
				for (int j = -this.scale; j <= this.scale; j++) {
					float f = Mth.sqrt((float)(i * i + j * j));
					if (!(f > (float)this.scale)) {
						int k = this.scale(f);
						if (k > 0) {
							if ((double)random.nextFloat() < 0.2) {
								k = (int)((float)k * Mth.randomBetween(random, 0.8F, 1.0F));
							}

							BlockPos.MutableBlockPos mutable = this.pos.offset(i, 0, j).mutable();
							boolean bl = false;
							int l = this.isStalagmite ? world.getHeight(Heightmap.Types.WORLD_SURFACE_WG, mutable.getX(), mutable.getZ()) : Integer.MAX_VALUE;

							for (int m = 0; m < k && mutable.getY() < l; m++) {
								BlockPos blockPos = wind.modify(mutable);
								if (SpikedIceHelper.canGenerateOrLava(world, blockPos)) {
									bl = true;
									Block block = Blocks.PACKED_ICE ;
									world.setBlock(blockPos, block.defaultBlockState(), Block.UPDATE_CLIENTS);
								} else if (bl && world.getBlockState(blockPos).is(BlockTags.BASE_STONE_OVERWORLD)) {
									break;
								}

								mutable.move(this.isStalagmite ? Direction.UP : Direction.DOWN);
							}
						}
					}
				}
			}
		}

		boolean generateWind(LargeDripstoneConfiguration config) {
			return this.scale >= config.minRadiusForWind && this.bluntness >= (double)config.minBluntnessForWind;
		}
	}

	static final class WindModifier {
		private final int y;
		@Nullable
		private final Vec3 wind;

		WindModifier(int y, RandomSource random, FloatProvider wind) {
			this.y = y;
			float f = wind.sample(random);
			float g = Mth.randomBetween(random, 0.0F, (float) Math.PI);
			this.wind = new Vec3((double)(Mth.cos(g) * f), 0.0, (double)(Mth.sin(g) * f));
		}

		private WindModifier() {
			this.y = 0;
			this.wind = null;
		}

		static LargerSpikedIceFeature.WindModifier create() {
			return new LargerSpikedIceFeature.WindModifier();
		}

		BlockPos modify(BlockPos pos) {
			if (this.wind == null) {
				return pos;
			} else {
				int i = this.y - pos.getY();
				Vec3 vec3d = this.wind.scale((double)i);
				return pos.offset(Mth.floor(vec3d.x), 0, Mth.floor(vec3d.z));
			}
		}
	}
}
