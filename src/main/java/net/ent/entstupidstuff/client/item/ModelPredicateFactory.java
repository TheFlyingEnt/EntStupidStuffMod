package net.ent.entstupidstuff.client.item;

@Deprecated
public class ModelPredicateFactory {

    /*public static void onInitialize() {

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

        registerVariant(ItemFactory.BUTTERFLY_JAR);

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

    

    public static void registerVariant(Item item) {
        Identifier id = Identifier.ofVanilla("variant");

        ClampedModelPredicateProvider provider = (stack, world, entity, seed) -> {
            NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
            NbtCompound nbt = nbtComponent.copyNbt();

            if (nbt.contains("variant", 3)) { // INT_TYPE
                return (float) nbt.getInt("variant"); // return 0–8 directly
            }
            return 0f;
        };

        ModelPredicateProviderRegistry.register(item, id, provider);
    }*/
    
}
