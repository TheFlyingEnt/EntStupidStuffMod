package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
//import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/*
* This is based on Mojangs SwordItem Class but has been give much more Freedom and Customizablity
*/
public class WeaponItem extends ToolItem{


	ToolMaterial arcToolMat;
	//private List<ITrait> traits;

	public WeaponItem(ToolMaterial toolMaterial, Item.Settings settings/*, ITrait... traits */) {
		super(toolMaterial, settings.component(DataComponentTypes.TOOL, createToolComponent()));
		//this.traits = Arrays.asList(traits);
		
		arcToolMat = toolMaterial;
	}

	private static ToolComponent createToolComponent() {
		return new ToolComponent(
			List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2
		);
	}
	
	public static final Identifier BASE_ATTACK_RANGE_MODIFIER_ID = EntStupidStuff.id("base_attack_reach");
    public static final Identifier BASE_ATTACK_SWEEP_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");
	public static final Identifier BASE_ATTACK_KNOCKBACK_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");

	public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, double attackDamage, double attackSpeed, float toolReach, int attackSweep, int attackKnockback) {
		return AttributeModifiersComponent.builder()
			.add(
				EntityAttributes.GENERIC_ATTACK_DAMAGE,
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
