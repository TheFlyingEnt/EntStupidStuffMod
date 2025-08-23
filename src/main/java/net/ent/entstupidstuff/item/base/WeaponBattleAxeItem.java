package net.ent.entstupidstuff.item.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PillarBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class WeaponBattleAxeItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 80;
    private static final float ATTACK_RADIUS = 3.5f;
    private static final float KNOCKBACK_STRENGTH = 0.25f;

    private static final double BASE_ATTACK_DAMAGE = 5;;
    private static double ATTACK_DAMAGE;

    public WeaponBattleAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(), 
                -2.5f, 
                1, 
                1, 
                0.25f
            )
        ).component(DataComponentTypes.TOOL, toolMaterial.createComponent(BlockTags.AXE_MINEABLE)));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.isBlocking() && attacker instanceof PlayerEntity player) {
			// simulate shield disable similar to vanilla axe bonus
			target.timeUntilRegen = 0;
			// brief high damage impulse
			target.damage(player.getDamageSources().playerAttack(player), 1.0F);
			// optional: tiny exhaustion to suggest "break"
			target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 0.8f, 1.0f);
		}
		return super.postHit(stack, target, attacker);
	}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClient) {

            if (player.isCreative() == true) {
                player.getItemCooldownManager().set(this, 3);
            } else {
                player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }

            Vec3d playerPos = player.getPos();
            List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, 
                new Box(playerPos.add(-ATTACK_RADIUS, -1, -ATTACK_RADIUS), playerPos.add(ATTACK_RADIUS, 2, ATTACK_RADIUS)), 
                e -> e != player
            );

            for (LivingEntity entity : entities) {
                // Apply Spin Attack Damage - entity.damage(new DamageSources((ServerWorld) world).create(ModDamageTypes.SLASH_DAMAGE), getAttackDamage() * 1.3f);
                entity.damage(player.getDamageSources().playerAttack(player), (float) ATTACK_DAMAGE * 1.25f);
                System.out.print("Spin Attack");

                // Apply Knockback Effect - Might Remove Knockback
                Vec3d knockback = entity.getPos().subtract(playerPos).normalize().multiply(KNOCKBACK_STRENGTH);
                entity.addVelocity(knockback.x, 0.5, knockback.z);
                entity.velocityModified = true;
            }

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);

            ((ServerWorld) world).spawnParticles(
                ParticleTypes.LARGE_SMOKE, //ParticleTypes.SWEEP_ATTACK, 
                player.getX(), player.getY() + 1, player.getZ(), 
                10, 1.5, 0.5, 1.5, 0.1
            );


        }

        return TypedActionResult.success(player.getStackInHand(hand));

    }

    protected static final Map<Block, Block> STRIPPED_BLOCKS = new Builder<Block, Block>()
		.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
		.put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
		.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
		.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
		.put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
		.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
		.put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD)
		.put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG)
		.put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
		.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
		.put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
		.put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
		.put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
		.put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
		.put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
		.put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
		.put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
		.put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
		.put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
		.put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
		.put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
		.build();

    @Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		PlayerEntity playerEntity = context.getPlayer();
		if (shouldCancelStripAttempt(context)) {
			return ActionResult.PASS;
		} else {
			Optional<BlockState> optional = this.tryStrip(world, blockPos, playerEntity, world.getBlockState(blockPos));
			if (optional.isEmpty()) {
				return ActionResult.PASS;
			} else {
				ItemStack itemStack = context.getStack();
				if (playerEntity instanceof ServerPlayerEntity) {
					Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
				}

				world.setBlockState(blockPos, (BlockState)optional.get(), Block.NOTIFY_ALL_AND_REDRAW);
				world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, (BlockState)optional.get()));
				if (playerEntity != null) {
					itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
				}

				return ActionResult.success(world.isClient);
			}
		}
	}

	private static boolean shouldCancelStripAttempt(ItemUsageContext context) {
		PlayerEntity playerEntity = context.getPlayer();
		return context.getHand().equals(Hand.MAIN_HAND) && playerEntity.getOffHandStack().isOf(Items.SHIELD) && !playerEntity.shouldCancelInteraction();
	}

	@SuppressWarnings("rawtypes")
    private Optional<BlockState> tryStrip(World world, BlockPos pos, @Nullable PlayerEntity player, BlockState state) {
		Optional<BlockState> optional = this.getStrippedState(state);
		if (optional.isPresent()) {
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return optional;
		} else {
			Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(state);
			if (optional2.isPresent()) {
				world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);
				return optional2;
			} else {
				Optional<BlockState> optional3 = Optional.ofNullable((Block)((BiMap)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get()).get(state.getBlock()))
					.map(block -> block.getStateWithProperties(state));
				if (optional3.isPresent()) {
					world.playSound(player, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
					world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, pos, 0);
					return optional3;
				} else {
					return Optional.empty();
				}
			}
		}
	}

	private Optional<BlockState> getStrippedState(BlockState state) {
		return Optional.ofNullable((Block)STRIPPED_BLOCKS.get(state.getBlock()))
			.map(block -> block.getDefaultState().with(PillarBlock.AXIS, (Direction.Axis)state.get(PillarBlock.AXIS)));
	}

    @Override
	public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, EquipmentSlot.MAINHAND);
	}
    
}
