package net.ent.entstupidstuff.client.item.model.special;

import java.util.Set;

import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import com.mojang.serialization.MapCodec;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.StrongShieldEntityModel;

import java.util.Objects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.ComponentMap;
import net.minecraft.util.Unit;

@Environment(EnvType.CLIENT)
public class StoneShieldSpecialRenderer implements SpecialModelRenderer<ComponentMap> {

    public static final SpriteIdentifier MAIN_SHIELD_BASE = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entstupidstuff", "entity/shield/stone_shield_base")
	);
	public static final SpriteIdentifier MAIN_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(
		TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entstupidstuff", "entity/shield/stone_shield_base_no_pattern")
	);
    
    private final SpriteHolder spriteHolder;
    private final StrongShieldEntityModel model;
    
    public StoneShieldSpecialRenderer(SpriteHolder spriteHolder, StrongShieldEntityModel model) {
        this.spriteHolder = spriteHolder;
        this.model = model;
    }
    
    @Nullable
    @Override
    public ComponentMap getData(ItemStack itemStack) {
        return itemStack.getImmutableComponents();
    }
    
    @Override
    public void render(
        @Nullable ComponentMap componentMap,
        ItemDisplayContext itemDisplayContext,
        MatrixStack matrixStack,
        OrderedRenderCommandQueue orderedRenderCommandQueue,
        int light,
        int overlay,
        boolean glint,
        int outlineColor
    ) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null
            ? componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
            : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean hasBanner = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        
        // Use your custom shield textures instead of vanilla ones
        SpriteIdentifier spriteIdentifier = hasBanner ? MAIN_SHIELD_BASE : MAIN_SHIELD_BASE_NO_PATTERN;
        
        orderedRenderCommandQueue.submitModelPart(
            this.model.getHandle(),
            matrixStack,
            this.model.getLayer(spriteIdentifier.getAtlasId()),
            light,
            overlay,
            this.spriteHolder.getSprite(spriteIdentifier),
            false,
            false,
            -1,
            null,
            outlineColor
        );
        
        if (hasBanner) {
            BannerBlockEntityRenderer.renderCanvas(
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
                this.model.getPlate(),
                matrixStack,
                this.model.getLayer(spriteIdentifier.getAtlasId()),
                light,
                overlay,
                this.spriteHolder.getSprite(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );

            orderedRenderCommandQueue.submitModelPart(
                this.model.getSide(),
                matrixStack,
                this.model.getLayer(spriteIdentifier.getAtlasId()),
                light,
                overlay,
                this.spriteHolder.getSprite(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );

            orderedRenderCommandQueue.submitModelPart(
                this.model.getSide2(),
                matrixStack,
                this.model.getLayer(spriteIdentifier.getAtlasId()),
                light,
                overlay,
                this.spriteHolder.getSprite(spriteIdentifier),
                false,
                glint,
                -1,
                null,
                outlineColor
            );
        }
        
        matrixStack.pop();
    }
    
    @Override
    public void collectVertices(Set<Vector3f> vertices) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.model.getRootPart().collectVertices(matrixStack, vertices);
    }
    
    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final StoneShieldSpecialRenderer.Unbaked INSTANCE = new StoneShieldSpecialRenderer.Unbaked();
        public static final MapCodec<StoneShieldSpecialRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);
        
        @Override
        public MapCodec<StoneShieldSpecialRenderer.Unbaked> getCodec() {
            return CODEC;
        }
        
        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakeContext context) {
            return new StoneShieldSpecialRenderer(
                context.spriteHolder(),
                new StrongShieldEntityModel(context.entityModelSet().getModelPart(ModEntityModelLayers.DIAMOND_SHIELD))
            );
        }
    }
}
