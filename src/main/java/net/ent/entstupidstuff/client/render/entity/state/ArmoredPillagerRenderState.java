package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;

@Environment(EnvType.CLIENT)
public class ArmoredPillagerRenderState extends IllagerRenderState {

    public ArmoredPillagerEntity.Variant variant = ArmoredPillagerEntity.Variant.GOLD;

    public boolean isCaptain;

}
