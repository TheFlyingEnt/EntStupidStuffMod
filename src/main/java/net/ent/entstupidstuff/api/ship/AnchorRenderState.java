package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;

public class AnchorRenderState extends EntityRenderState {
    /** Vector from the anchor's render position to the ship's chain attach point. */
    public Vec3 toShip = Vec3.ZERO;

    /** Number of chain links to render (0 = no chain visible). */
    public int chainLinks = 0;

    /** How much the chain droops at its midpoint (blocks). */
    public double chainDroop = 0.5;
}

