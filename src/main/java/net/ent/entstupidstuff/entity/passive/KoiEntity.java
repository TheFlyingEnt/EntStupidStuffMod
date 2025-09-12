package net.ent.entstupidstuff.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.render.entity.KoiVariant;
import net.ent.entstupidstuff.client.render.entity.KoiVariantRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class KoiEntity extends SchoolingFishEntity{

    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(KoiEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public KoiEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    public void setVariantId(int id) {
        this.dataTracker.set(VARIANT, id);
    }

    public int getVariantId() {
        return this.dataTracker.get(VARIANT);
    }

    public KoiVariant getVariantObject() {
        return KoiVariantRegistry.getById(getVariantId());
    }

    @Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        // Pick a random variant
        KoiVariant randomVariant = KoiVariantRegistry.getRandom(this.random);
        this.setVariantId(KoiVariantRegistry.getId(randomVariant));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public KoiVariant getVariant() {
        return KoiVariantRegistry.getById(this.dataTracker.get(VARIANT));
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        NbtCompound nbt = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT).copyNbt();
        nbt.putInt("BucketVariantTag", KoiVariantRegistry.getId(this.getVariantObject()));
        stack.set(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.of(nbt));
    }
    
}
