package net.ent.entstupidstuff.item.base.combat;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class WeaponClaymoreItem extends WeaponUpdatedItem{

    private static final int BLOCK_DURATION_TICKS = 20;
    private static final int PARRY_COOLDOWN_TICKS = 60;
    private static final float DAMAGE_REDUCTION = 0.5f;
    private static final float PARRY_KNOCKBACK = 1.5f;

    private static final double BASE_ATTACK_DAMAGE = 5.5;;
    private static double ATTACK_DAMAGE;

    public WeaponClaymoreItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(), 
                -2.6f, 
                1.5f, 
                4, 
                0.25f
            )
        ));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    @Override
	public InteractionResult use(Level world, Player user, InteractionHand hand) {
		ItemStack itemStack = user.getItemInHand(hand);
		user.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

    @Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 72000;
	}
    
}
