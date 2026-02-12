package net.ent.entstupidstuff;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModSkullStype;
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
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CustomRecipe.Serializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

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

        for (ModSkullStype type : ModSkullStype.values()) {
            SkullBlock.Type.TYPES.put(type.getSerializedName(), type);
        }

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.DROWNED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.DROWNED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BLAZE_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BLAZE_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BREEZE_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BREEZE_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.HUSK_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.HUSK_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.STRAY_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.STRAY_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BOGGED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.BOGGED_WALL_SKULL);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_LOBBER_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_LOBBER_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SCORCHED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SCORCHED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SLIMED_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_SLIMED_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FROSTBITTEN_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FROSTBITTEN_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FUNGAL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.ZOMBIE_FUNGAL_WALL_HEAD);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREBONE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREBONE_SKULL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREPER_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SPOREPER_WALL_HEAD);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SOUL_SKELETON_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.SOUL_SKELETON_WALL_SKULL);

        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BRAIN_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BRAIN_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_FIRE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_FIRE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_HORN_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_HORN_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_TUBE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_TUBE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BUBBLE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_BUBBLE_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_UNUSED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.CORAL_SKELETON_UNUSED_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_DEFAULT_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_DEFAULT_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_RED_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_RED_WALL_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_BLUE_SKULL);
        ((FabricBlockEntityType) BlockEntityType.SKULL).addSupportedBlock(BlockFactory.METAL_SKELETON_BLUE_WALL_SKULL);



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