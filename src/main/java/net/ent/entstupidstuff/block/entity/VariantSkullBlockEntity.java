package net.ent.entstupidstuff.block.entity;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class VariantSkullBlockEntity extends SkullBlockEntity {
    private static final String TAG_VARIANT = "variant";
    private String variant = "default";

    public VariantSkullBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public String getVariant() {
        return this.variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
        this.setChanged(); // Mark as changed so it saves
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.store(TAG_VARIANT, Codec.STRING, this.variant);
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.variant = valueInput.read(TAG_VARIANT, Codec.STRING).orElse("default");
    }
}
