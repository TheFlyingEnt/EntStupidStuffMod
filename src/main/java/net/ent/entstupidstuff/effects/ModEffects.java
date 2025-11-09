package net.ent.entstupidstuff.effects;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect SUNKEN_EFFECT = new SunkenEffect();
    //public static final StatusEffect RGB_SHIFT = new RGBShiftEffect();
    public static final RegistryEntry<StatusEffect> BLEEDING = register("bleeding", new BleedingEffect(StatusEffectCategory.HARMFUL, 0x8B0000));

    public static final RegistryEntry<StatusEffect> RGB_SHIFT = register("rgb_shift", new RGBShiftEffect());
    public static final RegistryEntry<StatusEffect> BLUR = register("blur", new RGBShiftEffect());
    public static final RegistryEntry<StatusEffect> CREEPER = register("creeper", new RGBShiftEffect());

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

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, id), statusEffect);
	}

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "sunken"), SUNKEN_EFFECT);
        //Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "rgb_shift"), RGB_SHIFT);




        //Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "bleeding"), BLEEDING_EFFECT);
    }

    

    

}
