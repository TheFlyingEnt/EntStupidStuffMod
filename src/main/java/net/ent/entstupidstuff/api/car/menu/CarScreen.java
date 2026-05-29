package net.ent.entstupidstuff.api.car.menu;

import java.util.Optional;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
 
/**
 * Client-side car customization screen.
 *
 * GUI texture: assets/entstupidstuff/textures/gui/car_menu.png (176×180)
 *
 * Layout matches the slot positions in CarMenu:
 *   Row 1:  [Plate]  [Fuel]  ----car silhouette----  [Wrap]  [Radio]
 *   Row 2:       [FL wheel]       [FR wheel]
 *   Row 3:       [RL wheel]       [RR wheel]
 *   ─────────────────────────────────────────
 *   Player inventory (3 rows + hotbar)
 *
 * Labels are drawn on the texture itself — "Plate", "Fuel", "Wrap",
 * "Radio", "FL", "FR", "RL", "RR". The Java code only draws the
 * background texture and slot highlights.
 */
public class CarScreen extends AbstractContainerScreen<CarMenu> {
 
    private static final ResourceLocation MENU_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/gui/car_menu.png");
    private static final ResourceLocation EMPTY_SLOT_WRAP = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/car_wrap");
    private static final ResourceLocation EMPTY_SLOT_PLATE_NA = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/license_plate_na");
    private static final ResourceLocation EMPTY_SLOT_PLATE_EU = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/license_plate_eu");
    private static final ResourceLocation EMPTY_SLOT_WHEEL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/wheel");
    private static final ResourceLocation EMPTY_SLOT_FUEL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/fuel_canister");
    
    public CarScreen(CarMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageHeight = 180;
        this.inventoryLabelY = this.imageHeight - 94;
    }
 
    @Override
    protected void renderBg(GuiGraphics gfx, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		gfx.blit(RenderPipelines.GUI_TEXTURED, MENU_TEXTURE, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 176, 180);

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_WRAP,
            EMPTY_SLOT_WRAP
        );

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_WHEEL_FL,
            EMPTY_SLOT_WHEEL
        );

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_WHEEL_FR,
            EMPTY_SLOT_WHEEL
        );

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_WHEEL_RR,
            EMPTY_SLOT_WHEEL
        );

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_WHEEL_RL,
            EMPTY_SLOT_WHEEL
        );

        renderEmptySlotIcon(
            gfx,
            CarMenu.SLOT_FUEL,
            EMPTY_SLOT_FUEL
        );

        var slot = this.menu.getSlot(CarMenu.SLOT_PLATE);

        if (!slot.hasItem()) {

            long time = Minecraft.getInstance().level.getGameTime();

            // swap every 20 ticks (1 second)
            ResourceLocation sprite =
                    ((time / 20) % 2 == 0)
                            ? EMPTY_SLOT_PLATE_NA
                            : EMPTY_SLOT_PLATE_EU;

            gfx.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    sprite,
                    this.leftPos + slot.x,
                    this.topPos + slot.y,
                    16,
                    16
            );
        }

    }
 
    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTick) {
        super.render(gfx, mouseX, mouseY, partialTick);
        this.renderTooltip(gfx, mouseX, mouseY);
        this.renderOnboardingTooltips(gfx, mouseX, mouseY);
 
        // Draw slot labels
        int x = this.leftPos;
        int y = this.topPos;
        gfx.drawString(this.font, "Plate",  x + 12,  y + 9,  0x404040, false);
        gfx.drawString(this.font, "Fuel",   x + 50,  y + 9,  0x404040, false);
        gfx.drawString(this.font, "Wrap",   x + 104, y + 9,  0x404040, false);
        gfx.drawString(this.font, "Radio",  x + 137, y + 9,  0x404040, false);
        gfx.drawString(this.font, "FL",     x + 38,  y + 43, 0x606060, false);
        gfx.drawString(this.font, "FR",     x + 92,  y + 43, 0x606060, false);
        gfx.drawString(this.font, "RL",     x + 38,  y + 63, 0x606060, false);
        gfx.drawString(this.font, "RR",     x + 92,  y + 63, 0x606060, false);
 
        // Fuel bar — show remaining fuel as a colored bar
        var fuelStack = this.menu.getSlot(CarMenu.SLOT_FUEL).getItem();
        if (!fuelStack.isEmpty() && fuelStack.isDamageableItem()) {
            int maxDmg = fuelStack.getMaxDamage();
            int dmg    = fuelStack.getDamageValue();
            float pct  = 1f - (float) dmg / maxDmg;
            int barW   = (int)(16 * pct);
            int color  = pct > 0.5f ? 0xFF00AA00 : pct > 0.2f ? 0xFFAAAA00 : 0xFFAA0000;
            gfx.fill(x + 53, y + 37, x + 53 + barW, y + 39, color);
        }
    }

    private void renderEmptySlotIcon(GuiGraphics gfx, int slotIndex, ResourceLocation sprite) {
        var slot = this.menu.getSlot(slotIndex);
        if (!slot.hasItem()) {

            gfx.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    sprite,
                    this.leftPos + slot.x,
                    this.topPos + slot.y,
                    16,
                    16
            );
        }
    }

    private static final Component SLOT_PLATE_TOOLTIP = Component.translatable("container.carscreen.slot_plate_tooltip");
    private static final Component SLOT_FUEL_TOOLTIP = Component.translatable("container.carscreen.slot_fuel_tooltip");
    private static final Component SLOT_WRAP_TOOLTIP = Component.translatable("container.carscreen.slot_warp_tooltip");
    private static final Component SLOT_RADIO_TOOLTIP = Component.translatable("container.carscreen.slot_radio_tooltip");
    private static final Component SLOT_WHEEL_TOOLTIP = Component.translatable("container.carscreen.slot_wheel_tooltip");


    private void renderOnboardingTooltips(GuiGraphics guiGraphics, int i, int j) {
		Optional<Component> optional = Optional.empty();

		if (this.hoveredSlot != null) {
            if (this.hoveredSlot.index == 0) {
				optional = Optional.of(SLOT_PLATE_TOOLTIP);
			} else if (this.hoveredSlot.index == 1) {
				optional = Optional.of(SLOT_FUEL_TOOLTIP);
			} else if (this.hoveredSlot.index == 2) {
				optional = Optional.of(SLOT_WRAP_TOOLTIP);
			} else if (this.hoveredSlot.index == 3) {
				optional = Optional.of(SLOT_RADIO_TOOLTIP);
			} else if (this.hoveredSlot.index >= 4 && this.hoveredSlot.index <= 7) {
				optional = Optional.of(SLOT_WHEEL_TOOLTIP);
			}
		}

		optional.ifPresent(component -> guiGraphics.setTooltipForNextFrame(this.font, this.font.split(component, 115), i, j));
	}



}

