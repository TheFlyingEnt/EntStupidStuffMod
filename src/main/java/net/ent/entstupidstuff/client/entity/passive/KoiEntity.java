package net.ent.entstupidstuff.client.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class KoiEntity extends /* SchoolingFishEntity */ AbstractFish {

    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(KoiEntity.class,
            EntityDataSerializers.INT);

    public KoiEntity(EntityType<? extends AbstractFish> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getBucketItemStack() {
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
        return KoiVariantRegistry.getByIndex(this.entityData.get(VARIANT));
    }

    public void setVariant(KoiVariant variant) {
        this.entityData.set(VARIANT, KoiVariantRegistry.getIndex(variant));
    }

    // ----- Spawning -----

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason,
            @Nullable SpawnGroupData entityData) {
        this.setVariant(KoiVariantRegistry.getRandom(world.getRandom()));

        // KoiVariant randomVariant = KoiVariantRegistry.getRandom(this.random);
        // this.setVariant(randomVariant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    // ----- Data Tracking -----

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    // ----- NBT Sync -----

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.store(
            "Variant",
            KoiVariantRegistry.INDEX_CODEC,
            this.getVariant()
        );
    }


    @Override
    public void saveToBucketTag(ItemStack stack) {
        super.saveToBucketTag(stack);
        stack.copyFrom(ModDataComponentTypes.KOI_FISH_VARIANT, this);
    }


    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setVariant(
            view.read("Variant", KoiVariantRegistry.INDEX_CODEC)
                .orElse(KoiVariantRegistry.getByIndex(0))
        );
    }

    @Nullable
	@Override
	public <T> T get(DataComponentType<? extends T> type) {
		return type == ModDataComponentTypes.KOI_FISH_VARIANT ? castComponentValue((DataComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter from) {
		this.applyImplicitComponentIfPresent(from, ModDataComponentTypes.KOI_FISH_VARIANT);
		super.applyImplicitComponents(from);
	}

	@Override
	protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.KOI_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.KOI_FISH_VARIANT, value));
			return true;
		} else {
			return super.applyImplicitComponent(type, value);
		}
    }


}
