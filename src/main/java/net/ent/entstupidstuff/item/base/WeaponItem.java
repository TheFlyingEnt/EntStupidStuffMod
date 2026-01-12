package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;


/*
* This is based on Mojangs SwordItem Class but has been give much more Freedom and Customizablity
*/
public class WeaponItem extends Item{


	ToolMaterial arcToolMat;
	//private List<ITrait> traits;

	public WeaponItem(ToolMaterial toolMaterial, Item.Properties settings/*, ITrait... traits */) {
		//super(settings.component(DataComponentTypes.TOOL, createToolComponent()));
		super(settings);
		//this.traits = Arrays.asList(traits);
		
		arcToolMat = toolMaterial;
	}

	/*private static ToolComponent createToolComponent() {
		return new ToolComponent(
			List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2
		);
	}*/
	
	public static final ResourceLocation BASE_ATTACK_RANGE_MODIFIER_ID = EntStupidStuff.id("base_attack_reach");
    public static final ResourceLocation BASE_ATTACK_SWEEP_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");
	public static final ResourceLocation BASE_ATTACK_KNOCKBACK_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");

	/*public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, double attackDamage, double attackSpeed, float toolReach, int attackSweep, int attackKnockback) {
		return AttributeModifiersComponent.builder()
			.add(
				EntityAttributes.ATTACK_DAMAGE,
				new EntityAttributeModifier(
					BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)attackDamage), EntityAttributeModifier.Operation.ADD_VALUE
				),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.GENERIC_ATTACK_SPEED,
				new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
            .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                new EntityAttributeModifier(BASE_ATTACK_RANGE_MODIFIER_ID, toolReach, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
			.add(EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO,
                new EntityAttributeModifier(BASE_ATTACK_SWEEP_MODIFIER_ID, 1, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
			.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 
				new EntityAttributeModifier(BASE_ATTACK_KNOCKBACK_MODIFIER_ID, attackKnockback, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			) 
			.build();
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return !miner.isCreative();
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	@Override
	public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, EquipmentSlot.MAINHAND);
	}

	public ToolMaterial getMaterial() {
		return arcToolMat;
	}

	/*public List<ITrait> getTraits() {
        return traits;
    }*/



}
