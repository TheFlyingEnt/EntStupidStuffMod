package net.ent.entstupidstuff.api.hat;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
 
/**
 * Hat selection menu.
 *
 * Layout:
 *   ┌──────────────────────────────────────┐
 *   │  [Player Preview]  │  [Hat List    ] │
 *   │                    │  ┌───────────┐  │
 *   │   (player model    │  │ hat icon  │  │
 *   │    rotates to      │  │ hat name  │  │
 *   │    face cursor)    │  │ ...       │  │
 *   │                    │  └───────────┘  │
 *   │  [Remove Hat btn]  │  [scrollbar   ] │
 *   └──────────────────────────────────────┘
 *
 * Clicking a row sends {@link HatSelectPayload} C2S.
 * Hovering a row previews the hat on the player model locally (no packet).
 */

public class HatMenuScreen extends Screen {
 
    // ── Layout ────────────────────────────────────────────────────────────────
    private static final int PANEL_WIDTH       = 320;
    private static final int PANEL_HEIGHT      = 230;
    private static final int PREVIEW_WIDTH     = 120;
    private static final int ROW_HEIGHT        = 22;
    private static final int ROWS_VISIBLE      = 8;
    private static final int LIST_WIDTH        = PANEL_WIDTH - PREVIEW_WIDTH - 18;
    private static final int SCROLLBAR_WIDTH   = 6;
    private static final int REMOVE_BTN_HEIGHT = 18;
    private static final int SEARCH_HEIGHT     = 16;
    private static final int SEARCH_MARGIN     = 4;
 
    // ── Colours ───────────────────────────────────────────────────────────────
    private static final int COLOR_WHITE = 0xFFFFFFFF;
    private static final int COLOR_GRAY  = 0xFFAAAAAA;
    private static final int COLOR_GREEN = 0xFF88FF88;
 
    // ── State ─────────────────────────────────────────────────────────────────
    private final List<String> allHats      = new ArrayList<>(HatRegistry.getNames());
    private final List<String> filteredHats = new ArrayList<>(allHats);
    private int     scrollOffset = 0;
    private int     hoveredRow   = -1;
    private String  previewHat  = "";
    private boolean scrolling   = false;
 
    private EditBox searchBox;
    private int panelX, panelY;
 
    // Pre-computed list top so all methods agree on the same Y origin
    private int listTop;
 
    public HatMenuScreen() {
        super(Component.translatable("gui.entstupidstuff.hat_menu"));
    }
 
    @Override
    protected void init() {
        panelX = (width  - PANEL_WIDTH)  / 2;
        panelY = (height - PANEL_HEIGHT) / 2;
 
        // List rows start just below the "Hats" header
        listTop = panelY + 4 + font.lineHeight + SEARCH_MARGIN;
 
        // Search box sits at the bottom of the list panel, above the panel border
        int listX   = panelX + PREVIEW_WIDTH + 4;
        int searchY = panelY + PANEL_HEIGHT - SEARCH_HEIGHT - SEARCH_MARGIN - 2;
 
        searchBox = new EditBox(
            font,
            listX, searchY,
            LIST_WIDTH - 2, SEARCH_HEIGHT,
            Component.literal("Search...")
        );
        searchBox.setHint(Component.literal("Search..."));
        searchBox.setMaxLength(64);
        searchBox.setBordered(true);
        searchBox.setResponder(text -> {
            scrollOffset = 0;
            filteredHats.clear();
            String lower = text.toLowerCase();
            for (String hat : allHats) {
                if (hat.toLowerCase().contains(lower)) filteredHats.add(hat);
            }
        });
 
        addRenderableWidget(searchBox);
 
        var player = Minecraft.getInstance().player;
        if (player != null) previewHat = player.getAttachedOrElse(ModAttachments.HAT, "");
    }
 
    // ── Rendering ─────────────────────────────────────────────────────────────
 
    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        renderTransparentBackground(g);
 
