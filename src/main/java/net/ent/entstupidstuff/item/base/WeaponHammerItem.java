package net.ent.entstupidstuff.item.base;

import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class WeaponHammerItem extends Item {
    private static final int COOLDOWN_TICKS = 60;
    private static final double BASE_ATTACK_DAMAGE = 6.5;
    private static double ATTACK_DAMAGE;
    //.component(DataComponents.WEAPON, new Weapon(2, h));

    public WeaponHammerItem(ToolMaterial toolMaterial, Properties settings) {

        //float attackspeed = -3.4f;
        //float shieldcooldown = 2.0F;
        //float toolReach = 1.0F;
        //float attackSweep = 0.0F;
        //float attackKnockback = 1.25F;

        super(ModProperties.hammer(settings, toolMaterial, BlockTags.MINEABLE_WITH_PICKAXE, 6.5F, -3.4f));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();

        

    }

    public double attackDamageBonus() {
        return ATTACK_DAMAGE;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);

        var client = Minecraft.getInstance();
        boolean shiftHeld = InputConstants.isKeyDown(client.getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) 
                        || InputConstants.isKeyDown(client.getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);

        if (type.isAdvanced() && client.player != null) {
            boolean hasTwoHandsFree = client.player.getOffhandItem().isEmpty() || client.player.getMainHandItem().isEmpty();

            // Two-Handed I tooltip
            ChatFormatting twoHandedColor = hasTwoHandsFree ? ChatFormatting.GRAY : ChatFormatting.RED;
            textConsumer.accept(Component.literal("Two-Handed I").withStyle(twoHandedColor));

            if (shiftHeld) {
                textConsumer.accept(Component.literal("- Holding Two Items decreases Damage").withStyle(ChatFormatting.GRAY));
            }
        }

        // Ground Pound tooltip
        textConsumer.accept(Component.literal("Ground Pound").withStyle(ChatFormatting.GRAY));

        if (shiftHeld && client.player != null) {
            boolean hasTwoHandsFree = client.player.getOffhandItem().isEmpty() || client.player.getMainHandItem().isEmpty();
            String percent = hasTwoHandsFree ? "50% " : "25% ";
            ChatFormatting percentColor = hasTwoHandsFree ? ChatFormatting.GRAY : ChatFormatting.RED;

            textConsumer.accept(
                Component.literal("- Right Clicking on a Block causes AoE Damage worth ")
                    .withStyle(ChatFormatting.GRAY)
                    .append(Component.literal(percent).withStyle(percentColor))
                    .append(Component.literal("of Weapon's Damage").withStyle(ChatFormatting.GRAY))
            );
        }

        // Shift Hint
        if (!shiftHeld) {
            textConsumer.accept(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.DARK_GRAY));
            //textConsumer.accept(Component.empty());
        }

        //item.entstupidstuff.double_hand.tooltip



    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        final int radius = 3;

        if (!world.isClientSide() && player != null) {

            //Adding Cooldown & Durability Damage
            if (player.getMainHandItem().getItem() instanceof WeaponHammerItem && !player.isCreative()) {
                player.getCooldowns().addCooldown(player.getMainHandItem(), COOLDOWN_TICKS);
                stack.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);
            }
            else if (player.getOffhandItem().getItem() instanceof WeaponHammerItem && !player.isCreative()) {
                player.getCooldowns().addCooldown(player.getOffhandItem(), COOLDOWN_TICKS);
                stack.hurtAndBreak(2, player, EquipmentSlot.OFFHAND);
            }

            //Getting Attack Pos
            Vec3 attackPos = pos.getCenter();

            //Playing Sound
            world.playSound(null, pos, SoundFactory.COMBAT_HAMMER_GROUND, SoundSource.PLAYERS, 1.0f, 1.0f);
            
            //AOE Attack
            List<LivingEntity> entities = world.getEntitiesOfClass(
                LivingEntity.class, 
                new AABB(attackPos.add(-radius, -1, -radius), attackPos.add(radius, 2, radius)), 
                e -> e != player
            );

            float DamangeMutiplyer = 0.25f;

            if (player.getOffhandItem().isEmpty() || player.getMainHandItem().isEmpty()) {
                DamangeMutiplyer = 0.5f;
            }

            for (LivingEntity targetEntity : entities) {

                targetEntity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 1));
                targetEntity.hurtServer((ServerLevel) world, player.damageSources().playerAttack(player), (float) ATTACK_DAMAGE * DamangeMutiplyer);

                Vec3 knockback = targetEntity.position().subtract(attackPos).normalize().scale(0.5);
                targetEntity.push(knockback.x, 0.3, knockback.z);
                targetEntity.hurtMarked = true;

                world.playSound(null, pos, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 6.0f, 0.1f);
                 
            }

            

        }

        //Effects
        BlockState blockState = world.getBlockState(context.getClickedPos());
        if (world instanceof ServerLevel serverWorld) {
            serverWorld.sendParticles(
                ParticleTypesFactory.HAMMER_BOOM,
                pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                1, 0.0, 0.0, 0.0, 0.0 // count, offsetX, offsetY, offsetZ, speed
            );
        }

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx * dx + dz * dz <= radius * radius) {
                    double px = pos.getX() + 1 + dx;
                    double py = pos.getY() + 1;
                    double pz = pos.getZ() + 1 + dz;

                    if (world instanceof ServerLevel serverWorld) {
                        serverWorld.sendParticles(
                            new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                            px, py, pz,
                            3, 0.25, 0.25, 0.25, 0.05 // count & spread for variation
                        );
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
    

    @Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker instanceof Player) {
            Player player = (Player) attacker;

            if (player.getMainHandItem().getItem() instanceof WeaponHammerItem) {
                stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
            }
            else if (player.getOffhandItem().getItem() instanceof WeaponHammerItem) {
                stack.hurtAndBreak(1, attacker, EquipmentSlot.OFFHAND);
            }
        }

        //stack.hurtAndBreak(1, attacker, stack.getHand().asEquipmentSlot());
		stack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);
	}
    
}
