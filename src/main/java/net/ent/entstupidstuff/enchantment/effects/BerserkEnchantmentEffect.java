package net.ent.entstupidstuff.enchantment.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public record BerserkEnchantmentEffect (EnchantmentLevelBasedValue value) implements EnchantmentEntityEffect {

    public static final MapCodec<BerserkEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("value").forGetter(BerserkEnchantmentEffect::value)//,
			//DamageType.ENTRY_CODEC.fieldOf("damage_type").forGetter(BerserkEnchantmentEffect::damageType)  
        ).apply(instance, BerserkEnchantmentEffect::new)
	);

    private static final Identifier BERSERK_MODIFIER_ID = Identifier.of("entstupidstuff", "berserk_modifier");

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {


        //if (user instanceof LivingEntity owner) {
        if (context.owner() != null) {

            float playerHealth = context.owner().getHealth();
            float playerMaxHealth = context.owner().getMaxHealth();
            float healthRatio = playerHealth / playerMaxHealth;
            float damageMultiplier = 1.0f + (1.0f - healthRatio) * level;

            // Check if the item in the main hand has the Berserk enchantment
            ItemStack mainHandItem = context.owner().getMainHandStack();
            if (mainHandItem.hasEnchantments()) {
                // Remove existing Berserk modifier if present
                EntityAttributeModifier existingModifier = context.owner().getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getModifier(BERSERK_MODIFIER_ID);
                if (existingModifier != null) {
                    context.owner().getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).removeModifier(existingModifier);
                }

                // Apply the new Berserk damage multiplier
                EntityAttributeModifier berserkModifier = new EntityAttributeModifier(BERSERK_MODIFIER_ID, damageMultiplier - 1.0f, EntityAttributeModifier.Operation.ADD_VALUE);
                context.owner().getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addTemporaryModifier(berserkModifier);
            } else {
                // Remove the Berserk modifier if the item is no longer enchanted
                EntityAttributeModifier existingModifier = context.owner().getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getModifier(BERSERK_MODIFIER_ID);
                if (existingModifier != null) {
                    context.owner().getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).removeModifier(existingModifier);
                }
            }
        }


        // Math Time!!! 10 Max Hears

        //Level I
        //Max of 25% Damage Increase

        // If Health is %25 - 10%
        // If Health is %50 - 15%
        // If Health is %75 - 25%

        //Max of 50% Damage Increase

        // If Health is %25 - 15%
        // If Health is %50 - 25%
        // If Health is %75 - 40%

        //Max of 75% Damage Increase

        // If Health is %25 - 25%
        // If Health is %50 - 35%%
        // If Health is %75 - 50%









        /*if (context.owner() != null){
            //float healthRegained = this.amount.getValue(level) * victim.getMaxHealth();
            //context.owner().heal(healthRegained);

            //Testing:
            float weaponD = context.owner().getActiveItem(); //Check how to get dmage
            float playerH = context.owner().getHealth();
            float playerMH = context.owner().getMaxHealth();

            float playerLH = (playerMH - playerH);

            float fiftyp = (context.owner().getMaxHealth() / 2);
            float twithp = (context.owner().getMaxHealth() / 4);

            


        }*/
        
    }


    @Override
    public MapCodec<? extends BerserkEnchantmentEffect> getCodec() {
        return CODEC;
    }

    /*@Override
    public float apply(int level, Random random, float inputValue) {
        
        return 1000;
    }*/
}
