package net.ent.entstupidstuff;

import net.ent.entstupidstuff.block.ModRenderLayers;
import net.ent.entstupidstuff.client.ParticlesClient;
import net.ent.entstupidstuff.client.item.ModelPredicateFactory;
import net.ent.entstupidstuff.client.render.BuiltInModelItemRenderer;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.item.ModModelPredicateReg;
import net.ent.entstupidstuff.item.base.FinalCannon;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class EntStupidStuffClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.onInitialize();
        ModRenderLayers.onInitializeClient();
        ModModelPredicateReg.onInitialize();
        ModelPredicateFactory.onInitialize();
        BuiltInModelItemRenderer.onInitialize();
        ParticlesClient.initalize();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null) {
                ItemStack stack = client.player.getMainHandStack();
                if (stack.getItem() instanceof FinalCannon && FinalCannon.isCharged(stack)) {
                    int x = drawContext.getScaledWindowWidth() / 2;
                    int y = drawContext.getScaledWindowHeight() / 2;
                    //Identifier TEX = Identifier.of("entstupidstuff", "textures/gui/cannon_crosshair.png");
                    //Identifier TEX = Identifier.of("entstupidstuff", "textures/gui/sprites/hud/crosshair_cannon.png");

                    //drawContext.drawTexture(TEX, x - 8, y - 8, 0, 0, 16, 16, 16, 16);
                }
            }
        });
        
    }
}
