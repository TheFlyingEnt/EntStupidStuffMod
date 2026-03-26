package net.ent.entstupidstuff.datagen;

import java.util.Optional;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintingVariant {
    public static final ResourceKey<PaintingVariant> JUSTCRAFTINGON = create("jco");
    public static final ResourceKey<PaintingVariant> FRIENDSMINING = create("friendmine");
    public static final ResourceKey<PaintingVariant> COOKIESTEALER = create("cookiesteal");
    public static final ResourceKey<PaintingVariant> THERETURNKING = create("trking");
    public static final ResourceKey<PaintingVariant> THREESECTNIK = create("ttofthrees");
    public static final ResourceKey<PaintingVariant> RICKROLL = create("rickroll");
    public static final ResourceKey<PaintingVariant> APEX = create("apex");
    public static final ResourceKey<PaintingVariant> COCOBEANS = create("cocobeans");
    public static final ResourceKey<PaintingVariant> EON = create("eon");
    public static final ResourceKey<PaintingVariant> SOVIETS = create("soviets");
    public static final ResourceKey<PaintingVariant> AMANANDFOX = create("amanandfox");
    public static final ResourceKey<PaintingVariant> AZUREGROVE = create("azure_grove");
    public static final ResourceKey<PaintingVariant> CAR = create("car");
    

    public static void bootstrap(BootstrapContext<PaintingVariant> bootstrapContext) {
        register(bootstrapContext, JUSTCRAFTINGON, 2, 2, false);
        register(bootstrapContext, FRIENDSMINING, 4, 2);
        register(bootstrapContext, COOKIESTEALER, 4, 2);
        register(bootstrapContext, THERETURNKING, 4, 2);
        register(bootstrapContext, THREESECTNIK, 4, 2);
        register(bootstrapContext, RICKROLL, 1, 1, false);
        register(bootstrapContext, APEX, 1, 1, false);
        register(bootstrapContext, COCOBEANS, 1, 1, false);
        register(bootstrapContext, EON, 1, 1, false);
        register(bootstrapContext, SOVIETS, 1, 1, false);
        register(bootstrapContext, AMANANDFOX, 4, 2);  
        register(bootstrapContext, AZUREGROVE, 4, 4, false);
        register(bootstrapContext, CAR, 4, 2, false);
    }

    private static void register(BootstrapContext<PaintingVariant> bootstrapContext, ResourceKey<PaintingVariant> resourceKey, int i, int j) {
		register(bootstrapContext, resourceKey, i, j, true);
	}

	private static void register(BootstrapContext<PaintingVariant> bootstrapContext, ResourceKey<PaintingVariant> resourceKey, int i, int j, boolean bl) {
		bootstrapContext.register(
			resourceKey,
			new PaintingVariant(
				i,
				j,
				resourceKey.location(),
				Optional.of(Component.translatable(resourceKey.location().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
				bl ? Optional.of(Component.translatable(resourceKey.location().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY)) : Optional.empty()
			)
		);
	}

	private static ResourceKey<PaintingVariant> create(String string) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, string));
	}
    
}
