package net.ent.entstupidstuff.item.itemType;

import java.util.List;

import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class DaggerItem extends WeaponItem /*implements IBleedingTrait*/{

    /***
     * @param toolMaterial
     * @param settings
     * @param - Material
     * @param - # + toolMaterial Damage + 1
     * @param - Default Speed (4) - #
     * @param - AttackSpeed
    */

    public DaggerItem(ToolMaterial toolMaterial, Settings settings) { //Wood add is 1; So 1 + 4
        super(toolMaterial, settings);
        //super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(toolMaterial, (3 /*3.5*/)  + toolMaterial.attackDamageBonus(), -2.6f, 1, 0, 0)));
    }


    /*@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected ) {
        System.out.println("Dagger Check");
        TwoHandTrait.weaponCheck(stack, world, entity, slot, selected, this.getMaterial(), (float)(3.5)  + this.getMaterial().attackDamageBonus());

    }*/

    // For 1.20.5+ Adding TwoHanded ToolTip
    /*@Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.entstupidstuff.bleeding.tooltip").formatted(Formatting.GRAY));
    }*/

    /*@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        

		return super.postHit(stack, target, attacker);
	}*/


}
