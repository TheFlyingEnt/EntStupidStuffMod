package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintingVariantTagsProvider extends KeyTagProvider<PaintingVariant> {
    public ModPaintingVariantTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(packOutput, Registries.PAINTING_VARIANT, completableFuture);
	}

    @Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE)
			.add(
                ModPaintingVariant.JUSTCRAFTINGON,
                ModPaintingVariant.FRIENDSMINING,
                ModPaintingVariant.COOKIESTEALER,
                ModPaintingVariant.THERETURNKING,
                ModPaintingVariant.THREESECTNIK,
                ModPaintingVariant.AMANANDFOX
			);
	}
    
}
