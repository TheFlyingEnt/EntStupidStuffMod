package net.ent.entstupidstuff.api.ship;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
 
/**
 * Keeps deck-walkers glued to the boat across the network.
 *
 * The trick: sync a walker's position RELATIVE to the boat (a slow-changing,
 * lag-tolerant value), then on every client rebuild their world position from
 * that client's own smooth boat position. The boat's velocity never crosses
 * the wire, so remote walkers stop sliding while the ship moves.
 */
public final class DeckSync {
 
    /** A walker's offset in the boat's local frame, plus which boat. */
    public record Anchor(int boatId, double x, double y, double z) {
        public static final StreamCodec<ByteBuf, Anchor> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, Anchor::boatId,
            ByteBufCodecs.DOUBLE,  Anchor::x,
            ByteBufCodecs.DOUBLE,  Anchor::y,
            ByteBufCodecs.DOUBLE,  Anchor::z,
            Anchor::new);
    }
 
    /** World delta (entity - boat) expressed in the boat's local frame. */
    public static Anchor compute(CustomBoatEntity boat, Entity e) {
        double rad = Math.toRadians(boat.getYRot());
        double dx = e.getX() - boat.getX();
        double dz = e.getZ() - boat.getZ();
        // rotate by -yaw  (world -> local)
        double lx = dx * Math.cos(-rad) - dz * Math.sin(-rad);
        double lz = dx * Math.sin(-rad) + dz * Math.cos(-rad);
        double ly = e.getY() - boat.getY();
        return new Anchor(boat.getId(), lx, ly, lz);
    }
 
    /**
     * Rebuild the entity's world position from the boat's CURRENT (local, smooth)
     * position + the boat-local offset. Also fixes the previous-tick position so
     * render interpolation tracks the boat instead of lagging a frame behind it.
     */
    public static void apply(Level level, Entity e, Anchor a) {
        if (!(level.getEntity(a.boatId()) instanceof CustomBoatEntity boat)) return;
 
        // current world pos (local -> world: rotate by +yaw)
        double rad = Math.toRadians(boat.getYRot());
        double cos = Math.cos(rad), sin = Math.sin(rad);
        double wx = boat.getX() + a.x() * cos - a.z() * sin;
        double wz = boat.getZ() + a.x() * sin + a.z() * cos;
        double wy = boat.getY() + a.y();
        e.setPos(wx, wy, wz);
 
        // previous-tick world pos, using the boat's previous center + yaw, so the
        // renderer lerps smoothly with the boat (even through turns)
        float pYaw = boat.getYRot() - boat.getDeckDYaw();
        double pcx = boat.getX() - boat.getDeckDX();
        double pcz = boat.getZ() - boat.getDeckDZ();
        double pcy = boat.getY() - boat.getDeckDY();
        double prad = Math.toRadians(pYaw);
        double pcos = Math.cos(prad), psin = Math.sin(prad);
        e.xo = pcx + a.x() * pcos - a.z() * psin;
        e.zo = pcz + a.x() * psin + a.z() * pcos;
        e.yo = pcy + a.y();
    }
 
    /** The CustomBoat whose deck this entity is standing on, or null. */
    public static CustomBoatEntity findDeckBoat(Level level, Entity e) {
        AABB box = e.getBoundingBox().inflate(4.0, 2.0, 4.0);
        for (CustomBoatEntity boat : level.getEntitiesOfClass(CustomBoatEntity.class, box)) {
            if (boat.isOnDeck(e, false)) return boat;   // false = no stick-margin for initial detection
        }
        return null;
    }
 
    private DeckSync() {}
}

