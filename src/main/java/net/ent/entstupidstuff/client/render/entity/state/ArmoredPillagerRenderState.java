package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.IllagerEntityRenderState;

@Environment(EnvType.CLIENT)
public class ArmoredPillagerRenderState extends IllagerEntityRenderState {

    public ArmoredPillagerEntity.Variant variant = ArmoredPillagerEntity.Variant.GOLD;

}
