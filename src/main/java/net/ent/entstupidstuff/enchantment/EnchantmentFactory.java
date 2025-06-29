package net.ent.entstupidstuff.enchantment;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


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
    public static final RegistryKey<Enchantment> FROSTBITE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "frostbite"));
    public static final RegistryKey<Enchantment> BERSERK = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "berserk"));
    public static final RegistryKey<Enchantment> BANEOFRAIDERS = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "baneofraiders"));
    public static final RegistryKey<Enchantment> EXPERIENCE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "experience"));
    public static final RegistryKey<Enchantment> WALL_JUMP = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "wall_jump"));
    public static final RegistryKey<Enchantment> OSMOSIS = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "osmosis"));
    public static final RegistryKey<Enchantment> CURSE_FIRE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "curse_fire"));


    //TBA
    public static final RegistryKey<Enchantment> LIGHTEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "lighten"));
    public static final RegistryKey<Enchantment> SCORCHING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "scorching"));
    public static final RegistryKey<Enchantment> CLEVING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "cleving"));









    ///////////////


    






    public static final RegistryKey<Enchantment> STAFE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "stafe"));
    //public static final RegistryKey<Enchantment> MAGIC_PROTECTION = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "magic_protection"));
    //public static final RegistryKey<Enchantment> LIGHTEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "lighten"));
    public static final RegistryKey<Enchantment> STALWART = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "stalwart"));
    public static final RegistryKey<Enchantment> SHIELD_BASH = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "shield_bash"));
    //public static final RegistryKey<Enchantment> SCORCHING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "scorching"));

    public static final RegistryKey<Enchantment> MAGIC_PROTECTION = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "magic_protection"));

    //public static final TagKey<Enchantment> FROSTBITETAG = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "frostbite"));


    public static void onInitialize() {
       ModCodec.onInitialize();
       ModComponentTypes.onInitialize();


       //Registry.ENCHANTMENT.addTag(FROSTBITETAG, FROSTBITE);

    }

}
