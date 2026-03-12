package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.mold.ArmorMoldItem;
import net.ent.entstupidstuff.api.mold.ToolMoldItem;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {

    @Inject(method = "createResult", at = @At("TAIL"))
    private void onCreateResult(CallbackInfo ci) {
        ItemCombinerMenuAccessor accessor = (ItemCombinerMenuAccessor)(Object) this;
        ItemStack left  = accessor.entstupidstuff$getInputSlots().getItem(0);
        ItemStack right = accessor.entstupidstuff$getInputSlots().getItem(1);

        if (left.isEmpty()) return;

        if (right.getItem() instanceof ToolMoldItem toolMold
                && ToolMoldItem.isValidTarget(left)) {
            ItemStack result = left.copy();
            result.set(ModDataComponentTypes.TOOL_MOLD,
                ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, toolMold.getMoldName()));
            accessor.entstupidstuff$getResultSlots().setItem(0, result);

        } else if (right.getItem() instanceof ArmorMoldItem armorMold
                && ArmorMoldItem.isValidTarget(left)) {
            ItemStack result = left.copy();
            result.set(ModDataComponentTypes.ARMOR_MOLD,
                ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, armorMold.getMoldName()));
            accessor.entstupidstuff$getResultSlots().setItem(0, result);
        }
    }
}
