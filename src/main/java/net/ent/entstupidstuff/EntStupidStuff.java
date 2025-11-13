package net.ent.entstupidstuff;

import net.ent.entstupidstuff.api.cutscene.CutsceneManager;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.entity.BlockEntityFactory;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.datagen.recipes.ShieldDecorationRecipeExtra;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentEffects;
import net.ent.entstupidstuff.event.WeaponEvent;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.ent.entstupidstuff.world.gen.ModEntitySpawns;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModGroup;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.DispenserBlock;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShieldDecorationRecipe;
import net.minecraft.recipe.SpecialCraftingRecipe.SpecialRecipeSerializer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.arguments.StringArgumentType;

public class EntStupidStuff implements ModInitializer {

	public static final String MOD_ID = "entstupidstuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Boolean DEV_MODE = true;

	public static final Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
		
    }

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_OAK_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_oak_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_OAK_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_SPRUCE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_spruce_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_SPRUCE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_BIRCH_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_birch_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BIRCH_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_JUNGLE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_jungle_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_JUNGLE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_BAMBOO_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_bambo_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BAMBOO_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_ACAICA_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_acacia_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_ACACIA_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_DARK_OAK_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_dark_oak_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_DARK_OAK_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_MANGROVE_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_mangrove_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_MANGROVE_SHIELD))
	);

	public static RecipeSerializer<ShieldDecorationRecipe> WOODEN_CHERRY_SHIELD_DECORATION = RecipeSerializer.register(
		"crafting_special_wooden_cherry_shielddecoration", new SpecialRecipeSerializer<>((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_CHERRY_SHIELD))
	);




	@Override
	public void onInitialize() {
		LOGGER.info("Mod Initializing");

		WeaponEvent.onInitialize(); //To be Removed
		EntityFactory.onInitialize();
		ModEffects.registerEffects();
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

		//Test Code for Screen Support

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("playcutscene")
                .then(CommandManager.argument("filename", StringArgumentType.string())
                    .executes(context -> {
                        String filename = StringArgumentType.getString(context, "filename");
                        CutsceneManager.playCutscene(filename, true, true);
                        //context.getSource().sendFeedback(() -> Text.literal("Playing cutscene: " + filename), false);
                        return 1;
                    })));
        });




		
	}
}