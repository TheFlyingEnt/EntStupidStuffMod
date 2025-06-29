package net.ent.entstupidstuff.item.itemType;

import java.util.List;

import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

public class BattleAxeItem extends WeaponItem{

    public BattleAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(
        toolMaterial, //Tool Material
        5  + toolMaterial.getAttackDamage(), //Attack Damage
         -2.6f,  //Attack Speed
        1, //Reach
        1, //Sweep Ratio
        1))); //Knockback
    }

    // For 1.20.5+ Adding TwoHanded ToolTip
    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
    }

}
