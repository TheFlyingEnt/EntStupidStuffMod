package net.ent.entstupidstuff.item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.base.AncientTridentItem;
import net.ent.entstupidstuff.item.base.BassBucketItem;
import net.ent.entstupidstuff.item.base.BottleOfRumItem;
import net.ent.entstupidstuff.item.base.ButterflyJarItem;
import net.ent.entstupidstuff.item.base.CannonballItem;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.item.base.KoiBucketItem;
import net.ent.entstupidstuff.item.base.MahiMahiBucketItem;
import net.ent.entstupidstuff.item.base.PerchBucketItem;
import net.ent.entstupidstuff.item.base.PrismerineArrowItem;
import net.ent.entstupidstuff.item.base.WeaponBattleAxeItem;
import net.ent.entstupidstuff.item.base.WeaponClaymoreItem;
import net.ent.entstupidstuff.item.base.WeaponGlaiveItem;
import net.ent.entstupidstuff.item.base.WeaponHammerItem;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.ent.entstupidstuff.item.base.ZebraFishBucketItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponGlaiveItem22;
import net.ent.entstupidstuff.item.base.weapons.WeaponGreatSwordItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponHalberdItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponRapierItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponScytheItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponTwinDaggerItem;
import net.ent.entstupidstuff.item.base.weapons.WeaponWarhammerItem;
import net.ent.entstupidstuff.item.itemType.DaggerItem;
import net.ent.entstupidstuff.item.itemType.LongSwordItem;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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

    //public static final Map<Identifier, Item> BlockFactory.ItemList = new LinkedHashMap<>();
    public static final Map<Identifier, Item> ModelList = new LinkedHashMap<>();

    //Food Comp

    public static final FoodComponent FOOD_BASS = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent FOOD_COOKED_BASS = new FoodComponent.Builder().nutrition(7).saturationModifier(0.8F).build();

    public static final FoodComponent FOOD_ALLIGATOR_JAR = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent FOOD_COOKED_ALLIGATOR_JAR = new FoodComponent.Builder().nutrition(4).saturationModifier(0.5F).build();

    public static final FoodComponent FOOD_MAHIMAHI = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent FOOD_COOKED_FOOD_MAHIMAHI = new FoodComponent.Builder().nutrition(8).saturationModifier(0.9F).build();

    //Launch - Welcome to Stupidity
    
    public static final Item MARSHMELLOW_RAW = registerItem2("raw_marshmellow", settings -> new Item(settings.useRemainder(Items.STICK).maxCount(16).food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().build())));
    public static final Item MARSHMELLOW_TOASTED = registerItem2("toasted_marshmellow", settings -> new Item(settings.useRemainder(Items.STICK).maxCount(16).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().build())));
    public static final Item BAGGUETTE = registerItem2("baguette", settings -> new Item(settings.maxCount(16).food(FoodComponents.BREAD).attributeModifiers(WeaponUpdatedItem.createAttributeModifiers(ToolMaterial.WOOD, 1  + ToolMaterial.WOOD.attackDamageBonus(), 2.6f, 1, 0, 0))));

    //public static final Item MARSHMELLOW_RAW = new Item(settings.maxCount(16).food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().alwaysEdible().build()));
    //public static final Item MARSHMELLOW_TOASTED = new Item(settings.maxCount(16).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().alwaysEdible().build()));
    //public static final Item BAGGUETTE = new Item(settings.maxCount(16).food(FoodComponents.BREAD).attributeModifiers(WeaponUpdatedItem.createAttributeModifiers(ToolMaterial.WOOD, 1  + ToolMaterial.WOOD.attackDamageBonus(), 2.6f, 1, 0, 0)));
    
    public static final Item BUTTERFLY_JAR = registerItem2("butterfly_jar", settings -> new ButterflyJarItem(EntityFactory.BUTTERFLY, SoundEvents.ITEM_BOTTLE_FILL, settings));
    public static final Item BUTTERFLY_SPAWN_EGG = registerItem2("butterfly_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.BUTTERFLY)));
    
    public static final Item ZOMBIE_LOBBER_SPAWN_EGG = registerItem2("zombie_lobber_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.LOBBER_ZOMBIE)));
    public static final Item ZOMBIE_SCORCHED_SPAWN_EGG = registerItem2("zombie_scorched_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_SCORCHED)));
    public static final Item ZOMBIE_FROSTBITE_SPAWN_EGG = registerItem2("zombie_frostbite_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_FROSTBITTEN)));
    public static final Item ZOMBIE_SLIMED_SPAWN_EGG = registerItem2("zombie_slimed_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_SLIMED)));
    public static final Item ARMORED_PILLAGER_SPAWN_EGG = registerItem2("armored_pillager_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ARMORED_PILLAGER)));


    //public static final Item NOODEL_BOWL = new Item(settings.maxCount(1).food(FoodComponents.RABBIT_STEW));
    //public static final Item APPLE_PIE = new Item(settings.food(FoodComponents.PUMPKIN_PIE));

    //public static final Item MUSHROOM_GLOWING = null;
    //public static final Item MAGIC_DUST = null;
    //public static final Item VENOM_GLOB = null;

    //public static final Item FLINT_LOCK_BOW = null;
    //public static final Item CLAB_GEM = null; //Alterive to Quarts???
    //public static final Item SUNKEN_ARMOR_TRIM_SMITHING_TEMPLATE = null;


    //registerItem2("baguette", settings -> 

    //The Fire of the Hunt Update:

    public static final Item WITHER_BONE = registerItem2("wither_bone", settings -> new Item(settings));
    public static final Item SCORCHED_FLESHED = null;
    public static final Item ANCIENT_DEBRIS_NUGGET = registerItem2("ancient_debris_nugget", settings -> new Item(settings));

    public static final Item HUNT_ARMOR_TRIM_SMITHING_TEMPLATE = null;

    public static final Item PIGLIN_WARRIOR_SPAWN = registerItem2("piglin_warrior_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.PIGLIN_WARRIOR)));
    public static final Item BLAZING_INFERNO_SPAWN = registerItem2("blazing_inferno_spawn_egg", settings -> new SpawnEggItem( settings.spawnEgg(EntityFactory.HOVERING_INFERNO)));
    public static final Item SOUL_SKELETON_SPAWN = registerItem2("soul_skeleton_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SOUL_SKELETON)));

    // Tale of the Seas Update: (aka The Sea of Dead (Pirate Life) - On Stranger Tides)
        
    public static final Item ZEBRA_FISH_BUCKET =registerItem2("zebra_fish_bucket", settings ->  new ZebraFishBucketItem(EntityFactory.ZEBRA_FISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item ZEBRA_FISH =registerItem2("zebra_fish", settings ->  new Item((settings).food(FoodComponents.TROPICAL_FISH)));
    
    public static final Item ALLIGATOR_GAR_BUCKET =registerItem2("alligator_gar_bucket", settings ->  new EntityBucketItem(EntityFactory.ALLIGATOR_GAR, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item ALLIGATOR_GAR =registerItem2("alligator_gar", settings ->  new Item((settings).food(FOOD_ALLIGATOR_JAR)));
    public static final Item COOKED_ALLIGATOR_GAR =registerItem2("cooked_alligator_gar", settings ->  new Item((settings).food(FOOD_COOKED_ALLIGATOR_JAR)));
    
    public static final Item MACKEREL_BUCKET =registerItem2("mackerel_bucket", settings ->  new EntityBucketItem(EntityFactory.MACKEREL, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item MACKEREL = registerItem2("mackerel", settings ->  new Item((settings).food(FoodComponents.COD)));
    public static final Item COOKED_MACKEREL = registerItem2("cooked_mackerel", settings -> new Item((settings).food(FoodComponents.COOKED_COD)));
    
    public static final Item BASS_BUCKET = registerItem2("bass_bucket", settings -> new BassBucketItem(EntityFactory.BASS, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item BASS = registerItem2("bass", settings -> new Item((settings).food(FOOD_BASS)));
    public static final Item COOKED_BASS = registerItem2("cooked_bass", settings -> new Item((settings).food(FOOD_COOKED_BASS)));
    
    public static final Item FUR_TROUT_BUCKET = registerItem2("fur_trout_bucket", settings -> new BassBucketItem(EntityFactory.FURTROUT, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    
    public static final Item KOI_BUCKET = registerItem2("koi_bucket", settings -> new KoiBucketItem(EntityFactory.KOI, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item KOI = registerItem2("koi", settings -> new Item((settings).food(FoodComponents.TROPICAL_FISH)));
    
    public static final Item PERCH_BUCKET = registerItem2("perch_bucket", settings -> new PerchBucketItem(EntityFactory.PERCH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item PERCH = registerItem2("perch", settings -> new Item((settings).food(FoodComponents.COD)));
    public static final Item COOKED_PERCH = registerItem2("cooked_perch", settings -> new Item((settings).food(FoodComponents.COOKED_COD)));
    
    public static final Item MAHIMAHI_BUCKET = registerItem2("mahimahi_bucket", settings -> new MahiMahiBucketItem(EntityFactory.MAHIMAHI, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item MAHIMAHI = registerItem2("mahimahi", settings -> new Item((settings).food(FOOD_MAHIMAHI)));
    public static final Item COOKED_MAHIMAHI = registerItem2("cooked_mahimahi", settings -> new Item((settings).food(FOOD_COOKED_FOOD_MAHIMAHI)));
    
    public static final Item SNAPPER_BUCKET = registerItem2("snapper_bucket", settings -> new EntityBucketItem(EntityFactory.SNAPPER, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (settings).maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item SNAPPER = registerItem2("snapper", settings -> new Item((settings).food(FOOD_BASS)));
    public static final Item COOKED_SNAPPER = registerItem2("cooked_snapper", settings -> new Item((settings).food(FOOD_COOKED_BASS)));

    public static final Item RUM = registerItem2("bottle_of_rum", settings -> new BottleOfRumItem(settings));
    public static final Item CANNON_BALL_ITEM = registerItem2("cannon_ball", settings -> new CannonballItem(settings));
    public static final Item CANNON_ITEM = registerItem2("cannon", settings -> new CannonItem(settings));
    public static final Item PRISMERINE_ARROW = registerItem2("prismerine_arrow", settings -> new PrismerineArrowItem(settings));

    public static final Item SUNKEN_SKELETON_SPAWN = registerItem2("sunken_skeleton_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SUNKEN_SKELETON)));
    public static final Item SUNKEN_SKELETON2_SPAWN = registerItem2("sunken_skeleton2_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SUNKEN_SKELETON_CROSSBOW)));
    public static final Item SKELETON_PIRATE_CAPTAIN_SPAWN = registerItem2("skeleton_pirate_captain_spawn_egg", settings -> new SpawnEggItem( settings.spawnEgg(EntityFactory.SKELETON_PIRATE_CAPTAIN)));
    public static final Item METAL_SKELETON_SPAWN = registerItem2("skeleton_metal_spawn_egg", settings -> new SpawnEggItem( settings.spawnEgg(EntityFactory.METAL_SKELETON)));
    public static final Item ASHEN_SKELETON_SPAWN = null;

    public static final Item SUNKEN_DROWN_SPAWN = null;
    public static final Item ANCIENT_DROWN_SPAWN = registerItem2("ancient_drowned", settings -> new SpawnEggItem( settings.spawnEgg(EntityFactory.ANCIENT_DROWNED)));
    public static final Item ANCIENT_TRIDENT = registerItem2("ancient_trident", settings -> new AncientTridentItem((settings).rarity(Rarity.EPIC).maxDamage(250).attributeModifiers(AncientTridentItem.createAttributeModifiers()).component(DataComponentTypes.TOOL, AncientTridentItem.createToolComponent())));

    public static final Item PHANTOM_SKELETON_SPAWN = registerItem2("skeleton_phantom_spawn_egg", settings -> new SpawnEggItem( settings.spawnEgg(EntityFactory.PHANTOM_SKELETON)));
    public static final Item PHANTOM_PILLAGER_SPAWN = null;
    public static final Item PHANTOM_VINDICATOR_SPAWN = null;

    //public static final Item PHANTOM_TORCH_ITEM = registerRaw(new VerticallyAttachableBlockItem(BlockFactory.PHANTOM_TORCH, BlockFactory.PHANTOM_WALL_TORCH, settings, Direction.DOWN));


    public static final Item PIRATE_PILLAGER_SPAWN = null;
    public static final Item PIRATE_VINDICATOR_SPAWN = null;
    public static final Item PIRATE_CAPTAIN_SPAWN = null;
    public static final Item GIANT_CLAB_SPAWN = null;


    // Shields

    public static final Item STONE_SHIELD = registerItem2("stone_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));;
    public static final Item STONE_BLACKSTONE_SHIELD = registerItem2("stone_deepslate_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));; //Custom Nether Design
    public static final Item STONE_DEEPSLATE_SHIELD = registerItem2("stone_blackstone_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));; // Custom Cave Design


    public static final Item GOLDEN_SHIELD = registerItem2("golden_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));;
    public static final Item DIAMOND_SHIELD = registerItem2("diamond_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item BLAZING_SHIELD = null; // Basically Netherite Shield // Custom Model - This has a Shield Bash, set enemys on fire
    public static final Item AMYTHESTH_SHIELD = null; // Basically Cooper  Shield - Low Durrabilty //Custom Model,

    public static final Item WOODEN_OAK_SHIELD = registerItem2("wooden_oak_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT))); //All use Basic Design
    public static final Item WOODEN_SPRUCE_SHIELD = registerItem2("wooden_spruce_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_BIRCH_SHIELD = registerItem2("wooden_birch_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_JUNGLE_SHIELD = registerItem2("wooden_jungle_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_ACACIA_SHIELD = registerItem2("wooden_acacia_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_DARK_OAK_SHIELD = registerItem2("wooden_dark_oak_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_MANGROVE_SHIELD = registerItem2("wooden_mangrove_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_CHERRY_SHIELD = registerItem2("wooden_cherry_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    //public static final Item WOODEN_PALE_OAK_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    //public static final Item WOODEN_CRIMSON_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    //public static final Item WOODEN_WARPED_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_BAMBOO_SHIELD = registerItem2("wooden_bamboo_shield", settings -> new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));

    //Testing
    
    public static final Item BattleAxeTest = registerItem2("battle_test", settings -> new WeaponBattleAxeItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item GlaiveTest = registerItem2("glaive_test", settings -> new WeaponGlaiveItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item RapierTest = registerItem2("rapier_test", settings -> new WeaponRapierItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item WarHammerTest = registerItem2("warhammer_test", settings -> new WeaponWarhammerItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item ScytheTest = registerItem2("scythe_test", settings -> new WeaponScytheItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item HalberdTest = registerItem2("halberd_test", settings -> new WeaponHalberdItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item TwinDaggersTest = registerItem2("twindaggers_test", settings ->  new WeaponTwinDaggerItem(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item GlaiveTest2 = registerItem2("glaive_test22", settings -> new WeaponGlaiveItem22(ToolMaterial.IRON, settings.maxDamage(336)));
    public static final Item GreatSwrodTest = registerItem2("greatsword_test", settings -> new WeaponGreatSwordItem(ToolMaterial.IRON, settings.maxDamage(336)));
    //public static final Item ClaymoreTest = new WeaponClaymoreItem(ToolMaterial.IRON, settings.maxDamage(336));

    public static final Item WOODEN_BATTLE_AXE = registerItem2("wooden_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_BATTLE_AXE = registerItem2("stone_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_BATTLE_AXE = registerItem2("golden_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_BATTLE_AXE = registerItem2("copper_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_BATTLE_AXE = registerItem2("iron_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_BATTLE_AXE = registerItem2("diamond_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_BATTLE_AXE = registerItem2("netherite_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.NETHERITE, settings.fireproof()));

    // Weapon & Combat
    public static final Item WOODEN_CLAYMORE = registerItem2("wooden_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_CLAYMORE = registerItem2("stone_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_CLAYMORE = registerItem2("golden_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_CLAYMORE = registerItem2("copper_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_CLAYMORE = registerItem2("iron_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_CLAYMORE = registerItem2("diamond_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_CLAYMORE = registerItem2("netherite_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.NETHERITE, settings.fireproof()));

    public static final Item WOODEN_GLAIVE = registerItem2("wooden_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_GLAIVE = registerItem2("stone_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_GLAIVE = registerItem2("golden_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_GLAIVE = registerItem2("copper_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_GLAIVE = registerItem2("iron_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_GLAIVE = registerItem2("diamond_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_GLAIVE = registerItem2("netherite_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.NETHERITE, settings.fireproof()));

    public static final Item WOODEN_HAMMER = registerItem2("wooden_hammer", settings -> new WeaponHammerItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_HAMMER = registerItem2("stone_hammer", settings -> new WeaponHammerItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_HAMMER = registerItem2("golden_hammer", settings -> new WeaponHammerItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_HAMMER = registerItem2("copper_hammer", settings -> new WeaponHammerItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_HAMMER = registerItem2("iron_hammer", settings -> new WeaponHammerItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_HAMMER = registerItem2("diamond_hammer", settings -> new WeaponHammerItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_HAMMER = registerItem2("netherite_hammer", settings -> new WeaponHammerItem(ToolMaterial.NETHERITE, settings.fireproof()));

    public static final Item WOODEN_DAGGER = registerItem2("wooden_dagger", settings -> new DaggerItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_DAGGER = registerItem2("stone_dagger", settings -> new DaggerItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_DAGGER = registerItem2("golden_dagger", settings -> new DaggerItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_DAGGER = registerItem2("copper_dagger", settings -> new DaggerItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_DAGGER = registerItem2("iron_dagger", settings -> new DaggerItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_DAGGER = registerItem2("diamond_dagger", settings -> new DaggerItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_DAGGER = registerItem2("netherite_dagger", settings -> new DaggerItem(ToolMaterial.NETHERITE, settings.fireproof()));

    public static final Item WOODEN_LONG_SWORD = registerItem2("wooden_long_sword", settings -> new LongSwordItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_LONG_SWORD = registerItem2("stone_long_sword", settings -> new LongSwordItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_LONG_SWORD = registerItem2("golden_long_sword", settings -> new LongSwordItem(ToolMaterial.GOLD, settings));
    // public static final Item COPPER_LONG_SWORD = registerItem2("copper_long_sword", settings -> new LongSwordItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_LONG_SWORD = registerItem2("iron_long_sword", settings -> new LongSwordItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_LONG_SWORD = registerItem2("diamond_long_sword", settings -> new LongSwordItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_LONG_SWORD = registerItem2("netherite_long_sword", settings -> new LongSwordItem(ToolMaterial.NETHERITE, settings.fireproof()));


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




    //public static final Item REDWOOD_SIGN = new SignItem(settings.maxCount(16), BlockFactoryUpt.callBlock("redwood_sign"), BlockFactoryUpt.callBlock("redwood_wall_sign"));
    //public static final Item REDWOOD_HANGING_SIGN = new HangingSignItem(BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"), settings.maxCount(16));

    public static void onInitialize() {

        //Development - Work in Progress
        /*
        registerItem3("battle_test", BattleAxeTest);
        registerItem3("glaive_test", GlaiveTest);
        registerItem3("rapier_test", RapierTest);
        registerItem3("warhammer_test", WarHammerTest);
        registerItem3("scythe_test", ScytheTest);
        registerItem3("halberd_test", HalberdTest);
        registerItem3("twindaggers_test", TwinDaggersTest);
        registerItem3("glaive_test22", GlaiveTest2);
        registerItem3("greatsword_test", GreatSwrodTest);

        registerItem3("wooden_battle_axe", new WeaponBattleAxeItem(ToolMaterial.WOOD, settings));
        registerItem3("stone_battle_axe", new WeaponBattleAxeItem(ToolMaterial.STONE, settings));
        registerItem3("golden_battle_axe", new WeaponBattleAxeItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_battle_axe", new WeaponBattleAxeItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_battle_axe", new WeaponBattleAxeItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_battle_axe", new WeaponBattleAxeItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_battle_axe", new WeaponBattleAxeItem(ToolMaterial.NETHERITE, settings.fireproof()));

        // Weapon & Combat

        registerItem3("wooden_claymore", new WeaponClaymoreItem(ToolMaterial.WOOD, settings));
        registerItem3("stone_claymore", new WeaponClaymoreItem(ToolMaterial.STONE, settings));
        registerItem3("golden_claymore", new WeaponClaymoreItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_claymore", new WeaponClaymoreItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_claymore", new WeaponClaymoreItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_claymore", new WeaponClaymoreItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_claymore", new WeaponClaymoreItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItem3("wooden_glaive", new WeaponGlaiveItem(ToolMaterial.WOOD, settings)); //Throwable??
        registerItem3("stone_glaive", new WeaponGlaiveItem(ToolMaterial.STONE, settings));
        registerItem3("golden_glaive", new WeaponGlaiveItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_glaive", new WeaponGlaiveItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_glaive", new WeaponGlaiveItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_glaive", new WeaponGlaiveItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_glaive", new WeaponGlaiveItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItem3("wooden_hammer", new WeaponHammerItem(ToolMaterial.WOOD, settings));
        registerItem3("stone_hammer", new WeaponHammerItem(ToolMaterial.STONE, settings));
        registerItem3("golden_hammer", new WeaponHammerItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_hammer", new WeaponHammerItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_hammer", new WeaponHammerItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_hammer", new WeaponHammerItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_hammer", new WeaponHammerItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItem3("wooden_dagger", new DaggerItem(ToolMaterial.WOOD, settings));
        registerItem3("stone_dagger", new DaggerItem(ToolMaterial.STONE, settings));
        registerItem3("golden_dagger", new DaggerItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_dagger", new DaggerItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_dagger", new DaggerItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_dagger", new DaggerItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_dagger", new DaggerItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItem3("wooden_long_sword", new LongSwordItem(ToolMaterial.WOOD, settings));
        registerItem3("stone_long_sword", new LongSwordItem(ToolMaterial.STONE, settings));
        registerItem3("golden_long_sword", new LongSwordItem(ToolMaterial.GOLD, settings));
        //registerItem3("copper_long_sword", new LongSwordItem(ToolMaterial.COPPER, settings));
        registerItem3("iron_long_sword", new LongSwordItem(ToolMaterial.IRON, settings));
        registerItem3("diamond_long_sword", new LongSwordItem(ToolMaterial.DIAMOND, settings));
        registerItem3("netherite_long_sword", new LongSwordItem(ToolMaterial.NETHERITE, settings.fireproof()));

        //Launch - Welcome to Stupidity

        registerItem3("butterfly_jar", BUTTERFLY_JAR);
        registerItem3("butterfly_spawn_egg", BUTTERFLY_SPAWN_EGG);
        //registerItem3("raw_marshmellow", MARSHMELLOW_RAW);
        //registerItem3("toasted_marshmellow", MARSHMELLOW_TOASTED);
        //registerItem3("baguette", BAGGUETTE);
        
        registerItem3("zombie_lobber_spawn_egg", ZOMBIE_LOBBER_SPAWN_EGG);
        registerItem3("zombie_scorched_spawn_egg", ZOMBIE_SCORCHED_SPAWN_EGG);
        registerItem3("zombie_frostbite_spawn_egg", ZOMBIE_FROSTBITE_SPAWN_EGG);
        registerItem3("zombie_slimed_spawn_egg", ZOMBIE_SLIMED_SPAWN_EGG);
        registerItem3("armored_pillager_spawn_egg", ARMORED_PILLAGER_SPAWN_EGG);

        //The Fire of the Hunt Update:

        registerItem3("ancient_debris_nugget", ANCIENT_DEBRIS_NUGGET);
        registerItem3("wither_bone", WITHER_BONE);
        registerItem3("bottle_of_rum", RUM);
        registerItem3("cannon_ball", CANNON_BALL_ITEM);
        registerItem3("cannon", CANNON_ITEM);
        registerItem3("prismerine_arrow", PRISMERINE_ARROW);

        registerItem3("piglin_warrior_spawn_egg", PIGLIN_WARRIOR_SPAWN);
        registerItem3("blazing_inferno_spawn_egg", BLAZING_INFERNO_SPAWN);
        registerItem3("soul_skeleton_spawn_egg", SOUL_SKELETON_SPAWN);

        // Tale of the Seas Update: (aka The Sea of Dead (Pirate Life) - On Stranger Tides)

        registerItem3("zebra_fish_bucket", ZEBRA_FISH_BUCKET);
        registerItem3("zebra_fish", ZEBRA_FISH);

        registerItem3("alligator_gar_bucket", ALLIGATOR_GAR_BUCKET);
        registerItem3("alligator_gar", ALLIGATOR_GAR);
        registerItem3("cooked_alligator_gar", COOKED_ALLIGATOR_GAR);

        registerItem3("mackerel_bucket", MACKEREL_BUCKET);
        registerItem3("mackerel", MACKEREL);
        registerItem3("cooked_mackerel", COOKED_MACKEREL);

        registerItem3("bass_bucket", BASS_BUCKET);
        registerItem3("bass", BASS);
        registerItem3("cooked_bass", COOKED_BASS);

        registerItem3("fur_trout_bucket", FUR_TROUT_BUCKET);
        //registerItem3("fur_trout", FUR_TROUT);
        //registerItem3("cooked_fur_trout", COOKED_FUR_TROUT);

        registerItem3("koi_bucket", KOI_BUCKET);
        registerItem3("koi", KOI);

        registerItem3("perch_bucket", PERCH_BUCKET);
        registerItem3("perch", PERCH);
        registerItem3("cooked_perch", COOKED_PERCH);

        registerItem3("snapper_bucket", SNAPPER_BUCKET);
        registerItem3("snapper", SNAPPER);
        registerItem3("cooked_snapper", COOKED_SNAPPER);

        registerItem3("mahimahi_bucket", MAHIMAHI_BUCKET);
        registerItem3("mahimahi", MAHIMAHI);
        registerItem3("cooked_mahimahi", COOKED_MAHIMAHI);


        registerItem3("ancient_trident", ANCIENT_TRIDENT);
        registerItem3("ancient_drowned", ANCIENT_DROWN_SPAWN);

        registerItem3("sunken_skeleton_spawn_egg", SUNKEN_SKELETON_SPAWN);
        registerItem3("sunken_skeleton2_spawn_egg", SUNKEN_SKELETON2_SPAWN);
        registerItem3("skeleton_pirate_captain_spawn_egg", SKELETON_PIRATE_CAPTAIN_SPAWN);

        registerItem3("skeleton_metal_spawn_egg", METAL_SKELETON_SPAWN);
        registerItem3("skeleton_phantom_spawn_egg", PHANTOM_SKELETON_SPAWN);

        // Shields

        registerItem3("wooden_oak_shield", WOODEN_OAK_SHIELD);
        registerItem3("wooden_spruce_shield", WOODEN_SPRUCE_SHIELD);
        registerItem3("wooden_birch_shield", WOODEN_BIRCH_SHIELD);
        registerItem3("wooden_jungle_shield", WOODEN_JUNGLE_SHIELD);
        registerItem3("wooden_acacia_shield", WOODEN_ACACIA_SHIELD);
        registerItem3("wooden_dark_oak_shield", WOODEN_DARK_OAK_SHIELD);
        registerItem3("wooden_mangrove_shield", WOODEN_MANGROVE_SHIELD);
        registerItem3("wooden_cherry_shield", WOODEN_CHERRY_SHIELD);
        registerItem3("wooden_bamboo_shield", WOODEN_BAMBOO_SHIELD);

        registerItem3("stone_shield", STONE_SHIELD);
        registerItem3("stone_deepslate_shield", STONE_DEEPSLATE_SHIELD);
        registerItem3("stone_blackstone_shield", STONE_BLACKSTONE_SHIELD);

        registerItem3("golden_shield", GOLDEN_SHIELD);
        registerItem3("diamond_shield", DIAMOND_SHIELD);
         */
        onInitializeMobEggs();

        // Register Multiple Block Models with One Item (IE. Signs, Hanging Signs)
        //registerItem3("redwood_sign", REDWOOD_SIGN);
        //registerItem3("redwood_hanging_sign", REDWOOD_HANGING_SIGN);

    }

    public static void BlockItem(){
        //registerItem3("redwood_sign", new SignItem(settings.maxCount(16), BlockFactoryUpt.callBlock("redwood_sign"), BlockFactoryUpt.callBlock("redwood_wall_sign")));
        //registerItem3("redwood_hanging_sign", new HangingSignItem(BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"), settings.maxCount(16)));
    }

    public static void onInitializeMobEggs() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.addAfter(
                ZOMBIE_LOBBER_SPAWN_EGG,
                ZOMBIE_SCORCHED_SPAWN_EGG,
                PIGLIN_WARRIOR_SPAWN,
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

    /*public static <I extends Item> I registerItem3(String name, I item) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        System.out.println("Item: " + id.toString());
        BlockFactory.ItemList.put(id, item);
        Registry.register(Registries.ITEM, id, item);

        ModGroup.addToDefault(name);

        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
        }

        return item;
    }*/

    public static Item registerBlockItem3(Block block, String id, ItemGroup group) {
        return Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, id), new BlockItem(block, new Item.Settings()));
    }

    public static <I extends BlockItem> I registerBlockItems(String name, I item) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        if (EntStupidStuff.DEV_MODE)
            System.out.println("Item: " + id.toString());
        BlockFactory.ItemList.put(id, item);

        Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Registries.BLOCK.getId(item.getBlock())), item);

        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
        }

        return item;
    }



    public static Item callItem(String name) {
        //return BlockFactory.ItemList.get(Identifier.of(EntStupidStuff.MOD_ID, name));
        try {
            return BlockFactory.ItemList.get(Identifier.of(EntStupidStuff.MOD_ID, name));
        } catch(Exception e) {
            System.out.println("An Error was called in callItem()... Tried to call: " + name);
            return null;
        }
    }
    
    public static Item registerRaw(BlockItem item) {
		return registerRaw(item.getBlock(), item);
	}

	public static Item registerRaw(Block block, Item item) {
		return registerRaw(Registries.BLOCK.getId(block), item);
	}

    public static Item registerRaw(Identifier id, Item item) {
		return registerRaw(RegistryKey.of(Registries.ITEM.getKey(), id), item);
	}


    public static Item registerRaw(RegistryKey<Item> key, Item item) {
		if (item instanceof BlockItem) {
			((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
		}

		return Registry.register(Registries.ITEM, key, item);
	}

    // NEW //////////////////////////////////////////

    private static Item registerItem2(String name, Function<Item.Settings, Item> function) {
        return register(keyOf(name), function, new Item.Settings(), name);
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings, String name) {
		Item item = (Item)factory.apply(settings.registryKey(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
		}
        System.out.println("Items: " + Identifier.of(EntStupidStuff.MOD_ID, name));
        BlockFactory.ItemList.put(Identifier.of(EntStupidStuff.MOD_ID, name), item);
        ModGroup.addToDefault(name);


		return Registry.register(Registries.ITEM, key, item);
	}

    private static RegistryKey<Item> keyOf(String id) {
		return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EntStupidStuff.MOD_ID, id));
	}

    //old to new

    public static <I extends Item> I registerItem3(String name, I item) {
        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
            return item;
        }

        // Create RegistryKey
        RegistryKey<Item> key = keyOf(name);

        // Apply registry key to item settings if possible
        Item.Settings settings = new Item.Settings();
        try {
            settings.registryKey(key);
        } catch (Exception ignored) {}

        // If item is a BlockItem, register it properly with appendBlocks
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        // Register in the vanilla registry
        Registry.register(Registries.ITEM, key, item);

        // Put in custom item list
        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        BlockFactory.ItemList.put(id, item);

        // Add to default mod group
        //ModGroup.addToDefault(name);

        System.out.println("Item: " + id.toString());
        return item;
    }


}
