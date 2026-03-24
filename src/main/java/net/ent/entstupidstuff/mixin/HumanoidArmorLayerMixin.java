package net.ent.entstupidstuff.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.casting.ArmorCastingComponent;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<S extends HumanoidRenderState> {

    @Unique
    private ItemStack entstupidstuff$currentItemStack = ItemStack.EMPTY;

    // Capture the itemStack before renderLayers is called
    @Inject(method = "renderArmorPiece", at = @At("HEAD"))
    private void captureItemStack(
        PoseStack poseStack, SubmitNodeCollector collector,
        ItemStack itemStack, EquipmentSlot slot, int light, S renderState,
        CallbackInfo ci
    ) {
        this.entstupidstuff$currentItemStack = itemStack;
    }

    // Redirect the orElseThrow() call that produces the ResourceKey<EquipmentAsset>
    @Redirect(
        method = "renderArmorPiece",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Optional;orElseThrow()Ljava/lang/Object;"
        )
    )
    private Object redirectAssetKey(Optional<?> optional) {
        ResourceKey<EquipmentAsset> originalKey = (ResourceKey<EquipmentAsset>) optional.orElseThrow();
        //ResourceLocation castId = entstupidstuff$currentItemStack.get(ModDataComponentTypes.ARMOR_CAST);
        ArmorCastingComponent castId = entstupidstuff$currentItemStack.get(ModDataComponentTypes.ARMOR_CAST);
        if (castId != null) {
            String originalPath = originalKey.location().getPath();
            String castName = castId.castId().getPath();
            //String castName = castId.getPath();
            return ResourceKey.create(
                EquipmentAssets.ROOT_ID,
                ResourceLocation.fromNamespaceAndPath(
                    EntStupidStuff.MOD_ID,
                    originalPath + "_" + castName
                )
            );
        }
        return originalKey;
    }
}
