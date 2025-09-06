package net.ent.entstupidstuff.screen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DarkEnchantingTableScreen extends HandledScreen<DarkEnchantingTableHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(EntStupidStuff.MOD_ID, "textures/gui/pedestal/pedestal_gui.png");

    public DarkEnchantingTableScreen(DarkEnchantingTableHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
