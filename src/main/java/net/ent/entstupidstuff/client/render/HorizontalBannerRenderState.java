package net.ent.entstupidstuff.client.render;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class HorizontalBannerRenderState extends BlockEntityRenderState {
    public Direction facing = Direction.NORTH;
    public DyeColor baseColor = DyeColor.WHITE;
    public BannerPatternLayers patterns = BannerPatternLayers.EMPTY;
}