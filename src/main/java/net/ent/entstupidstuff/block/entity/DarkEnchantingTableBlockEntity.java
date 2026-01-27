package net.ent.entstupidstuff.block.entity;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.screen.DarkEnchantmentScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap.Builder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueOutput;

public class DarkEnchantingTableBlockEntity extends BlockEntity implements Nameable, MenuProvider{
    private Component customName;

    public DarkEnchantingTableBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityFactory.DARK_ENCHANTING_TABLE, pos, state);
    }

    @Override
        public Component getName() {
            return (Component)(this.customName != null ? this.customName : Component.translatable("container.enchant"));
        }
    
        public void setCustomName(@Nullable Component customName) {
            this.customName = customName;
        }
    
        @Nullable
        @Override
        public Component getCustomName() {
            return this.customName;
        }
    
        @Override
        protected void applyImplicitComponents(DataComponentGetter components) {
      super.applyImplicitComponents(components);
      this.customName = (Component)components.get(DataComponents.CUSTOM_NAME);
    }
    
        @Override
        protected void collectImplicitComponents(Builder componentMapBuilder) {
            super.collectImplicitComponents(componentMapBuilder);
            componentMapBuilder.set(DataComponents.CUSTOM_NAME, this.customName);
        }
    
        @Override
        public void removeComponentsFromTag(ValueOutput view) {
            view.discard("CustomName");
        }

        

	@Override
	public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
		return new DarkEnchantmentScreenHandler(syncId, playerInventory, ContainerLevelAccess.create(level, worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.dark_enchanting");
	}

	
}
