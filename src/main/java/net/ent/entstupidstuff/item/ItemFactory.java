package net.ent.entstupidstuff.item;

import java.util.LinkedHashMap;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.base.AncientTridentItem;
import net.ent.entstupidstuff.item.base.BottleOfRumItem;
import net.ent.entstupidstuff.item.base.ButterflyJarItem;
import net.ent.entstupidstuff.item.base.CannonBallItem;
import net.ent.entstupidstuff.item.base.FinalCannon;
import net.ent.entstupidstuff.item.base.PrismerineArrowItem;
import net.ent.entstupidstuff.item.base.WeaponBattleAxeItem;
import net.ent.entstupidstuff.item.base.WeaponClaymoreItem;
import net.ent.entstupidstuff.item.base.WeaponGlaiveItem;
import net.ent.entstupidstuff.item.base.WeaponHammerItem;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.ent.entstupidstuff.item.itemType.DaggerItem;
import net.ent.entstupidstuff.item.itemType.LongSwordItem;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BowItem;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemFactory {

    /*
     *  This is the Updated ItemFactory that merges
     *  WeaponFactory, ModFood and ModSpawn
     *  Design to be more Inline with Minecraft's System
     * 
     *  While this system is not the most optimized, For Read ability:
     *  It was designed like this
     * 
     */

    public static final Map<Identifier, Item> ItemList = new LinkedHashMap<>();
    public static final Map<Identifier, Item> ModelList = new LinkedHashMap<>();

    //Launch - Welcome to Stupidity
    public static final Item MARSHMELLOW_RAW = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).usingConvertsTo(Items.STICK).alwaysEdible().build()));
    public static final Item MARSHMELLOW_TOASTED = new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).usingConvertsTo(Items.STICK).alwaysEdible().build()));
    public static final Item BAGGUETTE = new Item(new Item.Settings().maxCount(16).food(FoodComponents.BREAD).attributeModifiers(WeaponItem.createAttributeModifiers(ToolMaterials.WOOD, 1  + ToolMaterials.WOOD.getAttackDamage(), 2.6f, 1, 0, 0)));
    public static final Item NOODEL_BOWL = new Item(new Item.Settings().maxCount(1).food(FoodComponents.RABBIT_STEW));
    public static final Item APPLE_PIE = new Item(new Item.Settings().food(FoodComponents.PUMPKIN_PIE));

    public static final Item BUTTERFLY_JAR =  new ButterflyJarItem(new Item.Settings().maxCount(16));

    public static final Item BUTTERFLY_SPAWN_EGG = new SpawnEggItem(EntityFactory.BUTTERFLY, 0xed7545, 0x4a1d0b, new Item.Settings()); //Butterfly

    public static final Item OTTER_SPAWN_EGG = null;

    public static final Item MUSHROOM_GLOWING = null;
    public static final Item MAGIC_DUST = null;
    public static final Item VENOM_GLOB = null;

    public static final Item ZOMBIE_LOBBER_SPAWN_EGG = new SpawnEggItem(EntityFactory.LOBBER_ZOMBIE, /*0x9CAE86, 0x39574B*/ 0x39574b, 0x748365, new Item.Settings()); //39574b and 748365
    public static final Item ZOMBIE_SCORCHED_SPAWN_EGG = new SpawnEggItem(EntityFactory.ZOMBIE_SCORCHED, /*0x6D2729, 0xE38D2F*/0x732124, 0xe38d2f, new Item.Settings()); //732124 and e38d2f
    public static final Item ZOMBIE_FROSTBITE_SPAWN_EGG = new SpawnEggItem(EntityFactory.ZOMBIE_FROSTBITTEN, /*0x6D2729, 0xE38D2F*/0x732124, 0xe38d2f, new Item.Settings()); //732124 and e38d2f


    //Launch - Welcome to Stupidity
    public static final Item WITHER_BONE = new Item(new Item.Settings());
    public static final Item SCORCHED_FLESHED = null;
    public static final Item ANCIENT_DEBRIS_NUGGET = new Item(new Item.Settings());;

    public static final Item HUNT_ARMOR_TRIM_SMITHING_TEMPLATE = null;

    public static final Item PIGLIN_WARRIOR_SPAWN = new SpawnEggItem(EntityFactory.PIGLIN_WARRIOR, 0x591010, 0xF9F3A4, new Item.Settings()); //Colored
    public static final Item PIGLIN_FUNGAL_THROWER_SPAWN = new SpawnEggItem(EntityFactory.PIGLIN_FUNGAL, 0x455A4D, 0xF9F3A4, new Item.Settings());
    public static final Item BLAZING_INFERNO_SPAWN = new SpawnEggItem(EntityFactory.HOVERING_INFERNO, /*0x5d342c, 0xedaa45*/0xfc9600, 0x502727, new Item.Settings()); //fc9600 and 502727
    public static final Item SOUL_SKELETON_SPAWN = new SpawnEggItem(EntityFactory.SOUL_SKELETON, /*0xC1C1C1, 0x39D6E0*/ 0xdac9be, 0x4b3739, new Item.Settings()); // dac9be and 4b3739
    public static final Item FIRE_FOX_SPAWN = null;
    public static final Item BEETLE_BOMB_SPAWN = null;
    public static final Item HOVREING_INFERNO = null;

    public static final Item ZEBRA_FISH_BUCKET = new EntityBucketItem(EntityFactory.ZEBRA_FISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT));
    public static final Item ALLIGATOR_GAR_BUCKET = new EntityBucketItem(EntityFactory.ALLIGATOR_GAR, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT));
    public static final Item MACKEREL_BUCKET = new EntityBucketItem(EntityFactory.MACKEREL, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT));

    public static final Item ZEBRA_FISH = new Item((new Item.Settings()).food(FoodComponents.COD));
    //public static final Item COOKED_ZEBRA_FISH = new Item((new Item.Settings()).food(FoodComponents.COOKED_COD));

    public static final Item ALLIGATOR_GAR = new Item((new Item.Settings()).food(FoodComponents.COD));
    public static final Item COOKED_ALLIGATOR_GAR = new Item((new Item.Settings()).food(FoodComponents.COOKED_COD));

    public static final Item MACKEREL = new Item((new Item.Settings()).food(FoodComponents.COD));
    public static final Item COOKED_MACKEREL = new Item((new Item.Settings()).food(FoodComponents.COOKED_COD));
    
    public static final Item ARMORED_PILLAGER_SPAWN_EGG = new SpawnEggItem(EntityFactory.ARMORED_PILLAGER, /*0x6D2729, 0xE38D2F*/0x732124, 0xe38d2f, new Item.Settings()); //732124 and e38d2f

    //The Sea of Dead (Pirate Life) - On Stranger Tides
    public static final Item RUM = new BottleOfRumItem(new Item.Settings());
    public static final Item CANNON_BALL_ITEM = new CannonBallItem(new Item.Settings());
    public static final Item CANNON_ITEM = new FinalCannon(new Item.Settings());
    public static final Item FLINT_LOCK_BOW = null;
    public static final Item PRISMERINE_ARROW = new PrismerineArrowItem(new Item.Settings());
    public static final Item CLAB_GEM = null; //Alterive to Quarts???

    public static final Item SUNKEN_ARMOR_TRIM_SMITHING_TEMPLATE = null;

    public static final Item SUNKEN_SKELETON_SPAWN = new SpawnEggItem(EntityFactory.SUNKEN_SKELETON, 0xC1C1C1, 0x51A03E, new Item.Settings());
    public static final Item SUNKEN_SKELETON2_SPAWN = new SpawnEggItem(EntityFactory.SUNKEN_SKELETON_CROSSBOW, 0xC1C1C1, 0x7EBF6E, new Item.Settings());
    public static final Item SKELETON_PIRATE_CAPTAIN_SPAWN = new SpawnEggItem(EntityFactory.SKELETON_PIRATE_CAPTAIN, 0x455A4D, 0x412220, new Item.Settings());
    public static final Item METAL_SKELETON_SPAWN = new SpawnEggItem(EntityFactory.METAL_SKELETON, 0xFFFFFF, 0xFFFFFF, new Item.Settings());
    public static final Item ASHEN_SKELETON_SPAWN = null;

    public static final Item SUNKEN_DROWN_SPAWN = null;
    public static final Item ANCIENT_DROWN_SPAWN = null;
    public static final Item ANCIENT_TRIDENT = new AncientTridentItem((new Item.Settings()).rarity(Rarity.EPIC).maxDamage(250).attributeModifiers(AncientTridentItem.createAttributeModifiers()).component(DataComponentTypes.TOOL, AncientTridentItem.createToolComponent()));

    public static final Item PHANTOM_SKELETON_SPAWN = new SpawnEggItem(EntityFactory.PHANTOM_SKELETON, 0xFFFFFF, 0xFFFFFF, new Item.Settings());
    public static final Item PHANTOM_SPAWN = null;
    public static final Item PHANTOM_PILLAGER_SPAWN = null;
    public static final Item PHANTOM_VINDICATOR_SPAWN = null;

    public static final Item PIRATE_PILLAGER_SPAWN = null;
    public static final Item PIRATE_VINDICATOR_SPAWN = null;
    public static final Item PIRATE_CAPTAIN_SPAWN = null;
    public static final Item GIANT_CLAB_SPAWN = null;

    //public static final Item WOODEN_SHIELD = null; Replaced with Varient Shields
    public static final Item STONE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));;
    public static final Item STONE_BLACKSTONE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));; //Custom Nether Design
    public static final Item STONE_DEEPSLATE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));; // Custom Cave Design



    public static final Item GOLDEN_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));;
    public static final Item DIAMOND_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item BLAZING_SHIELD = null; // Basically Netherite Shield // Custom Model - This has a Shield Bash, set enemys on fire
    public static final Item AMYTHESTH_SHIELD = null; // Basically Cooper  Shield - Low Durrabilty //Custom Model,

    public static final Item WOODEN_OAK_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)); //All use Basic Design
    public static final Item WOODEN_SPRUCE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_BIRCH_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_JUNGLE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_ACACIA_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_DARK_OAK_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_MANGROVE_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_CHERRY_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    //public static final Item WOODEN_PALE_OAK_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    //public static final Item WOODEN_CRIMSON_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    //public static final Item WOODEN_WARPED_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    public static final Item WOODEN_BAMBOO_SHIELD = new ShieldItem(new Item.Settings().maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));

    @SuppressWarnings("unused")
    private static final Item WOODEN_ARROW = null;

    //Testing
    public static final Item BattleAxeTest = new WeaponBattleAxeItem(ToolMaterials.IRON, new Item.Settings().maxDamage(336));
    public static final Item GlaiveTest = new WeaponGlaiveItem(ToolMaterials.IRON, new Item.Settings().maxDamage(336));
    //public static final Item ClaymoreTest = new WeaponClaymoreItem(ToolMaterials.IRON, new Item.Settings().maxDamage(336));


    /*
     * [plp]
     * [psp]
     * [ p ]
     */

    //Maybe Add Bash Shields? - Armored Shields - Have a Pushback knockback ability
    // Bash Shield has the whole texture the type as well as the extra buts

    // Heavy Shield

    // Heavy Shield

    //Combat:
    //Sword:      0.6 Sec Cooldown, Sweeping
    //Axe:        1.2 Sec Cooldown,
    //Claymore:   1.6 Sec Cooldown,
    //BattleAxe:  0.95 Sec Cooldown
    //Dagger:     0.4 Sec Cooldown 
    //Glaive:     1.4 Sec Cooldown
    //Hammer:     2.0 Sec Cooldown
    //Katana:     00.65 Sec Cooldown
    //Shield
    //Summon Vex Scroll
    //Healing Tomb




    //public static final Item REDWOOD_SIGN = new SignItem(new Item.Settings().maxCount(16), BlockFactoryUpt.callBlock("redwood_sign"), BlockFactoryUpt.callBlock("redwood_wall_sign"));
    //public static final Item REDWOOD_HANGING_SIGN = new HangingSignItem(BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"), new Item.Settings().maxCount(16));

    public static void onInitialize() {

        registerItems("battle_test", BattleAxeTest);
        registerItems("glaive_test", GlaiveTest);
        registerItems("butterfly_jar", BUTTERFLY_JAR);
        //registerItems("diamond_claymore", ClaymoreTest);

        registerItems("wooden_claymore", new WeaponClaymoreItem(ToolMaterials.WOOD, new Item.Settings()));
        registerItems("stone_claymore", new WeaponClaymoreItem(ToolMaterials.STONE, new Item.Settings()));
        registerItems("golden_claymore", new WeaponClaymoreItem(ToolMaterials.GOLD, new Item.Settings()));
        registerItems("iron_claymore", new WeaponClaymoreItem(ToolMaterials.IRON, new Item.Settings()));
        registerItems("diamond_claymore", new WeaponClaymoreItem(ToolMaterials.DIAMOND, new Item.Settings()));
        registerItems("netherite_claymore", new WeaponClaymoreItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        registerItems("wooden_glaive", new WeaponGlaiveItem(ToolMaterials.WOOD, new Item.Settings())); //Throwable??
        registerItems("stone_glaive", new WeaponGlaiveItem(ToolMaterials.STONE, new Item.Settings()));
        registerItems("golden_glaive", new WeaponGlaiveItem(ToolMaterials.GOLD, new Item.Settings()));
        registerItems("iron_glaive", new WeaponGlaiveItem(ToolMaterials.IRON, new Item.Settings()));
        registerItems("diamond_glaive", new WeaponGlaiveItem(ToolMaterials.DIAMOND, new Item.Settings()));
        registerItems("netherite_glaive", new WeaponGlaiveItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        registerItems("wooden_hammer", new WeaponHammerItem(ToolMaterials.WOOD, new Item.Settings()));
        registerItems("stone_hammer", new WeaponHammerItem(ToolMaterials.STONE, new Item.Settings()));
        registerItems("golden_hammer", new WeaponHammerItem(ToolMaterials.GOLD, new Item.Settings()));
        registerItems("iron_hammer", new WeaponHammerItem(ToolMaterials.IRON, new Item.Settings()));
        registerItems("diamond_hammer", new WeaponHammerItem(ToolMaterials.DIAMOND, new Item.Settings()));
        registerItems("netherite_hammer", new WeaponHammerItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        //registerItems("wooden_hammer_new", new WeaponHammerItem(ToolMaterials.WOOD, new Item.Settings()));
        //registerItems("stone_hammer_new", new WeaponHammerItem(ToolMaterials.STONE, new Item.Settings()));
        //registerItems("golden_hammer_new", new WeaponHammerItem(ToolMaterials.GOLD, new Item.Settings()));
        //registerItems("iron_hammer_new", new WeaponHammerItem(ToolMaterials.IRON, new Item.Settings()));
        //registerItems("diamond_hammer_new", new WeaponHammerItem(ToolMaterials.DIAMOND, new Item.Settings()));
        //registerItems("netherite_hammer_new", new WeaponHammerItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        //registerItems("wooden_battleaxe", new WeaponBattleAxeItem(ToolMaterials.WOOD, new Item.Settings())); //Throwable??
        //registerItems("stone_battleaxe", new WeaponBattleAxeItem(ToolMaterials.STONE, new Item.Settings()));
        //registerItems("golden_battleaxe", new WeaponBattleAxeItem(ToolMaterials.GOLD, new Item.Settings()));
        //registerItems("iron_battleaxe", new WeaponBattleAxeItem(ToolMaterials.IRON, new Item.Settings()));
        //registerItems("diamond_battleaxe", new WeaponBattleAxeItem(ToolMaterials.DIAMOND, new Item.Settings()));
        //registerItems("netherite_battleaxe", new WeaponBattleAxeItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        registerItems("wooden_dagger", new DaggerItem(ToolMaterials.WOOD, new Item.Settings()));
        registerItems("stone_dagger", new DaggerItem(ToolMaterials.STONE, new Item.Settings()));
        registerItems("golden_dagger", new DaggerItem(ToolMaterials.GOLD, new Item.Settings()));
        registerItems("iron_dagger", new DaggerItem(ToolMaterials.IRON, new Item.Settings()));
        registerItems("diamond_dagger", new DaggerItem(ToolMaterials.DIAMOND, new Item.Settings()));
        registerItems("netherite_dagger", new DaggerItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        registerItems("wooden_long_sword", new LongSwordItem(ToolMaterials.WOOD, new Item.Settings()));
        registerItems("stone_long_sword", new LongSwordItem(ToolMaterials.STONE, new Item.Settings()));
        registerItems("golden_long_sword", new LongSwordItem(ToolMaterials.GOLD, new Item.Settings()));
        registerItems("iron_long_sword", new LongSwordItem(ToolMaterials.IRON, new Item.Settings()));
        registerItems("diamond_long_sword", new LongSwordItem(ToolMaterials.DIAMOND, new Item.Settings()));
        registerItems("netherite_long_sword", new LongSwordItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        //registerModel("wooden_hammer_model", new ToolItem(ToolMaterials.WOOD, new Item.Settings()));
        //registerModel("stone_hammer_model", new ToolItem(ToolMaterials.STONE, new Item.Settings()));
        //registerModel("golden_hammer_model", new ToolItem(ToolMaterials.GOLD, new Item.Settings()));
        //registerModel("iron_hammer_model", new ToolItem(ToolMaterials.IRON, new Item.Settings()));
        //registerModel("diamond_hammer_model", new ToolItem(ToolMaterials.DIAMOND, new Item.Settings()));
        //registerModel("netherite_hammer_model", new ToolItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));

        //registerModel("netherite_hammer_model_piglin", new ToolItem(ToolMaterials.GOLD, new Item.Settings()));
        //registerModel("golden_hammer_model_piglin", new ToolItem(ToolMaterials.GOLD, new Item.Settings()));

        //registerModel("phantom_bow", new BowItem(new Item.Settings()));
        //registerModel("phantom_cutlass", new ToolItem(ToolMaterials.IRON, new Item.Settings())); 

        //registerModel("ancient_trident_in_hand", new ToolItem(ToolMaterials.IRON, new Item.Settings()));

        //registerItems("test_hammer_type", new KatanaItem(ToolMaterials.IRON, new Item.Settings()));

        registerItems("wooden_oak_shield", WOODEN_OAK_SHIELD);
        registerItems("wooden_spruce_shield", WOODEN_SPRUCE_SHIELD);
        registerItems("wooden_birch_shield", WOODEN_BIRCH_SHIELD);
        registerItems("wooden_jungle_shield", WOODEN_JUNGLE_SHIELD);
        registerItems("wooden_acacia_shield", WOODEN_ACACIA_SHIELD);
        registerItems("wooden_dark_oak_shield", WOODEN_DARK_OAK_SHIELD);
        registerItems("wooden_mangrove_shield", WOODEN_MANGROVE_SHIELD);
        registerItems("wooden_cherry_shield", WOODEN_CHERRY_SHIELD);
        registerItems("wooden_bamboo_shield", WOODEN_BAMBOO_SHIELD);

        registerItems("stone_shield", STONE_SHIELD);
        registerItems("stone_deepslate_shield", STONE_DEEPSLATE_SHIELD);
        registerItems("stone_blackstone_shield", STONE_BLACKSTONE_SHIELD);

        registerItems("golden_shield", GOLDEN_SHIELD);
        registerItems("diamond_shield", DIAMOND_SHIELD);


        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "redwood_sign"), REDWOOD_SIGN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "redwood_hanging_sign"), REDWOOD_HANGING_SIGN);

        //Launch - Welcome to Stupidity
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "raw_marshmellow"), MARSHMELLOW_RAW);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "toasted_marshmellow"), MARSHMELLOW_TOASTED);

        registerItems("raw_marshmellow", MARSHMELLOW_RAW);
        registerItems("toasted_marshmellow", MARSHMELLOW_TOASTED);
        registerItems("baguette", BAGGUETTE);
        registerItems("noodle_bowl", NOODEL_BOWL);
        registerItems("apple_pie", APPLE_PIE);
        registerItems("butterfly_spawn_egg", BUTTERFLY_SPAWN_EGG);

        registerItems("zebra_fish_bucket", ZEBRA_FISH_BUCKET);
        registerItems("alligator_gar_bucket", ALLIGATOR_GAR_BUCKET);
        registerItems("mackerel_bucket", MACKEREL_BUCKET);

        registerItems("zebra_fish", ZEBRA_FISH);
        //registerItems("cooked_zebra_fish", COOKED_ZEBRA_FISH);

        registerItems("alligator_gar", ALLIGATOR_GAR);
        registerItems("cooked_alligator_gar", COOKED_ALLIGATOR_GAR);

        registerItems("mackerel", MACKEREL);
        registerItems("cooked_mackerel", COOKED_MACKEREL);


        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "noodle_bowl"), NOODEL_BOWL);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "baggette"), BAGGETTE);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "apple_pie"), APPLE_PIE);

        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "zombie_lobber_spawn_egg"), ZOMBIE_LOBBER_SPAWN_EGG);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "zombie_scorched_spawn_egg"), ZOMBIE_SCORCHED_SPAWN_EGG);
        registerItems("zombie_lobber_spawn_egg", ZOMBIE_LOBBER_SPAWN_EGG);
        registerItems("zombie_scorched_spawn_egg", ZOMBIE_SCORCHED_SPAWN_EGG);
        registerItems("zombie_frostbite_spawn_egg", ZOMBIE_FROSTBITE_SPAWN_EGG);
        registerItems("armored_pillager_spawn_egg", ARMORED_PILLAGER_SPAWN_EGG);
        registerItems("ancient_trident", ANCIENT_TRIDENT);



        //Flames of the Hunt
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "wither_bone"), WITHER_BONE);
        registerItems("wither_bone", WITHER_BONE);


        //SCORCHED_FLESHED
        //HUNT_ARMOR_TRIM_SMITHING
        registerItems("ancient_debris_nugget", ANCIENT_DEBRIS_NUGGET);


        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "ancient_debris_nugget"), ANCIENT_DEBRIS_NUGGET);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "piglin_warrior_spawn_egg"), PIGLIN_WARRIOR_SPAWN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "piglin_fungal_thrower_spawn_egg"), PIGLIN_FUNGAL_THROWER_SPAWN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "blazing_inferno_spawn_egg"), BLAZING_INFERNO_SPAWN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "soul_skeleton_spawn_egg"), SOUL_SKELETON_SPAWN);
        //FIRE_FOX_SPAWN
        //BEETLE_BOMB_SPAWN
        registerItems("piglin_warrior_spawn_egg", PIGLIN_WARRIOR_SPAWN);
        registerItems("piglin_fungal_thrower_spawn_egg", PIGLIN_FUNGAL_THROWER_SPAWN);
        registerItems("blazing_inferno_spawn_egg", BLAZING_INFERNO_SPAWN);
        registerItems("soul_skeleton_spawn_egg", SOUL_SKELETON_SPAWN);



        //The Sea of Dead (Pirate Life)
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "bottle_of_rum"), RUM);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "cannon_ball"), CANNON_BALL_ITEM);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "cannon"), CANNON_ITEM);
        registerItems("bottle_of_rum", RUM);
        registerItems("cannon_ball", CANNON_BALL_ITEM);
        registerItems("cannon", CANNON_ITEM);
        registerItems("prismerine_arrow", PRISMERINE_ARROW);




        //FLINT_LOCK_BOW
        //CRAB_GEM
        //SUNKEN_ARMOR_TRIM_SMITING_TEMPLATE
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton_spawn_egg"), SUNKEN_SKELETON_SPAWN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "sunken_skeleton2_spawn_egg"), SUNKEN_SKELETON2_SPAWN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "skeleton_pirate_captain_spawn_egg"), SKELETON_PIRATE_CAPTAIN_SPAWN);

        registerItems("sunken_skeleton_spawn_egg", SUNKEN_SKELETON_SPAWN);
        registerItems("sunken_skeleton2_spawn_egg", SUNKEN_SKELETON2_SPAWN);
        registerItems("skeleton_pirate_captain_spawn_egg", SKELETON_PIRATE_CAPTAIN_SPAWN);

        registerItems("skeleton_metal_spawn_egg", METAL_SKELETON_SPAWN);
        registerItems("skeleton_phantom_spawn_egg", PHANTOM_SKELETON_SPAWN);

        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "redwood_sign"), REDWOOD_SIGN);
        //Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, "redwood_hanging_sign"), REDWOOD_HANGING_SIGN);

        onInitializeMobEggs();

        /*ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(Identifier.of(EntStupidStuff.MOD_ID, "item/ancient_trident_in_hand"));
        });

        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(Identifier.of(EntStupidStuff.MOD_ID, "ancient_trident_in_hand"));
        });*/



        // Register Block Models with Default Items and add to ItemList


        // Register Multiple Block Models with One Item (IE. Signs, Hanging Signs)
        //registerItems("redwood_sign", REDWOOD_SIGN);
        //registerItems("redwood_hanging_sign", REDWOOD_HANGING_SIGN);

    }

    public static void BlockItem(){
        //registerItems("redwood_sign", new SignItem(new Item.Settings().maxCount(16), BlockFactoryUpt.callBlock("redwood_sign"), BlockFactoryUpt.callBlock("redwood_wall_sign")));
        //registerItems("redwood_hanging_sign", new HangingSignItem(BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"), new Item.Settings().maxCount(16)));
    }

    public static void onInitializeMobEggs() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.addAfter(
                ZOMBIE_LOBBER_SPAWN_EGG,
                ZOMBIE_SCORCHED_SPAWN_EGG,
                PIGLIN_WARRIOR_SPAWN,
                PIGLIN_FUNGAL_THROWER_SPAWN,
                BLAZING_INFERNO_SPAWN,
                SOUL_SKELETON_SPAWN,
                SUNKEN_SKELETON_SPAWN,
                SUNKEN_SKELETON2_SPAWN,
                SKELETON_PIRATE_CAPTAIN_SPAWN
            );
        });
    }

    public static <I extends Item> I registerModel(String name, I item) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        System.out.println(id.toString());
        ModelList.put(id, item);
        Registry.register(Registries.ITEM, id, item);
        return item;
    }

    public static <I extends Item> I registerItems(String name, I item) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        System.out.println("Item: " + id.toString());
        ItemList.put(id, item);
        Registry.register(Registries.ITEM, id, item);

        ModGroup.addToDefault(name);

        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
        }

        return item;
    }

    public static Item callItem(String name) {
        return ItemList.get(Identifier.of(EntStupidStuff.MOD_ID, name));
    }
    
}
