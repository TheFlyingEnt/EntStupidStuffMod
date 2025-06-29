package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class PiglinWarriorEntity extends PiglinBruteEntity{

    public PiglinWarriorEntity(EntityType<? extends PiglinBruteEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 25;
        applyArmorStats();
    }

    private void applyArmorStats() {
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(7.0);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(2.0);
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(11.0); // Gold armor value
        //this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0.0);
    }

    @Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("golden_hammer")));
	}

}
