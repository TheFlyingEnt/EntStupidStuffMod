package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.mob.MountaineerPillagerEntity;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;

public class MountaineerPillagerRenderState extends IllagerRenderState {

    public MountaineerPillagerEntity.Variant variant = MountaineerPillagerEntity.Variant.GOLD;

    public boolean isCaptain;

}
