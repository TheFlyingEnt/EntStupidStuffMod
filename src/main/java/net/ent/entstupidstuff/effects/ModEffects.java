package net.ent.entstupidstuff.effects;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

     public static final StatusEffect SUNKEN_EFFECT = new SunkenEffect();

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(EntStupidStuff.MOD_ID, "sunken"), SUNKEN_EFFECT);
    }

}
