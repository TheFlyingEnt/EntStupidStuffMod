package net.ent.entstupidstuff.api.legacy;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.minecraft.text.Text;
import java.util.List;

public class TwoHandTrait /*implements Trait*/{

    
    public void addTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip"));
    }


    //Code Clean up V2:

    public static final Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static boolean isOffHandFree(PlayerEntity player) {
        return !player.getOffHandStack().isEmpty();
    }

    public static void applyMiningFatigue(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 4));
    }

    public float modWeaponDamage(ToolMaterial material, float baseDamage, DamageSource source, LivingEntity attackerE, LivingEntity victiumE){
        float damageMultiplier = 0.25f;
        float finalDamage = (material.getAttackDamage() + baseDamage) * damageMultiplier;

        return (1 - finalDamage);
    }

    public static void applyTrait()
    {
        
    }

















/**
 * 
 * Legacy Code, Only Used for Long Swords
 * Soon the be @deprecated
 */



    public static boolean isUsingTwoHands(PlayerEntity player) {
        return !player.getOffHandStack().isEmpty();
    }

    /*public static void applyMiningFatigue(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 4));
    }*/

    //public static final Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static void applyDamageReduction(PlayerEntity player, boolean reduceDamage, double toolDamage) {
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        EntityAttributeInstance attackDamageInstance = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);

        // Remove the existing modifier if it exists
        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);
            attackDamageInstance.addTemporaryModifier(new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                toolDamage * (damageMultiplier - 1), 
                EntityAttributeModifier.Operation.ADD_VALUE)
            );
        }
        System.out.println(BASE_ATTACK_DAMAGE_MODIFIER_ID);
    }

    public static void weaponCheck(ItemStack stack, World world, Entity entity, int slot, boolean selected, ToolMaterial toolMaterial, float attackDamage ) {
        System.out.println("InMethod");
        if (!world.isClient && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            boolean isHoldingTwoHandedWeapon = player.getMainHandStack().getItem() instanceof WeaponItem;

            if (isHoldingTwoHandedWeapon) {
                boolean reduceDamage = isUsingTwoHands(player);
                System.out.println("Active");
                applyDamageReduction(player, reduceDamage, attackDamage);
                if (reduceDamage) {
                    applyMiningFatigue(player); //This is Running
                }

            } else {
                // Ensure the damage reduction is removed if the player is not holding the weapon
                System.out.println("Disable");
                applyDamageReduction(player, false, 0);
            }
        }
    }




    @Deprecated
    public float applyDamageReduction2(PlayerEntity player, LivingEntity victim, float toolDamage) {
        float modDamage = toolDamage;

        if(isUsingTwoHands(player) == false) {
            modDamage = modDamage * 0.75f;
        }
        return modDamage;

    }

    /*public static void applyDamageReduction(PlayerEntity player, boolean reduceDamage, double toolDamage) {
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        EntityAttributeInstance attackDamageInstance = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);

        // Remove the existing modifier if it exists
        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);
            
            // Add new modifier if we need to reduce damage
            if (reduceDamage) {
                double dr = toolDamage * (damageMultiplier - 1);
                System.out.println("Damage is now: " + (toolDamage * (damageMultiplier - 1)));
                System.out.println(BASE_ATTACK_DAMAGE_MODIFIER_ID);
                
                attackDamageInstance.addTemporaryModifier(new EntityAttributeModifier(
                    BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                    dr/*toolDamage * (damageMultiplier - 1)* 
                    EntityAttributeModifier.Operation.ADD_VALUE
                ));
            } 
        }
    }*/

}

