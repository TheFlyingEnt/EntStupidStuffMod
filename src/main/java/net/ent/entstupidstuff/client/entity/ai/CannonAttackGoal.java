package net.ent.entstupidstuff.client.entity.ai;

import java.util.EnumSet;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;

public class CannonAttackGoal <T extends Monster & RangedAttackMob & CrossbowAttackMob> extends Goal {
    
        public static final UniformInt COOLDOWN_RANGE = TimeUtil.rangeOfSeconds(1, 2);
        private final T actor;
        private CannonAttackGoal.Stage stage = CannonAttackGoal.Stage.UNCHARGED;
        private final double speed;
        private final float squaredRange;
        private int seeingTargetTicker;
        private int chargedTicksLeft;
        private int cooldown;
    
        public CannonAttackGoal(T actor, double speed, float range) {
            this.actor = actor;
            this.speed = speed;
            this.squaredRange = range * range;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
    
        @Override
        public boolean canUse() {
            return this.hasAliveTarget() && this.isEntityHoldingCannon();
        }
    
        private boolean isEntityHoldingCannon() {
            return this.actor.isHolding(ItemFactory.CANNON_ITEM);
        }
    
        @Override
        public boolean canContinueToUse() {
            return this.hasAliveTarget() && (this.canUse() || !this.actor.getNavigation().isDone()) && this.isEntityHoldingCannon();
        }
    
        private boolean hasAliveTarget() {
            return this.actor.getTarget() != null && this.actor.getTarget().isAlive();
        }
    
        @Override
        public void stop() {
            super.stop();
            this.actor.setAggressive(false);
            this.actor.setTarget(null);
            this.seeingTargetTicker = 0;
            if (this.actor.isUsingItem()) {
                this.actor.stopUsingItem();
                this.actor.setChargingCrossbow(false);
                this.actor.getUseItem().set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
            }
        }
    
        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    
        @Override
        public void tick() {
            LivingEntity livingEntity = this.actor.getTarget();
            if (livingEntity != null) {
                boolean bl = this.actor.getSensing().hasLineOfSight(livingEntity);
                boolean bl2 = this.seeingTargetTicker > 0;
                if (bl != bl2) {
                    this.seeingTargetTicker = 0;
                }
    
                if (bl) {
                    this.seeingTargetTicker++;
                } else {
                    this.seeingTargetTicker--;
                }
    
                double d = this.actor.distanceToSqr(livingEntity);
                boolean bl3 = (d > (double)this.squaredRange || this.seeingTargetTicker < 5) && this.chargedTicksLeft == 0;
                if (bl3) {
                    this.cooldown--;
                    if (this.cooldown <= 0) {
                        this.actor.getNavigation().moveTo(livingEntity, this.isUncharged() ? this.speed : this.speed * 0.5);
                        this.cooldown = COOLDOWN_RANGE.sample(this.actor.getRandom());
                    }
                } else {
                    this.cooldown = 0;
                    this.actor.getNavigation().stop();
                }
    
                this.actor.getLookControl().setLookAt(livingEntity, 30.0F, 30.0F);
                if (this.stage == CannonAttackGoal.Stage.UNCHARGED) {
                    if (!bl3) {
                        this.actor.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.actor, ItemFactory.CANNON_ITEM));
                        this.stage = CannonAttackGoal.Stage.CHARGING;
                        this.actor.setChargingCrossbow(true);
                    }
                } else if (this.stage == CannonAttackGoal.Stage.CHARGING) {
                    if (!this.actor.isUsingItem()) {
                        this.stage = CannonAttackGoal.Stage.UNCHARGED;
                    }
    
                    int i = this.actor.getTicksUsingItem();
                    ItemStack itemStack = this.actor.getUseItem();
                    if (i >= CannonItem.getPullTime(itemStack, this.actor)) {
                        this.actor.releaseUsingItem();
                        this.stage = CannonAttackGoal.Stage.CHARGED;
                        this.chargedTicksLeft = 20 + this.actor.getRandom().nextInt(20);
                        this.actor.setChargingCrossbow(false);
                    }
                } else if (this.stage == CannonAttackGoal.Stage.CHARGED) {
                    this.chargedTicksLeft--;
                    if (this.chargedTicksLeft == 0) {
                        this.stage = CannonAttackGoal.Stage.READY_TO_ATTACK;
                    }
                } else if (this.stage == CannonAttackGoal.Stage.READY_TO_ATTACK && bl) {
                    this.actor.performRangedAttack(livingEntity, 1.0F);
                    this.stage = CannonAttackGoal.Stage.UNCHARGED;
                }
            }
        }
    
        private boolean isUncharged() {
            return this.stage == CannonAttackGoal.Stage.UNCHARGED;
        }
    
        static enum Stage {
            UNCHARGED,
            CHARGING,
            CHARGED,
            READY_TO_ATTACK;
        }
    }
    
