package net.ent.entstupidstuff.api.ship;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;


/*
Fixes:
1. Fix the player on the boat. When the boat is moving, players on the Boat bug out. Fix this.
2. The Boat rocking need to be subtle
3. When Velocity/Movement need to be kept. When I Press A for example, the rutter moves. If I press off it will slow return back to default. I dont want inta Left and Right. Also when I press A for example, then let go, that Moment need to be there where the boat still kepts on going left.
4. The way I want the anchor to work is like this: When I press G, An Anchor Entity Will drop for example. Lets make this entity unable to jump. So when it hits a corner or hit a block that goes up, It  will make the anchor. This let the anchor drag till it hits something. Maybe have a Lead like thingy put chains that connect to the anchor below.
5. The boat leaning need to also be subtle

⚓     🟨   ->       ⚓🟨   -  Gets stuck here, hence stops the ship. 
🟨🟨🟨🟨       🟨🟨🟨🟨

6. Use game like sea of thieves for inpirtation. 
I need you step up. You did So good for the car getting everything I wanted. Kinda throwing the ball here. 

*/



 
/** Shows the hull health bar in the action-bar area whenever the player is aboard a ship. */
public final class ShipHud {
 
    private static final int SEGMENTS = 14;
 
    public static void register() {
        HudRenderCallback.EVENT.register((g, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.options.hideGui) return;
            if (!(mc.player.getVehicle() instanceof CustomBoatEntity ship)) return;
 
            Component text = healthBar(ship);
            int x = g.guiWidth() / 2;
            int y = g.guiHeight() - 60;          // just above the hotbar
            g.drawCenteredString(mc.font, text, x, y, 0xFFFFFF);
        });
    }
 
    public static Component healthBar(CustomBoatEntity ship) {
        if (ship.isSinking()) {
            return Component.literal("\u2693 SINKING \u2693").withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD);
        }
 
        float pct = ship.getHealthPct();
        int filled = Math.round(pct * SEGMENTS);
        ChatFormatting color = pct > 0.5f ? ChatFormatting.GREEN
                             : pct > 0.25f ? ChatFormatting.YELLOW
                             : ChatFormatting.RED;
 
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < SEGMENTS; i++) bar.append(i < filled ? '\u2588' : '\u2591'); // █ / ░
 
        MutableComponent c = Component.literal("Hull ").withStyle(ChatFormatting.GRAY)
            .append(Component.literal(bar.toString()).withStyle(color))
            .append(Component.literal(" " + Math.round(pct * 100) + "%").withStyle(color));
 
        if (ship.isFlooding()) {
            c.append(Component.literal("   \u26A0 TAKING ON WATER").withStyle(ChatFormatting.AQUA));
        }
        return c;
    }
 
    private ShipHud() {}
}

