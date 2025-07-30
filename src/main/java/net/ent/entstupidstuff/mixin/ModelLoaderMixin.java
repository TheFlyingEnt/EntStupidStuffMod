package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 1))
    private void onInit(CallbackInfo ci) {
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "ancient_trident_in_hand")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "wooden_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "stone_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "golden_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "iron_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "diamond_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "netherite_hammer_model")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "netherite_hammer_model_piglin")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "golden_hammer_model_piglin")));

        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "phantom_bow")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "phantom_bow_pulling_0")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "phantom_bow_pulling_1")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "phantom_bow_pulling_2")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(EntStupidStuff.MOD_ID, "phantom_cutlass")));

    }
}

