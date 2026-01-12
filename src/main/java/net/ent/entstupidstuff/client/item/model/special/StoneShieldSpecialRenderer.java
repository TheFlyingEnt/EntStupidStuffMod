package net.ent.entstupidstuff.client.item.model.special;

import java.util.Set;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

@Environment(EnvType.CLIENT)
public class StoneShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {

    public static final Material MAIN_SHIELD_BASE = new Material(
		Sheets.SHIELD_SHEET, ResourceLocation.fromNamespaceAndPath("entstupidstuff", "entity/shield/stone_shield_base")
	);
	public static final Material MAIN_SHIELD_BASE_NO_PATTERN = new Material(
		Sheets.SHIELD_SHEET, ResourceLocation.fromNamespaceAndPath("entstupidstuff", "entity/shield/stone_shield_base_no_pattern")
	);
    
    private final MaterialSet spriteHolder;
    private final StrongShieldEntityModel model;
    
    public StoneShieldSpecialRenderer(MaterialSet spriteHolder, StrongShieldEntityModel model) {
        this.spriteHolder = spriteHolder;
        this.model = model;
    }
    
    @Nullable
    @Override
    public DataComponentMap extractArgument(ItemStack itemStack) {
        return itemStack.immutableComponents();
    }
    
    @Override
    public void submit(
        @Nullable DataComponentMap componentMap,
        ItemDisplayContext itemDisplayContext,
        PoseStack matrixStack,
        SubmitNodeCollector orderedRenderCommandQueue,
        int light,
        int overlay,
        boolean glint,
        int outlineColor
    ) {
        BannerPatternLayers bannerPatternsComponent = componentMap != null
            ? componentMap.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
            : BannerPatternLayers.EMPTY;
        DyeColor dyeColor = componentMap != null ? componentMap.get(DataComponents.BASE_COLOR) : null;
        boolean hasBanner = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        
        matrixStack.pushPose();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        
        // Use your custom shield textures instead of vanilla ones
        Material spriteIdentifier = hasBanner ? MAIN_SHIELD_BASE : MAIN_SHIELD_BASE_NO_PATTERN;
        
        orderedRenderCommandQueue.submitModelPart(
            this.model.handle(),
            matrixStack,
            this.model.renderType(spriteIdentifier.atlasLocation()),
            light,
            overlay,
            this.spriteHolder.get(spriteIdentifier),
            false,
            false,
            -1,
            null,
            outlineColor
        );
        
        if (hasBanner) {
            BannerRenderer.submitPatterns(
                this.spriteHolder,
                matrixStack,
                orderedRenderCommandQueue,
                light,
                overlay,
                this.model,
                Unit.INSTANCE,
                spriteIdentifier,
                false,
                (DyeColor)Objects.requireNonNullElse(dyeColor, DyeColor.WHITE),
                bannerPatternsComponent,
                glint,
                null,
                outlineColor
            );
        } else {
            orderedRenderCommandQueue.submitModelPart(
                this.model.plate(),
                matrixStack,
                this.model.renderType(spriteIdentifier.atlasLocation()),
                light,
                overlay,
                this.spriteHolder.get(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );

            orderedRenderCommandQueue.submitModelPart(
                this.model.getSide(),
                matrixStack,
                this.model.renderType(spriteIdentifier.atlasLocation()),
                light,
                overlay,
                this.spriteHolder.get(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );

            orderedRenderCommandQueue.submitModelPart(
                this.model.getSide2(),
                matrixStack,
                this.model.renderType(spriteIdentifier.atlasLocation()),
                light,
                overlay,
                this.spriteHolder.get(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );
        }
        
        matrixStack.popPose();
    }
    
    @Override
    public void getExtents(Set<Vector3f> vertices) {
        PoseStack matrixStack = new PoseStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(matrixStack, vertices);
    }
    
    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final StoneShieldSpecialRenderer.Unbaked INSTANCE = new StoneShieldSpecialRenderer.Unbaked();
        public static final MapCodec<StoneShieldSpecialRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);
        
        @Override
        public MapCodec<StoneShieldSpecialRenderer.Unbaked> type() {
            return CODEC;
        }
        
        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            return new StoneShieldSpecialRenderer(
                context.materials(),
                new StrongShieldEntityModel(context.entityModelSet().bakeLayer(ModEntityModelLayers.DIAMOND_SHIELD))
            );
        }
    }
}
