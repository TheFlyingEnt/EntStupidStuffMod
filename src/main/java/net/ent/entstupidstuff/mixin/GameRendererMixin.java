package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    
    @Shadow @Final
    private Minecraft minecraft;
    
    @Shadow
    private boolean effectActive;
    
    @Shadow
    protected abstract void setPostEffect(ResourceLocation resourceLocation); //private void setPostEffect(ResourceLocation resourceLocation)
    
    @Shadow
    protected abstract void clearPostEffect(); //public void clearPostEffect() {

    
    /**
     * Inject into the tick method to check for RGB shift effect
     * and apply the post-processor accordingly
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {

        /*if (this.client.getCameraEntity() instanceof LivingEntity livingEntity) {
            boolean hasEffect = livingEntity.hasStatusEffect(ModEffects.RGB_SHIFT);

            if (hasEffect && !this.postProcessorEnabled) {
                // Activate the shader if not already active
                this.setPostProcessor(Identifier.of(EntStupidStuff.MOD_ID, "rgb_shift"));
                this.postProcessorEnabled = true;
            } else if (!hasEffect && this.postProcessorEnabled) {
                // Remove the shader when the effect ends
                this.clearPostProcessor();
                this.postProcessorEnabled = false;
            }
        }*/


        if (this.minecraft.getCameraEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(ModEffects.RGB_SHIFT)) {
                // Apply RGB shift post-processor
                this.setPostEffect(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "rgb_shift"));
            } else if (livingEntity.hasEffect(ModEffects.BLUR)) {
                // Apply RGB shift post-processor
                this.setPostEffect(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "blur"));
            } else if (livingEntity.hasEffect(ModEffects.CREEPER)) {
                // Apply RGB shift post-processor
                this.setPostEffect(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "creeper"));
            } else if (livingEntity.hasEffect(ModEffects.HALLUC)) {
                // Apply RGB shift post-processor
                this.setPostEffect(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hallucinating"));
            } else if (this.effectActive) {
                // Clear if we had it enabled but effect is gone
                this.clearPostEffect();
            }
        }

    }
}
