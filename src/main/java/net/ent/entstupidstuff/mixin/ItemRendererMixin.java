package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useRubyStaffModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        /*if (stack.isOf(ItemFactory.callItem("stone_hammer")) && renderMode != ModelTransformationMode.GUI) { //Identifier.of("entstupidstuff")
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "diamond_hammer"), "inventory"));
        } 
        else if (stack.isOf(ItemFactory.callItem("stone_hammer")) && renderMode != ModelTransformationMode.GROUND) { //Identifier.of("entstupidstuff")
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "diamond_hammer"), "ground"));
        }*/

        //dropLoot

        // Entity Render

        //Entity entity = ((ItemRendererAccessor) this).getHeldItemRenderer();

        /*if (stack.isOf(ItemFactory.callItem("golden_hammer"))) {

            if (entity instanceof PiglinEntity || entity instanceof PiglinWarriorEntity) {
                if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                    return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "golden_hammer_model_piglin"), "inventory"));   
                } 

            }
        }*/


        // Player Render

        if (stack.isOf(ItemFactory.callItem("wooden_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "wooden_hammer_model"), "inventory"));   
            } 
        }
        if (stack.isOf(ItemFactory.callItem("stone_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "stone_hammer_model"), "inventory"));   
            } 
        }
        if (stack.isOf(ItemFactory.callItem("iron_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "iron_hammer_model"), "inventory"));   
            } 
        }
        if (stack.isOf(ItemFactory.callItem("golden_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "golden_hammer_model"), "inventory"));   
            } 
        }
        if (stack.isOf(ItemFactory.callItem("diamond_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "diamond_hammer_model"), "inventory"));   
            } 
        }
        if (stack.isOf(ItemFactory.callItem("netherite_hammer"))) {
            if (!renderMode.equals(ModelTransformationMode.GUI) && !renderMode.equals(ModelTransformationMode.GROUND)) {
                return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "netherite_hammer_model"), "inventory"));   
            } 
        }

        return value;

        //ModelIdentifier
    }

    @Inject(method = "getModel", at = @At("HEAD"), cancellable = true)
    public void onGetModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if (entity != null) {

            if (entity instanceof PiglinWarriorEntity || entity instanceof ZombifiedPiglinEntity) {

                if (stack.isOf(ItemFactory.callItem("golden_hammer"))) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "golden_hammer_model_piglin"), "inventory"));
                    cir.setReturnValue(customModel);
                }
                else if (stack.isOf(ItemFactory.callItem("netherite_hammer"))) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "netherite_hammer_model_piglin"), "inventory"));
                    cir.setReturnValue(customModel);
                }
               
            }

            /*else if (entity instanceof SeaPhantomEntity) {

                if (stack.isOf(Items.IRON_SWORD)) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "iron_cutlass_phantom"), "inventory"));
                    cir.setReturnValue(customModel);
                }
                else if (stack.isOf(stack.isOf(ModFood.FLINT_BOW)) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "flintlock_bow_phantom"), "inventory"));
                    cir.setReturnValue(customModel);
                }
               
            } */

            // Custom logic to modify the model based on the entity - THIS WORKED
            // Replace this with your custom logic
            /*if (entity.isSneaking() && stack.isOf(ModFood.CANNON_ITEM)) {
                // Example: change model if the entity is sneaking
                BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "diamond_hammer_model"), "inventory"));
                cir.setReturnValue(customModel);
            }*/
        }
    }

}