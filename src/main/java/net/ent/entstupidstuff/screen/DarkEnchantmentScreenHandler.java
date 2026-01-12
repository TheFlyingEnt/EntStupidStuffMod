package net.ent.entstupidstuff.screen;

import java.util.List;
import java.util.Optional;
import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.IdMap;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.EnchantingTableBlock;
import com.mojang.datafixers.util.Pair;

/*
 * EnchantmentScreen
 * TODO: Implement
 */

public class DarkEnchantmentScreenHandler extends AbstractContainerMenu {
    static final ResourceLocation EMPTY_ECHO_SHARD_SLOT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "item/empty_slot_echo_shard");

    private final Container inventory = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            DarkEnchantmentScreenHandler.this.slotsChanged(this);
        }
    };

    private final ContainerLevelAccess context;
    private final RandomSource random = RandomSource.create();
    private final DataSlot seed = DataSlot.standalone();
    public final int[] enchantmentPower = new int[3];
    public final int[] enchantmentId = new int[]{-1, -1, -1};
    public final int[] enchantmentLevel = new int[]{-1, -1, -1};

    public DarkEnchantmentScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public DarkEnchantmentScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(ScreenHandlerFactory.DARK_ENCHANTING_TABLE_HANDLER, syncId);
        this.context = context;

        // Slot 0: Item to enchant
        this.addSlot(new Slot(this.inventory, 0, 15, 47) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        // Slot 1: Echo Shards
        this.addSlot(new Slot(this.inventory, 1, 35, 47) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.ECHO_SHARD);
            }

            @Override
            public ResourceLocation getNoItemIcon() {
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
        this.addDataSlot(DataSlot.shared(this.enchantmentPower, 0));
        this.addDataSlot(DataSlot.shared(this.enchantmentPower, 1));
        this.addDataSlot(DataSlot.shared(this.enchantmentPower, 2));
        this.addDataSlot(this.seed).set(playerInventory.player.getEnchantmentSeed());
        this.addDataSlot(DataSlot.shared(this.enchantmentId, 0));
        this.addDataSlot(DataSlot.shared(this.enchantmentId, 1));
        this.addDataSlot(DataSlot.shared(this.enchantmentId, 2));
        this.addDataSlot(DataSlot.shared(this.enchantmentLevel, 0));
        this.addDataSlot(DataSlot.shared(this.enchantmentLevel, 1));
        this.addDataSlot(DataSlot.shared(this.enchantmentLevel, 2));
    }

    @Override
	public void slotsChanged(Container inventory) {
		if (inventory == this.inventory) {
			ItemStack itemStack = inventory.getItem(0);
			if (!itemStack.isEmpty() && itemStack.isEnchantable()) {
				this.context.execute((world, pos) -> {
					IdMap<Holder<Enchantment>> indexedIterable = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).asHolderIdMap();
					int ix = 0;

					for (BlockPos blockPos : EnchantingTableBlock.BOOKSHELF_OFFSETS) {
						if (EnchantingTableBlock.isValidBookShelf(world, pos, blockPos)) {
							ix++;
						}
					}

					this.random.setSeed((long)this.seed.get());

					for (int j = 0; j < 3; j++) {
						this.enchantmentPower[j] = EnchantmentHelper.getEnchantmentCost(this.random, j, ix, itemStack);
						this.enchantmentId[j] = -1;
						this.enchantmentLevel[j] = -1;
						if (this.enchantmentPower[j] < j + 1) {
							this.enchantmentPower[j] = 0;
						}
					}

					for (int jx = 0; jx < 3; jx++) {
						if (this.enchantmentPower[jx] > 0) {
							List<EnchantmentInstance> list = this.generateEnchantments(world.registryAccess(), itemStack, jx, this.enchantmentPower[jx]);
							if (list != null && !list.isEmpty()) {
								EnchantmentInstance enchantmentLevelEntry = (EnchantmentInstance)list.get(this.random.nextInt(list.size()));
								this.enchantmentId[jx] = indexedIterable.getId(enchantmentLevelEntry.enchantment());
								this.enchantmentLevel[jx] = enchantmentLevelEntry.level();
							}
						}
					}

					this.broadcastChanges();
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
    public boolean clickMenuButton(Player player, int id) {
        if (id >= 0 && id < this.enchantmentPower.length) {
            ItemStack itemStack = this.inventory.getItem(0);
            ItemStack shardStack = this.inventory.getItem(1);
            int cost = id + 1; // cost in shards

            if ((shardStack.isEmpty() || shardStack.getCount() < cost) && !player.hasInfiniteMaterials()) {
                return false;
            } else if (this.enchantmentPower[id] <= 0 || itemStack.isEmpty()) {
                return false;
            } else {
                this.context.execute((world, pos) -> {
                    List<EnchantmentInstance> list = this.generateEnchantments(world.registryAccess(), itemStack, id, this.enchantmentPower[id]);
                    if (!list.isEmpty()) {
                        if (!player.getAbilities().instabuild) {
                            shardStack.shrink(cost);
                            if (shardStack.isEmpty()) {
                                this.inventory.setItem(1, ItemStack.EMPTY);
                            }
                        }

                        ItemStack enchanted = itemStack;
                        if (itemStack.is(Items.BOOK)) {
                            enchanted = itemStack.transmuteCopy(Items.ENCHANTED_BOOK);
                            this.inventory.setItem(0, enchanted);
                        }

                        for (EnchantmentInstance entry : list) {
                            enchanted.enchant(entry.enchantment(), entry.level());
                        }

                        player.awardStat(Stats.ENCHANT_ITEM);
                        if (player instanceof ServerPlayer spe) {
                            CriteriaTriggers.ENCHANTED_ITEM.trigger(spe, enchanted, cost);
                        }

                        this.inventory.setChanged();
                        this.seed.set(player.getEnchantmentSeed());
                        this.slotsChanged(this.inventory);
                        world.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F,
                                world.random.nextFloat() * 0.1F + 0.9F);
                    }
                });
                return true;
            }
        }
        return false;
    }

    private List<EnchantmentInstance> generateEnchantments(RegistryAccess registryManager, ItemStack stack, int slot, int level) {
        this.random.setSeed((long) (this.seed.get() + slot));

        // 🔽 Vanilla enchantments only (for now)
        Optional<HolderSet.Named<Enchantment>> optional =
                registryManager.lookupOrThrow(Registries.ENCHANTMENT).get(EnchantmentTags.IN_ENCHANTING_TABLE);
        if (optional.isEmpty()) {
            return List.of();
        }

        List<EnchantmentInstance> list =
                EnchantmentHelper.selectEnchantment((net.minecraft.util.RandomSource) this.random, stack, level, optional.get().stream());

        if (stack.is(Items.BOOK) && list.size() > 1) {
            list.remove(this.random.nextInt(list.size()));
        }

        return list;
    }

    public int getEchoShardCount() {
        ItemStack stack = this.inventory.getItem(1);
        return stack.isEmpty() ? 0 : stack.getCount();
    }

    public int getSeed() {
        return this.seed.get();
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.context.execute((world, pos) -> this.clearContainer(player, this.inventory));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.context, player, BlockFactory.DARK_ENCHANTMENT_TABLE);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            itemStack = itemStack2.copy();

            if (slot == 0) {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 1) {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.is(Items.ECHO_SHARD)) {
                if (!this.moveItemStackTo(itemStack2, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.slots.get(0).hasItem() || !this.slots.get(0).mayPlace(itemStack2)) {
                    return ItemStack.EMPTY;
                }
                ItemStack single = itemStack2.copyWithCount(1);
                itemStack2.shrink(1);
                this.slots.get(0).setByPlayer(single);
            }

            if (itemStack2.isEmpty()) {
                slot2.setByPlayer(ItemStack.EMPTY);
            } else {
                slot2.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
        }

        return itemStack;
    }
}

