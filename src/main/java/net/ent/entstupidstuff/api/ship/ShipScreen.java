package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

/**
 * Ship management screen — shows the attachment slot and ship stats.
 * Compact GUI: attachment slot at top, player inventory below.
 */
public class ShipScreen extends AbstractContainerScreen<ShipMenu> {

    // GUI dimensions
    private static final int GUI_WIDTH  = 176;
    private static final int GUI_HEIGHT = 140;

    // Colors (ARGB)
    private static final int BG_OUTER  = 0xFF1A1A2E;   // dark navy
    private static final int BG_INNER  = 0xFF16213E;   // slightly lighter
    private static final int BORDER    = 0xFF0F3460;    // blue border
    private static final int SLOT_BG   = 0xFF0A0A1A;   // dark slot background
    private static final int SLOT_BORDER = 0xFF3A3A5C;  // slot border
    private static final int TEXT_WHITE = 0xFFFFFFFF;
    private static final int TEXT_GRAY  = 0xFFAAAAAA;
    private static final int TEXT_GOLD  = 0xFFFFAA00;

    public ShipScreen(ShipMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth = GUI_WIDTH;
        this.imageHeight = GUI_HEIGHT;
    }

    @Override
    protected void init() {
        super.init();
        // Hide the default inventory label
        this.inventoryLabelY = this.imageHeight - 94;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.titleLabelY = 6;
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        // ── Outer background ──
        g.fill(x, y, x + GUI_WIDTH, y + GUI_HEIGHT, BG_OUTER);
        // Inner panel
        g.fill(x + 3, y + 3, x + GUI_WIDTH - 3, y + GUI_HEIGHT - 3, BG_INNER);
        // Border lines
        g.fill(x, y, x + GUI_WIDTH, y + 1, BORDER);                    // top
        g.fill(x, y + GUI_HEIGHT - 1, x + GUI_WIDTH, y + GUI_HEIGHT, BORDER); // bottom
        g.fill(x, y, x + 1, y + GUI_HEIGHT, BORDER);                    // left
        g.fill(x + GUI_WIDTH - 1, y, x + GUI_WIDTH, y + GUI_HEIGHT, BORDER); // right

        // ── Attachment slot background ──
        int slotX = x + 79;   // matches slot x=80 minus 1px border
        int slotY = y + 19;   // matches slot y=20 minus 1px border
        g.fill(slotX - 1, slotY - 1, slotX + 19, slotY + 19, SLOT_BORDER);
        g.fill(slotX, slotY, slotX + 18, slotY + 18, SLOT_BG);

        // ── "BOW ATTACHMENT" label ──
        String label = "Bow Attachment";
        int labelW = this.font.width(label);
        g.drawString(this.font, label, x + (GUI_WIDTH - labelW) / 2, y + 8, TEXT_GOLD);

        // ── Attachment slot hint (when empty) ──
        if (!this.menu.getSlot(0).hasItem()) {
            g.drawString(this.font, "+", slotX + 6, slotY + 5, 0xFF555555);
        }

        // ── Ship stats below the attachment slot ──
        CustomBoatEntity ship = this.menu.getShip();
        if (ship != null) {
            int infoY = y + 42;
            String attachment = switch (ship.getAttachment()) {
                case CustomBoatEntity.ATTACHMENT_HARPOON -> "Harpoon";
                case CustomBoatEntity.ATTACHMENT_CANNON  -> "Cannon";
                default -> "None";
            };
            g.drawString(this.font, "Equipped: " + attachment, x + 8, infoY, TEXT_GRAY);
        }

        // ── Player inventory slot backgrounds ──
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int sx = x + 7 + col * 18;
                int sy = y + 55 + row * 18;
                g.fill(sx, sy, sx + 18, sy + 18, SLOT_BG);
            }
        }
        // Hotbar
        for (int col = 0; col < 9; col++) {
            int sx = x + 7 + col * 18;
            int sy = y + 113;
            g.fill(sx, sy, sx + 18, sy + 18, SLOT_BG);
        }
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        this.renderTooltip(g, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        // Don't render default title/inventory labels — we draw our own in renderBg
    }
}
