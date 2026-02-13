package net.ent.entstupidstuff.item.base;

import java.util.function.Consumer;

import net.ent.entstupidstuff.client.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.client.entity.passive.KoiPatternMain;
import net.ent.entstupidstuff.client.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluid;

public class KoiBucketItem extends MobBucketItem {

    public KoiBucketItem(EntityType<? extends Mob> type, Fluid fluid, SoundEvent emptyingSound,
            Item.Properties settings) {
        super(type, fluid, emptyingSound, settings);
    }

    //Koi Fish can be Kohaku, Showa or Sanke

    //Bekko Koi: White, Red, or Yellow Koi with Black Markings
    //Shiro Bekko - White
    //Aka Bekko - Red or Hi Ut
    //Ki Bekko - Yellow

    //Sanke = Small
    //Showa == Big


    // Ki Bekko
    // Yellow, Black


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        KoiVariant variant = stack.get(ModDataComponentTypes.KOI_FISH_VARIANT);
        System.out.println("KoiBucketItem:");
        System.out.println("variant:" + variant);

        if (variant != null) {

        KoiBaseColor koiBase = variant.getBaseColor();
        KoiPatternMain koiMainPattern = variant.getPatternKohaku();
        KoiPatternSecondary koiSecondaryPattern = variant.getSecondaryPattern();

        System.out.println("koiBase:" + koiBase);
        System.out.println("koiMainPattern:" + koiMainPattern);
        System.out.println("koiSecondaryPattern:" + koiSecondaryPattern);

        String nameLine = "";
        String colorLine = "";
        String styleLine = "";

        if (koiBase == KoiBaseColor.RED) {
            colorLine += "Red";

            if (koiSecondaryPattern != null) {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += "Aka Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += "Hi Utsuri";
                    colorLine += ", Black";
                }
            }
            else {
                nameLine += "Benigoi";
            }
        }

