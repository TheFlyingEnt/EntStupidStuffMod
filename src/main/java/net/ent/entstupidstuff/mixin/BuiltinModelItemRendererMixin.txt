package net.ent.entstupidstuff.mixin;

import java.util.Objects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientTridentModel;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.DyeColor;




@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {

    @Shadow private final EntityModelLoader entityModelLoader;
    @Shadow private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    private StrongShieldEntityModel DIAMOND_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_OAK_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_SPRUCE_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_BIRCH_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_JUNGLE_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_ACACIA_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_DARK_OAK_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_MANGROVE_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_CHERRY_SHIELD_MODEL;
    private ShieldEntityModel WOODEN_BAMBOO_SHIELD_MODEL;

    private ShieldEntityModel STONE_SHIELD_MODEL;
    private ShieldEntityModel STONE_DEEPSLATE_SHIELD_MODEL;
    private ShieldEntityModel STONE_BLACKSTONE_SHIELD_MODEL;

    private StrongShieldEntityModel GOLDEN_SHIELD_MODEL;

    private AncientTridentModel modelAncientTrident;

    public BuiltinModelItemRendererMixin(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelLoader entityModelLoader) {
		this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
		this.entityModelLoader = entityModelLoader;
	}

    @Inject(method = "reload", at = @At("HEAD"), cancellable = true)
	public void reload(ResourceManager manager, CallbackInfo ci) {

        this.WOODEN_OAK_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_OAK_SHIELD));
        this.WOODEN_SPRUCE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_SPRUCE_SHIELD));
        this.WOODEN_BIRCH_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_BIRCH_SHIELD));
        this.WOODEN_JUNGLE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_JUNGLE_SHIELD));
        this.WOODEN_ACACIA_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_ACACIA_SHIELD));
        this.WOODEN_DARK_OAK_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_DARK_OAK_SHIELD));
        this.WOODEN_MANGROVE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_MANGROVE_SHIELD));
        this.WOODEN_CHERRY_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_CHERRY_SHIELD));
        this.WOODEN_BAMBOO_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.WOODEN_BAMBOO_SHIELD));

        this.STONE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.STONE_SHIELD));
        this.STONE_DEEPSLATE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.STONE_DEEPSLATE_SHIELD));
        this.STONE_BLACKSTONE_SHIELD_MODEL = new ShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.STONE_BLACKSTONE_SHIELD));

        this.GOLDEN_SHIELD_MODEL = new StrongShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.GOLDEN_SHIELD));
        this.DIAMOND_SHIELD_MODEL = new StrongShieldEntityModel(this.entityModelLoader.getModelPart(ModModelLayers.DIAMOND_SHIELD));

        this.modelAncientTrident = new AncientTridentModel(this.entityModelLoader.getModelPart(ModModelLayers.ANCIENT_TRIDENT));


	}



    //diamond_shield_patterns


    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {

        /*if (stack.isOf(ItemFactory.DIAMOND_SHIELD)) {
            //EntStupidStuffClient.renderBanner(stack, matrices, vertexConsumers, light, overlay, DIAMOND_SHIELD_MODEL, EntStupidStuffClient.SHIELD_BASE_1, EntStupidStuffClient.BASE_NO_PATTERN_1););

            SpriteIdentifier base = ShieldSprite.DIAMOND_SHIELD_BASE;
            SpriteIdentifier base_nopattern = ShieldSprite.DIAMOND_SHIELD_BASE_NO_PATTERN_1;
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
        else if (stack.isOf(ItemFactory.WOODEN_OAK_SHIELD)) {
            
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_OAK_SHIELD_MODEL, ShieldSprite.WOODEN_OAK_SHIELD_BASE, ShieldSprite.WOODEN_OAK_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_SPRUCE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_SPRUCE_SHIELD_MODEL, ShieldSprite.WOODEN_SPRUCE_SHIELD_BASE, ShieldSprite.WOODEN_SPRUCE_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_BIRCH_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_BIRCH_SHIELD_MODEL, ShieldSprite.WOODEN_BIRCH_SHIELD_BASE, ShieldSprite.WOODEN_BIRCH_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_JUNGLE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_JUNGLE_SHIELD_MODEL, ShieldSprite.WOODEN_JUNGLE_SHIELD_BASE, ShieldSprite.WOODEN_JUNGLE_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_BAMBOO_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_BAMBOO_SHIELD_MODEL, ShieldSprite.WOODEN_BAMBOO_SHIELD_BASE, ShieldSprite.WOODEN_BAMBOO_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_ACACIA_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_ACACIA_SHIELD_MODEL, ShieldSprite.WOODEN_ACACIA_SHIELD_BASE, ShieldSprite.WOODEN_ACACIA_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_DARK_OAK_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_DARK_OAK_SHIELD_MODEL, ShieldSprite.WOODEN_DARK_OAK_SHIELD_BASE, ShieldSprite.WOODEN_DARK_OAK_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_MANGROVE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_MANGROVE_SHIELD_MODEL, ShieldSprite.WOODEN_MANGROVE_SHIELD_BASE, ShieldSprite.WOODEN_MANGROVE_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.WOODEN_CHERRY_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, WOODEN_CHERRY_SHIELD_MODEL, ShieldSprite.WOODEN_CHERRY_SHIELD_BASE, ShieldSprite.WOODEN_CHERRY_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.STONE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_SHIELD_MODEL, ShieldSprite.STONE_SHIELD_BASE, ShieldSprite.STONE_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.STONE_DEEPSLATE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_DEEPSLATE_SHIELD_MODEL, ShieldSprite.STONE_DEEPSLATE_SHIELD_BASE, ShieldSprite.STONE_DEEPSLATE_BASE_NO_PATTERN_1);
        } else if (stack.isOf(ItemFactory.STONE_BLACKSTONE_SHIELD)) {
            createDefaultShield(stack, mode, matrices, vertexConsumers, light, overlay, STONE_BLACKSTONE_SHIELD_MODEL, ShieldSprite.STONE_BLACKSTONE_SHIELD_BASE, ShieldSprite.STONE_BLACKSTONE_BASE_NO_PATTERN_1);
        } /*else if (stack.isOf(ItemFactory.GOLDEN_SHIELD)) {
            System.out.println("Shield Rest");
            SpriteIdentifier base = ShieldSprite.GOLDEN_SHIELD_BASE;
            SpriteIdentifier base_nopattern = ShieldSprite.GOLDEN_BASE_NO_PATTERN_1;
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
        } /*else if (stack.isOf(ItemFactory.ANCIENT_TRIDENT)) {
            System.out.println("Trident - BuildInItemModel"); //This sould call (but Works) but is not
            AncientTridentModel model = modelAncientTrident;


            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, model.getLayer(AncientTridentModel.TEXTURE), false, stack.hasGlint());
            model.render(matrices, vertexConsumer2, light, overlay);
            matrices.pop();
        } else if (stack.isOf(Items.TRIDENT)) { //This Worked
            System.out.println("Trident Rest - Alt");
            AncientTridentModel model = modelAncientTrident;


            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, model.getLayer(AncientTridentModel.TEXTURE), false, stack.hasGlint());
            model.render(matrices, vertexConsumer2, light, overlay);
            matrices.pop();
        } */
        //else {}
    }

    public void createDefaultShield(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ShieldEntityModel model, SpriteIdentifier base, SpriteIdentifier base_nopattern){

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
