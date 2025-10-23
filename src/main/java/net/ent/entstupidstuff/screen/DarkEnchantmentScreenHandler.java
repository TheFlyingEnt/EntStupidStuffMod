package net.ent.entstupidstuff.screen;

import java.util.List;
import java.util.Optional;
import net.minecraft.util.math.random.Random;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.collection.IndexedIterable;
import net.minecraft.util.math.BlockPos;

/*
 * EnchantmentScreen
 * TODO: Implement
 */

public class DarkEnchantmentScreenHandler extends ScreenHandler {
    static final Identifier EMPTY_ECHO_SHARD_SLOT_TEXTURE =
            Identifier.of("entstupidstuff", "item/empty_slot_echo_shard");

    private final Inventory inventory = new SimpleInventory(2) {
        @Override
        public void markDirty() {
            super.markDirty();
            DarkEnchantmentScreenHandler.this.onContentChanged(this);
        }
    };

    private final ScreenHandlerContext context;
    private final Random random = Random.create();
    private final Property seed = Property.create();
    public final int[] enchantmentPower = new int[3];
    public final int[] enchantmentId = new int[]{-1, -1, -1};
    public final int[] enchantmentLevel = new int[]{-1, -1, -1};

    public DarkEnchantmentScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public DarkEnchantmentScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlerFactory.DARK_ENCHANTING_TABLE_HANDLER, syncId);
        this.context = context;

        // Slot 0: Item to enchant
        this.addSlot(new Slot(this.inventory, 0, 15, 47) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        // Slot 1: Echo Shards
        this.addSlot(new Slot(this.inventory, 1, 35, 47) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.ECHO_SHARD);
            }

            @Override
            public Identifier getBackgroundSprite() {
                return EMPTY_ECHO_SHARD_SLOT_TEXTURE;
            }
        });

        // Player Inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        // Sync props
        this.addProperty(Property.create(this.enchantmentPower, 0));
        this.addProperty(Property.create(this.enchantmentPower, 1));
        this.addProperty(Property.create(this.enchantmentPower, 2));
        this.addProperty(this.seed).set(playerInventory.player.getEnchantingTableSeed());
        this.addProperty(Property.create(this.enchantmentId, 0));
        this.addProperty(Property.create(this.enchantmentId, 1));
        this.addProperty(Property.create(this.enchantmentId, 2));
        this.addProperty(Property.create(this.enchantmentLevel, 0));
        this.addProperty(Property.create(this.enchantmentLevel, 1));
        this.addProperty(Property.create(this.enchantmentLevel, 2));
    }

    @Override
	public void onContentChanged(Inventory inventory) {
		if (inventory == this.inventory) {
			ItemStack itemStack = inventory.getStack(0);
			if (!itemStack.isEmpty() && itemStack.isEnchantable()) {
				this.context.run((world, pos) -> {
					IndexedIterable<RegistryEntry<Enchantment>> indexedIterable = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getIndexedEntries();
					int ix = 0;

					for (BlockPos blockPos : EnchantingTableBlock.POWER_PROVIDER_OFFSETS) {
						if (EnchantingTableBlock.canAccessPowerProvider(world, pos, blockPos)) {
							ix++;
						}
					}

					this.random.setSeed((long)this.seed.get());

					for (int j = 0; j < 3; j++) {
						this.enchantmentPower[j] = EnchantmentHelper.calculateRequiredExperienceLevel(this.random, j, ix, itemStack);
						this.enchantmentId[j] = -1;
						this.enchantmentLevel[j] = -1;
						if (this.enchantmentPower[j] < j + 1) {
							this.enchantmentPower[j] = 0;
						}
					}

					for (int jx = 0; jx < 3; jx++) {
						if (this.enchantmentPower[jx] > 0) {
							List<EnchantmentLevelEntry> list = this.generateEnchantments(world.getRegistryManager(), itemStack, jx, this.enchantmentPower[jx]);
							if (list != null && !list.isEmpty()) {
								EnchantmentLevelEntry enchantmentLevelEntry = (EnchantmentLevelEntry)list.get(this.random.nextInt(list.size()));
								this.enchantmentId[jx] = indexedIterable.getRawId(enchantmentLevelEntry.enchantment());
								this.enchantmentLevel[jx] = enchantmentLevelEntry.level();
							}
						}
					}

					this.sendContentUpdates();
				});
			} else {
				for (int i = 0; i < 3; i++) {
					this.enchantmentPower[i] = 0;
					this.enchantmentId[i] = -1;
					this.enchantmentLevel[i] = -1;
				}
			}
		}
	}

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id >= 0 && id < this.enchantmentPower.length) {
            ItemStack itemStack = this.inventory.getStack(0);
            ItemStack shardStack = this.inventory.getStack(1);
            int cost = id + 1; // cost in shards

            if ((shardStack.isEmpty() || shardStack.getCount() < cost) && !player.isInCreativeMode()) {
                return false;
            } else if (this.enchantmentPower[id] <= 0 || itemStack.isEmpty()) {
                return false;
            } else {
                this.context.run((world, pos) -> {
                    List<EnchantmentLevelEntry> list = this.generateEnchantments(world.getRegistryManager(), itemStack, id, this.enchantmentPower[id]);
                    if (!list.isEmpty()) {
                        if (!player.getAbilities().creativeMode) {
                            shardStack.decrement(cost);
                            if (shardStack.isEmpty()) {
                                this.inventory.setStack(1, ItemStack.EMPTY);
                            }
                        }

                        ItemStack enchanted = itemStack;
                        if (itemStack.isOf(Items.BOOK)) {
                            enchanted = itemStack.withItem(Items.ENCHANTED_BOOK);
                            this.inventory.setStack(0, enchanted);
                        }

                        for (EnchantmentLevelEntry entry : list) {
                            enchanted.addEnchantment(entry.enchantment(), entry.level());
                        }

                        player.incrementStat(Stats.ENCHANT_ITEM);
                        if (player instanceof ServerPlayerEntity spe) {
                            Criteria.ENCHANTED_ITEM.trigger(spe, enchanted, cost);
                        }

                        this.inventory.markDirty();
                        this.seed.set(player.getEnchantingTableSeed());
                        this.onContentChanged(this.inventory);
                        world.playSound(null, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F,
                                world.random.nextFloat() * 0.1F + 0.9F);
                    }
                });
                return true;
            }
        }
        return false;
    }

    private List<EnchantmentLevelEntry> generateEnchantments(DynamicRegistryManager registryManager, ItemStack stack, int slot, int level) {
        this.random.setSeed((long) (this.seed.get() + slot));

        // 🔽 Vanilla enchantments only (for now)
        Optional<RegistryEntryList.Named<Enchantment>> optional =
                registryManager.getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(EnchantmentTags.IN_ENCHANTING_TABLE);
        if (optional.isEmpty()) {
            return List.of();
        }

        List<EnchantmentLevelEntry> list =
                EnchantmentHelper.generateEnchantments((net.minecraft.util.math.random.Random) this.random, stack, level, optional.get().stream());

        if (stack.isOf(Items.BOOK) && list.size() > 1) {
            list.remove(this.random.nextInt(list.size()));
        }

        return list;
    }

    public int getEchoShardCount() {
        ItemStack stack = this.inventory.getStack(1);
        return stack.isEmpty() ? 0 : stack.getCount();
    }

    public int getSeed() {
        return this.seed.get();
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inventory));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, BlockFactory.ConceptEnchantment2);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();

            if (slot == 0) {
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 1) {
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.isOf(Items.ECHO_SHARD)) {
                if (!this.insertItem(itemStack2, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.slots.get(0).hasStack() || !this.slots.get(0).canInsert(itemStack2)) {
                    return ItemStack.EMPTY;
                }
                ItemStack single = itemStack2.copyWithCount(1);
                itemStack2.decrement(1);
                this.slots.get(0).setStack(single);
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }
}

