package net.ent.entstupidstuff.mixin;

import java.util.List;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.api.casting.CastingTemplateItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;

@Mixin(SmithingScreen.class)
@Environment(EnvType.CLIENT)
public class SmithingScreenMixin {

    @Shadow private CyclingSlotBackground templateIcon;
    @Shadow private CyclingSlotBackground baseIcon;
    @Shadow private CyclingSlotBackground additionalIcon;

    @Inject(method = "containerTick", at = @At("HEAD"), cancellable = true)
    private void onContainerTick(CallbackInfo ci) {
        SmithingScreen screen = (SmithingScreen)(Object) this;
        ItemStack templateSlot = screen.getMenu().getSlot(SmithingMenu.TEMPLATE_SLOT).getItem();

        if (templateSlot.getItem() instanceof CastingTemplateItem cast) {
            // Cancel vanilla tick entirely and handle all icons ourselves
            ci.cancel();

            // Still tick template icon with vanilla smithing template icons
            // so it keeps cycling normally
            this.templateIcon.tick(List.of(
                ResourceLocation.withDefaultNamespace("container/slot/smithing_template_armor_trim"),
                ResourceLocation.withDefaultNamespace("container/slot/smithing_template_netherite_upgrade")
            ));

            // Tick base and additional with our custom icons
            this.baseIcon.tick(cast.getBaseSlotEmptyIcons());
            this.additionalIcon.tick(cast.getAdditionSlotEmptyIcons());
        }
        // If no cast, vanilla runs normally
    }

    @Inject(method = "renderOnboardingTooltips", at = @At("HEAD"), cancellable = true)
    private void onRenderOnboardingTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci) {
        SmithingScreen screen = (SmithingScreen)(Object) this;
        ItemStack templateSlot = screen.getMenu().getSlot(SmithingMenu.TEMPLATE_SLOT).getItem();

        if (!(templateSlot.getItem() instanceof CastingTemplateItem cast)) return;

        ci.cancel();

        Optional<Component> optional = Optional.empty();
        SmithingScreenAccessor accessor = (SmithingScreenAccessor)(Object) this;

        if (accessor.entstupidstuff$getHoveredSlot() != null) {
            ItemStack hoveredItem = accessor.entstupidstuff$getHoveredSlot().getItem();
            if (hoveredItem.isEmpty()) {
                if (accessor.entstupidstuff$getHoveredSlot().index == SmithingMenu.BASE_SLOT) {
                    optional = Optional.of(cast.getBaseSlotDescription());
                } else if (accessor.entstupidstuff$getHoveredSlot().index == SmithingMenu.ADDITIONAL_SLOT) {
                    optional = Optional.of(cast.getAdditionSlotDescription());
                }
            }
        }

        SmithingScreenFontAccessor fontAccessor = (SmithingScreenFontAccessor)(Object) this;
        optional.ifPresent(component ->
            guiGraphics.setTooltipForNextFrame(
                fontAccessor.entstupidstuff$getFont(),
                fontAccessor.entstupidstuff$getFont().split(component, 115),
                mouseX, mouseY
            )
        );
    }
}