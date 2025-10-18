package net.ent.entstupidstuff.item.base;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.ent.entstupidstuff.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
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

    public double getAttackDamage() {
        return ATTACK_DAMAGE;
    }

    @Override
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
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockPos pos = context.getBlockPos();
        final int radius = 3;

        if (!world.isClient && player != null) {

            //Adding Cooldown & Durability Damage
            player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            stack.damage(2, player, EquipmentSlot.MAINHAND);

            //Getting Attack Pos
            Vec3d attackPos = pos.toCenterPos();

            //Playing Sound
            world.playSound(null, pos, SoundFactory.COMBAT_HAMMER_GROUND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            
            //AOE Attack
            List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, 
                new Box(attackPos.add(-radius, -1, -radius), attackPos.add(radius, 2, radius)), 
                e -> e != player
            );

            float DamangeMutiplyer = 0.25f;

            if (player.getOffHandStack().isEmpty() || player.getMainHandStack().isEmpty()) {
                DamangeMutiplyer = 0.5f;
            }

            for (LivingEntity targetEntity : entities) {

                targetEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
                targetEntity.damage(player.getDamageSources().playerAttack(player), (float) ATTACK_DAMAGE * 0.5f);

                Vec3d knockback = targetEntity.getPos().subtract(attackPos).normalize().multiply(0.5);
                targetEntity.addVelocity(knockback.x, 0.3, knockback.z);
                targetEntity.velocityModified = true;

                world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 6.0f, 0.1f);
                 
            }
        }

        //Effects
        BlockState blockState = world.getBlockState(context.getBlockPos());
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
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

                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                            new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                            px, py, pz,
                            3, 0.25, 0.25, 0.25, 0.05 // count & spread for variation
                        );
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }
    

    @Override
	public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, EquipmentSlot.MAINHAND);
	}
    
}
