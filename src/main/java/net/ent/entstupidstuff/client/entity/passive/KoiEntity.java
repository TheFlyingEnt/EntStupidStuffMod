package net.ent.entstupidstuff.client.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Bucketable;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class KoiEntity extends /* SchoolingFishEntity */ FishEntity {

    public static final TrackedData<Integer> VARIANT = DataTracker.registerData(KoiEntity.class,
            TrackedDataHandlerRegistry.INTEGER);

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

    public KoiVariant getVariant() {
        return KoiVariantRegistry.getByIndex(this.dataTracker.get(VARIANT));
    }

    public void setVariant(KoiVariant variant) {
        this.dataTracker.set(VARIANT, KoiVariantRegistry.getIndex(variant));
    }

    // ----- Spawning -----

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
            @Nullable EntityData entityData) {
        this.setVariant(KoiVariantRegistry.getRandom(world.getRandom()));

        // KoiVariant randomVariant = KoiVariantRegistry.getRandom(this.random);
        // this.setVariant(randomVariant);
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
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.put(
            "Variant",
            KoiVariantRegistry.INDEX_CODEC,
            this.getVariant()
        );
    }


    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        stack.copy(ModDataComponentTypes.KOI_FISH_VARIANT, this);
    }


    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setVariant(
            view.read("Variant", KoiVariantRegistry.INDEX_CODEC)
                .orElse(KoiVariantRegistry.getByIndex(0))
        );
    }

    @Nullable
	@Override
	public <T> T get(ComponentType<? extends T> type) {
		return type == ModDataComponentTypes.KOI_FISH_VARIANT ? castComponentValue((ComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void copyComponentsFrom(ComponentsAccess from) {
		this.copyComponentFrom(from, ModDataComponentTypes.KOI_FISH_VARIANT);
		super.copyComponentsFrom(from);
	}

	@Override
	protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.KOI_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.KOI_FISH_VARIANT, value));
			return true;
		} else {
			return super.setApplicableComponent(type, value);
		}
    }


}
