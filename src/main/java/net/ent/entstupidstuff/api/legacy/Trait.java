package net.ent.entstupidstuff.api.legacy;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ToolMaterial;

public interface Trait {


    public default float modWeaponDamage(ToolMaterial material, float baseDamage, DamageSource source, LivingEntity attackerE, LivingEntity victiumE){
        return baseDamage;
    }

    public default void addTooltip(){}
    

}
