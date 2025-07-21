package net.ent.entstupidstuff.item.base;

import java.util.List;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WeaponHammerItem extends WeaponUpdatedItem{
    private static final int COOLDOWN_TICKS = 60;
    private static final double BASE_ATTACK_DAMAGE = 6.5;
    private static double ATTACK_DAMAGE;

    public WeaponHammerItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(), 
                -3.4f, 
                1, 
                0, 
                1.25f //Handled in Code
            )
        ).component(DataComponentTypes.TOOL, toolMaterial.createComponent(BlockTags.PICKAXE_MINEABLE)));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();

    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(itemStack, context, tooltip, type);
        //tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
        //tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
        //tooltip.add(Text.translatable("item.entstupidstuff.blunt.tooltip").formatted(Formatting.GRAY));
    }



    /*@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getWorld() instanceof ServerWorld serverWorld) {
            // Apply Slash Damage - target.damage(new DamageSources(serverWorld).create(ModDamageTypes.SLASH_DAMAGE), getAttackDamage());
            System.out.print("Main Attack Done!");

            // Apply Knockback
            /*double knockbackStrength = 1.5;
            Vec3d knockback = target.getPos().subtract(attacker.getPos()).normalize().multiply(knockbackStrength);
            target.addVelocity(knockback.x, 0.4, knockback.z);
            target.velocityModified = true; // Ensure movement applies*
        }
        return super.postHit(stack, target, attacker);
    }*/

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient && player != null) {

            // Checking if Player has a Second Item in Off Hand:
            if (!player.getOffHandStack().isEmpty()) {
                System.out.println("Hammer Used, Item in Off Hand");
                return ActionResult.PASS;
            }

            player.getItemCooldownManager().set(this, COOLDOWN_TICKS);

            BlockPos pos = context.getBlockPos();
            Vec3d attackPos = pos.toCenterPos();

            world.playSound(null, pos, SoundFactory.COMBAT_HAMMER_GROUND, SoundCategory.PLAYERS, 1.0f, 1.0f);
             

            float radius = 3.0f;
            List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, 
                new Box(attackPos.add(-radius, -1, -radius), attackPos.add(radius, 2, radius)), 
                e -> e != player
            );

            for (LivingEntity entity : entities) {
                System.out.println("Player isUsingTwoHand = false");

                System.out.print("Weapon Hammer: AoE Attack Done!");
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
                entity.damage(player.getDamageSources().playerAttack(player), (float) ATTACK_DAMAGE * 0.5f);

                Vec3d knockback = entity.getPos().subtract(attackPos).normalize().multiply(0.5);
                entity.addVelocity(knockback.x, 0.3, knockback.z);
                entity.velocityModified = true;

                world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                BlockState blockState = world.getBlockState(context.getBlockPos());

                world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                    attackPos.x, attackPos.y + 3, attackPos.z,
                    0.0, 3.0, 0.0);
                 
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
	public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, EquipmentSlot.MAINHAND);
	}
    
}
