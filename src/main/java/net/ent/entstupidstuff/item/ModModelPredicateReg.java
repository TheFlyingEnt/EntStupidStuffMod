package net.ent.entstupidstuff.item;


import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ModModelPredicateReg {



    public static void onInitialize() {

        ModelPredicateProviderRegistry.register(
			ItemFactory.CANNON_ITEM,
			Identifier.ofVanilla("pull"),
			(stack, world, entity, seed) -> {
				if (entity == null) {
					return 0.0F;
				} else {
					return CrossbowItem.isCharged(stack)
						? 0.0F
						: (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack, entity);
				}
			}
		);

		ModelPredicateProviderRegistry.register(
			ItemFactory.CANNON_ITEM,
			Identifier.ofVanilla("pulling"),
			(stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
		);

		ModelPredicateProviderRegistry.register(ItemFactory.CANNON_ITEM, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);

		ModelPredicateProviderRegistry.register(
			ItemFactory.BUTTERFLY_JAR,
			Identifier.ofVanilla("variant"), //Not Channging at Item Compoent change later, F u mojang
			(stack, world, entity, seed) -> {
				NbtComponent component = stack.get(DataComponentTypes.BUCKET_ENTITY_DATA);
        		if (component != null) {

					NbtCompound nbt = component.copyNbt(); 
					if (nbt.contains("Variant")) {
						if (nbt.contains("Variant")) {
							//System.out.println("Varient was Found");
							//return (float) nbt.getInt("Variant");
							return nbt.getFloat("Variant");
							
						}
					}



            		
            		/*if (nbt.contains("Variant", NbtElement.INT_TYPE)) {
						System.out.println(nbt.getInt("Variant"));
                	return (float) nbt.getInt("Variant");
            		}*/
        		}
        		return 0; // Default fallback
			}
		);


    }

}
