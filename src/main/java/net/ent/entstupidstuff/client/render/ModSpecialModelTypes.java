package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.client.item.model.special.AncientTridentSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.CopperShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.DiamondShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.IronShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.NetheriteShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.StoneShieldSpecialRenderer;
import net.ent.entstupidstuff.client.item.model.special.WoodenShieldSpecialRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.util.Identifier;

public class ModSpecialModelTypes {

    public static void onInit() {

        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "wooden_shield"),
            WoodenShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "stone_shield"),
            StoneShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "iron_shield"),
            IronShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "copper_shield"),
            CopperShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "diamond_shield"),
            DiamondShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "netherite_shield"),
            NetheriteShieldSpecialRenderer.Unbaked.CODEC
        );
        
        SpecialModelTypes.ID_MAPPER.put(
            Identifier.of("entstupidstuff", "ancient_trident"),
            AncientTridentSpecialRenderer.Unbaked.CODEC
        );

    }

}
