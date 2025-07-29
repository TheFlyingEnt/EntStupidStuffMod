package net.ent.entstupidstuff.client.render.entity.model;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.passive.CustomBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.EntityTrackerEntry;

public class CustomBoatPart extends Entity {
   public final CustomBoatEntity owner;
   public final String name;
   private final EntityDimensions partDimensions;

   public CustomBoatPart(CustomBoatEntity owner, String name, float width, float height) {
      super(owner.getType(), owner.getWorld());
      this.partDimensions = EntityDimensions.changing(width, height);
      this.calculateDimensions();
      this.owner = owner;
      this.name = name;
   }

   protected void initDataTracker(DataTracker.Builder builder) {
   }

   protected void readCustomDataFromNbt(NbtCompound nbt) {
   }

   protected void writeCustomDataToNbt(NbtCompound nbt) {
   }

   public boolean canHit() {
      return true;
   }

   @Nullable
   public ItemStack getPickBlockStack() {
      return this.owner.getPickBlockStack();
   }

   public boolean damage(DamageSource source, float amount) {
      return this.isInvulnerableTo(source) ? false : this.owner.damagePart(this, source, amount);
   }

   public boolean isPartOf(Entity entity) {
      return this == entity || this.owner == entity;
   }

   public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
      throw new UnsupportedOperationException();
   }

   public EntityDimensions getDimensions(EntityPose pose) {
      return this.partDimensions;
   }

   public boolean shouldSave() {
      return false;
   }
}
