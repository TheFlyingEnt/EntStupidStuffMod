package net.ent.entstupidstuff.enchantment;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;


public class EnchantmentFactory {

    //Combat
    /*public static final RegistryKey<Enchantment> COMMITTED = register("committed", new CommittedEnchantment());
    public static final RegistryKey<Enchantment> CRITICAL_HIT = register("critical_hit", new CriticalHitEnchantment());
    public static final RegistryKey<Enchantment> ECHO = register("echo", new EchoEnchantment());
    public static final RegistryKey<Enchantment> FREEZING = register("freezing", new FreezingEnchantment());
    public static final RegistryKey<Enchantment> GRAVITY_MELEE = register("gravity_melee", new GravityMeleeEnchantment());
    public static final RegistryKey<Enchantment> BANE_OF_ILLAGERS = register("bane_of_illagers", new BaneOfIllagersEnchantment());
    public static final RegistryKey<Enchantment> LEECHING = register("leeching", new LeechingEnchantment());
    public static final RegistryKey<Enchantment> RAMPAGING = register("rampaging", new RampagingEnchantment());
    public static final RegistryKey<Enchantment> SHOCKWAVE = register("shockwave", new ShockwaveEnchantment());
    public static final RegistryKey<Enchantment> SWIRLING = register("swirling", new SwirlingEnchantment());
    public static final RegistryKey<Enchantment> THUNDERING = register("thundering", new ThunderingEnchantment());
    public static final RegistryKey<Enchantment> STUNNING = register("stunning", new StunningEnchantment());
    public static final RegistryKey<Enchantment> VOID_TOUCHED_MELEE = register("void_touched_melee", new VoidTouchedMeleeEnchantment());

    // Ranged Enchantments
    public static final RegistryKey<Enchantment> BONUS_SHOT = register("bonus_shot", new BonusShotEnchantment());
    public static final RegistryKey<Enchantment> MULTI_CHARGE = register("multi_charge", new MultiChargeEnchantment());
    public static final RegistryKey<Enchantment> RAPID_FIRE = register("rapid_fire", new RapidFireEnchantment());
    public static final RegistryKey<Enchantment> RICOCHET = register("ricochet", new RicochetEnchantment());*/




    

    //Loading
    public static final ResourceKey<Enchantment> FROSTBITE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "frostbite"));
    public static final ResourceKey<Enchantment> BERSERK = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "berserk"));
    public static final ResourceKey<Enchantment> BANEOFRAIDERS = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "baneofraiders"));
    public static final ResourceKey<Enchantment> EXPERIENCE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "experience"));
    public static final ResourceKey<Enchantment> WALL_JUMP = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "wall_jump"));
    public static final ResourceKey<Enchantment> OSMOSIS = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "osmosis"));
    public static final ResourceKey<Enchantment> CURSE_FIRE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "curse_fire"));


    //TBA
    public static final ResourceKey<Enchantment> LIGHTEN = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "lighten"));
    public static final ResourceKey<Enchantment> SCORCHING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "scorching"));
    public static final ResourceKey<Enchantment> CLEVING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "cleving"));









    ///////////////


    






    public static final ResourceKey<Enchantment> STAFE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stafe"));
    //public static final RegistryKey<Enchantment> MAGIC_PROTECTION = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "magic_protection"));
    //public static final RegistryKey<Enchantment> LIGHTEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "lighten"));
    public static final ResourceKey<Enchantment> STALWART = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "stalwart"));
    public static final ResourceKey<Enchantment> SHIELD_BASH = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "shield_bash"));
    //public static final RegistryKey<Enchantment> SCORCHING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "scorching"));

    public static final ResourceKey<Enchantment> MAGIC_PROTECTION = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "magic_protection"));

    //public static final TagKey<Enchantment> FROSTBITETAG = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "frostbite"));


    public static void onInitialize() {
       ModCodec.onInitialize();
       ModComponentTypes.onInitialize();


       //Registry.ENCHANTMENT.addTag(FROSTBITETAG, FROSTBITE);

    }

}
