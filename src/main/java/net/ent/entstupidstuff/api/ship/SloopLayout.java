package net.ent.entstupidstuff.api.ship;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

/**
 * Static layout definition for the Sloop hull.
 *
 * The ship is built out of 1×1×1 axis-aligned cubes ("bricks"). Each brick is
 * an invisible solid that vanilla collision handles natively — players walk
 * on the floor bricks and bump into the wall bricks. Tiling the hull out of
 * small cubes is what lets the ship rotate smoothly: the per-brick AABB error
 * when the ship turns is bounded by ~0.2 blocks at the corners, which is
 * imperceptible.
 *
 * Coordinate convention (ship-local):
 *   +X = starboard (right, looking forward)
 *   +Y = up
 *   +Z = bow (forward)
 *
 * Layer structure (y from bottom to top):
 *   y = -3.5   Lower deck FLOOR  — full rectangle. Top surface = y -3.
 *   y = -2.5   Lower deck WALLS  — perimeter only.
 *   y = -1.5   Lower deck WALLS  — perimeter only.
 *   y = -0.5   Top deck FLOOR    — full rectangle minus a 1-cell hatch.
 *                                  Top surface = y 0.
 *   y = +0.5   RAILING           — perimeter only, 1 block tall.
 *
 * Player feet at y=-3 (lower deck) or y=0 (top deck).
 * Lower deck headroom = 2 blocks (y=-3 floor to y=-1 ceiling). Just enough.
 *
 * Default 4×8 dimensions produce ~123 bricks per ship. Bump the constants
 * below to scale up to a Brigantine/Galleon footprint once basic walking
 * works.
 */
public final class SloopLayout {

    // ------------------------------------------------------------- tunables

    /** Width in blocks (x extent). */
    public static final int DECK_WIDTH  = 4;
    /** Length in blocks (z extent). Bow at +Z, stern at -Z. */
    public static final int DECK_LENGTH = 8;

    // Layer y-centers. Cubes have half-extent 0.5, so floor top = y + 0.5.
    private static final double Y_LOWER_FLOOR  = -3.5;
    private static final double Y_LOWER_WALL_1 = -2.5;
    private static final double Y_LOWER_WALL_2 = -1.5;
    private static final double Y_TOP_FLOOR    = -0.5;
    private static final double Y_RAILING      = +0.5;

    // Where to cut the ladder hatch in the top deck.
    // Indices into the deck grid: 0..DECK_WIDTH-1, 0..DECK_LENGTH-1.
    private static final int HATCH_X_IDX = DECK_WIDTH  / 2 - 1; // slightly port
    private static final int HATCH_Z_IDX = DECK_LENGTH / 2;     // amidships

    // ------------------------------------------------------------- output

    /** All bricks for one Sloop, generated once at class load. */
    public static final Brick[] BRICKS = generate();

    private SloopLayout() {}

    /**
     * One cubic collision element of the ship in ship-local coordinates.
     * The owning Ship transforms (center) into world coords each tick;
     * (hx, hy, hz) are the AABB half-extents of the brick itself.
     */
    public record Brick(double cx, double cy, double cz,
                        double hx, double hy, double hz) {
        public Vec3 center() { return new Vec3(cx, cy, cz); }

        /** The position to pass to Entity.setPos so the AABB ends up
         *  centered on (cx, cy, cz) given vanilla's "feet at position"
         *  convention. */
        public Vec3 entityPosition() {
            return new Vec3(cx, cy - hy, cz);
        }

        public static Brick unitCube(double cx, double cy, double cz) {
            return new Brick(cx, cy, cz, 0.5, 0.5, 0.5);
        }
    }

    /** Useful for verifying counts in logs while tuning. */
    public static String describe() {
        return String.format(
                "SloopLayout: %d×%d, %d total bricks (hatch at xi=%d, zi=%d)",
                DECK_WIDTH, DECK_LENGTH, BRICKS.length,
                HATCH_X_IDX, HATCH_Z_IDX);
    }

    // ------------------------------------------------------------- generation

    private static Brick[] generate() {
        List<Brick> bricks = new ArrayList<>();

        // Lower deck: solid floor.
        addFullLayer(bricks, Y_LOWER_FLOOR, -1, -1);

        // Lower deck hull: 2-block-tall perimeter wall (= 2 blocks of headroom).
        addPerimeter(bricks, Y_LOWER_WALL_1);
        addPerimeter(bricks, Y_LOWER_WALL_2);

        // Top deck: floor with a 1-cell hatch cut out for the ladder.
        addFullLayer(bricks, Y_TOP_FLOOR, HATCH_X_IDX, HATCH_Z_IDX);

        // Railing around the top deck so you can't walk off the edge.
        addPerimeter(bricks, Y_RAILING);

        return bricks.toArray(new Brick[0]);
    }

    /**
     * Add unit cubes covering every cell of the deck grid at height y, except
     * the single cell (skipXIdx, skipZIdx) if both indices are in range.
     * Pass (-1, -1) for no skip.
     */
    private static void addFullLayer(List<Brick> out, double y,
                                     int skipXIdx, int skipZIdx) {
        double xStart = -(DECK_WIDTH  / 2.0) + 0.5;
        double zStart = -(DECK_LENGTH / 2.0) + 0.5;
        for (int xi = 0; xi < DECK_WIDTH; xi++) {
            for (int zi = 0; zi < DECK_LENGTH; zi++) {
                if (xi == skipXIdx && zi == skipZIdx) continue;
                double x = xStart + xi;
                double z = zStart + zi;
                out.add(Brick.unitCube(x, y, z));
            }
        }
    }

    /**
     * Add unit cubes only along the perimeter of the deck grid at height y.
     */
    private static void addPerimeter(List<Brick> out, double y) {
        double xStart = -(DECK_WIDTH  / 2.0) + 0.5;
        double zStart = -(DECK_LENGTH / 2.0) + 0.5;
        for (int xi = 0; xi < DECK_WIDTH; xi++) {
            for (int zi = 0; zi < DECK_LENGTH; zi++) {
                boolean onPerimeter =
                        xi == 0 || xi == DECK_WIDTH - 1
                     || zi == 0 || zi == DECK_LENGTH - 1;
                if (!onPerimeter) continue;
                double x = xStart + xi;
                double z = zStart + zi;
                out.add(Brick.unitCube(x, y, z));
            }
        }
    }


}
