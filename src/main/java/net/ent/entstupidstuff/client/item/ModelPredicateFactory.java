package net.ent.entstupidstuff.client.item;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateFactory {

    public static void onInitialize() {

        registerShield(ItemFactory.WOODEN_ACACIA_SHIELD);
        registerShield(ItemFactory.WOODEN_BAMBOO_SHIELD);
        registerShield(ItemFactory.WOODEN_BIRCH_SHIELD);
        registerShield(ItemFactory.WOODEN_CHERRY_SHIELD);
        registerShield(ItemFactory.WOODEN_DARK_OAK_SHIELD);
        registerShield(ItemFactory.WOODEN_JUNGLE_SHIELD);
        registerShield(ItemFactory.WOODEN_MANGROVE_SHIELD);
        registerShield(ItemFactory.WOODEN_OAK_SHIELD);
        registerShield(ItemFactory.WOODEN_SPRUCE_SHIELD);

        registerShield(ItemFactory.STONE_SHIELD);
        registerShield(ItemFactory.STONE_DEEPSLATE_SHIELD);
        registerShield(ItemFactory.STONE_BLACKSTONE_SHIELD);

        registerShield(ItemFactory.GOLDEN_SHIELD);

        registerShield(ItemFactory.DIAMOND_SHIELD);
        

    }



    public static void registerShield(Item item) {
        Identifier id = Identifier.ofVanilla("blocking");
        ClampedModelPredicateProvider provider =  (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;

        ModelPredicateProviderRegistry.register(item, id, provider);
		
	}

    
}
