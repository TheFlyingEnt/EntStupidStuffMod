package net.ent.entstupidstuff.util;

import java.util.ArrayList;
import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.casting.ArmorCastingComponent;
import net.ent.entstupidstuff.api.casting.CastingTemplateItem;
import net.ent.entstupidstuff.api.casting.CastingTooltipCondenser;
import net.ent.entstupidstuff.api.casting.ToolCastingComponent;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class CastingClientUtil {

    public static void onInitializeClient() {

        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            if (!(stack.getItem() instanceof CastingTemplateItem casting)) return;

            // Build all our lines in order
            List<Component> toInsert = new ArrayList<>();

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.title",
                Component.literal(casting.getCastName()).withStyle(ChatFormatting.GOLD)
            ).withStyle(ChatFormatting.GRAY));

            toInsert.add(CommonComponents.EMPTY);

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.applies_to"
            ).withStyle(ChatFormatting.GRAY));

            /*for (Item item : mold.getValidItems()) {
                toInsert.add(CommonComponents.space().append(
                    item.getName().copy().withStyle(ChatFormatting.BLUE)
                ));
            }*/

            toInsert.addAll(CastingTooltipCondenser.condense(casting.getValidItems()));

            toInsert.add(CommonComponents.EMPTY);

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.ingredients"
            ).withStyle(ChatFormatting.GRAY));

            toInsert.add(CommonComponents.space().append(
                Component.translatable("item.minecraft.lava_bucket")
                    .withStyle(ChatFormatting.BLUE)
            ));

            // Insert all lines right after item name (index 0)
            // Use reverse order so each insert at index 1 puts them in correct order
            for (int i = toInsert.size() - 1; i >= 0; i--) {
                lines.add(1, toInsert.get(i));
            }
        });

        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            ArmorCastingComponent armorCast = stack.get(ModDataComponentTypes.ARMOR_CAST);
            ToolCastingComponent toolCast   = stack.get(ModDataComponentTypes.TOOL_CAST);
            if (armorCast == null && toolCast == null) return;

            String castName = armorCast != null
                ? armorCast.castId().getPath()
                : toolCast.castId().getPath();

            Component castLine = CommonComponents.space().append(
                Component.translatable("cast." + EntStupidStuff.MOD_ID + "." + castName)
                    .withStyle(ChatFormatting.BLUE)
            );

            String upgradeHeader = Component.translatable(
                "item.minecraft.smithing_template.upgrade"
            ).getString();

            int insertAt = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).getString().equals(upgradeHeader)) {
                    // Found the header, now find the last indented line in this section
                    insertAt = i;
                    for (int j = i + 1; j < lines.size(); j++) {
                        if (lines.get(j).getString().startsWith(" ")) {
                            insertAt = j;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }

            /// ---------------------

            if (insertAt >= 0) {
                lines.add(insertAt + 1, castLine);
            } else {
                // Find "When on X:" line to insert upgrade section before it
                int whenOnIndex = -1;
                String whenOn = Component.translatable("item.modifiers.chest").getString();

                // Check all slot types
                List<String> whenOnKeys = List.of(
                    Component.translatable("item.modifiers.chest").getString(),
                    Component.translatable("item.modifiers.head").getString(),
                    Component.translatable("item.modifiers.legs").getString(),
                    Component.translatable("item.modifiers.feet").getString(),
                    Component.translatable("item.modifiers.mainhand").getString(),
                    Component.translatable("item.modifiers.offhand").getString()
                );

                for (int i = 0; i < lines.size(); i++) {
                    if (whenOnKeys.contains(lines.get(i).getString())) {
                        whenOnIndex = i;
                        break;
                    }
                }

                if (whenOnIndex >= 0) {
                    int insertBefore = whenOnIndex;
                    
                    // Remove ALL empty lines before "When on X:"
                    while (insertBefore > 1 && lines.get(insertBefore - 1).getString().isEmpty()) {
                        lines.remove(insertBefore - 1);
                        insertBefore--;
                    }
                    
                    // Now insert: Upgrade header, mold line, single empty gap
                    lines.add(insertBefore, CommonComponents.EMPTY); // single gap before "When on Chest:"
                    lines.add(insertBefore, castLine);
                    lines.add(insertBefore, Component.translatable("item.minecraft.smithing_template.upgrade")
                        .withStyle(ChatFormatting.GRAY));
                } else {
                    // Last resort - just before the last empty line or at end
                    int lastEmpty = -1;
                    for (int i = lines.size() - 1; i >= 0; i--) {
                        if (lines.get(i).getString().isEmpty()) {
                            lastEmpty = i;
                            break;
                        }
                    }
                    int pos = lastEmpty >= 0 ? lastEmpty : lines.size();
                    lines.add(pos, CommonComponents.EMPTY);
                    lines.add(pos, castLine);
                    lines.add(pos, Component.translatable("item.minecraft.smithing_template.upgrade")
                        .withStyle(ChatFormatting.GRAY));
                    // Only add leading empty if something is above the header
                    if (pos > 1) {
                        lines.add(pos, CommonComponents.EMPTY);
                    }
                }
            }
        });

    }
    
}