        g.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF2B2B2B);
        g.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + 1,            0xFF555555);
        g.fill(panelX, panelY, panelX + 1, panelY + PANEL_HEIGHT,           0xFF555555);
        g.fill(panelX + PANEL_WIDTH - 1, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF444444);
        g.fill(panelX, panelY + PANEL_HEIGHT - 1, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF444444);
        g.fill(panelX + PREVIEW_WIDTH, panelY + 4, panelX + PREVIEW_WIDTH + 1, panelY + PANEL_HEIGHT - 4, 0xFF444444);
 
        g.drawCenteredString(font, Component.literal("Hat Menu"), panelX + PREVIEW_WIDTH / 2, panelY + 6, COLOR_WHITE);
 
        int listX = panelX + PREVIEW_WIDTH + 4;
        g.drawString(font, Component.literal("Hats"), listX + 2, panelY + 6, COLOR_GRAY);
 
        renderPlayerPreview(g, mouseX, mouseY);
        renderRemoveButton(g, mouseX, mouseY);
        renderHatList(g, mouseX, mouseY);
        renderScrollbar(g);
 
        super.render(g, mouseX, mouseY, delta);
    }
 
    private void renderPlayerPreview(GuiGraphics g, int mouseX, int mouseY) {
        var player = Minecraft.getInstance().player;
        if (player == null) return;
 
        String savedHat = player.getAttachedOrElse(ModAttachments.HAT, "");
        player.setAttached(ModAttachments.HAT, previewHat);
 
        InventoryScreen.renderEntityInInventoryFollowsMouse(
            g,
            panelX + 4, panelY + 20,
            panelX + PREVIEW_WIDTH - 4, panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 10,
            40, 0.0f,
            (float) mouseX, (float) mouseY,
            player
        );
 
        player.setAttached(ModAttachments.HAT, savedHat);
    }
 
    private void renderRemoveButton(GuiGraphics g, int mouseX, int mouseY) {
        int bx = panelX + 4;
        int by = panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 4;
        int bw = PREVIEW_WIDTH - 8;
 
        boolean hovered = mouseX >= bx && mouseX < bx + bw
                       && mouseY >= by && mouseY < by + REMOVE_BTN_HEIGHT;
 
        g.fill(bx, by, bx + bw, by + REMOVE_BTN_HEIGHT, hovered ? 0xFF8B1A1A : 0xFF5C1111);
        g.fill(bx, by, bx + bw, by + 1, 0xFFAA3333);
        g.drawCenteredString(font, Component.literal("Remove Hat"), bx + bw / 2, by + 5, COLOR_WHITE);
 
        if (hovered) previewHat = "";
    }
 
    private void renderHatList(GuiGraphics g, int mouseX, int mouseY) {
        int listX = panelX + PREVIEW_WIDTH + 4;
        int listW = LIST_WIDTH;
 
        // Clip list rows — stops above the search box
        int listBottom = panelY + PANEL_HEIGHT - SEARCH_HEIGHT - SEARCH_MARGIN * 2 - 2;
        g.enableScissor(listX, listTop, listX + listW, listBottom);
 
        hoveredRow = -1;
 
        for (int i = 0; i < ROWS_VISIBLE; i++) {
            int idx = scrollOffset + i;
            if (idx >= filteredHats.size()) break;
 
            String hatName = filteredHats.get(idx);
            int ry = listTop + i * ROW_HEIGHT;
 
            boolean hovered = mouseX >= listX && mouseX < listX + listW
                           && mouseY >= ry    && mouseY < ry + ROW_HEIGHT;
 
            var player = Minecraft.getInstance().player;
            String equipped = player != null ? player.getAttachedOrElse(ModAttachments.HAT, "") : "";
            boolean isEquipped = hatName.equals(equipped);
 
            if (isEquipped) {
                g.fill(listX, ry, listX + listW, ry + ROW_HEIGHT, 0xFF3A5E3A);
            } else if (hovered) {
                g.fill(listX, ry, listX + listW, ry + ROW_HEIGHT, 0xFF3C3C50);
                hoveredRow = idx;
                previewHat = hatName;
            }
 
            g.fill(listX, ry + ROW_HEIGHT - 1, listX + listW, ry + ROW_HEIGHT, 0xFF3A3A3A);
 
            var item = HatRegistry.getHat(hatName);
            if (item != null) g.renderItem(new ItemStack(item), listX + 3, ry + 3);
 
            Component name = Component.translatable("item.entstupidstuff." + hatName);
            g.drawString(font, name, listX + 24, ry + 7, isEquipped ? COLOR_GREEN : COLOR_WHITE);
        }
 
        g.disableScissor();
    }
 
    private void renderScrollbar(GuiGraphics g) {
        if (filteredHats.size() <= ROWS_VISIBLE) return;
 
        int trackH  = ROWS_VISIBLE * ROW_HEIGHT;
        int sbX     = panelX + PANEL_WIDTH - SCROLLBAR_WIDTH - 4;
 
        g.fill(sbX, listTop, sbX + SCROLLBAR_WIDTH, listTop + trackH, 0xFF1A1A1A);
 
        float ratio   = (float) ROWS_VISIBLE / filteredHats.size();
        int   thumbH  = Math.max(16, (int)(trackH * ratio));
        int   maxScroll = filteredHats.size() - ROWS_VISIBLE;
        int   thumbY  = listTop + (int)((trackH - thumbH) * ((float) scrollOffset / maxScroll));
 
        g.fill(sbX, thumbY, sbX + SCROLLBAR_WIDTH, thumbY + thumbH, 0xFF888888);
        g.fill(sbX, thumbY, sbX + SCROLLBAR_WIDTH, thumbY + 1,      0xFFAAAAAA);
    }
 
    // ── Input ─────────────────────────────────────────────────────────────────
 
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int maxScroll = Math.max(0, filteredHats.size() - ROWS_VISIBLE);
        scrollOffset  = Mth.clamp(scrollOffset - (int) Math.signum(scrollY), 0, maxScroll);
        return true;
    }
 
    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (event.button() != 0) return super.mouseClicked(event, bl);
 
        int mx = (int) event.x();
        int my = (int) event.y();
 
        // Scrollbar
        int sbX    = panelX + PANEL_WIDTH - SCROLLBAR_WIDTH - 4;
        int trackH = ROWS_VISIBLE * ROW_HEIGHT;
        if (mx >= sbX && mx <= sbX + SCROLLBAR_WIDTH && my >= listTop && my <= listTop + trackH) {
            scrolling = true;
            updateScrollFromMouse(my);
            return true;
        }
 
        // Remove button
        int bx = panelX + 4;
        int by = panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 4;
        int bw = PREVIEW_WIDTH - 8;
        if (mx >= bx && mx <= bx + bw && my >= by && my <= by + REMOVE_BTN_HEIGHT) {
            selectHat("");
            return true;
        }
 
        // Hat row
        if (hoveredRow >= 0 && hoveredRow < filteredHats.size()) {
            selectHat(filteredHats.get(hoveredRow));
            return true;
        }
 
        return super.mouseClicked(event, bl);
    }
 
    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        if (scrolling) {
            updateScrollFromMouse((int) event.y());
            return true;
        }
        return super.mouseDragged(event, dx, dy);
    }
 
    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        scrolling = false;
        return super.mouseReleased(event);
    }
 
    // ── Helpers ───────────────────────────────────────────────────────────────
 
    private void selectHat(String hatName) {
        var player = Minecraft.getInstance().player;
        if (player != null) player.setAttached(ModAttachments.HAT, hatName);
        previewHat = hatName;
        ClientPlayNetworking.send(new HatSelectPayload(hatName));
    }
 
    private void updateScrollFromMouse(int mouseY) {
        int trackH = ROWS_VISIBLE * ROW_HEIGHT;
        float t    = Mth.clamp((mouseY - listTop) / (float) trackH, 0f, 1f);
        scrollOffset = Mth.clamp(
            (int)(t * (filteredHats.size() - ROWS_VISIBLE)),
            0, Math.max(0, filteredHats.size() - ROWS_VISIBLE)
        );
    }
 
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

