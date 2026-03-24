package net.ent.entstupidstuff.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.api.emote.EmoteClientState;
import net.ent.entstupidstuff.api.emote.EmoteRegistry;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;

@Mixin(PlayerModel.class)
public class PlayerModelMixin {
 
    /**
     * Cache of baked animations for this model instance.
     * Populated on first use of each emote — never re-baked after that.
     */
    @Unique
    private final Map<String, KeyframeAnimation> hatsmod$bakedEmotes = new HashMap<>();
 
    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void hatsmod$applyEmote(AvatarRenderState renderState, CallbackInfo ci) {
        var entry = EmoteClientState.get(renderState.id);
        if (entry == null) return;
 
        var emoteEntry = EmoteRegistry.get(entry.emoteName());
        if (emoteEntry == null) return;
 
        var state = entry.animationState();
        if (!state.isStarted()) return;
 
        // For non-looping emotes, stop once the full duration has elapsed.
        // getTimeInMillis(ageInTicks) = (ageInTicks - startTick) * 50ms
        long elapsedMs = state.getTimeInMillis(renderState.ageInTicks);
        if (!emoteEntry.looping() && elapsedMs > (long)(emoteEntry.durationSeconds() * 1000.0f)) {
            EmoteClientState.stop(renderState.id);
            return;
        }
 
        // Bake the AnimationDefinition against this model instance's ModelParts
        // on first use. bake() walks the definition's bone name map and resolves
        // each name to the live ModelPart on this specific model object.
        KeyframeAnimation baked = hatsmod$bakedEmotes.computeIfAbsent(
            entry.emoteName(),
            name -> emoteEntry.definition().bake(
                ((PlayerModel)(Object) this).root()
            )
        );
 
        // apply(animationState, ageInTicks) drives the animation using the
        // elapsed time from animationState and ageInTicks for interpolation.
        baked.apply(state, renderState.ageInTicks);
    }
}
