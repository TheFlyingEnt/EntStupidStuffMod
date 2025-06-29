package net.ent.entstupidstuff.api.legacy;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ToolMaterial;

public interface Trait {


    public default float modWeaponDamage(ToolMaterial material, float baseDamage, DamageSource source, LivingEntity attackerE, LivingEntity victiumE){
        return baseDamage;
    }

    public default void addTooltip(){}
    

}
