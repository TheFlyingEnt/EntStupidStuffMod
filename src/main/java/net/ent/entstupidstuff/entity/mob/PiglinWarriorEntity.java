package net.ent.entstupidstuff.entity.mob;

import java.util.List;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.WeaponHammerItem;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PiglinWarriorEntity extends PiglinBruteEntity{

    public PiglinWarriorEntity(EntityType<? extends PiglinBruteEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 25;
        applyArmorStats();
    }

    private void applyArmorStats() {
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(7.0);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(2.0);
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(11.0); // Gold armor value
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0.0);
    }

    @Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("golden_hammer")));
	}

    public static boolean canSpawn(EntityType<PiglinWarriorEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
	}

    @Override
    public boolean tryAttack(Entity target) {
        boolean success = super.tryAttack(target);

        if (success && target instanceof LivingEntity livingTarget) {
            ItemStack stack = this.getMainHandStack();

            // Check if holding your hammer
            if (stack.getItem() instanceof WeaponHammerItem hammer) {

                int roll = this.getWorld().getRandom().nextInt(7) + 1;
                if (roll == 7) {
                    World world = this.getWorld();
                    Vec3d attackPos = livingTarget.getPos();

                    
                    // Play hammer sound
                    world.playSound(
                        null,
                        livingTarget.getBlockPos(),
                        SoundFactory.COMBAT_HAMMER_GROUND,
                        SoundCategory.PLAYERS,
                        1.0f,
                        1.0f
                    );

                    int radius = 3;
                    // Get all nearby living entities except Piglins
                    List<LivingEntity> entities = world.getEntitiesByClass(
                        LivingEntity.class,
                        new Box(
                            attackPos.add(-radius, -1, -radius),
                            attackPos.add(radius, 2, radius)
                        ),
                        e -> !(e instanceof PiglinEntity || e instanceof PiglinWarriorEntity) && e.isAlive()
                    );

                    // Damage multiplier based on held items
                    float damageMultiplier =
                        (this.getOffHandStack().isEmpty() || this.getMainHandStack().isEmpty())
                            ? 0.5f
                            : 0.25f;

                    for (LivingEntity targetEntity : entities) {
                        // Status effect
                        targetEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));

                        // Damage
                        targetEntity.damage(
                            this.getDamageSources().mobAttack(this),
                            (float) hammer.getAttackDamage() * damageMultiplier
                        );

                        // Knockback
                        Vec3d knockback = targetEntity.getPos().subtract(attackPos).normalize().multiply(0.5);
                        targetEntity.addVelocity(knockback.x, 0.3, knockback.z);
                        targetEntity.velocityModified = true;

                        // Sweep sound
                        world.playSound(
                            null,
                            targetEntity.getBlockPos(),
                            SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                            SoundCategory.PLAYERS,
                            1.0f,
                            1.0f
                        );
                    }

                    // Spawn hammer particles
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                            ParticleTypesFactory.HAMMER_BOOM,
                            attackPos.x, attackPos.y + 1, attackPos.z,
                            1, 0.0, 0.0, 0.0, 0.0
                        );
                    }
                    
                }
            }
        }

        return success;
    }


}
