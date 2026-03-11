package net.ent.entstupidstuff.client.render;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.client.item.model.special.AncientTridentSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.CopperShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.DiamondShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.HorizontalBannerSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.IronShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.NetheriteShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.StoneShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.WoodenShieldSpecialRenderer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.renderer.special.SkullSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;

@Environment(EnvType.CLIENT)
public class ModSpecialModelTypes {

    public static void onInit() {

        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "wooden_shield"),
            WoodenShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "stone_shield"),
            StoneShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "iron_shield"),
            IronShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "copper_shield"),
            CopperShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "diamond_shield"),
            DiamondShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "netherite_shield"),
            NetheriteShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "ancient_trident"),
            AncientTridentSpecialRenderer.Unbaked.CODEC
        );

        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "horizontal_banner"),
            HorizontalBannerSpecialRenderer.Unbaked.MAP_CODEC
        );
    }

    public static final ExtraCodecs.LateBoundIdMapper<ResourceLocation, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<SpecialModelRenderer.Unbaked> CODEC = ID_MAPPER.codec(ResourceLocation.CODEC)
		.dispatch(SpecialModelRenderer.Unbaked::type, mapCodec -> mapCodec);

    private static final Map<Block, SpecialModelRenderer.Unbaked> STATIC_BLOCK_MAPPING = ImmutableMap.<Block, SpecialModelRenderer.Unbaked>builder()
		.put(Blocks.SKELETON_SKULL, new SkullSpecialRenderer.Unbaked(SkullBlock.Types.SKELETON))
	.build();

    public static Map<Block, SpecialModelRenderer<?>> createBlockRenderers(SpecialModelRenderer.BakingContext bakingContext) {
		Map<Block, SpecialModelRenderer.Unbaked> map = new HashMap(STATIC_BLOCK_MAPPING);

		Builder<Block, SpecialModelRenderer<?>> builder = ImmutableMap.builder();
		map.forEach((block, unbaked) -> {
			SpecialModelRenderer<?> specialModelRenderer = unbaked.bake(bakingContext);
			if (specialModelRenderer != null) {
				builder.put(block, specialModelRenderer);
			}
		});
		return builder.build();
	}
}
