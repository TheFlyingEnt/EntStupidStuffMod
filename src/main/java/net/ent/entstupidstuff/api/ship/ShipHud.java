package net.ent.entstupidstuff.api.ship;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * Ship HUD — renders above the hotbar whenever the player is aboard a ship
 * (riding OR standing on deck). Dark background panel so it's always readable.
 */
public final class ShipHud {

    private static final int SEGMENTS = 14;
    private static final double TICKS_PER_SEC = 20.0;
    private static final double KNOT = 0.5144;

    // Background color: semi-transparent black
    private static final int BG_COLOR = 0xAA000000;
    private static final int PADDING = 3;

    public static void register() {
        HudRenderCallback.EVENT.register((g, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.options.hideGui) return;

            // Find the ship — either riding it or standing on its deck
            CustomBoatEntity ship = null;
            if (mc.player.getVehicle() instanceof CustomBoatEntity s) {
                ship = s;
            } else if (mc.level != null) {
                ship = DeckSync.findDeckBoat(mc.level, mc.player);
            }
            if (ship == null) return;

            renderHud(g, mc, ship);
        });
    }

    private static void renderHud(GuiGraphics g, Minecraft mc, CustomBoatEntity ship) {
        int cx = g.guiWidth() / 2;
        int lineHeight = mc.font.lineHeight + 2;  // ~11px per line

        // Build both lines
        Component line1 = buildHealthLine(ship);
        Component line2 = buildInfoLine(ship);

        int w1 = mc.font.width(line1);
        int w2 = mc.font.width(line2);
        int maxW = Math.max(w1, w2);

        // Position: centered, above hotbar
        int panelW = maxW + PADDING * 2;
        int panelH = lineHeight * 2 + PADDING * 2;
        int panelX = cx - panelW / 2;
        int panelY = g.guiHeight() - 60 - panelH;

        // Dark background panel
        //g.fill(panelX, panelY, panelX + panelW, panelY + panelH, BG_COLOR);

        // Line 1: health bar
        int textY1 = panelY + PADDING;
        g.drawString(mc.font, line1, cx - w1 / 2, textY1, 0xFFFFFFFF);

        // Line 2: speed | sail | anchor | attachment
        int textY2 = textY1 + lineHeight;
        g.drawString(mc.font, line2, cx - w2 / 2, textY2, 0xFFFFFFFF);
    }

    private static Component buildHealthLine(CustomBoatEntity ship) {
        if (ship.isSinking()) {
            return Component.literal("\u2693 SINKING \u2693")
                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD);
        }

        float pct = ship.getHealthPct();
        int filled = Math.round(pct * SEGMENTS);
        ChatFormatting color = pct > 0.5f ? ChatFormatting.GREEN
                             : pct > 0.25f ? ChatFormatting.YELLOW
                             : ChatFormatting.RED;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < SEGMENTS; i++) bar.append(i < filled ? '\u2588' : '\u2591');

        MutableComponent c = Component.literal("Hull ").withStyle(ChatFormatting.GRAY)
            .append(Component.literal(bar.toString()).withStyle(color))
            .append(Component.literal(" " + Math.round(pct * 100) + "%").withStyle(color));

        if (ship.isFlooding()) {
            c.append(Component.literal("  \u26A0 FLOODING").withStyle(ChatFormatting.AQUA));
        }
        return c;
    }

    private static Component buildInfoLine(CustomBoatEntity ship) {
        MutableComponent line = Component.empty();

        // Speed in knots
        double speedBpt = ship.getHorizontalSpeed();
        double knots = (speedBpt * TICKS_PER_SEC) / KNOT;
        line.append(Component.literal(String.format("%.1f kn", knots)).withStyle(ChatFormatting.WHITE));

        line.append(Component.literal("  | ").withStyle(ChatFormatting.DARK_GRAY));

        // Sail level
        int sail = ship.getSailLevel();
        String sailStr = switch (sail) {
            case 0 -> "Furled";
            case 1 -> "1/3 Sail";
            case 2 -> "2/3 Sail";
            case 3 -> "Full Sail";
            default -> sail + "/" + CustomBoatEntity.SAIL_MAX;
        };
        line.append(Component.literal(sailStr).withStyle(sail == 0 ? ChatFormatting.GRAY : ChatFormatting.WHITE));

        line.append(Component.literal("  | ").withStyle(ChatFormatting.DARK_GRAY));

        // Anchor
        int anchorState = ship.getAnchorState();
        if (anchorState == 0) {
            line.append(Component.literal("Anchor Up").withStyle(ChatFormatting.GRAY));
        } else if (anchorState == 1) {
            line.append(Component.literal("Anchor Down").withStyle(ChatFormatting.GOLD));
        } else {
            line.append(Component.literal("Raising...").withStyle(ChatFormatting.YELLOW));
        }

        // Attachment
        int attachment = ship.getAttachment();
        if (attachment != CustomBoatEntity.ATTACHMENT_NONE) {
            line.append(Component.literal("  | ").withStyle(ChatFormatting.DARK_GRAY));
            String name = switch (attachment) {
                case CustomBoatEntity.ATTACHMENT_HARPOON -> "Harpoon";
                case CustomBoatEntity.ATTACHMENT_CANNON  -> "Cannon";
                default -> "???";
            };
            line.append(Component.literal(name).withStyle(ChatFormatting.AQUA));
        }

        return line;
    }

    private ShipHud() {}
}