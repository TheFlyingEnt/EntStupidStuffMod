package net.ent.entstupidstuff.client.entity.mob;

import java.util.List;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.combat.WeaponHammerItem;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class PiglinWarriorEntity extends PiglinBrute{

    public PiglinWarriorEntity(EntityType<? extends PiglinBrute> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 25;
        applyArmorStats();
    }

    private void applyArmorStats() {
        this.getAttribute(Attributes.ARMOR).setBaseValue(7.0);
        this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(2.0);
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(11.0); // Gold armor value
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0.0);
    }

    @Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("golden_hammer")));
	}

    public static boolean canSpawn(EntityType<PiglinWarriorEntity> type, LevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
		return !world.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK);
	}

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        boolean success = super.doHurtTarget(world, target);

        if (success && target instanceof LivingEntity livingTarget) {
            ItemStack stack = this.getMainHandItem();

            // Check if holding your hammer
            if (stack.getItem() instanceof WeaponHammerItem hammer) {

                int roll = this.level().getRandom().nextInt(7) + 1;
                if (roll == 7) {
                    Vec3 attackPos = livingTarget.position();

                    
                    // Play hammer sound
                    world.playSound(
                        null,
                        livingTarget.blockPosition(),
                        SoundFactory.COMBAT_HAMMER_GROUND,
                        SoundSource.PLAYERS,
                        1.0f,
                        1.0f
                    );

                    int radius = 3;
                    // Get all nearby living entities except Piglins
                    List<LivingEntity> entities = world.getEntitiesOfClass(
                        LivingEntity.class,
                        new AABB(
                            attackPos.add(-radius, -1, -radius),
                            attackPos.add(radius, 2, radius)
                        ),
                        e -> !(e instanceof Piglin || e instanceof PiglinWarriorEntity) && e.isAlive()
                    );

                    // Damage multiplier based on held items
                    float damageMultiplier =
                        (this.getOffhandItem().isEmpty() || this.getMainHandItem().isEmpty())
                            ? 0.5f
                            : 0.25f;

                    for (LivingEntity targetEntity : entities) {
                        // Status effect
                        targetEntity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 1));

                        // Damage
                        targetEntity.hurtServer(
                            world,
                            this.damageSources().mobAttack(this),
                            (float) hammer.attackDamageBonus() * damageMultiplier
                        );

                        // Knockback
                        Vec3 knockback = targetEntity.position().subtract(attackPos).normalize().scale(0.5);
                        targetEntity.push(knockback.x, 0.3, knockback.z);
                        targetEntity.hurtMarked = true;

                        // Sweep sound
                        world.playSound(
                            null,
                            targetEntity.blockPosition(),
                            SoundEvents.PLAYER_ATTACK_SWEEP,
                            SoundSource.PLAYERS,
                            1.0f,
                            1.0f
                        );
                    }

                    // Spawn hammer particles
                    if (world instanceof ServerLevel serverWorld) {
                        serverWorld.sendParticles(
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
