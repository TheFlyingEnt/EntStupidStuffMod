package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class BleedingTrait implements ITrait {

    public static final ResourceLocation BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("bleed_damage");

    public static void applyBleedingEffect(Player player, LivingEntity target, float baseDamage, boolean DR) {

        float targetHealth = target.getHealth();
        float targetMaxHealth = target.getMaxHealth();

        float damageMultiplier;
        if (DR) 
        {
            damageMultiplier = targetHealth < 0.25 * targetMaxHealth ? 2.0f : 1.0f;
        } 
        else 
        {
            damageMultiplier = 1.0f;
        }

        AttributeInstance attackDamageInstance = player.getAttribute(Attributes.ATTACK_DAMAGE);

        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);
                attackDamageInstance.addTransientModifier(new AttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                baseDamage * (damageMultiplier), 
                AttributeModifier.Operation.ADD_VALUE)
            );
        }
    }
}

