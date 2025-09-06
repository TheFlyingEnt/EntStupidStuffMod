package net.ent.entstupidstuff.block.entity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap.Builder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;

public class DarkEnchantingTableBlockEntity extends BlockEntity implements Nameable {

    private Text customName;

    public DarkEnchantingTableBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityFactory.DARK_ENCHANTING_TABLE, pos, state);
    }



    @Override
	public Text getName() {
		return (Text)(this.customName != null ? this.customName : Text.translatable("container.enchant"));
	}

    public void setCustomName(@Nullable Text customName) {
		this.customName = customName;
	}

	@Nullable
	@Override
	public Text getCustomName() {
		return this.customName;
	}

	@Override
	protected void readComponents(BlockEntity.ComponentsAccess components) {
		super.readComponents(components);
		this.customName = components.get(DataComponentTypes.CUSTOM_NAME);
	}

	@Override
	protected void addComponents(Builder componentMapBuilder) {
		super.addComponents(componentMapBuilder);
		componentMapBuilder.add(DataComponentTypes.CUSTOM_NAME, this.customName);
	}

	@Override
	public void removeFromCopiedStackNbt(NbtCompound nbt) {
		nbt.remove("CustomName");
	}
}
