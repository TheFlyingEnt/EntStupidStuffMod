package net.ent.entstupidstuff.item.itemType;

import java.util.List;

import net.ent.entstupidstuff.api.IntTrait.IBluntTrait;
import net.ent.entstupidstuff.api.IntTrait.ITwoHandTrait;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class HammerItem extends SwordItem implements IBluntTrait, ITwoHandTrait{

    /***
     * @param toolMaterial
     * @param settings
     * @param - Material
     * @param - # + toolMaterial Damage + 1
     * @param - Default Speed (4) - #
     * @param - AttackSpeed
    */

    public HammerItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(toolMaterial, (6.5 /*5.5*/)  + toolMaterial.getAttackDamage(), -3.4f, 1, 0, 3)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.entstupidstuff.blunt.tooltip").formatted(Formatting.GRAY));
    }

    //Concept

    private double knockbackStrength = 0;

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        applyKnockback(target, attacker, knockbackStrength);
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    public void setKnockbackStrength() {
        this.knockbackStrength = 20;
    }

    private void applyKnockback(LivingEntity target, LivingEntity attacker, double strength) {
        double xRatio = attacker.getX() - target.getX();
        double zRatio = attacker.getZ() - target.getZ();
        while (xRatio * xRatio + zRatio * zRatio < 0.0001) {
            xRatio = (Math.random() - Math.random()) * 0.01;
            zRatio = (Math.random() - Math.random()) * 0.01;
        }
        target.takeKnockback(strength, xRatio, zRatio);
        target.velocityModified = true;
    }
}

