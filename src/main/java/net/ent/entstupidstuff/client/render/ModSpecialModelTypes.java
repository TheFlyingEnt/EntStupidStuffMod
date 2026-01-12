package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.client.item.model.special.AncientTridentSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.CopperShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.DiamondShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.IronShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.NetheriteShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.StoneShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.WoodenShieldSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.ResourceLocation;

public class ModSpecialModelTypes {

    public static void onInit() {

        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "wooden_shield"),
            WoodenShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "stone_shield"),
            StoneShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "iron_shield"),
            IronShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "copper_shield"),
            CopperShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "diamond_shield"),
            DiamondShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "netherite_shield"),
            NetheriteShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelRenderers.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "ancient_trident"),
            AncientTridentSpecialRenderer.Unbaked.CODEC
        );

    }

}
