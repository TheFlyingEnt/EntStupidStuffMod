package net.ent.entstupidstuff.api.util;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;

public class OminousBannerHelper {
    private static final Component OMINOUS_BANNER_PATTERN_NAME_H = 
        Component.translatable("block.minecraft.ominous_banner");

    public static ItemStack getOminousHorizontalBannerInstance(HolderGetter<BannerPattern> holderGetter) {
        ItemStack itemStack = new ItemStack(ItemFactory.callItem("white_horizontal_banner"));
        BannerPatternLayers bannerPatternLayers = new BannerPatternLayers.Builder()
            .addIfRegistered(holderGetter, BannerPatterns.RHOMBUS_MIDDLE, DyeColor.CYAN)
            .addIfRegistered(holderGetter, BannerPatterns.STRIPE_BOTTOM, DyeColor.LIGHT_GRAY)
            .addIfRegistered(holderGetter, BannerPatterns.STRIPE_CENTER, DyeColor.GRAY)
            .addIfRegistered(holderGetter, BannerPatterns.BORDER, DyeColor.LIGHT_GRAY)
            .addIfRegistered(holderGetter, BannerPatterns.STRIPE_MIDDLE, DyeColor.BLACK)
            .addIfRegistered(holderGetter, BannerPatterns.HALF_HORIZONTAL, DyeColor.LIGHT_GRAY)
            .addIfRegistered(holderGetter, BannerPatterns.CIRCLE_MIDDLE, DyeColor.LIGHT_GRAY)
            .addIfRegistered(holderGetter, BannerPatterns.BORDER, DyeColor.BLACK)
            .build();
        itemStack.set(DataComponents.BANNER_PATTERNS, bannerPatternLayers);
        itemStack.set(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.BANNER_PATTERNS, true));
        itemStack.set(DataComponents.ITEM_NAME, OMINOUS_BANNER_PATTERN_NAME_H);
        itemStack.set(DataComponents.RARITY, Rarity.UNCOMMON);
        return itemStack;
    }
}
