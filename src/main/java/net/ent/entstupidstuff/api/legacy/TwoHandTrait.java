package net.ent.entstupidstuff.api.legacy;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class TwoHandTrait /*implements Trait*/{

    
    public void addTooltip(ItemStack itemStack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.translatable("item.entstupidstuff.double_hand.tooltip"));
    }


    //Code Clean up V2:

    public static final ResourceLocation BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static boolean isOffHandFree(Player player) {
        return !player.getOffhandItem().isEmpty();
    }

    public static void applyMiningFatigue(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 20, 4));
    }

    public float modWeaponDamage(ToolMaterial material, float baseDamage, DamageSource source, LivingEntity attackerE, LivingEntity victiumE){
        float damageMultiplier = 0.25f;
        float finalDamage = (material.attackDamageBonus() + baseDamage) * damageMultiplier;

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



    public static boolean isUsingTwoHands(Player player) {
        return !player.getOffhandItem().isEmpty();
    }

    /*public static void applyMiningFatigue(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 4));
    }*/

    //public static final Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static void applyDamageReduction(Player player, boolean reduceDamage, double toolDamage) {
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        AttributeInstance attackDamageInstance = player.getAttribute(Attributes.ATTACK_DAMAGE);

        // Remove the existing modifier if it exists
        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);
            attackDamageInstance.addTransientModifier(new AttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                toolDamage * (damageMultiplier - 1), 
                AttributeModifier.Operation.ADD_VALUE)
            );
        }
        System.out.println(BASE_ATTACK_DAMAGE_MODIFIER_ID);
    }

    public static void weaponCheck(ItemStack stack, Level world, Entity entity, int slot, boolean selected, ToolMaterial toolMaterial, float attackDamage ) {
        System.out.println("InMethod");
        if (!world.isClientSide() && entity instanceof Player) {
            Player player = (Player) entity;
            boolean isHoldingTwoHandedWeapon = false; //= player.getMainHandStack().getItem() instanceof WeaponItem;

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
    public float applyDamageReduction2(Player player, LivingEntity victim, float toolDamage) {
        float modDamage = toolDamage;

        if(isUsingTwoHands(player) == false) {
            modDamage = modDamage * 0.75f;
        }
        return modDamage;

    }

    /*public static void applyDamageReduction(PlayerEntity player, boolean reduceDamage, double toolDamage) {
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        EntityAttributeInstance attackDamageInstance = player.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

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

