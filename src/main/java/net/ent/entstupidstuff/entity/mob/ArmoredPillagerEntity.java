package net.ent.entstupidstuff.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class ArmoredPillagerEntity extends PillagerEntity{

    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ArmoredPillagerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private Variant variant;

    public enum Variant {
        DIAMOND(0, "diamond"),
        GOLD(1, "gold");

        private static final Variant[] VALUES = values();
		private final int id;
		private final String name;

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

        public static Variant getRandom(Random random) {
			//return VALUES[random.nextInt(VALUES.length)];
			Random varientR = Random.create();
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return Variant.DIAMOND;
        	} else {
            	return Variant.GOLD;
            }

		}

    }

    @Override
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(VARIANT, 0);
    }

    @Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

		Variant randomVariant = Variant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.initialize(world, difficulty, spawnReason, entityData);
	}

    //Start of Code

    public ArmoredPillagerEntity(EntityType<? extends PillagerEntity> entityType, World world) {
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

    @SuppressWarnings("unused")
    private void equipDefaultEquipment() {
        System.out.println("AP - equipDefaultEquipment");
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

    private void applyArmorStats() {
        if (this.variant == Variant.DIAMOND) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(20.0); // Diamond armor value
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(2.0);
        } else if (this.variant == Variant.GOLD) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(11.0); // Gold armor value
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0.0);
        }
    }

    public static DefaultAttributeContainer.Builder createArmoredPillagerAttributes() {
        return PillagerEntity.createPillagerAttributes()
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35F)
		.add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)
		.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
		.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" }) //TODO: Check on This
    @Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(2, new RaiderEntity.PatrolApproachGoal(this, 10.0F));
		this.goalSelector.add(3, new CrossbowAttackGoal<>(this, 1.0, 8.0F));
		this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
		this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F, 1.0F));
		this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 15.0F));
		this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge());
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, MerchantEntity.class, false));
		this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, true));
	}
    
    /* Pillager Code */

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
        System.out.println("AP - initEquipment");
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));

        applyArmorStats();
    }

    //Varientation Code:
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(Variant.byId(nbt.getInt("Variant")));

    }

	public void setVariant(ArmoredPillagerEntity.Variant variant) {
		this.variant = variant; // Ensure the field is updated
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public Variant getVariant() {
		return Variant.byId(this.dataTracker.get(VARIANT)); // Ensure it retrieves from dataTracker
	}


    
}


