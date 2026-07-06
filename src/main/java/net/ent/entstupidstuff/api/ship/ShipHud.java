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
        int lineHeight = mc.font.lineHeight + 2;

        Component line1 = buildHealthLine(ship);
        Component line2 = buildInfoLine(ship);

        int w1 = mc.font.width(line1);
        int w2 = mc.font.width(line2);
        int maxW = Math.max(w1, w2);

        // Line 3 is shared by two roles (they never overlap):
        //   • bow gunner → reload indicator
        //   • helmsman   → sail trim degree
        Component line3 = null;
        int w3 = 0;
        if (ship.isBowGunner(mc.player)) {
            line3 = buildReloadLine(ship);
        } else if (ship.getSailLevel() > 0 && !ship.isSinking()) {
            line3 = buildTrimLine(ship);
        }
        if (line3 != null) {
            w3 = mc.font.width(line3);
            maxW = Math.max(maxW, w3);
        }

        int panelW = maxW + PADDING * 2;
        int lines = (line3 != null) ? 3 : 2;
        int panelH = lineHeight * lines + PADDING * 2;
        int panelX = cx - panelW / 2;
        int panelY = g.guiHeight() - 60 - panelH;

        // Line 1: health bar
        int textY1 = panelY + PADDING;
        g.drawString(mc.font, line1, cx - w1 / 2, textY1, 0xFFFFFFFF);

        // Line 2: speed | sail | anchor | attachment
        int textY2 = textY1 + lineHeight;
        g.drawString(mc.font, line2, cx - w2 / 2, textY2, 0xFFFFFFFF);

        // Line 3: reload indicator (bow gunner only)
        if (line3 != null) {
            int textY3 = textY2 + lineHeight;
            g.drawString(mc.font, line3, cx - w3 / 2, textY3, 0xFFFFFFFF);
        }

        drawWindCompass(g, mc, g.guiWidth() - 40, 40); //checked

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


    /**
     * Reload indicator for the bow gunner.
     * Shows: [+] READY      — when loaded and ready to fire
     *        [■■■□□] 60%    — reload progress bar
     *        [X] NO AMMO    — no ammo in ship inventory
     */
    private static Component buildReloadLine(CustomBoatEntity ship) {
        int att = ship.getAttachment();
        if (att == CustomBoatEntity.ATTACHMENT_NONE) {
            return Component.literal("No Attachment").withStyle(ChatFormatting.GRAY);
        }

        int cooldown = ship.getCannonCooldown();
        int maxCooldown = ship.getCannonCooldownMax();
        boolean hasAmmo = ship.hasAmmoLoaded();

        if (!hasAmmo && cooldown <= 0) {
            // No ammo
            return Component.literal("[")
                .withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("X").withStyle(ChatFormatting.RED))
                .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal("NO AMMO").withStyle(ChatFormatting.RED));
        }

        if (cooldown <= 0) {
            // Ready to fire
            return Component.literal("[")
                .withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("+").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD))
                .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal("READY").withStyle(ChatFormatting.GREEN));
        }

        // Reloading — progress bar
        float progress = 1.0f - ((float) cooldown / maxCooldown);
        int barLength = 8;
        int filled = Math.round(progress * barLength);
        int pct = Math.round(progress * 100);

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            bar.append(i < filled ? '\u25A0' : '\u25A1');  // ■ filled, □ empty
        }

        ChatFormatting barColor = progress > 0.7f ? ChatFormatting.GREEN
                                : progress > 0.4f ? ChatFormatting.YELLOW
                                : ChatFormatting.RED;

        return Component.literal("[")
            .withStyle(ChatFormatting.DARK_GRAY)
            .append(Component.literal(bar.toString()).withStyle(barColor))
            .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
            .append(Component.literal(pct + "%").withStyle(barColor));
    }

    /**
     * Sail trim readout for the helmsman.
     * Shows the trim degree and a quality word coloured by how well the sail
     * is drawing for the current wind (green Trimmed / yellow Adjust / grey Luffing).
     */
    private static Component buildTrimLine(CustomBoatEntity ship) {
        int trim = Math.round(ship.getTrim());   // -75..+75 degrees

        String dir = trim == 0 ? "Sail Centered"
                   : trim > 0  ? "Sail R " + trim + "\u00B0"
                               : "Sail L " + (-trim) + "\u00B0";

        float eff = ship.getSailEfficiency();
        ChatFormatting qColor;
        String quality;
        // Thresholds tuned to the new trim range (perfect ~1.12, worst ~0.55×base).
        if (eff >= 1.08f)      { qColor = ChatFormatting.GREEN;  quality = "Trimmed"; }
        else if (eff >= 0.90f) { qColor = ChatFormatting.YELLOW; quality = "Adjust";  }
        else                   { qColor = ChatFormatting.GRAY;   quality = "Luffing"; }

        return Component.literal(dir).withStyle(ChatFormatting.WHITE)
            .append(Component.literal("  \u2022 ").withStyle(ChatFormatting.DARK_GRAY))
            .append(Component.literal(quality).withStyle(qColor));
    }


    // Draw a wind arrow. cx,cy = centre of a small compass on the HUD.
    private static void drawWindCompass(GuiGraphics g, Minecraft mc, int cx, int cy) {
        float windToward = WindManager.getWindDir();
        float strength   = WindManager.getWindStrength();

        // Show wind relative to the player's facing so "up" = where they look.
        float viewYaw = mc.player.getYRot();
        float rel = (float) Math.toRadians(net.minecraft.util.Mth.wrapDegrees(windToward - viewYaw));

        int len = 10 + Math.round(strength * 6);   // arrow length scales with strength
        int ax = cx + Math.round((float) Math.sin(rel) * len);
        int ay = cy - Math.round((float) Math.cos(rel) * len);

        int col = 0xFFFFFFFF;
        // shaft
        g.fill(cx - 1, cy - 1, cx + 1, cy + 1, 0xFF3060FF);       // hub dot
        drawLine(g, cx, cy, ax, ay, col);
        // simple label
        g.drawString(mc.font, "Wind", cx - 12, cy + 12, 0xFFFFFFFF);
    }

    // Minimal line helper (Bresenham-ish via fill dots).
    private static void drawLine(GuiGraphics g, int x0, int y0, int x1, int y1, int color) {
        int dx = Math.abs(x1 - x0), dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1, sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        while (true) {
            g.fill(x0, y0, x0 + 1, y0 + 1, color);
            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * err;
            if (e2 > -dy) { err -= dy; x0 += sx; }
            if (e2 <  dx) { err += dx; y0 += sy; }
        }
    }



    private ShipHud() {}
}