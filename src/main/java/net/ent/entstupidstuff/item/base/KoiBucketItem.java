package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.entity.passive.KoiPatternMain;
import net.ent.entstupidstuff.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.entity.passive.KoiVariant;
import net.ent.entstupidstuff.entity.passive.KoiVariantRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KoiBucketItem extends EntityBucketItem {

    public KoiBucketItem(EntityType<? extends MobEntity> type, Fluid fluid, SoundEvent emptyingSound, Item.Settings settings) {
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


    /*@Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context,
                              List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        // Grab the NBT that stores entity data
        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();

        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            int variantId = nbt.getInt("BucketVariantTag");
            KoiVariant variant = KoiVariantRegistry.getById(variantId);

            if (variant == null) {
                return; // invalid variant
            }

            KoiBaseColor koiBase = variant.getBaseColor();
            KoiPatternMain koiMainPattern = variant.getPatternKohaku();
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
                tooltip.add(Text.literal(nameLine + " Koi").formatted(Formatting.GRAY, Formatting.ITALIC));
            }

            if (!colorLine.isEmpty()) {
                tooltip.add(Text.literal(colorLine).formatted(Formatting.GRAY, Formatting.ITALIC));
            }

            if (!styleLine.isEmpty()) {
                tooltip.add(Text.literal(styleLine).formatted(Formatting.GRAY, Formatting.ITALIC));
            }
        }
    }*/
}
