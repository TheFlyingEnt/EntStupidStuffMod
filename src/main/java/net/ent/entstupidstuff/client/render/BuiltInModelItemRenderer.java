package net.ent.entstupidstuff.client.render;

import java.util.Objects;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BuiltInModelItemRenderer {

    public static final SpriteIdentifier DIAMOND_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/diamond_shield_base")
	);

    public static final SpriteIdentifier DIAMOND_SHIELD_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/diamond_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_OAK_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_oak_shield_base")
	);

    public static final SpriteIdentifier WOODEN_OAK_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_oak_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_SPRUCE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_spruce_shield_base")
	);

    public static final SpriteIdentifier WOODEN_SPRUCE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_spruce_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_JUNGLE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_jungle_shield_base")
	);

	public static final SpriteIdentifier WOODEN_BIRCH_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_birch_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_BIRCH_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_birch_shield_base")
	);

    public static final SpriteIdentifier WOODEN_JUNGLE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_jungle_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_ACACIA_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_acacia_shield_base")
	);

    public static final SpriteIdentifier WOODEN_ACACIA_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_acacia_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_DARK_OAK_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_dark_oak_shield_base")
	);

    public static final SpriteIdentifier WOODEN_DARK_OAK_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_dark_oak_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_MANGROVE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_mangrove_shield_base")
	);

    public static final SpriteIdentifier WOODEN_MANGROVE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_mangrove_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_CHERRY_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_cherry_shield_base")
	);

    public static final SpriteIdentifier WOODEN_CHERRY_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_cherry_shield_base_nopattern")
	);

    public static final SpriteIdentifier WOODEN_BAMBOO_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_bamboo_shield_base")
	);

    public static final SpriteIdentifier WOODEN_BAMBOO_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/wooden_bamboo_shield_base_nopattern")
	);

    public static final SpriteIdentifier STONE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_shield_base")
	);

    public static final SpriteIdentifier STONE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_shield_base_nopattern")
	);

    public static final SpriteIdentifier STONE_DEEPSLATE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_deepslate_shield_base")
	);

    public static final SpriteIdentifier STONE_DEEPSLATE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_deepslate_shield_base_nopattern")
	);

    public static final SpriteIdentifier STONE_BLACKSTONE_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_blackstone_shield_base")
	);

    public static final SpriteIdentifier STONE_BLACKSTONE_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/stone_blackstone_shield_base_nopattern")
	);

    public static final SpriteIdentifier GOLDEN_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/golden_shield_base")
	);

    public static final SpriteIdentifier GOLDEN_BASE_NO_PATTERN_1 = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of(EntStupidStuff.MOD_ID, "entity/shield/golden_shield_base_nopattern")
	);

    public static void onInitialize() {

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.ANCIENT_TRIDENT,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                //System.out.println("Trident - Clint Code");

                AncientTridentModel model = new AncientTridentModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.ANCIENT_TRIDENT)
                );
                matrices.push();
                matrices.scale(1.0F, -1.0F, -1.0F);
                VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, model.getLayer(AncientTridentModel.TEXTURE), false, stack.hasGlint());
                model.render(matrices, vertexConsumer2, light, overlay);
                matrices.pop();
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.GOLDEN_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
            SpriteIdentifier base = GOLDEN_SHIELD_BASE;
            SpriteIdentifier base_nopattern = GOLDEN_BASE_NO_PATTERN_1;

            StrongShieldEntityModel GOLDEN_SHIELD_MODEL = new StrongShieldEntityModel(
                MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.GOLDEN_SHIELD)
            );

            StrongShieldEntityModel model = GOLDEN_SHIELD_MODEL;

            BannerPatternsComponent bannerPatternsComponent = (BannerPatternsComponent)stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
            DyeColor dyeColor2 = (DyeColor)stack.get(DataComponentTypes.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            SpriteIdentifier spriteIdentifier = bl ? base : base_nopattern;
            VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint()));
            model.getHandle().render(matrices, vertexConsumer, light, overlay);
            model.getSide2().render(matrices, vertexConsumer, light, overlay);
            model.getSide().render(matrices, vertexConsumer, light, overlay);
            if (bl) {
                BannerBlockEntityRenderer.renderCanvas(
					matrices,
					vertexConsumers,
					light,
					overlay,
					model.getPlate(),
					spriteIdentifier,
					false,
					(DyeColor)Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE),
					bannerPatternsComponent,
					stack.hasGlint()
					);
        
            } else {
                model.getPlate().render(matrices, vertexConsumer, light, overlay);
            }

            matrices.pop();
            }
        );
         
        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_OAK_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_OAK_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_OAK_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_OAK_SHIELD_MODEL, WOODEN_OAK_SHIELD_BASE, WOODEN_OAK_BASE_NO_PATTERN_1);
            }
        );
         
        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_BIRCH_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_BIRCH_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_BIRCH_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_BIRCH_SHIELD_MODEL, WOODEN_BIRCH_SHIELD_BASE, WOODEN_BIRCH_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_SPRUCE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_SPRUCE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_SPRUCE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_SPRUCE_SHIELD_MODEL, WOODEN_SPRUCE_SHIELD_BASE, WOODEN_SPRUCE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_JUNGLE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_JUNGLE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_JUNGLE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_JUNGLE_SHIELD_MODEL, WOODEN_JUNGLE_SHIELD_BASE, WOODEN_JUNGLE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_ACACIA_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_ACACIA_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_ACACIA_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_ACACIA_SHIELD_MODEL, WOODEN_ACACIA_SHIELD_BASE, WOODEN_ACACIA_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_DARK_OAK_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_DARK_OAK_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_DARK_OAK_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_DARK_OAK_SHIELD_MODEL, WOODEN_DARK_OAK_SHIELD_BASE, WOODEN_DARK_OAK_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_MANGROVE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_MANGROVE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_MANGROVE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_MANGROVE_SHIELD_MODEL, WOODEN_MANGROVE_SHIELD_BASE, WOODEN_MANGROVE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_CHERRY_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_CHERRY_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_CHERRY_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_CHERRY_SHIELD_MODEL, WOODEN_CHERRY_SHIELD_BASE, WOODEN_CHERRY_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_BAMBOO_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_BAMBOO_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_BAMBOO_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_BAMBOO_SHIELD_MODEL, WOODEN_BAMBOO_SHIELD_BASE, WOODEN_BAMBOO_BASE_NO_PATTERN_1);
            }
        );
            

        /*BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.WOODEN_PALE_OAK_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel WOODEN_PALE_OAK_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.WOODEN_PALE_OAK_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_PALE_OAK_SHIELD_MODEL, WOODEN_PALE_OAK_SHIELD_BASE, WOODEN_PALE_OAK_BASE_NO_PATTERN_1);
            }
        );*/

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.STONE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel STONE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.STONE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_SHIELD_MODEL, STONE_SHIELD_BASE, STONE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.STONE_DEEPSLATE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel STONE_DEEPSLATE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.STONE_DEEPSLATE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_DEEPSLATE_SHIELD_MODEL, STONE_DEEPSLATE_SHIELD_BASE, STONE_DEEPSLATE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.STONE_BLACKSTONE_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                ShieldEntityModel STONE_BLACKSTONE_SHIELD_MODEL = new ShieldEntityModel(
                    MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.STONE_BLACKSTONE_SHIELD)
                );
                createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_BLACKSTONE_SHIELD_MODEL, STONE_BLACKSTONE_SHIELD_BASE, STONE_BLACKSTONE_BASE_NO_PATTERN_1);
            }
        );

        BuiltinItemRendererRegistry.INSTANCE.register(
            ItemFactory.DIAMOND_SHIELD,
            (stack, mode, matrices, vertexConsumers, light, overlay) -> {
            SpriteIdentifier base = DIAMOND_SHIELD_BASE;
            SpriteIdentifier base_nopattern = DIAMOND_SHIELD_BASE_NO_PATTERN_1;

            StrongShieldEntityModel DIAMOND_SHIELD_MODEL = new StrongShieldEntityModel(
                MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.DIAMOND_SHIELD)
            );

            StrongShieldEntityModel model = DIAMOND_SHIELD_MODEL;

            BannerPatternsComponent bannerPatternsComponent = (BannerPatternsComponent)stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
            DyeColor dyeColor2 = (DyeColor)stack.get(DataComponentTypes.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            SpriteIdentifier spriteIdentifier = bl ? base : base_nopattern;
            VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint()));
            model.getHandle().render(matrices, vertexConsumer, light, overlay);
            model.getSide2().render(matrices, vertexConsumer, light, overlay);
            model.getSide().render(matrices, vertexConsumer, light, overlay);
            if (bl) {
                BannerBlockEntityRenderer.renderCanvas(
					matrices,
					vertexConsumers,
					light,
					overlay,
					model.getPlate(),
					spriteIdentifier,
					false,
					(DyeColor)Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE),
					bannerPatternsComponent,
					stack.hasGlint()
					);
        
            } else {
                model.getPlate().render(matrices, vertexConsumer, light, overlay);
            }

            matrices.pop();
            }
        );
            
            



    }

    public static void createDefaultShield(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ShieldEntityModel model, SpriteIdentifier base, SpriteIdentifier base_nopattern){

        BannerPatternsComponent bannerPatternsComponent = (BannerPatternsComponent)stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
        DyeColor dyeColor2 = (DyeColor)stack.get(DataComponentTypes.BASE_COLOR);
        boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl ? base : base_nopattern;
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint()));
        model.getHandle().render(matrices, vertexConsumer, light, overlay);
        if (bl) {
            BannerBlockEntityRenderer.renderCanvas(
				matrices,
				vertexConsumers,
				light,
				overlay,
				model.getPlate(),
				spriteIdentifier,
				false,
				(DyeColor)Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE),
				bannerPatternsComponent,
				stack.hasGlint()
			);
        } else {
            model.getPlate().render(matrices, vertexConsumer, light, overlay);
        }
        matrices.pop();
    }


    
}
