package net.ent.entstupidstuff.datagen.recipes;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class ShieldDecorationRecipeExtra extends ShieldDecorationRecipe{

	public ShieldDecorationRecipeExtra(CraftingBookCategory craftingRecipeCategory) {
		super(craftingRecipeCategory);
	}

	private Item ModShield;

    public ShieldDecorationRecipeExtra(CraftingBookCategory craftingRecipeCategory, Item shield) {
		super(craftingRecipeCategory);
		this.ModShield = shield;
	}

	@Override
    public boolean matches(CraftingInput craftingRecipeInput, Level world) {
		ItemStack itemStack = ItemStack.EMPTY;
		ItemStack itemStack2 = ItemStack.EMPTY;

		for (int i = 0; i < craftingRecipeInput.size(); i++) { // TODO 1.21.10 - CHECK THIS
			ItemStack itemStack3 = craftingRecipeInput.getItem(i);
			if (!itemStack3.isEmpty()) {
				if (itemStack3.getItem() instanceof BannerItem) {
					if (!itemStack2.isEmpty()) {
						return false;
					}

					itemStack2 = itemStack3;
				} else {
					if (!itemStack3.is(ModShield)) {
						return false;
					}

					if (!itemStack.isEmpty()) {
						return false;
					}

					BannerPatternLayers bannerPatternsComponent = itemStack3.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
					if (!bannerPatternsComponent.layers().isEmpty()) {
						return false;
					}

					itemStack = itemStack3;
				}
			}
		}

		return !itemStack.isEmpty() && !itemStack2.isEmpty();
	}

	@Override
	public ItemStack assemble(CraftingInput craftingRecipeInput, HolderLookup.Provider wrapperLookup) {
		ItemStack itemStack = ItemStack.EMPTY;
		ItemStack itemStack2 = ItemStack.EMPTY;

		for (int i = 0; i < craftingRecipeInput.size(); i++) {
			ItemStack itemStack3 = craftingRecipeInput.getItem(i);
			if (!itemStack3.isEmpty()) {
				if (itemStack3.getItem() instanceof BannerItem) {
					itemStack = itemStack3;
				} else if (itemStack3.is(ModShield)) {
					itemStack2 = itemStack3.copy();
				}
			}
		}

		if (itemStack2.isEmpty()) {
			return itemStack2;
		} else {
			itemStack2.set(DataComponents.BANNER_PATTERNS, itemStack.get(DataComponents.BANNER_PATTERNS));
			itemStack2.set(DataComponents.BASE_COLOR, ((BannerItem)itemStack.getItem()).getColor());
			return itemStack2;
		}
	}

	@Override
	public RecipeSerializer getSerializer() {
		return EntStupidStuff.WOODEN_OAK_SHIELD_DECORATION;
	}
    
}
