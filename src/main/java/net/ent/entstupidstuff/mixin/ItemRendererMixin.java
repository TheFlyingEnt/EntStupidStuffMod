package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    protected BuiltinModelItemRenderer builtinModelItemRenderer;

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel modRenderItem(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {


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

    @Inject( method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"), cancellable = true)
    private void entstupidstuff$renderAncientTrident(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (!stack.isEmpty()) {
            //matrices.push();
            boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;  //////////////////////////////////////

            if (!bl && stack.isOf(ItemFactory.ANCIENT_TRIDENT)) { //This is the HotBar Model
                //System.out.println("Trident - renderItem");
                matrices.push();
                model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident_in_hand"), "inventory"));
                model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
                matrices.translate(-0.5F, -0.5F, -0.5F);
                //matrices.translate(5F, 5F, 5F);
                //matrices.scale(1.5F, 1.5F, 1.5F);
                this.builtinModelItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
                matrices.pop();
                ci.cancel();
            } /*else if (bl && stack.isOf(ItemFactory.ANCIENT_TRIDENT)) { //This is the HotBar Model
                matrices.push();
                //model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident"), "inventory"));
                model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
                matrices.translate(1F, 1F, 1F);
                this.builtinModelItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
                matrices.pop();
                ci.cancel();
            }*/

        }
        
        /*
            boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
            if (bl && stack.isOf(ItemFactory.ANCIENT_TRIDENT)) { //This is the HotBar Model
            System.out.println("renderItem");
            //model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of("entstupidstuff", "ancient_trident")));
            //model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of("entstupidstuff", "ancient_trident")));
            model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident"), "inventory"));

            matrices.push();
            model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
            matrices.translate(-0.5F, -0.5F, -0.5F);

            this.builtinModelItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
            matrices.pop();

            ci.cancel();// Skip the original method to prevent duplicate render
        }*/
    }


    @Inject(method = "getModel", at = @At("HEAD"), cancellable = true)
    public void onGetModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if (entity != null) {
            if (entity instanceof PiglinWarriorEntity || entity instanceof ZombifiedPiglinEntity || entity instanceof PiglinBruteEntity || entity instanceof PiglinEntity) {
                if (stack.isOf(ItemFactory.callItem("golden_hammer"))) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "golden_hammer_model_piglin"), "inventory"));
                    //BakedModel customModel = this.models.getModelManager().getModel((ModelIdentifier.ofInventoryVariant(Identifier.of("entstupidstuff","golden_hammer_model_piglin"))));
                    cir.setReturnValue(customModel);
                }
                else if (stack.isOf(ItemFactory.callItem("netherite_hammer"))) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "netherite_hammer_model_piglin"), "inventory"));
                    cir.setReturnValue(customModel);
                }
               
            }
            else if (entity instanceof PhantomSkeletonEntity) {
                if (stack.isOf(Items.BOW)) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "phantom_bow"), "inventory"));
                    cir.setReturnValue(customModel);
                }
                else if (stack.isOf(Items.IRON_SWORD)) {
                    BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "phantom_cutlass"), "inventory"));
                    cir.setReturnValue(customModel);
                }

            }

            /*if (stack.isOf(ItemFactory.ANCIENT_TRIDENT)) {
                BakedModel model;
                /*if (entity != null && entity.isUsingItem()) {
                    // Optional: check if it's currently being used
                    model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident_in_hand"), "inventory"));
                } else if (entity != null && entity.getMainHandStack() == stack) {
                    // Held in main hand
                    model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident_in_hand"), "inventory"));
                } else {
                    // Default item model (inventory/GUI)
                    model = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident"), "inventory"));
                }*

                cir.setReturnValue(model);
            }


            /*else if (stack.isOf(ItemFactory.ANCIENT_TRIDENT)) { //When Item Held
                System.out.println("Acient Trident - getModel");
                //BakedModel BbakedModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of("entstupidstuff", "trident_in_hand")));
                BakedModel BbakedModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident_in_hand"), "inventory"));
                ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
                BakedModel bakedModel2 = BbakedModel.getOverrides().apply(BbakedModel, stack, clientWorld, entity, seed);
                cir.setReturnValue(bakedModel2 == null ? ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getMissingModel() : bakedModel2);
            }*/

        
           /*if (stack.isOf(ItemFactory.callItem("ancient_trident"))) {
                /*BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel((ModelIdentifier.ofInventoryVariant(Identifier.of("entstupidstuff","ancient_trident_in_hand"))));
                ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
                BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);*

                BakedModel customModel



                //return bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2;
                cir.setReturnValue(bakedModel2);
                //ModelIdentifier.ofInventoryVariant(Identifier.ofVanilla("trident"));
                //TRIDENT_IN_HAND = ModelIdentifier.ofInventoryVariant(Identifier.ofVanilla("trident_in_hand"));
            
                //ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
                //BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
                //cir.setReturnValue(bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2);
            } */ 



            // Custom logic to modify the model based on the entity - THIS WORKED
            // Replace this with your custom logic
            /*if (entity.isSneaking() && stack.isOf(ModFood.CANNON_ITEM)) {
                // Example: change model if the entity is sneaking
                BakedModel customModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "diamond_hammer_model"), "inventory"));
                cir.setReturnValue(customModel);
            }*/
        }
        
        /*if (stack.isOf(ItemFactory.callItem("ancient_trident"))) {

            BakedModel bakedModel = ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(Identifier.of("entstupidstuff", "ancient_trident_in_hand"), "inventory"));
            System.out.println("TridentItremRender - Baked");
            System.out.println(bakedModel);

            //ModelIdentifier.ofInventoryVariant(Identifier.ofVanilla("trident_in_hand"));
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
            BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
            cir.setReturnValue(bakedModel2 == null ? ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getMissingModel() : bakedModel2);

            /*
            BakedModel bakedModel;
            if (stack.isOf(Items.TRIDENT)) {
                bakedModel = this.models.getModelManager().getModel(TRIDENT_IN_HAND);
            } else if (stack.isOf(Items.SPYGLASS)) {
                bakedModel = this.models.getModelManager().getModel(SPYGLASS_IN_HAND);
            } else {
                bakedModel = this.models.getModel(stack);
            }

            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
            BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
            return bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2;
            *    
        }*/




    
    }

}