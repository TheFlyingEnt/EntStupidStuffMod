package net.ent.entstupidstuff.item.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableMap.Builder;

public class WeaponBattleAxeItem extends WeaponUpdatedItem {
    private static final int COOLDOWN_TICKS = 80;
    private static final float ATTACK_RADIUS = 3.5f;
    private static final float KNOCKBACK_STRENGTH = 0.25f;

    private static final double BASE_ATTACK_DAMAGE = 5;;
    private static double ATTACK_DAMAGE;

    public WeaponBattleAxeItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(), 
                -2.5f, 
                1, 
                1, 
                0.25f
            )
        ).component(DataComponents.TOOL, new Tool(
					List.of(
						Tool.Rule.minesAndDrops(BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK).getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), -3.4f )
					),
					1.0F,
					1,
					true
				)));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

	/*@Override
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
	}*/

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {

        if (!world.isClientSide()) {

            if (player.isCreative() == true) {
                player.getCooldowns().addCooldown(player.getMainHandItem(), 3);
            } else {
                player.getCooldowns().addCooldown(player.getMainHandItem(), COOLDOWN_TICKS);
            }

            Vec3 playerPos = player.position();
            List<LivingEntity> entities = world.getEntitiesOfClass(
                LivingEntity.class, 
                new AABB(playerPos.add(-ATTACK_RADIUS, -1, -ATTACK_RADIUS), playerPos.add(ATTACK_RADIUS, 2, ATTACK_RADIUS)), 
                e -> e != player
            );

            for (LivingEntity entity : entities) {
                // Apply Spin Attack Damage - entity.damage(new DamageSources((ServerWorld) world).create(ModDamageTypes.SLASH_DAMAGE), attackDamageBonus() * 1.3f);
                entity.hurtServer((ServerLevel)world, player.damageSources().playerAttack(player), (float) ATTACK_DAMAGE * 1.25f);
                System.out.print("Spin Attack");

                // Apply Knockback Effect - Might Remove Knockback
                Vec3 knockback = entity.position().subtract(playerPos).normalize().scale(KNOCKBACK_STRENGTH);
                entity.push(knockback.x, 0.5, knockback.z);
                entity.hurtMarked = true;
            }

            world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0f, 1.0f);

            ((ServerLevel) world).sendParticles(
                ParticleTypes.LARGE_SMOKE, //ParticleTypes.SWEEP_ATTACK, 
                player.getX(), player.getY() + 1, player.getZ(), 
                10, 1.5, 0.5, 1.5, 0.1
            );


        }

        return InteractionResult.SUCCESS;

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
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos blockPos = context.getClickedPos();
		Player playerEntity = context.getPlayer();
		if (shouldCancelStripAttempt(context)) {
			return InteractionResult.PASS;
		} else {
			Optional<BlockState> optional = this.tryStrip(world, blockPos, playerEntity, world.getBlockState(blockPos));
			if (optional.isEmpty()) {
				return InteractionResult.PASS;
			} else {
				ItemStack itemStack = context.getItemInHand();
				if (playerEntity instanceof ServerPlayer) {
					CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)playerEntity, blockPos, itemStack);
				}

				world.setBlock(blockPos, (BlockState)optional.get(), Block.UPDATE_ALL_IMMEDIATE);
				world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(playerEntity, (BlockState)optional.get()));
				if (playerEntity != null) {
					itemStack.hurtAndBreak(1, playerEntity, context.getHand().asEquipmentSlot());
				}

				return InteractionResult.SUCCESS;
			}
		}
	}

	private static boolean shouldCancelStripAttempt(UseOnContext context) {
		Player playerEntity = context.getPlayer();
		return context.getHand().equals(InteractionHand.MAIN_HAND) && playerEntity.getOffhandItem().is(Items.SHIELD) && !playerEntity.isSecondaryUseActive();
	}

	@SuppressWarnings("rawtypes")
    private Optional<BlockState> tryStrip(Level world, BlockPos pos, @Nullable Player player, BlockState state) {
		Optional<BlockState> optional = this.getStrippedState(state);
		if (optional.isPresent()) {
			world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			return optional;
		} else {
			Optional<BlockState> optional2 = WeatheringCopper.getPrevious(state);
			if (optional2.isPresent()) {
				world.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
				world.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, pos, 0);
				return optional2;
			} else {
				Optional<BlockState> optional3 = Optional.ofNullable((Block)((BiMap)HoneycombItem.WAX_OFF_BY_BLOCK.get()).get(state.getBlock()))
					.map(block -> block.withPropertiesOf(state));
				if (optional3.isPresent()) {
					world.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
					world.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, pos, 0);
					return optional3;
				} else {
					return Optional.empty();
				}
			}
		}
	}

	private Optional<BlockState> getStrippedState(BlockState state) {
		return Optional.ofNullable((Block)STRIPPED_BLOCKS.get(state.getBlock()))
			.map(block -> block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, (Direction.Axis)state.getValue(RotatedPillarBlock.AXIS)));
	}

    @Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}
    
}
