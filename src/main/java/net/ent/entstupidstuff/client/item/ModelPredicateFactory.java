package net.ent.entstupidstuff.client.item;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateFactory {

    public static void onInitialize() {

        registerBlocking(ItemFactory.WOODEN_ACACIA_SHIELD);
        registerBlocking(ItemFactory.WOODEN_BAMBOO_SHIELD);
        registerBlocking(ItemFactory.WOODEN_BIRCH_SHIELD);
        registerBlocking(ItemFactory.WOODEN_CHERRY_SHIELD);
        registerBlocking(ItemFactory.WOODEN_DARK_OAK_SHIELD);
        registerBlocking(ItemFactory.WOODEN_JUNGLE_SHIELD);
        registerBlocking(ItemFactory.WOODEN_MANGROVE_SHIELD);
        registerBlocking(ItemFactory.WOODEN_OAK_SHIELD);
        registerBlocking(ItemFactory.WOODEN_SPRUCE_SHIELD);

        registerBlocking(ItemFactory.STONE_SHIELD);
        registerBlocking(ItemFactory.STONE_DEEPSLATE_SHIELD);
        registerBlocking(ItemFactory.STONE_BLACKSTONE_SHIELD);

        registerBlocking(ItemFactory.GOLDEN_SHIELD);

        registerBlocking(ItemFactory.DIAMOND_SHIELD);

        //registerThrowing(ItemFactory.ANCIENT_TRIDENT);
        

    }



    public static void registerBlocking(Item item) {
        Identifier id = Identifier.ofVanilla("blocking");
        ClampedModelPredicateProvider provider =  (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;

        ModelPredicateProviderRegistry.register(item, id, provider);	
	}

    public static void registerThrowing(Item item) {
        Identifier id = Identifier.ofVanilla("throwing");
        ClampedModelPredicateProvider provider =  (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;

        ModelPredicateProviderRegistry.register(item, id, provider);	
	}  

    
}
