package net.ent.entstupidstuff.client.entity.mob;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SilkmothEntity extends Animal implements FlyingAnimal{

    public SilkmothEntity(EntityType<? extends SilkmothEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 8.0)
        .add(Attributes.FLYING_SPEED, 0.6)
        .add(Attributes.MOVEMENT_SPEED, 0.2)
        .add(Attributes.TEMPT_RANGE, 10.0);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, (itemStack) -> {
            return itemStack.is(ItemFactory.callItem("blue_mushroom"));
        }, false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new SilkmothEntity.SilkmothEntityWanderGoal());
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        return nav;
    }

    

    public static boolean isValidNaturalSpawn(EntityType<? extends SilkmothEntity> type, LevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        //return true;
        boolean lightCheck = world.getRawBrightness(pos, 0) < 8; //> 8;
        return lightCheck; //&& world.getBlockState(pos.below()).is(ModTags.SILKMOTH_SPAWNABLE_ON);
    }

    @Override
	protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
	}

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.onGround() && this.getDeltaMovement().y < 0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ItemFactory.callItem("blue_mushroom"));
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityFactory.SILKMOTH.create(serverLevel, EntitySpawnReason.BREEDING);

    }
    

    // ## Silkmoth Goals:

    class SilkmothEntityWanderGoal extends Goal {
		SilkmothEntityWanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
        public boolean canUse() {
            return !SilkmothEntity.this.isPassenger()
                && SilkmothEntity.this.random.nextInt(7) == 0;
        }

		@Override
		public boolean canContinueToUse() {
			return SilkmothEntity.this.navigation.isInProgress();
		}

		@Override
		public void start() {
			Vec3 vec3 = this.findPos();
			if (vec3 != null) {
				SilkmothEntity.this.navigation.moveTo(SilkmothEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0);
			}
		}

		@Nullable
		private Vec3 findPos() {
			Vec3 vec32;
			vec32 = SilkmothEntity.this.getViewVector(0.0F);

			int i = 8;
			Vec3 vec33 = HoverRandomPos.getPos(SilkmothEntity.this, 8, 7, vec32.x, vec32.z, (float) (Math.PI / 2), 3, 1);
			return vec33 != null ? vec33 : AirAndWaterRandomPos.getPos(SilkmothEntity.this, 8, 4, -2, vec32.x, vec32.z, (float) (Math.PI / 2));
		}
	}
    
}
