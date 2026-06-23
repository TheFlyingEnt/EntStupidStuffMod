package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;
 
public class AnchorRenderState extends EntityRenderState {
    /** Vector from the anchor (render origin) to the ship's chain attach point, in world space. */
    public Vec3 toShip = Vec3.ZERO;
}

