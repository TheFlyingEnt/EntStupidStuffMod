package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.effects.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    
    @Shadow @Final
    private MinecraftClient client;
    
    @Shadow
    private boolean postProcessorEnabled;
    
    @Shadow
    protected abstract void setPostProcessor(Identifier id);
    
    @Shadow
    protected abstract void clearPostProcessor();

    
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


        if (this.client.getCameraEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity.hasStatusEffect(ModEffects.RGB_SHIFT)) {
                // Apply RGB shift post-processor
                this.setPostProcessor(Identifier.of(EntStupidStuff.MOD_ID, "rgb_shift"));
            } else if (livingEntity.hasStatusEffect(ModEffects.BLUR)) {
                // Apply RGB shift post-processor
                this.setPostProcessor(Identifier.of(EntStupidStuff.MOD_ID, "blur"));
            } else if (livingEntity.hasStatusEffect(ModEffects.CREEPER)) {
                // Apply RGB shift post-processor
                this.setPostProcessor(Identifier.of(EntStupidStuff.MOD_ID, "creeper"));
            } else if (this.postProcessorEnabled) {
                // Clear if we had it enabled but effect is gone
                this.clearPostProcessor();
            }
        }

    }
}
