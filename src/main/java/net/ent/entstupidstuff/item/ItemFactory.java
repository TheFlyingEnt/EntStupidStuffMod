package net.ent.entstupidstuff.item;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.base.AncientTridentItem;
import net.ent.entstupidstuff.item.base.BassBucketItem;
import net.ent.entstupidstuff.item.base.BottleOfRumItem;
import net.ent.entstupidstuff.item.base.ButterflyJarItem;
import net.ent.entstupidstuff.item.base.CannonballItem;
import net.ent.entstupidstuff.item.base.FlintlockPistolItem;
import net.ent.entstupidstuff.item.base.DoubleBarrelCrossbowItem;
import net.ent.entstupidstuff.item.base.DummyCrossbow;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.item.base.KoiBucketItem;
import net.ent.entstupidstuff.item.base.MahiMahiBucketItem;
import net.ent.entstupidstuff.item.base.PerchBucketItem;
import net.ent.entstupidstuff.item.base.PrismerineArrowItem;
import net.ent.entstupidstuff.item.base.WeaponBattleAxeItem;
import net.ent.entstupidstuff.item.base.WeaponClaymoreItem;
import net.ent.entstupidstuff.item.base.WeaponDaggerNew;
import net.ent.entstupidstuff.item.base.WeaponGlaiveItem;
import net.ent.entstupidstuff.item.base.WeaponHammerItem;
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
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.material.Fluids;

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
    public static final Map<ResourceLocation, Item> ModelList = new LinkedHashMap<>();

    //Food Comp

    public static final FoodProperties FOOD_BASS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties FOOD_COOKED_BASS = new FoodProperties.Builder().nutrition(7).saturationModifier(0.8F).build();

    public static final FoodProperties FOOD_ALLIGATOR_JAR = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties FOOD_COOKED_ALLIGATOR_JAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F).build();

    public static final FoodProperties FOOD_MAHIMAHI = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties FOOD_COOKED_FOOD_MAHIMAHI = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();

    //Launch - Welcome to Stupidity
    
    public static final Item MARSHMELLOW_RAW = registerItem("raw_marshmellow", settings -> new Item(settings.usingConvertsTo(Items.STICK).stacksTo(16).food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().build())));
    public static final Item MARSHMELLOW_TOASTED = registerItem("toasted_marshmellow", settings -> new Item(settings.usingConvertsTo(Items.STICK).stacksTo(16).food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().build())));
    public static final Item BAGGUETTE = registerItem("baguette", settings -> new Item(settings.stacksTo(16).food(Foods.BREAD).attributes(WeaponUpdatedItem.createAttributeModifiers(ToolMaterial.WOOD, 1  + ToolMaterial.WOOD.attackDamageBonus(), 2.6f, 1, 0, 0))));

    //public static final Item MARSHMELLOW_RAW = new Item(settings.maxCount(16).food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().alwaysEdible().build()));
    //public static final Item MARSHMELLOW_TOASTED = new Item(settings.maxCount(16).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).alwaysEdible().alwaysEdible().build()));
    //public static final Item BAGGUETTE = new Item(settings.maxCount(16).food(FoodComponents.BREAD).attributeModifiers(WeaponUpdatedItem.createAttributeModifiers(ToolMaterial.WOOD, 1  + ToolMaterial.WOOD.attackDamageBonus(), 2.6f, 1, 0, 0)));
    
    public static final Item BUTTERFLY_JAR = registerItem("butterfly_jar", settings -> new ButterflyJarItem(EntityFactory.BUTTERFLY, SoundEvents.BOTTLE_FILL, settings));
    public static final Item BUTTERFLY_SPAWN_EGG = registerItem("butterfly_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.BUTTERFLY)));
    
    public static final Item ZOMBIE_LOBBER_SPAWN_EGG = registerItem("zombie_lobber_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_LOBBER)));
    public static final Item ZOMBIE_SCORCHED_SPAWN_EGG = registerItem("zombie_scorched_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_SCORCHED)));
    public static final Item ZOMBIE_FROSTBITE_SPAWN_EGG = registerItem("zombie_frostbite_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_FROSTBITTEN)));
    public static final Item ZOMBIE_SLIMED_SPAWN_EGG = registerItem("zombie_slimed_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZOMBIE_SLIMED)));
    public static final Item ARMORED_PILLAGER_SPAWN_EGG = registerItem("armored_pillager_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ARMORED_PILLAGER)));


    //public static final Item NOODEL_BOWL = new Item(settings.maxCount(1).food(FoodComponents.RABBIT_STEW));
    //public static final Item APPLE_PIE = new Item(settings.food(FoodComponents.PUMPKIN_PIE));

    //public static final Item MUSHROOM_GLOWING = null;
    //public static final Item MAGIC_DUST = null;
    //public static final Item VENOM_GLOB = null;

    //public static final Item FLINT_LOCK_BOW = null;
    //public static final Item CLAB_GEM = null; //Alterive to Quarts???
    //public static final Item SUNKEN_ARMOR_TRIM_SMITHING_TEMPLATE = null;


    //registerItem("baguette", settings -> 

    //The Fire of the Hunt Update:

    public static final Item WITHER_BONE = registerItem("wither_bone", settings -> new Item(settings));
    public static final Item SCORCHED_FLESHED = null;
    public static final Item ANCIENT_DEBRIS_NUGGET = registerItem("ancient_debris_nugget", settings -> new Item(settings));

    public static final Item HUNT_ARMOR_TRIM_SMITHING_TEMPLATE = null;

    public static final Item PIGLIN_WARRIOR_SPAWN = registerItem("piglin_warrior_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.PIGLIN_WARRIOR)));
    public static final Item BLAZING_INFERNO_SPAWN = registerItem("blazing_inferno_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.HOVERING_INFERNO)));
    public static final Item SOUL_SKELETON_SPAWN = registerItem("soul_skeleton_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SOUL_SKELETON)));

    // Tale of the Seas Update: (aka The Sea of Dead (Pirate Life) - On Stranger Tides)
        
    public static final Item ZEBRA_FISH_BUCKET =registerItem("zebra_fish_bucket", settings ->  new ZebraFishBucketItem(EntityFactory.ZEBRA_FISH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item ZEBRA_FISH =registerItem("zebra_fish", settings ->  new Item((settings).food(Foods.TROPICAL_FISH)));
    public static final Item ZEBRA_FISH_SPAWN_EGG = registerItem("zebra_fish_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ZEBRA_FISH)));
    
    public static final Item ALLIGATOR_GAR_BUCKET =registerItem("alligator_gar_bucket", settings ->  new MobBucketItem(EntityFactory.ALLIGATOR_GAR, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item ALLIGATOR_GAR =registerItem("alligator_gar", settings ->  new Item((settings).food(FOOD_ALLIGATOR_JAR)));
    public static final Item COOKED_ALLIGATOR_GAR =registerItem("cooked_alligator_gar", settings ->  new Item((settings).food(FOOD_COOKED_ALLIGATOR_JAR)));
    public static final Item ALLIGATOR_GAR_SPAWN_EGG = registerItem("alligator_gar_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.ALLIGATOR_GAR)));

    public static final Item MACKEREL_BUCKET =registerItem("mackerel_bucket", settings ->  new MobBucketItem(EntityFactory.MACKEREL, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item MACKEREL = registerItem("mackerel", settings ->  new Item((settings).food(Foods.COD)));
    public static final Item COOKED_MACKEREL = registerItem("cooked_mackerel", settings -> new Item((settings).food(Foods.COOKED_COD)));
    public static final Item MACKEREL_SPAWN_EGG = registerItem("mackerel_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.MACKEREL)));
    
    public static final Item BASS_BUCKET = registerItem("bass_bucket", settings -> new BassBucketItem(EntityFactory.BASS, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item BASS = registerItem("bass", settings -> new Item((settings).food(FOOD_BASS)));
    public static final Item COOKED_BASS = registerItem("cooked_bass", settings -> new Item((settings).food(FOOD_COOKED_BASS)));
    public static final Item BASS_SPAWN_EGG = registerItem("bass_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.BASS)));
    
    public static final Item FUR_TROUT_BUCKET = registerItem("fur_trout_bucket", settings -> new BassBucketItem(EntityFactory.FURTROUT, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item FUR_TROUT_SPAWN_EGG = registerItem("fur_trout_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.FURTROUT)));

    public static final Item KOI_BUCKET = registerItem("koi_bucket", settings -> new KoiBucketItem(EntityFactory.KOI, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item KOI = registerItem("koi", settings -> new Item((settings).food(Foods.TROPICAL_FISH)));
    public static final Item KOI_SPAWN_EGG = registerItem("koi_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.KOI)));
    
    public static final Item PERCH_BUCKET = registerItem("perch_bucket", settings -> new PerchBucketItem(EntityFactory.PERCH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item PERCH = registerItem("perch", settings -> new Item((settings).food(Foods.COD)));
    public static final Item COOKED_PERCH = registerItem("cooked_perch", settings -> new Item((settings).food(Foods.COOKED_COD)));
    public static final Item PERCH_SPAWN_EGG = registerItem("perch_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.PERCH)));
    
    public static final Item MAHIMAHI_BUCKET = registerItem("mahimahi_bucket", settings -> new MahiMahiBucketItem(EntityFactory.MAHIMAHI, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item MAHIMAHI = registerItem("mahimahi", settings -> new Item((settings).food(FOOD_MAHIMAHI)));
    public static final Item COOKED_MAHIMAHI = registerItem("cooked_mahimahi", settings -> new Item((settings).food(FOOD_COOKED_FOOD_MAHIMAHI)));
    public static final Item MAHIMAHI_SPAWN_EGG = registerItem("mahimahi_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.MAHIMAHI)));
    
    public static final Item SNAPPER_BUCKET = registerItem("snapper_bucket", settings -> new MobBucketItem(EntityFactory.SNAPPER, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (settings).stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    public static final Item SNAPPER = registerItem("snapper", settings -> new Item((settings).food(FOOD_BASS)));
    public static final Item COOKED_SNAPPER = registerItem("cooked_snapper", settings -> new Item((settings).food(FOOD_COOKED_BASS)));
    public static final Item SNAPPER_SPAWN_EGG = registerItem("snapper_spawn_egg", settings ->  new SpawnEggItem(settings.spawnEgg(EntityFactory.SNAPPER)));

    public static final Item RUM = registerItem("bottle_of_rum", settings -> new BottleOfRumItem(settings));
    public static final Item CANNON_BALL_ITEM = registerItem("cannon_ball", settings -> new CannonballItem(settings.component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));
    public static final Item CANNON_ITEM = registerItem("cannon", settings -> new CannonItem(settings));
    public static final Item PRISMERINE_ARROW = registerItem("prismerine_arrow", settings -> new PrismerineArrowItem(settings));
    public static final Item FLINTLOCK_CROSSBOW = registerItem("flintlock_crossbow", settings -> new FlintlockPistolItem(settings)); //flintlock
    public static final Item DOUBLE_BARREL_CROSSBOW = registerItem("double_barrel_crossbow", settings -> new DoubleBarrelCrossbowItem(settings)); //flintlock_two
    public static final Item DUMMY_CROSSBOW = registerItem("dummy_crossbow", settings -> new DummyCrossbow(settings));


    public static final Item SUNKEN_SKELETON_SPAWN = registerItem("sunken_skeleton_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SUNKEN_SKELETON)));
    public static final Item SUNKEN_SKELETON2_SPAWN = registerItem("sunken_skeleton2_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SUNKEN_SKELETON_CROSSBOW)));
    public static final Item SKELETON_PIRATE_CAPTAIN_SPAWN = registerItem("skeleton_pirate_captain_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.SKELETON_PIRATE_CAPTAIN)));
    public static final Item METAL_SKELETON_SPAWN = registerItem("skeleton_metal_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.METAL_SKELETON)));
    public static final Item ASHEN_SKELETON_SPAWN = null;

    public static final Item SUNKEN_DROWN_SPAWN = null;
    public static final Item ANCIENT_DROWN_SPAWN = registerItem("ancient_drowned_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.ANCIENT_DROWNED)));
    public static final Item ANCIENT_TRIDENT = registerItem("ancient_trident", settings -> new AncientTridentItem((settings).rarity(Rarity.EPIC).durability(250).attributes(AncientTridentItem.createAttributes()).component(DataComponents.TOOL, AncientTridentItem.createToolProperties())));

    public static final Item PHANTOM_SKELETON_SPAWN = registerItem("skeleton_phantom_spawn_egg", settings -> new SpawnEggItem(settings.spawnEgg(EntityFactory.PHANTOM_SKELETON)));
    public static final Item PHANTOM_PILLAGER_SPAWN = null;
    public static final Item PHANTOM_VINDICATOR_SPAWN = null;

    //public static final Item PHANTOM_TORCH_ITEM = registerRaw(new VerticallyAttachableBlockItem(BlockFactory.PHANTOM_TORCH, BlockFactory.PHANTOM_WALL_TORCH, settings, Direction.DOWN));


    public static final Item PIRATE_PILLAGER_SPAWN = null;
    public static final Item PIRATE_VINDICATOR_SPAWN = null;
    public static final Item PIRATE_CAPTAIN_SPAWN = null;
    public static final Item GIANT_CLAB_SPAWN = null;


    // Shields

    public static final Item STONE_SHIELD = registerItem("stone_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY).equippableUnswappable(EquipmentSlot.OFFHAND)
			.component(
				DataComponents.BLOCKS_ATTACKS,
				new BlocksAttacks(
					0.25F,
					1.0F,
					List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
					new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
					Optional.of(DamageTypeTags.BYPASSES_SHIELD),
					Optional.of(SoundEvents.SHIELD_BLOCK),
					Optional.of(SoundEvents.SHIELD_BREAK)
				)
			)
			.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)));;
    public static final Item STONE_BLACKSTONE_SHIELD = registerItem("stone_deepslate_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));; //Custom Nether Design
    public static final Item STONE_DEEPSLATE_SHIELD = registerItem("stone_blackstone_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));; // Custom Cave Design


    public static final Item GOLDEN_SHIELD = registerItem("golden_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY).equippableUnswappable(EquipmentSlot.OFFHAND)
			.component(
				DataComponents.BLOCKS_ATTACKS,
				new BlocksAttacks(
					0.25F,
					1.0F,
					List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
					new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
					Optional.of(DamageTypeTags.BYPASSES_SHIELD),
					Optional.of(SoundEvents.SHIELD_BLOCK),
					Optional.of(SoundEvents.SHIELD_BREAK)
				)
			)
			.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)));;
    public static final Item DIAMOND_SHIELD = registerItem("diamond_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY).equippableUnswappable(EquipmentSlot.OFFHAND)
			.component(
				DataComponents.BLOCKS_ATTACKS,
				new BlocksAttacks(
					0.25F,
					1.0F,
					List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
					new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
					Optional.of(DamageTypeTags.BYPASSES_SHIELD),
					Optional.of(SoundEvents.SHIELD_BLOCK),
					Optional.of(SoundEvents.SHIELD_BREAK)
				)
			)
			.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)));
    public static final Item BLAZING_SHIELD = null; // Basically Netherite Shield // Custom Model - This has a Shield Bash, set enemys on fire
    public static final Item AMYTHESTH_SHIELD = null; // Basically Cooper  Shield - Low Durrabilty //Custom Model,

    public static final Item WOODEN_OAK_SHIELD = registerItem("wooden_oak_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY))); //All use Basic Design
    public static final Item WOODEN_SPRUCE_SHIELD = registerItem("wooden_spruce_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_BIRCH_SHIELD = registerItem("wooden_birch_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_JUNGLE_SHIELD = registerItem("wooden_jungle_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_ACACIA_SHIELD = registerItem("wooden_acacia_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_DARK_OAK_SHIELD = registerItem("wooden_dark_oak_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_MANGROVE_SHIELD = registerItem("wooden_mangrove_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    public static final Item WOODEN_CHERRY_SHIELD = registerItem("wooden_cherry_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
    //public static final Item WOODEN_PALE_OAK_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    //public static final Item WOODEN_CRIMSON_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    //public static final Item WOODEN_WARPED_SHIELD = new ShieldItem(settings.maxDamage(336).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)));
    public static final Item WOODEN_BAMBOO_SHIELD = registerItem("wooden_bamboo_shield", settings -> new ShieldItem(settings.durability(336).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));

    //Testing
    
    public static final Item BattleAxeTest = registerItem("battle_test", settings -> new WeaponBattleAxeItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item GlaiveTest = registerItem("glaive_test", settings -> new WeaponGlaiveItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item RapierTest = registerItem("rapier_test", settings -> new WeaponRapierItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item WarHammerTest = registerItem("warhammer_test", settings -> new WeaponWarhammerItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item ScytheTest = registerItem("scythe_test", settings -> new WeaponScytheItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item HalberdTest = registerItem("halberd_test", settings -> new WeaponHalberdItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item TwinDaggersTest = registerItem("twindaggers_test", settings ->  new WeaponTwinDaggerItem(ToolMaterial.IRON, settings.durability(336)));
    public static final Item GlaiveTest2 = registerItem("glaive_test22", settings -> new WeaponGlaiveItem22(ToolMaterial.IRON, settings.durability(336)));
    public static final Item GreatSwrodTest = registerItem("greatsword_test", settings -> new WeaponGreatSwordItem(ToolMaterial.IRON, settings.durability(336)));
    //public static final Item ClaymoreTest = new WeaponClaymoreItem(ToolMaterial.IRON, settings.maxDamage(336));

    public static final Item WOODEN_BATTLE_AXE = registerItem("wooden_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_BATTLE_AXE = registerItem("stone_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_BATTLE_AXE = registerItem("golden_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.GOLD, settings));
    public static final Item COPPER_BATTLE_AXE = registerItem("copper_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_BATTLE_AXE = registerItem("iron_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_BATTLE_AXE = registerItem("diamond_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_BATTLE_AXE = registerItem("netherite_battle_axe", settings -> new WeaponBattleAxeItem(ToolMaterial.NETHERITE, settings.fireResistant()));

    // Weapon & Combat
    public static final Item WOODEN_CLAYMORE = registerItem("wooden_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_CLAYMORE = registerItem("stone_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_CLAYMORE = registerItem("golden_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.GOLD, settings));
    public static final Item COPPER_CLAYMORE = registerItem("copper_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_CLAYMORE = registerItem("iron_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_CLAYMORE = registerItem("diamond_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_CLAYMORE = registerItem("netherite_claymore", settings -> new WeaponClaymoreItem(ToolMaterial.NETHERITE, settings.fireResistant()));

    public static final Item WOODEN_GLAIVE = registerItem("wooden_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_GLAIVE = registerItem("stone_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_GLAIVE = registerItem("golden_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.GOLD, settings));
    public static final Item COPPER_GLAIVE = registerItem("copper_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_GLAIVE = registerItem("iron_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_GLAIVE = registerItem("diamond_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_GLAIVE = registerItem("netherite_glaive", settings -> new WeaponGlaiveItem(ToolMaterial.NETHERITE, settings.fireResistant()));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", settings -> new WeaponHammerItem(ToolMaterial.WOOD, settings.stacksTo(1)));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", settings -> new WeaponHammerItem(ToolMaterial.STONE, settings.stacksTo(1)));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", settings -> new WeaponHammerItem(ToolMaterial.GOLD, settings.stacksTo(1)));
    public static final Item COPPER_HAMMER = registerItem("copper_hammer", settings -> new WeaponHammerItem(ToolMaterial.COPPER, settings.stacksTo(1)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", settings -> new WeaponHammerItem(ToolMaterial.IRON, settings.stacksTo(1)));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", settings -> new WeaponHammerItem(ToolMaterial.DIAMOND, settings.stacksTo(1)));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", settings -> new WeaponHammerItem(ToolMaterial.NETHERITE, settings.fireResistant().stacksTo(1)));

    public static final Item WOODEN_DAGGER = registerItem("wooden_dagger", settings -> new DaggerItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_DAGGER = registerItem("stone_dagger", settings -> new DaggerItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_DAGGER = registerItem("golden_dagger", settings -> new DaggerItem(ToolMaterial.GOLD, settings));
    public static final Item COPPER_DAGGER = registerItem("copper_dagger", settings -> new DaggerItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_DAGGER = registerItem("iron_dagger", settings -> new DaggerItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_DAGGER = registerItem("diamond_dagger", settings -> new DaggerItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_DAGGER = registerItem("netherite_dagger", settings -> new WeaponDaggerNew(settings.fireResistant().sword(ToolMaterial.WOOD, 3.0F, -2.4F)));

    public static final Item WOODEN_LONG_SWORD = registerItem("wooden_long_sword", settings -> new LongSwordItem(ToolMaterial.WOOD, settings));
    public static final Item STONE_LONG_SWORD = registerItem("stone_long_sword", settings -> new LongSwordItem(ToolMaterial.STONE, settings));
    public static final Item GOLDEN_LONG_SWORD = registerItem("golden_long_sword", settings -> new LongSwordItem(ToolMaterial.GOLD, settings));
    public static final Item COPPER_LONG_SWORD = registerItem("copper_long_sword", settings -> new LongSwordItem(ToolMaterial.COPPER, settings));
    public static final Item IRON_LONG_SWORD = registerItem("iron_long_sword", settings -> new LongSwordItem(ToolMaterial.IRON, settings));
    public static final Item DIAMOND_LONG_SWORD = registerItem("diamond_long_sword", settings -> new LongSwordItem(ToolMaterial.DIAMOND, settings));
    public static final Item NETHERITE_LONG_SWORD = registerItem("netherite_long_sword", settings -> new LongSwordItem(ToolMaterial.NETHERITE, settings.fireResistant()));


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
        registerItemLegacy("battle_test", BattleAxeTest);
        registerItemLegacy("glaive_test", GlaiveTest);
        registerItemLegacy("rapier_test", RapierTest);
        registerItemLegacy("warhammer_test", WarHammerTest);
        registerItemLegacy("scythe_test", ScytheTest);
        registerItemLegacy("halberd_test", HalberdTest);
        registerItemLegacy("twindaggers_test", TwinDaggersTest);
        registerItemLegacy("glaive_test22", GlaiveTest2);
        registerItemLegacy("greatsword_test", GreatSwrodTest);

        registerItemLegacy("wooden_battle_axe", new WeaponBattleAxeItem(ToolMaterial.WOOD, settings));
        registerItemLegacy("stone_battle_axe", new WeaponBattleAxeItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_battle_axe", new WeaponBattleAxeItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_battle_axe", new WeaponBattleAxeItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_battle_axe", new WeaponBattleAxeItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_battle_axe", new WeaponBattleAxeItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_battle_axe", new WeaponBattleAxeItem(ToolMaterial.NETHERITE, settings.fireproof()));

        // Weapon & Combat

        registerItemLegacy("wooden_claymore", new WeaponClaymoreItem(ToolMaterial.WOOD, settings));
        registerItemLegacy("stone_claymore", new WeaponClaymoreItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_claymore", new WeaponClaymoreItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_claymore", new WeaponClaymoreItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_claymore", new WeaponClaymoreItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_claymore", new WeaponClaymoreItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_claymore", new WeaponClaymoreItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItemLegacy("wooden_glaive", new WeaponGlaiveItem(ToolMaterial.WOOD, settings)); //Throwable??
        registerItemLegacy("stone_glaive", new WeaponGlaiveItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_glaive", new WeaponGlaiveItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_glaive", new WeaponGlaiveItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_glaive", new WeaponGlaiveItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_glaive", new WeaponGlaiveItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_glaive", new WeaponGlaiveItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItemLegacy("wooden_hammer", new WeaponHammerItem(ToolMaterial.WOOD, settings));
        registerItemLegacy("stone_hammer", new WeaponHammerItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_hammer", new WeaponHammerItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_hammer", new WeaponHammerItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_hammer", new WeaponHammerItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_hammer", new WeaponHammerItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_hammer", new WeaponHammerItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItemLegacy("wooden_dagger", new DaggerItem(ToolMaterial.WOOD, settings));
        registerItemLegacy("stone_dagger", new DaggerItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_dagger", new DaggerItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_dagger", new DaggerItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_dagger", new DaggerItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_dagger", new DaggerItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_dagger", new DaggerItem(ToolMaterial.NETHERITE, settings.fireproof()));

        registerItemLegacy("wooden_long_sword", new LongSwordItem(ToolMaterial.WOOD, settings));
        registerItemLegacy("stone_long_sword", new LongSwordItem(ToolMaterial.STONE, settings));
        registerItemLegacy("golden_long_sword", new LongSwordItem(ToolMaterial.GOLD, settings));
        //registerItemLegacy("copper_long_sword", new LongSwordItem(ToolMaterial.COPPER, settings));
        registerItemLegacy("iron_long_sword", new LongSwordItem(ToolMaterial.IRON, settings));
        registerItemLegacy("diamond_long_sword", new LongSwordItem(ToolMaterial.DIAMOND, settings));
        registerItemLegacy("netherite_long_sword", new LongSwordItem(ToolMaterial.NETHERITE, settings.fireproof()));

        //Launch - Welcome to Stupidity

        registerItemLegacy("butterfly_jar", BUTTERFLY_JAR);
        registerItemLegacy("butterfly_spawn_egg", BUTTERFLY_SPAWN_EGG);
        //registerItemLegacy("raw_marshmellow", MARSHMELLOW_RAW);
        //registerItemLegacy("toasted_marshmellow", MARSHMELLOW_TOASTED);
        //registerItemLegacy("baguette", BAGGUETTE);
        
        registerItemLegacy("zombie_lobber_spawn_egg", ZOMBIE_LOBBER_SPAWN_EGG);
        registerItemLegacy("zombie_scorched_spawn_egg", ZOMBIE_SCORCHED_SPAWN_EGG);
        registerItemLegacy("zombie_frostbite_spawn_egg", ZOMBIE_FROSTBITE_SPAWN_EGG);
        registerItemLegacy("zombie_slimed_spawn_egg", ZOMBIE_SLIMED_SPAWN_EGG);
        registerItemLegacy("armored_pillager_spawn_egg", ARMORED_PILLAGER_SPAWN_EGG);

        //The Fire of the Hunt Update:

        registerItemLegacy("ancient_debris_nugget", ANCIENT_DEBRIS_NUGGET);
        registerItemLegacy("wither_bone", WITHER_BONE);
        registerItemLegacy("bottle_of_rum", RUM);
        registerItemLegacy("cannon_ball", CANNON_BALL_ITEM);
        registerItemLegacy("cannon", CANNON_ITEM);
        registerItemLegacy("prismerine_arrow", PRISMERINE_ARROW);

        registerItemLegacy("piglin_warrior_spawn_egg", PIGLIN_WARRIOR_SPAWN);
        registerItemLegacy("blazing_inferno_spawn_egg", BLAZING_INFERNO_SPAWN);
        registerItemLegacy("soul_skeleton_spawn_egg", SOUL_SKELETON_SPAWN);

        // Tale of the Seas Update: (aka The Sea of Dead (Pirate Life) - On Stranger Tides)

        registerItemLegacy("zebra_fish_bucket", ZEBRA_FISH_BUCKET);
        registerItemLegacy("zebra_fish", ZEBRA_FISH);

        registerItemLegacy("alligator_gar_bucket", ALLIGATOR_GAR_BUCKET);
        registerItemLegacy("alligator_gar", ALLIGATOR_GAR);
        registerItemLegacy("cooked_alligator_gar", COOKED_ALLIGATOR_GAR);

        registerItemLegacy("mackerel_bucket", MACKEREL_BUCKET);
        registerItemLegacy("mackerel", MACKEREL);
        registerItemLegacy("cooked_mackerel", COOKED_MACKEREL);

        registerItemLegacy("bass_bucket", BASS_BUCKET);
        registerItemLegacy("bass", BASS);
        registerItemLegacy("cooked_bass", COOKED_BASS);

        registerItemLegacy("fur_trout_bucket", FUR_TROUT_BUCKET);
        //registerItemLegacy("fur_trout", FUR_TROUT);
        //registerItemLegacy("cooked_fur_trout", COOKED_FUR_TROUT);

        registerItemLegacy("koi_bucket", KOI_BUCKET);
        registerItemLegacy("koi", KOI);

        registerItemLegacy("perch_bucket", PERCH_BUCKET);
        registerItemLegacy("perch", PERCH);
        registerItemLegacy("cooked_perch", COOKED_PERCH);

        registerItemLegacy("snapper_bucket", SNAPPER_BUCKET);
        registerItemLegacy("snapper", SNAPPER);
        registerItemLegacy("cooked_snapper", COOKED_SNAPPER);

        registerItemLegacy("mahimahi_bucket", MAHIMAHI_BUCKET);
        registerItemLegacy("mahimahi", MAHIMAHI);
        registerItemLegacy("cooked_mahimahi", COOKED_MAHIMAHI);


        registerItemLegacy("ancient_trident", ANCIENT_TRIDENT);
        registerItemLegacy("ancient_drowned", ANCIENT_DROWN_SPAWN);

        registerItemLegacy("sunken_skeleton_spawn_egg", SUNKEN_SKELETON_SPAWN);
        registerItemLegacy("sunken_skeleton2_spawn_egg", SUNKEN_SKELETON2_SPAWN);
        registerItemLegacy("skeleton_pirate_captain_spawn_egg", SKELETON_PIRATE_CAPTAIN_SPAWN);

        registerItemLegacy("skeleton_metal_spawn_egg", METAL_SKELETON_SPAWN);
        registerItemLegacy("skeleton_phantom_spawn_egg", PHANTOM_SKELETON_SPAWN);

        // Shields

        registerItemLegacy("wooden_oak_shield", WOODEN_OAK_SHIELD);
        registerItemLegacy("wooden_spruce_shield", WOODEN_SPRUCE_SHIELD);
        registerItemLegacy("wooden_birch_shield", WOODEN_BIRCH_SHIELD);
        registerItemLegacy("wooden_jungle_shield", WOODEN_JUNGLE_SHIELD);
        registerItemLegacy("wooden_acacia_shield", WOODEN_ACACIA_SHIELD);
        registerItemLegacy("wooden_dark_oak_shield", WOODEN_DARK_OAK_SHIELD);
        registerItemLegacy("wooden_mangrove_shield", WOODEN_MANGROVE_SHIELD);
        registerItemLegacy("wooden_cherry_shield", WOODEN_CHERRY_SHIELD);
        registerItemLegacy("wooden_bamboo_shield", WOODEN_BAMBOO_SHIELD);

        registerItemLegacy("stone_shield", STONE_SHIELD);
        registerItemLegacy("stone_deepslate_shield", STONE_DEEPSLATE_SHIELD);
        registerItemLegacy("stone_blackstone_shield", STONE_BLACKSTONE_SHIELD);

        registerItemLegacy("golden_shield", GOLDEN_SHIELD);
        registerItemLegacy("diamond_shield", DIAMOND_SHIELD);
         */
        
        
        
        onInitializeMobEggs();

        // Register Multiple Block Models with One Item (IE. Signs, Hanging Signs)
        //registerItemLegacy("redwood_sign", REDWOOD_SIGN);
        //registerItemLegacy("redwood_hanging_sign", REDWOOD_HANGING_SIGN);

    }

    public static void BlockItem(){
        //registerItemLegacy("redwood_sign", new SignItem(settings.maxCount(16), BlockFactoryUpt.callBlock("redwood_sign"), BlockFactoryUpt.callBlock("redwood_wall_sign")));
        //registerItemLegacy("redwood_hanging_sign", new HangingSignItem(BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"), settings.maxCount(16)));
    }

    public static void onInitializeMobEggs() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
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

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name);
        System.out.println(id.toString());
        ModelList.put(id, item);
        Registry.register(BuiltInRegistries.ITEM, id, item);
        return item;
    }

    /*public static <I extends Item> I registerItemLegacy(String name, I item) {

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

    public static Item registerBlockItem3(Block block, String id, CreativeModeTab group) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id), new BlockItem(block, new Item.Properties()));
    }

    public static <I extends BlockItem> I registerBlockItems(String name, I item) {

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name);
        if (EntStupidStuff.DEV_MODE)
            System.out.println("Item: " + id.toString());
        BlockFactory.ItemList.put(id, item);

        Registry.register(BuiltInRegistries.ITEM, ResourceKey.create(BuiltInRegistries.ITEM.key(), BuiltInRegistries.BLOCK.getKey(item.getBlock())), item);

        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
        }

        return item;
    }



    public static Item callItem(String name) {
        //return BlockFactory.ItemList.get(Identifier.of(EntStupidStuff.MOD_ID, name));
        try {
            return BlockFactory.ItemList.get(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name));
        } catch(Exception e) {
            System.out.println("An Error was called in callItem()... Tried to call: " + name);
            return null;
        }
    }
    
    public static Item registerRaw(BlockItem item) {
		return registerRaw(item.getBlock(), item);
	}

	public static Item registerRaw(Block block, Item item) {
		return registerRaw(BuiltInRegistries.BLOCK.getKey(block), item);
	}

    public static Item registerRaw(ResourceLocation id, Item item) {
		return registerRaw(ResourceKey.create(BuiltInRegistries.ITEM.key(), id), item);
	}


    public static Item registerRaw(ResourceKey<Item> key, Item item) {
		if (item instanceof BlockItem) {
			((BlockItem)item).registerBlocks(Item.BY_BLOCK, item);
		}

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

    // NEW //////////////////////////////////////////

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return register(keyOf(name), function, new Item.Properties(), name);
    }

    public static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties settings, String name) {
		Item item = (Item)factory.apply(settings.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}
        System.out.println("Items: " + ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name));
        BlockFactory.ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        ModGroup.addToDefault(name);


		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

    private static ResourceKey<Item> keyOf(String id) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
	}

    //old to new

    public static <I extends Item> I registerItemLegacy(String name, I item) {
        if (name == null || item == null) {
            System.out.println("[ITEM-ERROR]: " + name + " with id " + item);
            return item;
        }

        // Create RegistryKey
        ResourceKey<Item> key = keyOf(name);

        // Apply registry key to item settings if possible
        Item.Properties settings = new Item.Properties();
        try {
            settings.setId(key);
        } catch (Exception ignored) {}

        // If item is a BlockItem, register it properly with appendBlocks
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        // Register in the vanilla registry
        Registry.register(BuiltInRegistries.ITEM, key, item);

        // Put in custom item list
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name);
        BlockFactory.ItemList.put(id, item);

        // Add to default mod group
        //ModGroup.addToDefault(name);

        System.out.println("Item: " + id.toString());
        return item;
    }


}
