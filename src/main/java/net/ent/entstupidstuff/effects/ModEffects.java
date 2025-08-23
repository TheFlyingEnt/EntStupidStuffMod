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
    //public static final StatusEffect BLEEDING_EFFECT = new BleedingEffect();

    public static final RegistryEntry<StatusEffect> BLEEDING = register("bleeding", new BleedingEffect(StatusEffectCategory.HARMFUL, 0x8B0000));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, id), statusEffect);
	}

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "sunken"), SUNKEN_EFFECT);
        //Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "bleeding"), BLEEDING_EFFECT);
    }

    

}
