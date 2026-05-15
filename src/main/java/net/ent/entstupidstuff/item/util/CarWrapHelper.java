package net.ent.entstupidstuff.item.util;

import java.util.ArrayList;
import java.util.List;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.world.item.ItemStack;

public class CarWrapHelper {

    public static ItemStack getCarWrapInstance_AUDI() {
        ItemStack itemStack = new ItemStack(ItemFactory.CAR_WRAP);
        itemStack.set(ModDataComponentTypes.CAR_TYPE, "car");
        itemStack.set(ModDataComponentTypes.WRAP_ID, "fone_audi");
        return itemStack;
    }

    //F1:
    public static String[] visableF1Wraps() {
        return new String[]{
            "fone_audi",
            "fone_redbull_japan",
            "fone_camel",
            "fone_demonslayer",
            "fone_senna",
            "fone_redbull",
            "fone_jurassic_studios",
            "fone_entity",
            "fone_lexus",
            "fone_stake",
            "fone_aston",
            "fone_mclaren",
            "fone_ferrari_sf24",
            "fone_ferrari_sf26",
            "fone_cadillac",
            "fone_haas_vf24",
            "fone_mercades_w15",
            "fone_vcarb_01",
            "fone_blast",
            "fone_beamy",
            "fone_ford",
            "fone_haas_vf26",
            "fone_bentley",
            "fone_clt",
            "fone_brawngp",
            "fone_blank_empty",
            "fone_stock",
            "fone_cyberpunk"
        };
    }


    public static List<ItemStack> getCarWrapInstance_F1Cars() {
        List<ItemStack> wraps = new ArrayList<>();

        for (String livery : visableF1Wraps()) {
            ItemStack itemStack = new ItemStack(ItemFactory.CAR_WRAP);
            itemStack.set(ModDataComponentTypes.CAR_TYPE, "car");
            itemStack.set(ModDataComponentTypes.WRAP_ID, livery);

            wraps.add(itemStack);
        }


        return wraps;
    }
    
}
