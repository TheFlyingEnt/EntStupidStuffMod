package net.ent.entstupidstuff.effects;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {

    public static final MobEffect SUNKEN_EFFECT = new SunkenEffect();
    //public static final StatusEffect RGB_SHIFT = new RGBShiftEffect();
    public static final Holder<MobEffect> BLEEDING = register("bleeding", new BleedingEffect(MobEffectCategory.HARMFUL, 0x8B0000));

    public static final Holder<MobEffect> RGB_SHIFT = register("rgb_shift", new RGBShiftEffect());
    public static final Holder<MobEffect> BLUR = register("blur", new RGBShiftEffect());
    public static final Holder<MobEffect> CREEPER = register("creeper", new RGBShiftEffect());

    /*
     * public static final RegistryEntry<StatusEffect> INVISIBILITY = register(
		"invisibility",
		new StatusEffect(StatusEffectCategory.BENEFICIAL, 16185078)
			.addAttributeModifier(
				EntityAttributes.WAYPOINT_TRANSMIT_RANGE,
				Identifier.ofVanilla("effect.waypoint_transmit_range_hide"),
				-1.0,
				EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
			)
	);
     */

    private static Holder<MobEffect> register(String id, MobEffect statusEffect) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id), statusEffect);
	}

    public static void registerEffects() {
        Registry.register(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken"), SUNKEN_EFFECT);
        //Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "rgb_shift"), RGB_SHIFT);




        //Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "bleeding"), BLEEDING_EFFECT);
    }

    

    

}
