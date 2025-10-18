package net.ent.entstupidstuff.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class KoiEntity extends /*SchoolingFishEntity*/ FishEntity{

    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(KoiEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public KoiEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ItemFactory.KOI_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundFactory.ENTITY_KOI_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundFactory.ENTITY_KOI_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundFactory.ENTITY_KOI_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundFactory.ENTITY_KOI_FLOP;
    }

    // ----- Variant Management -----

    public void setVariant(KoiVariant variant) {
        this.dataTracker.set(VARIANT, KoiVariantRegistry.getId(variant));
    }

    public KoiVariant getVariant() {
        return KoiVariantRegistry.getById(this.dataTracker.get(VARIANT));
    }

    // ----- Spawning -----

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        KoiVariant randomVariant = KoiVariantRegistry.getRandom(this.random);
        this.setVariant(randomVariant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    // ----- Data Tracking -----

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    // ----- NBT Sync -----

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", KoiVariantRegistry.getId(this.getVariant()));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(KoiVariantRegistry.getById(nbt.getInt("Variant")));
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        super.copyDataFromNbt(nbt);
        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            this.setVariant(KoiVariantRegistry.getById(nbt.getInt("BucketVariantTag")));
        }
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {
            nbtCompound.putInt("BucketVariantTag", KoiVariantRegistry.getId(this.getVariant()));
        });
    }


    
}
