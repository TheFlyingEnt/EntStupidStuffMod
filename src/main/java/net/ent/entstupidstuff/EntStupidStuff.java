package net.ent.entstupidstuff;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.entity.BlockEntityFactory;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.datagen.recipes.ShieldDecorationRecipeExtra;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentEffects;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.ent.entstupidstuff.world.gen.ModEntitySpawns;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModGroup;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CustomRecipe.Serializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntStupidStuff implements ModInitializer {

	public static final String MOD_ID = "entstupidstuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Boolean DEV_MODE = true;

	public static final ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
		
    }

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_OAK_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_oak_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_OAK_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_SPRUCE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_spruce_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_SPRUCE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_BIRCH_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_birch_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BIRCH_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_JUNGLE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_jungle_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_JUNGLE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_BAMBOO_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_bambo_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BAMBOO_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_ACAICA_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_acacia_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_ACACIA_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_DARK_OAK_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_dark_oak_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_DARK_OAK_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_MANGROVE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_mangrove_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_MANGROVE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_CHERRY_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_cherry_shielddecoration", new Serializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_CHERRY_SHIELD))
	);


    

	@Override
	public void onInitialize() {
		LOGGER.info("Mod Initializing");

		EntityFactory.onInitialize();
		ModEffects.registerEffects(); //StatusEffect
		ModEntitySpawns.addSpawns();
		BlockFactory.onInitialize();
		ItemFactory.onInitialize();
		ModGroup.onInitialize();
		SoundFactory.registerSounds();
		ParticleTypesFactory.initalizer();
		

		DispenserBlock.registerProjectileBehavior(ItemFactory.PRISMERINE_ARROW);

		UpdatedEnchantmentEffects.registerEnchantmentEffects();

		BlockEntityFactory.onInitialize();
		ScreenHandlerFactory.registerScreenHandlers();

		ModDataComponentTypes.register();

		//ServerDelayedTasks.init();
		//ModDataComponents.register();

	}

    // # Update Plan:

    //## Welcome to Stupidity

    //## Tales from the Dead Sea

    //Of Gold and Gear's
    // Nether & Pillager Update

    //The Lost Tales
    // Dinosour and Underground Cave Update

    //






}