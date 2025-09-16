package net.ent.entstupidstuff.item;


import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
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

    }

}
