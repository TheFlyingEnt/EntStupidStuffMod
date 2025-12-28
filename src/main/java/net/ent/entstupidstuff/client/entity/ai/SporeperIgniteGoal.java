package net.ent.entstupidstuff.client.entity.ai;

import java.util.EnumSet;

import net.ent.entstupidstuff.client.entity.mob.SporeperEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

public class SporeperIgniteGoal extends Goal {
	private final SporeperEntity creeper;
	@Nullable
	private LivingEntity target;

	public SporeperIgniteGoal(SporeperEntity creeper) {
		this.creeper = creeper;
		this.setControls(EnumSet.of(Goal.Control.MOVE));
	}

	@Override
	public boolean canStart() {
		LivingEntity livingEntity = this.creeper.getTarget();
		return this.creeper.getFuseSpeed() > 0 || livingEntity != null && this.creeper.squaredDistanceTo(livingEntity) < 9.0;
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
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		if (this.target == null) {
			this.creeper.setFuseSpeed(-1);
		} else if (this.creeper.squaredDistanceTo(this.target) > 49.0) {
			this.creeper.setFuseSpeed(-1);
		} else if (!this.creeper.getVisibilityCache().canSee(this.target)) {
			this.creeper.setFuseSpeed(-1);
		} else {
			this.creeper.setFuseSpeed(1);
		}
	}
}

