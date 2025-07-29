package net.ent.entstupidstuff.client.render.entity.model;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.passive.CustomBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.util.Identifier;

public class BoatPartEntity extends Entity {
	public final CustomBoatEntity owner;
	private final String name;
	private final EntityDimensions partDimensions;

	public BoatPartEntity(CustomBoatEntity owner, String name, float width, float height) {
		super(owner.getType(), owner.getWorld());
		this.owner = owner;
		this.name = name;
		this.partDimensions = EntityDimensions.changing(width, height);
		this.calculateDimensions();
		this.setNoGravity(true);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {}

	@Override
	public boolean canHit() {
		return true;
	}

	/*@Override
	public boolean damage(DamageSource source, float amount) {
		return this.isInvulnerableTo(source) ? false : this.owner.damagePart(this, source, amount);
	}*/

	public boolean damagePart(BoatPartEntity part, DamageSource source, float amount) {
		return this.damage(source, amount);
	}

	@Override
	public boolean isPartOf(Entity entity) {
		return this == entity || this.owner == entity;
	}

	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry trackerEntry) {
		throw new UnsupportedOperationException(); // You can implement this with a custom packet if needed
	}

	@Override
	public EntityDimensions getDimensions(EntityPose pose) {
		return partDimensions;
	}

	@Override
	public boolean shouldSave() {
		return false;
	}

	@Nullable
	@Override
	public ItemStack getPickBlockStack() {
		return null;
	}
}