package net.ent.entstupidstuff;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.datagen.recipes.ShieldDecorationRecipeExtra;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentEffects;
import net.ent.entstupidstuff.event.WeaponEvent;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.ent.entstupidstuff.world.gen.ModEntitySpawns;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModGroup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.DispenserBlock;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShieldDecorationRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntStupidStuff implements ModInitializer {

	public static final String MOD_ID = "entstupidstuff";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier id(String path) {
        return Identifier.of("entstupidstuff", path);
		
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

		WeaponEvent.onInitialize(); //TODO: UnCommened - Check
		//WeaponFactory.onInitialize();
		EntityFactory.onInitialize();
		//ModSpawnEgg.onInitialize();
		//SpawningFactory.onInitialize();
		//BlockFactory.onInitialize();
		//BlockFactory.onInitialize();
		//EnchantmentFactory.onInitialize();
		//ModFood.onInitialize();
		ModEffects.registerEffects();
		ModEntitySpawns.addSpawns();

		ItemFactory.onInitialize();
		BlockFactoryUpt.onInitialize();
		
		ModGroup.onInitialize();
		SoundFactory.registerSounds();
		

		DispenserBlock.registerProjectileBehavior(ItemFactory.PRISMERINE_ARROW);

		UpdatedEnchantmentEffects.registerEnchantmentEffects();
		

		//AttackCallbackAll.EVENT.register(WeaponEvent::onEntityAttack);

		
	}
}