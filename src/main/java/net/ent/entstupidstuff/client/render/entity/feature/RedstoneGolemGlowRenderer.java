package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.RedStoneGolemModelNew;
import net.ent.entstupidstuff.client.render.entity.state.RedStoneGolemRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RedstoneGolemGlowRenderer extends RenderLayer<RedStoneGolemRenderState, RedStoneGolemModelNew> {

    private static final ResourceLocation GLOW_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/redstone_golem_glow_body.png");
    private static final float PULSE_CYCLE_MS = 2000.0f;
    private static final float ALPHA_MIN = 0.1f;
    private static final float ALPHA_MAX = 1.0f;

    public RedstoneGolemGlowRenderer(RenderLayerParent<RedStoneGolemRenderState, RedStoneGolemModelNew> context) {
        super(context);
    }

    @Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, RedStoneGolemRenderState entityRenderState, float f, float g) {

        if (entityRenderState.isInvisible) return;

        float time = (System.currentTimeMillis() % (long) PULSE_CYCLE_MS) / PULSE_CYCLE_MS; // 0.0 -> 1.0
        float sine = (float) Math.sin(time * Math.PI * 2.0);                                 // -1.0 -> 1.0
        float t = (sine + 1.0f) / 2.0f;                                                      //  0.0 -> 1.0
        float alpha = ALPHA_MIN + t * (ALPHA_MAX - ALPHA_MIN);

        int alphaByte = Math.round(alpha * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;

		submitNodeCollector.order(1)
			.submitModel(
				this.getParentModel(), entityRenderState, poseStack, RenderType.eyes(GLOW_TEXTURE), i, OverlayTexture.NO_OVERLAY, color, null, entityRenderState.outlineColor, null
			);
	}
}
