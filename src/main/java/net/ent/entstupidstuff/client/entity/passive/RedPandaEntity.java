package net.ent.entstupidstuff.client.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class RedPandaEntity extends AnimalEntity{

    public RedPandaEntity(EntityType<? extends RedPandaEntity> entityType, World world) {
		super(entityType, world);
	}

    @Override
	public void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
    }

    public static DefaultAttributeContainer.Builder createRedPandaAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
			.add(EntityAttributes.MAX_HEALTH, 10.0)
			.add(EntityAttributes.FOLLOW_RANGE, 32.0)
			.add(EntityAttributes.ATTACK_DAMAGE, 2.0)
			.add(EntityAttributes.SAFE_FALL_DISTANCE, 5.0);
	}



    




















    @Override
    public boolean isBreedingItem(ItemStack stack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBreedingItem'");
    }






















    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
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