        if (koiBase == KoiBaseColor.YELLOW) {
                colorLine += "Yellow";

            if (koiSecondaryPattern != null) {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += " Ki Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += " Ki Utsuri";
                    colorLine += ", Black";
                }
            }
            else {
                nameLine += "Kigoi";
            }
        }

        if (koiBase == KoiBaseColor.WHITE) {
            nameLine += "Shiro";
            colorLine += "White";

            if (koiMainPattern != null) { // Kahaku, Sanka, or Showa

                if (koiMainPattern.getName() == "Inazuma_2" || koiMainPattern.getName() == "Inazuma_3") {
                    nameLine += " " + "Inazuma";
                } else if (koiMainPattern.getName() == "Menkaburi_2") {
                        nameLine += " " + "Menkaburi";
                } else {
                    nameLine += " " + koiMainPattern.getName();
                }

                colorLine += ", Red";

                if (koiSecondaryPattern == null) { 
                    // This is a Kohaku
                    nameLine += " Kohaku";
                } else {
                    String typeStr = koiSecondaryPattern.getType();
                    if ("sanka".equals(typeStr)) { // Sanke
                        nameLine += " Sanke";
                        colorLine += ", Black";
                    } else if ("showa".equals(typeStr)) { // Showa
                        nameLine += " " + koiSecondaryPattern.getName() + " Showa";
                        colorLine += ", Black";
                    }
                }
            } else if (koiSecondaryPattern != null) {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += " Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += " Utsuri";
                    colorLine += ", Black";
                }
            }
        }

        if (!nameLine.isEmpty()) {
            textConsumer.accept(Component.literal(nameLine + " Koi").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

        if (!colorLine.isEmpty()) {
            textConsumer.accept(Component.literal(colorLine).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

        if (!styleLine.isEmpty()) {
            textConsumer.accept(Component.literal(styleLine).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

        }


    }


    /*@Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {

        
        KoiVariant variant = stack.get(ModDataComponentTypes.KOI_FISH_VARIANT);

        if (variant == null) {
            return;
        }

        KoiBaseColor koiBase = variant.getBaseColor();
        KoiPatternMain koiMainPattern = variant.getMainPattern();// getPatternKohaku();
        KoiPatternSecondary koiSecondaryPattern = variant.getSecondaryPattern();

        String nameLine = "";
        String colorLine = "";
        String styleLine = "";

        if (koiBase == KoiBaseColor.RED) {
            colorLine += "Red";

            if (koiSecondaryPattern != null) {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += "Aka Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += "Hi Utsuri";
                    colorLine += ", Black";
                }
            }
        }

        if (koiBase == KoiBaseColor.YELLOW) {
            nameLine += "Ki";
            colorLine += "Yellow";

            if (koiSecondaryPattern != null) {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += " Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += " Utsuri";
                    colorLine += ", Black";
                }
            }
        }

        if (koiBase == KoiBaseColor.WHITE) {
            nameLine += "Shiro";
            colorLine += "White";

            if (koiMainPattern != null) { // Kahaku, Sanka, or Showa

                if (koiMainPattern.getName() == "Inazuma_2" || koiMainPattern.getName() == "Inazuma_3") {
                    nameLine += " " + "Inazuma";
                } else if (koiMainPattern.getName() == "Menkaburi_2") {
                    nameLine += " " + "Menkaburi";
                } else {
                    nameLine += " " + koiMainPattern.getName();
                }

                colorLine += ", Red";

                if (koiSecondaryPattern == null) {
                    // This is a Kohaku
                    nameLine += " Kohaku";
                } else {
                    String typeStr = koiSecondaryPattern.getType();
                    if ("sanka".equals(typeStr)) { // Sanke
                        nameLine += " Sanke";
                        colorLine += ", Black";
                    } else if ("showa".equals(typeStr)) { // Showa
                        nameLine += " " + koiSecondaryPattern.getName() + " Showa";
                        colorLine += ", Black";
                    }
                }
            } else {
                String typeStr = koiSecondaryPattern.getType();
                if ("sanka".equals(typeStr)) {
                    nameLine += " Bekko";
                    colorLine += ", Black";
                } else if ("showa".equals(typeStr)) {
                    nameLine += " Utsuri";
                    colorLine += ", Black";
                }
            }
        }

        if (!nameLine.isEmpty()) {
            textConsumer.accept(Component.literal(nameLine + " Koi").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

        if (!colorLine.isEmpty()) {
            textConsumer.accept(Component.literal(colorLine).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

        if (!styleLine.isEmpty()) {
            textConsumer.accept(Component.literal(styleLine).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }

    }*/

    /*
     * @Override
     * public void appendTooltip(ItemStack stack, Item.TooltipContext context,
     * List<Text> tooltip, TooltipType type) {
     * super.appendTooltip(stack, context, tooltip, type);
     * 
     * // Grab the NBT that stores entity data
     * NbtComponent nbtComponent =
     * stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA,
     * NbtComponent.DEFAULT);
     * NbtCompound nbt = nbtComponent.copyNbt();
     * 
     * if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
     * int variantId = nbt.getInt("BucketVariantTag");
     * KoiVariant variant = KoiVariantRegistry.getById(variantId);
     * 
     * if (variant == null) {
     * return; // invalid variant
     * }
     * 
     * KoiBaseColor koiBase = variant.getBaseColor();
     * KoiPatternMain koiMainPattern = variant.getPatternKohaku();
     * KoiPatternSecondary koiSecondaryPattern = variant.getSecondaryPattern();
     * 
     * String nameLine = "";
     * String colorLine = "";
     * String styleLine = "";
     * 
     * if (koiBase == KoiBaseColor.RED) {
     * colorLine += "Red";
     * 
     * if (koiSecondaryPattern != null) {
     * String typeStr = koiSecondaryPattern.getType();
     * if ("sanka".equals(typeStr)) {
     * nameLine += "Aka Bekko";
     * colorLine += ", Black";
     * } else if ("showa".equals(typeStr)) {
     * nameLine += "Hi Utsuri";
     * colorLine += ", Black";
     * }
     * }
     * }
     * 
     * if (koiBase == KoiBaseColor.YELLOW) {
     * nameLine += "Ki";
     * colorLine += "Yellow";
     * 
     * if (koiSecondaryPattern != null) {
     * String typeStr = koiSecondaryPattern.getType();
     * if ("sanka".equals(typeStr)) {
     * nameLine += " Bekko";
     * colorLine += ", Black";
     * } else if ("showa".equals(typeStr)) {
     * nameLine += " Utsuri";
     * colorLine += ", Black";
     * }
     * }
     * }
     * 
     * if (koiBase == KoiBaseColor.WHITE) {
     * nameLine += "Shiro";
     * colorLine += "White";
     * 
     * if (koiMainPattern != null) { // Kahaku, Sanka, or Showa
     * 
     * if (koiMainPattern.getName() == "Inazuma_2" || koiMainPattern.getName() ==
     * "Inazuma_3") {
     * nameLine += " " + "Inazuma";
     * } else if (koiMainPattern.getName() == "Menkaburi_2") {
     * nameLine += " " + "Menkaburi";
     * } else {
     * nameLine += " " + koiMainPattern.getName();
     * }
     * 
     * colorLine += ", Red";
     * 
     * if (koiSecondaryPattern == null) {
     * // This is a Kohaku
     * nameLine += " Kohaku";
     * } else {
     * String typeStr = koiSecondaryPattern.getType();
     * if ("sanka".equals(typeStr)) { // Sanke
     * nameLine += " Sanke";
     * colorLine += ", Black";
     * } else if ("showa".equals(typeStr)) { // Showa
     * nameLine += " " + koiSecondaryPattern.getName() + " Showa";
     * colorLine += ", Black";
     * }
     * }
     * } else {
     * String typeStr = koiSecondaryPattern.getType();
     * if ("sanka".equals(typeStr)) {
     * nameLine += " Bekko";
     * colorLine += ", Black";
     * } else if ("showa".equals(typeStr)) {
     * nameLine += " Utsuri";
     * colorLine += ", Black";
     * }
     * }
     * }
     * 
     * if (!nameLine.isEmpty()) {
     * tooltip.add(Text.literal(nameLine + " Koi").formatted(Formatting.GRAY,
     * Formatting.ITALIC));
     * }
     * 
     * if (!colorLine.isEmpty()) {
     * tooltip.add(Text.literal(colorLine).formatted(Formatting.GRAY,
     * Formatting.ITALIC));
     * }
     * 
     * if (!styleLine.isEmpty()) {
     * tooltip.add(Text.literal(styleLine).formatted(Formatting.GRAY,
     * Formatting.ITALIC));
     * }
     * }
     * }
     */
}
