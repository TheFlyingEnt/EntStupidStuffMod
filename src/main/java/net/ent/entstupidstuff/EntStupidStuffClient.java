package net.ent.entstupidstuff;

import net.ent.entstupidstuff.block.ModRenderLayers;
import net.ent.entstupidstuff.client.ParticlesClient;
import net.ent.entstupidstuff.client.item.ModelPredicateFactory;
import net.ent.entstupidstuff.client.render.BuiltInModelItemRenderer;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.item.ModModelPredicateReg;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;


public class EntStupidStuffClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModModelLayers.onInitialize();
        ModRenderLayers.onInitializeClient();
        ModModelPredicateReg.onInitialize();
        //ShieldSprite.onInitialize();     // No Longer in Use
        ModelPredicateFactory.onInitialize();
        BuiltInModelItemRenderer.onInitialize();
        //UpdatedEnchantmentEffects.registerEnchantmentEffects();

        ParticlesClient.initalize();
        
    }

    @SuppressWarnings("unused")
    private void scanTextures() {
        // This will now work because it's executed after the client is initialized and the ResourceManager is available
        MinecraftClient.getInstance().getResourceManager().findResources("textures/entity", path -> path.getPath().endsWith(".png")).forEach((key, resource) -> {
            System.out.println("Loaded texture: " + key);
        });
    }
}
