package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.util.OminousBannerHelper;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ArmoredPillagerEntity extends Pillager{

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ArmoredPillagerEntity.class, EntityDataSerializers.INT);

    private Variant variant;

    public enum Variant {
        DIAMOND(0, "diamond"),
        GOLD(1, "gold");

        private static final Variant[] VALUES = values();
		private final int id;
		private final String name;

        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(
            Variant::byId,
            Variant::getId
        );

        Variant(int id, String name) {
			this.id = id;
			this.name = name;
		}

        public int getId() {
			return id;
		}
	
		public String getName() {
			return name;
		}
	
		public static Variant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

        public static Variant getRandom(RandomSource random) {
			//return VALUES[random.nextInt(VALUES.length)];
			RandomSource varientR = RandomSource.create();
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return Variant.DIAMOND;
        	} else {
            	return Variant.GOLD;
            }

		}

    }

    @Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(VARIANT, 0);
    }

    @Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {

		Variant randomVariant = Variant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}


    //Start of Code

    public ArmoredPillagerEntity(EntityType<? extends Pillager> entityType, Level world) {
        super(entityType, world);
        //variant = Variant.DIAMOND; //Implement Varient Code - Please Test
        /*Random varientR = Random.create();
        float varientRC = varientR.nextFloat();
        
        if (varientRC <= 0.3f) {
            variant = Variant.DIAMOND;
        } else {
            variant = Variant.GOLD;
        }*/

        //this.applyArmorStats();
        //this.equipDefaultEquipment();
    }



    private void applyArmorStats() {
        if (this.variant == Variant.DIAMOND) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(20.0); // Diamond armor value
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(2.0);
        } else if (this.variant == Variant.GOLD) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(11.0); // Gold armor value
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(0.0);
        }
    }

    public static AttributeSupplier.Builder createArmoredPillagerAttributes() {
        return Pillager.createAttributes()
        .add(Attributes.MOVEMENT_SPEED, 0.35F)
		.add(Attributes.MAX_HEALTH, 24.0)
		.add(Attributes.ATTACK_DAMAGE, 5.0)
		.add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" }) //TODO: Check on This
    @Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(2, new Raider.HoldGroundAttackGoal(this, 10.0F));
		this.goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0, 8.0F));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
	}
    
    /* Pillager Code */

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        super.populateDefaultEquipmentSlots(random, localDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        applyArmorStats();

        /*RandomSource varientR = RandomSource.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
        	HolderGetter<BannerPattern> holderGetter = this.level().registryAccess().lookupOrThrow(Registries.BANNER_PATTERN);
            this.setItemSlot(EquipmentSlot.OFFHAND, OminousBannerHelper.getOminousHorizontalBannerInstance(holderGetter));
        }*/

    }

    //Varientation Code:
    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setVariant((ArmoredPillagerEntity.Variant)view.read("Variant", ArmoredPillagerEntity.Variant.INDEX_CODEC).orElse(ArmoredPillagerEntity.Variant.GOLD));
    }

	public void setVariant(ArmoredPillagerEntity.Variant variant) {
		this.variant = variant; // Ensure the field is updated
		this.entityData.set(VARIANT, variant.getId());
	}

	public Variant getVariant() {
		return Variant.byId(this.entityData.get(VARIANT)); // Ensure it retrieves from dataTracker
	}


    
}


