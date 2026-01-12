package net.ent.entstupidstuff.client.entity.ai;

import java.util.EnumSet;

import net.ent.entstupidstuff.client.entity.mob.SporeperEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

public class SporeperIgniteGoal extends Goal {
	private final SporeperEntity creeper;
	@Nullable
	private LivingEntity target;

	public SporeperIgniteGoal(SporeperEntity creeper) {
		this.creeper = creeper;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		LivingEntity livingEntity = this.creeper.getTarget();
		return this.creeper.getFuseSpeed() > 0 || livingEntity != null && this.creeper.distanceToSqr(livingEntity) < 9.0;
	}

	@Override
	public void start() {
		this.creeper.getNavigation().stop();
		this.target = this.creeper.getTarget();
	}

	@Override
	public void stop() {
		this.target = null;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		if (this.target == null) {
			this.creeper.setFuseSpeed(-1);
		} else if (this.creeper.distanceToSqr(this.target) > 49.0) {
			this.creeper.setFuseSpeed(-1);
		} else if (!this.creeper.getSensing().hasLineOfSight(this.target)) {
			this.creeper.setFuseSpeed(-1);
		} else {
			this.creeper.setFuseSpeed(1);
		}
	}
}

