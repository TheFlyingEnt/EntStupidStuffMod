package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class RedStoneGolemPartEntity extends Entity {
    
    public final RedStoneGolemEntity parentMob;
    private final EntityDimensions size;
    
    public RedStoneGolemPartEntity(RedStoneGolemEntity parent, float width, float height) {
        super(parent.getType(), parent.level());
        this.parentMob = parent;
        this.size = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
        this.noPhysics = true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }


    @Override
    public boolean is(Entity entity) {
        return this == entity || this.parentMob == entity;
    }



    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    protected void defineSynchedData(Builder builder) {
        // Part entities don't need synced data
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
		if (source.is(DamageTypeTags.IS_PROJECTILE)) {
			return false;
		}
		else {
			return true;
		}
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        // Part entities don't save data
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        // Part entities don't save data
    }

    @Nullable
	@Override
	public ItemStack getPickResult() {
		return this.parentMob.getPickResult();
	}

    @Override
    public boolean isAttackable() {
        return true;
    }


    @Override
    public void tick() {
        super.tick();
        // Debug: Print position every 20 ticks
        if (this.tickCount % 20 == 0 && !this.level().isClientSide()) {
            System.out.println("Back part at: " + this.getX() + ", " + this.getY() + ", " + this.getZ());
        }
    }
}
