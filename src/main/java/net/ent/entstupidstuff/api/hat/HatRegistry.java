package net.ent.entstupidstuff.api.hat;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ModGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Central registry for all cosmetic hats.
 *
 * To add a new hat:
 *   1. Call HatRegistry.register("your_hat_name") here (or in your own class).
 *   2. Add assets/hatsmod/models/item/your_hat_name.json  (item model with "head" display).
 *   3. Add the matching texture.
 *
 * Hat names are plain strings (e.g. "party_hat").  The full ResourceLocation used
 * internally is always hatsmod:<name>.
 */


public class HatRegistry {
 
    private static final Map<String, Item> HATS = new LinkedHashMap<>();
    private static final Map<String, HatSource> SOURCES = new LinkedHashMap<>();
 
    public static final Item HAT_MENACING = registerItem("menacing", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_ALPHA = registerItem("baseball_cap_alpha", settings -> new Item(settings), HatSource.BETA_TESTER);
    public static final Item HAT_BASEBALL_CAP_BETA = registerItem("baseball_cap_beta", settings -> new Item(settings), HatSource.BETA_TESTER);
    public static final Item HAT_BASEBALL_CAP_DARKEND = registerItem("baseball_cap_darkend", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_ECTOPLASM = registerItem("baseball_cap_ectoplasm", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_FADE_BLUE = registerItem("baseball_cap_fade_blue", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_FADE_RED = registerItem("baseball_cap_fade_red", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_FADE_YELLOW = registerItem("baseball_cap_fade_yellow", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_GLOWSQUID = registerItem("baseball_cap_glowsquid", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_GROOVY = registerItem("baseball_cap_groovy", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_MONKEY = registerItem("baseball_cap_monkey", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_NEGATIVE = registerItem("baseball_cap_negative", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_ORANGE_BURST= registerItem("baseball_cap_orange_burst",settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_PLATYPUS = registerItem("baseball_cap_platypus", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_PURPLE_PINK = registerItem("baseball_cap_purple_pink", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_RETRO_SUN = registerItem("baseball_cap_retro_sun", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_SPECIAL_RED = registerItem("baseball_cap_special_red", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_TEAL = registerItem("baseball_cap_teal", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_TENIS_LIME = registerItem("baseball_cap_tenis_lime", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_RGB = registerItem("baseball_cap_rgb", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_DISCORD_ZEEZO = registerItem("discord_zeezo", settings -> new Item(settings), HatSource.EVENT);
 
    public static final Item HAT_BASEBALL_CAP_BIGGUY = registerItem("baseball_cap_bigguy", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_BLACK = registerItem("baseball_cap_black", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_BLUE = registerItem("baseball_cap_blue", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_CYAN = registerItem("baseball_cap_cyan", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_GREEN = registerItem("baseball_cap_green", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_GREEN_TURTLE= registerItem("baseball_cap_green_turtle",settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_LIME = registerItem("baseball_cap_lime", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_MINT_GREEN = registerItem("baseball_cap_mint_green", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_ORANGE = registerItem("baseball_cap_orange", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_PINK = registerItem("baseball_cap_pink", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_PURPLE = registerItem("baseball_cap_purple", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_RED = registerItem("baseball_cap_red", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_BASEBALL_CAP_YELLOW = registerItem("baseball_cap_yellow", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_BASEBALL_CAP_ZEEZO2021 = registerItem("baseball_cap_zeezo2021", settings -> new Item(settings), HatSource.EVENT);
    public static final Item HAT_BASEBALL_CAP_BLAST1400 = registerItem("baseball_cap_blast1400", settings -> new Item(settings), HatSource.EVENT);
 
    public static final Item HAT_CABBY_BLUE = registerItem("cabby_hat_blue", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_DISCO = registerItem("disco", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_FEDORA_BLACK = registerItem("fedora_black", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_FEDORA_RED = registerItem("fedora_red", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_FEDORA_WHITE = registerItem("fedora_white", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_GOGGLES_STEAM_PUNK = registerItem("goggles_steam_punk", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_GAKURAN_WHITE = registerItem("gakuran_white", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_GAKURAN_BLACK = registerItem("gakuran_black", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_SUNGLASSES_PIXELATED = registerItem("sunglasses_pixelated", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_SUNGLASSES_90S = registerItem("sunglasses_90s", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_SUNGLASSES_GALAXY = registerItem("sunglasses_galaxy", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_SUNGLASSES_OCTANE = registerItem("sunglasses_octane", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_SUNGLASSES_ROCKET = registerItem("sunglasses_rocket", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_RAINBOW_ORBITERS = registerItem("rainbow_orbiters", settings -> new Item(settings), HatSource.ACHIEVEMENT);
    public static final Item HAT_THE_BLADE = registerItem("the_blade", settings -> new Item(settings), HatSource.ACHIEVEMENT);
 
    public static final Item HAT_TOPHAT = registerItem("tophat", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TOPHAT_THANKYOU = registerItem("tophat_thank_you", settings -> new Item(settings), HatSource.EVENT);
    public static final Item HAT_TOPHATHATHAT = registerItem("tophathathat", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TOPHATHATHATHATHAT = registerItem("tophathathathathat", settings -> new Item(settings), HatSource.DEFAULT);
 
    public static final Item HAT_TV_BLANK = registerItem("tv_blank", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TV_ERROR = registerItem("tv_error", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TV_NYANCAT = registerItem("tv_nyancat", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TV_NYANCAT_GALAXY = registerItem("tv_nyancat_galaxy", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TV_RICKROLL = registerItem("tv_rickroll", settings -> new Item(settings), HatSource.DEFAULT);
    public static final Item HAT_TV_FROG_RAVE = registerItem("tv_frog_rave", settings -> new Item(settings), HatSource.DEFAULT);

    //Ideas: - Advancement
    // We Need to Go Deeper - Portal Cap
    // Enchanter - Books on Head
    // Nether - Soul Fire
    // Return to Sender - Ghast Cap
    // Those Were the Days - Something Gold Related
    // Who is Cutting Onions? - Crying
    // Uneasy Alliance - Floating Mini Ghast
    // Brew a Potion - Drunk Glasses
    // What a Deal! - SIM Emerald
    // What a Deal! - Arrow in Head
    // Sneak 100 - Warren Ears
    // Sweet Dreams - Zzzzz
    // Postmortal - Halo
 
    private static ResourceKey<Item> keyOf(String id) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
    }
 
    private static Item registerItem(String name, Function<Item.Properties, Item> function, HatSource source) {
        ResourceLocation modelId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "item/hat/" + name);
        Item item = register(
            keyOf(name),
            function,
            new Item.Properties()
                .component(DataComponents.ITEM_MODEL, modelId)
                .equippable(EquipmentSlot.HEAD),
            name
        );
        SOURCES.put(name, source);
        return item;
    }
 
    public static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties settings, String name) {
        Item item = factory.apply(settings.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }
        HATS.put(name, item);
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
 
 
    public static Item getHat(String name) {
        return HATS.get(name);
    }
 
    public static HatSource getSource(String name) {
        return SOURCES.getOrDefault(name, HatSource.DEFAULT);
    }
 
    public static boolean isValid(String name) {
        return HATS.containsKey(name);
    }
 
    public static Collection<String> getNames() {
        return HATS.keySet();
    }
 
    public static void init() {}
}