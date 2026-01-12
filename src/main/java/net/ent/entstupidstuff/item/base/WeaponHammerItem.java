package net.ent.entstupidstuff.item.base;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import net.ent.entstupidstuff.client.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.fabricmc.fabric.api.item.v1.FabricItem.Settings;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys.Server;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class WeaponHammerItem extends WeaponUpdatedItem{
    private static final int COOLDOWN_TICKS = 60;
    private static final double BASE_ATTACK_DAMAGE = 6.5;
    private static double ATTACK_DAMAGE;

    public WeaponHammerItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(), 
                -3.4f, 
                1, 
                0, 
                1.25f //Handled in Code
            )
        ).component(DataComponents.TOOL, new Tool(
					List.of(
						Tool.Rule.minesAndDrops(BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK).getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), -3.4f )
					),
					1.0F,
					1,
					true
				)));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();

        

    }

    public double attackDamageBonus() {
        return ATTACK_DAMAGE;
    }
    

    /*@Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        super.appendTooltip(itemStack, context, tooltip, type);

        var client = net.minecraft.client.MinecraftClient.getInstance();
        long handle = client.getWindow().getHandle();
        boolean shiftHeld = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_SHIFT) 
                        || InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT_SHIFT);

        if (type.isAdvanced() && client.player != null) {
            boolean hasTwoHandsFree = client.player.getOffHandStack().isEmpty() || client.player.getMainHandStack().isEmpty();

            // Two-Handed I tooltip
            Formatting twoHandedColor = hasTwoHandsFree ? Formatting.GRAY : Formatting.RED;
            tooltip.add(Text.literal("Two-Handed I").formatted(twoHandedColor));

            if (shiftHeld) {
                tooltip.add(Text.literal("- Holding Two Items decreases Damage").formatted(Formatting.GRAY));
            }
        }

        // Ground Pound tooltip
        tooltip.add(Text.literal("Ground Pound").formatted(Formatting.GRAY));

        if (shiftHeld && client.player != null) {
            boolean hasTwoHandsFree = client.player.getOffHandStack().isEmpty() || client.player.getMainHandStack().isEmpty();
            String percent = hasTwoHandsFree ? "50% " : "25% ";
            Formatting percentColor = hasTwoHandsFree ? Formatting.GRAY : Formatting.RED;

            tooltip.add(
                Text.literal("- Right Clicking on a Block causes AoE Damage worth ")
                    .formatted(Formatting.GRAY)
                    .append(Text.literal(percent).formatted(percentColor))
                    .append(Text.literal("of Weapon's Damage").formatted(Formatting.GRAY))
            );
        }

        // Shift Hint
        if (!shiftHeld) {
            tooltip.add(Text.literal("Hold SHIFT for more info").formatted(Formatting.DARK_GRAY));
            tooltip.add(Text.empty());
        }

        //item.entstupidstuff.double_hand.tooltip
    }*/

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        final int radius = 3;

        if (!world.isClientSide() && player != null) {

            //Adding Cooldown & Durability Damage
            player.getCooldowns().addCooldown(player.getMainHandItem(), COOLDOWN_TICKS);
            stack.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);

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
                targetEntity.hurtServer((ServerLevel) world, player.damageSources().playerAttack(player), (float) ATTACK_DAMAGE * 0.5f);

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
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}
    
}
