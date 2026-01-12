package net.ent.entstupidstuff.client.entity.passive;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RedPandaEntity extends Animal{

    public RedPandaEntity(EntityType<? extends RedPandaEntity> entityType, Level world) {
		super(entityType, world);
	}

    @Override
	public void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
    }

    public static AttributeSupplier.Builder createRedPandaAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.3F)
			.add(Attributes.MAX_HEALTH, 10.0)
			.add(Attributes.FOLLOW_RANGE, 32.0)
			.add(Attributes.ATTACK_DAMAGE, 2.0)
			.add(Attributes.SAFE_FALL_DISTANCE, 5.0);
	}



    




















    @Override
    public boolean isFood(ItemStack stack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBreedingItem'");
    }






















    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChild'");
    }

    /*@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		RedPandaEntity pandaEntity = EntityType.PANDA.create(world); //Fix
		if (pandaEntity != null) {
			if (entity instanceof RedPandaEntity pandaEntity2) {
				pandaEntity.initGenes(this, pandaEntity2);
			}

			pandaEntity.resetAttributes();
		}

		return pandaEntity;
	}*/
    
}
