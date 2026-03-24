package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.casting.ArmorCastingComponent;
import net.ent.entstupidstuff.api.casting.CastingTemplateItem;
import net.ent.entstupidstuff.api.casting.ToolCastingComponent;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(SmithingMenu.class)
public class SmithingMenuMixin {

    @Inject(
        method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V",
        at = @At("TAIL")
    )
    private void onInit(int syncId, Inventory inv, ContainerLevelAccess access, CallbackInfo ci) {
        SmithingMenu menu = (SmithingMenu)(Object) this;

        // Replace template slot (0) to accept CastTemplateItem
        Slot oldTemplate = menu.slots.get(SmithingMenu.TEMPLATE_SLOT);
        Slot newTemplate = new Slot(oldTemplate.container, oldTemplate.getContainerSlot(), oldTemplate.x, oldTemplate.y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof CastingTemplateItem || oldTemplate.mayPlace(stack);
            }
            @Override
            public boolean mayPickup(Player player) {
                return true;
            }
        };
        newTemplate.index = oldTemplate.index;
        menu.slots.set(SmithingMenu.TEMPLATE_SLOT, newTemplate);

        // Replace addition slot (2) to accept lava bucket
        Slot oldAddition = menu.slots.get(SmithingMenu.ADDITIONAL_SLOT);
        Slot newAddition = new Slot(oldAddition.container, oldAddition.getContainerSlot(), oldAddition.x, oldAddition.y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.LAVA_BUCKET) || oldAddition.mayPlace(stack);
            }
            @Override
            public boolean mayPickup(Player player) {
                return true;
            }
        };
        newAddition.index = oldAddition.index;
        menu.slots.set(SmithingMenu.ADDITIONAL_SLOT, newAddition);
    }

    @Inject(method = "canMoveIntoInputSlots", at = @At("HEAD"), cancellable = true)
    private void allowShiftClick(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        SmithingMenu menu = (SmithingMenu)(Object) this;
        if (stack.getItem() instanceof CastingTemplateItem
                && !menu.getSlot(SmithingMenu.TEMPLATE_SLOT).hasItem()) {
            cir.setReturnValue(true);
            return;
        }
        if (stack.is(Items.LAVA_BUCKET)
                && !menu.getSlot(SmithingMenu.ADDITIONAL_SLOT).hasItem()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canTakeItemForPickAll", at = @At("HEAD"), cancellable = true)
    private void allowPickup(ItemStack stack, Slot slot, CallbackInfoReturnable<Boolean> cir) {
        if (slot.index == SmithingMenu.TEMPLATE_SLOT || slot.index == SmithingMenu.ADDITIONAL_SLOT) {
            cir.setReturnValue(slot.hasItem());
        }
    }

    @Inject(method = "createResult", at = @At("TAIL"))
    private void onCreateResult(CallbackInfo ci) {
        ItemCombinerMenuAccessor accessor = (ItemCombinerMenuAccessor)(Object) this;
        ItemStack cast     = accessor.entstupidstuff$getInputSlots().getItem(0);
        ItemStack base     = accessor.entstupidstuff$getInputSlots().getItem(1);
        ItemStack addition = accessor.entstupidstuff$getInputSlots().getItem(2);

        if (!accessor.entstupidstuff$getResultSlots().getItem(0).isEmpty()) return;
        if (cast.isEmpty() || base.isEmpty() || addition.isEmpty()) return;
        if (!addition.is(Items.LAVA_BUCKET)) return;
        if (!(cast.getItem() instanceof CastingTemplateItem castTemplate)) return;
        if (!castTemplate.canApplyTo(base)) return;

        ItemStack result = base.copy();

        if (castTemplate.isToolItem(base)) {
            //result.set(ModDataComponentTypes.TOOL_CAST, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, castTemplate.getCastName()));
            result.set(ModDataComponentTypes.TOOL_CAST,
                new ToolCastingComponent(
                    ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, castTemplate.getCastName())
                ));
            accessor.entstupidstuff$getResultSlots().setItem(0, result);
        } else if (castTemplate.isArmorItem(base)) {
            //result.set(ModDataComponentTypes.ARMOR_CAST, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, castTemplate.getCastName()));
            result.set(ModDataComponentTypes.ARMOR_CAST,
                new ArmorCastingComponent(
                    ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, castTemplate.getCastName())
                ));
            accessor.entstupidstuff$getResultSlots().setItem(0, result);
        }
    }

    @Inject(method = "onTake", at = @At("HEAD"), cancellable = true)
    private void onTakeCastResult(Player player, ItemStack result, CallbackInfo ci) {
        ItemCombinerMenuAccessor accessor = (ItemCombinerMenuAccessor)(Object) this;
        ItemStack cast = accessor.entstupidstuff$getInputSlots().getItem(0);

        if (cast.getItem() instanceof CastingTemplateItem) {
            result.onCraftedBy(player, result.getCount());
            // Replace lava bucket with empty bucket
            accessor.entstupidstuff$getInputSlots().setItem(2, new ItemStack(Items.BUCKET));
            // Clear base item slot
            accessor.entstupidstuff$getInputSlots().setItem(1, ItemStack.EMPTY);
            // Cast stays - reusable
            ci.cancel();
        }
    }
}