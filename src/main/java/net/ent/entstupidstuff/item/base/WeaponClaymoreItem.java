package net.ent.entstupidstuff.item.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class WeaponClaymoreItem extends WeaponUpdatedItem{

    private static final int BLOCK_DURATION_TICKS = 20;
    private static final int PARRY_COOLDOWN_TICKS = 60;
    private static final float DAMAGE_REDUCTION = 0.5f;
    private static final float PARRY_KNOCKBACK = 1.5f;

    private static final double BASE_ATTACK_DAMAGE = 5.5;;
    private static double ATTACK_DAMAGE;

    public WeaponClaymoreItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(), 
                -2.6f, 
                1.5f, 
                4, 
                0.25f
            )
        ));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		user.setCurrentHand(hand);
		return TypedActionResult.consume(itemStack);
	}

    @Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public int getMaxUseTime(ItemStack stack, LivingEntity user) {
		return 72000;
	}
    
}