/*public class HatMenuScreen extends Screen {
 
    private static final int PANEL_WIDTH       = 320;
    private static final int PANEL_HEIGHT      = 210;
    private static final int PREVIEW_WIDTH     = 120;
    private static final int ROW_HEIGHT        = 22;
    private static final int ROWS_VISIBLE      = 8;
    private static final int LIST_WIDTH        = PANEL_WIDTH - PREVIEW_WIDTH - 18;
    private static final int SCROLLBAR_WIDTH   = 6;
    private static final int REMOVE_BTN_HEIGHT = 18;
 
    // Fully-opaque ARGB colours — 0xFFFFFF is alpha=0 (transparent) in 1.21.10
    private static final int COLOR_WHITE       = 0xFFFFFFFF;  // -1
    private static final int COLOR_GRAY        = 0xFFAAAAAA;
    private static final int COLOR_GREEN       = 0xFF88FF88;
 
    private final List<String> hats = new ArrayList<>(HatRegistry.getNames());
    private int     scrollOffset = 0;
    private int     hoveredRow   = -1;
    private String  previewHat  = "";
    private boolean scrolling   = false;
 
    private int panelX, panelY;
 
    public HatMenuScreen() {
        super(Component.translatable("gui.entstupidstuff.hat_menu"));
    }
 
    @Override
    protected void init() {
        panelX = (width  - PANEL_WIDTH)  / 2;
        panelY = (height - PANEL_HEIGHT) / 2;
 
        var player = Minecraft.getInstance().player;
        if (player != null) {
            previewHat = player.getAttachedOrElse(ModAttachments.HAT, "");
        }
    }
 
    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        renderTransparentBackground(g);
 
        g.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF2B2B2B);
        g.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + 1,            0xFF555555);
        g.fill(panelX, panelY, panelX + 1, panelY + PANEL_HEIGHT,           0xFF555555);
        g.fill(panelX + PANEL_WIDTH - 1, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF444444);
        g.fill(panelX, panelY + PANEL_HEIGHT - 1, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xFF444444);
        g.fill(panelX + PREVIEW_WIDTH, panelY + 4, panelX + PREVIEW_WIDTH + 1, panelY + PANEL_HEIGHT - 4, 0xFF444444);
 
        g.drawCenteredString(font, title, panelX + PREVIEW_WIDTH / 2, panelY + 6, COLOR_WHITE);
 
        renderPlayerPreview(g, mouseX, mouseY);
        renderRemoveButton(g, mouseX, mouseY);
        renderHatList(g, mouseX, mouseY);
        renderScrollbar(g);
 
        super.render(g, mouseX, mouseY, delta);
    }
 
    private void renderPlayerPreview(GuiGraphics g, int mouseX, int mouseY) {
        var player = Minecraft.getInstance().player;
        if (player == null) return;
 
        String savedHat = player.getAttachedOrElse(ModAttachments.HAT, "");
        player.setAttached(ModAttachments.HAT, previewHat);
 
        InventoryScreen.renderEntityInInventoryFollowsMouse(
            g,
            panelX + 4,  panelY + 20,
            panelX + PREVIEW_WIDTH - 4, panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 10,
            40,
            0.0f,
            (float) mouseX, (float) mouseY,
            player
        );
 
        player.setAttached(ModAttachments.HAT, savedHat);
    }
 
    private void renderRemoveButton(GuiGraphics g, int mouseX, int mouseY) {
        int bx = panelX + 4;
        int by = panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 4;
        int bw = PREVIEW_WIDTH - 8;
 
        boolean hovered = mouseX >= bx && mouseX < bx + bw
                       && mouseY >= by && mouseY < by + REMOVE_BTN_HEIGHT;
 
        g.fill(bx, by, bx + bw, by + REMOVE_BTN_HEIGHT, hovered ? 0xFF8B1A1A : 0xFF5C1111);
        g.fill(bx, by, bx + bw, by + 1, 0xFFAA3333);
        g.drawCenteredString(font, Component.literal("Remove Hat"), bx + bw / 2, by + 5, COLOR_WHITE);
 
        if (hovered) previewHat = "";
    }
 
    private void renderHatList(GuiGraphics g, int mouseX, int mouseY) {
        int listX = panelX + PREVIEW_WIDTH + 4;
        int listY = panelY + 4;
        int listW = LIST_WIDTH;
 
        g.drawString(font, Component.literal("Hats"), listX + 2, listY + 4, COLOR_GRAY);
        listY += 14;
 
        g.enableScissor(listX, listY, listX + listW, listY + ROWS_VISIBLE * ROW_HEIGHT);
 
        hoveredRow = -1;
 
        for (int i = 0; i < ROWS_VISIBLE; i++) {
            int idx = scrollOffset + i;
            if (idx >= hats.size()) break;
 
            String hatName = hats.get(idx);
            int ry = listY + i * ROW_HEIGHT;
 
            boolean hovered = mouseX >= listX && mouseX < listX + listW
                           && mouseY >= ry    && mouseY < ry + ROW_HEIGHT;
 
            var player = Minecraft.getInstance().player;
            String equipped = player != null ? player.getAttachedOrElse(ModAttachments.HAT, "") : "";
            boolean isEquipped = hatName.equals(equipped);
 
            if (isEquipped) {
                g.fill(listX, ry, listX + listW, ry + ROW_HEIGHT, 0xFF3A5E3A);
            } else if (hovered) {
                g.fill(listX, ry, listX + listW, ry + ROW_HEIGHT, 0xFF3C3C50);
                hoveredRow = idx;
                previewHat = hatName;
            }
 
            g.fill(listX, ry + ROW_HEIGHT - 1, listX + listW, ry + ROW_HEIGHT, 0xFF3A3A3A);
 
            var item = HatRegistry.getHat(hatName);
            if (item != null) g.renderItem(new ItemStack(item), listX + 3, ry + 3);
 
            Component nameComponent = Component.translatable("item.entstupidstuff." + hatName);
            g.drawString(font, nameComponent, listX + 24, ry + 7, isEquipped ? COLOR_GREEN : COLOR_WHITE);
        }
 
        g.disableScissor();
    }
 
    private void renderScrollbar(GuiGraphics g) {
        if (hats.size() <= ROWS_VISIBLE) return;
 
        int listY    = panelY + 18;
        int trackH   = ROWS_VISIBLE * ROW_HEIGHT;
        int sbX      = panelX + PANEL_WIDTH - SCROLLBAR_WIDTH - 4;
 
        g.fill(sbX, listY, sbX + SCROLLBAR_WIDTH, listY + trackH, 0xFF1A1A1A);
 
        float ratio   = (float) ROWS_VISIBLE / hats.size();
        int   thumbH  = Math.max(16, (int)(trackH * ratio));
        int   maxScroll = hats.size() - ROWS_VISIBLE;
        int   thumbY  = listY + (int)((trackH - thumbH) * ((float) scrollOffset / maxScroll));
 
        g.fill(sbX, thumbY, sbX + SCROLLBAR_WIDTH, thumbY + thumbH, 0xFF888888);
        g.fill(sbX, thumbY, sbX + SCROLLBAR_WIDTH, thumbY + 1,      0xFFAAAAAA);
    }
 
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int maxScroll = Math.max(0, hats.size() - ROWS_VISIBLE);
        scrollOffset  = Mth.clamp(scrollOffset - (int) Math.signum(scrollY), 0, maxScroll);
        return true;
    }
 
    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (event.button() != 0) return super.mouseClicked(event, bl);
 
        int mx = (int) event.x();
        int my = (int) event.y();
 
        int sbX    = panelX + PANEL_WIDTH - SCROLLBAR_WIDTH - 4;
        int sbY    = panelY + 18;
        int trackH = ROWS_VISIBLE * ROW_HEIGHT;
        if (mx >= sbX && mx <= sbX + SCROLLBAR_WIDTH && my >= sbY && my <= sbY + trackH) {
            scrolling = true;
            updateScrollFromMouse(my);
            return true;
        }
 
        int bx = panelX + 4;
        int by = panelY + PANEL_HEIGHT - REMOVE_BTN_HEIGHT - 4;
        int bw = PREVIEW_WIDTH - 8;
        if (mx >= bx && mx <= bx + bw && my >= by && my <= by + REMOVE_BTN_HEIGHT) {
            selectHat("");
            return true;
        }
 
        if (hoveredRow >= 0 && hoveredRow < hats.size()) {
            selectHat(hats.get(hoveredRow));
            return true;
        }
 
        return super.mouseClicked(event, bl);
    }
 
    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        if (scrolling) {
            updateScrollFromMouse((int) event.y());
            return true;
        }
        return super.mouseDragged(event, dx, dy);
    }
 
    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        scrolling = false;
        return super.mouseReleased(event);
    }
 
    private void selectHat(String hatName) {
        var player = Minecraft.getInstance().player;
        if (player != null) player.setAttached(ModAttachments.HAT, hatName);
        previewHat = hatName;
        ClientPlayNetworking.send(new HatSelectPayload(hatName));
    }
 
    private void updateScrollFromMouse(int mouseY) {
        int sbY    = panelY + 18;
        int trackH = ROWS_VISIBLE * ROW_HEIGHT;
        float t    = Mth.clamp((mouseY - sbY) / (float) trackH, 0f, 1f);
        scrollOffset = Mth.clamp(
            (int)(t * (hats.size() - ROWS_VISIBLE)),
            0, Math.max(0, hats.size() - ROWS_VISIBLE)
        );
    }
 
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}*/