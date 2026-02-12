package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.client.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.client.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.client.render.entity.model.fish.KoiModel;
import net.ent.entstupidstuff.client.render.entity.state.KoiEntityRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class KoiPatternFeatureRenderer
        extends RenderLayer<KoiEntityRenderState, KoiModel> {

    public KoiPatternFeatureRenderer(
            RenderLayerParent<KoiEntityRenderState, KoiModel> ctx) {
        super(ctx);
    }

    @Override
    public void submit(
            PoseStack matrices,
            SubmitNodeCollector queue,
            int light,
            KoiEntityRenderState state,
            float limbAngle,
            float limbDistance
    ) {
        if (state.invisible) return;

        KoiVariant variant = state.variant;
        if (variant == null) return;

        /* ------------------------------------------------------------
         *  MAIN (Kohaku) PATTERN — White only
         * ------------------------------------------------------------ */

        if (variant.getBaseColor() == KoiBaseColor.WHITE
                && variant.getMainPattern() != null) {

            ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(
                    "entstupidstuff",
                    "textures/entity/koi/pattern_kohaku_"
                            + variant.getMainPattern().getName().toLowerCase()
                            + ".png"
            );

            queue.order(1).submitModel(
                    this.getParentModel(),
                    state,
                    matrices,
                    RenderType.entityTranslucent(tex),
                    light,
                    OverlayTexture.NO_OVERLAY,
                    -1,
                    null,
                    state.outlineColor,
                    null
            );
        }

        /* ------------------------------------------------------------
         *  SECONDARY PATTERN (Sanke / Showa)
         * ------------------------------------------------------------ */

        if (variant.getSecondaryPattern() != null) {
            KoiPatternSecondary sec = variant.getSecondaryPattern();

            ResourceLocation tex = switch (sec.getType()) {
                case "sanke" -> ResourceLocation.fromNamespaceAndPath(
                        "entstupidstuff",
                        "textures/entity/koi/pattern_sanke_"
                                + sec.getName().toLowerCase()
                                + ".png"
                );
                case "showa" -> ResourceLocation.fromNamespaceAndPath(
                        "entstupidstuff",
                        "textures/entity/koi/pattern_showa_"
                                + sec.getName().toLowerCase()
                                + ".png"
                );
                default -> null;
            };

            if (tex != null) {
                queue.order(1).submitModel(
                        this.getParentModel(),
                        state,
                        matrices,
                        RenderType.entityTranslucent(tex),
                        light,
                        OverlayTexture.NO_OVERLAY,
                        -1,
                        null,
                        state.outlineColor,
                        null
                );
            }
        }
    }
}



/*public class KoiPatternFeatureRenderer extends FeatureRenderer<KoiEntityRenderState, KoiModel> {

    public KoiPatternFeatureRenderer(FeatureRendererContext<KoiEntityRenderState, KoiModel> ctx) {
        super(ctx);
    }

    @Override
    public void submit(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, KoiEntityRenderState state, float limbAngle, float limbDistance) {
        if (state.invisible) return;

        KoiVariant variant = state.variant;
        if (variant.getBaseColor() == KoiBaseColor.WHITE && variant.getPatternKohaku() != null) {
            Identifier tex = Identifier.of("entstupidstuff",
                    "textures/entity/koi/pattern_kohaku_" + variant.getPatternKohaku().getName().toLowerCase() + ".png");
            queue.getBatchingQueue(1)
                 .submitModel(this.getContextModel(), state, matrices, RenderLayer.getEntityTranslucent(tex), light, 0, -1, null, state.outlineColor, null);
        }

        if (variant.getSecondaryPattern() != null) {
            KoiPatternSecondary sec = variant.getSecondaryPattern();
            String type = sec.getType();
            String name = sec.getName().toLowerCase();

            Identifier tex = null;
            if ("sanke".equals(type)) {
                tex = Identifier.of("entstupidstuff", "textures/entity/koi/pattern_sanke_" + name + ".png");
            } else if ("showa".equals(type)) {
                tex = Identifier.of("entstupidstuff", "textures/entity/koi/pattern_showa_" + name + ".png");
            }

            if (tex != null) {
                //queue.getBatchingQueue(1)
                //     .submitModel(this.getContextModel(), state, matrices, RenderLayer.getEntityTranslucent(tex), light, 0, /*-1*0xFFFFFFFF, null, state.outlineColor, null);
                queue.getBatchingQueue(1)
                    .submitModel(this.getContextModel(), state, matrices, RenderLayer.getEntityTranslucent(tex), light, OverlayTexture.getUv(0.0F, false), -1, null, state.outlineColor, null);
            }
        }

    }



    /*

    @Override
    public void submit(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, KoiEntity koi, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) 
    {
        if (koi.isInvisible()) 
            return;

        KoiVariant variant = koi.getVariant();

        // Kohaku (only if base is white)
        if (variant.getBaseColor() == KoiBaseColor.WHITE && variant.getPatternKohaku() != null) {
            Identifier tex = Identifier.of("entstupidstuff",
                    "textures/entity/koi/pattern_kohaku_" + variant.getPatternKohaku().getName() + ".png");
            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex));
            this.getContextModel().render(matrices, vc, light, LivingEntityRenderer.getOverlay(koi, 0.0F));
        }

        // Secondary patterns (sanke / showa)
        if (variant.getSecondaryPattern() != null) {
            KoiPatternSecondary sec = variant.getSecondaryPattern();

            String type = sec.getType();
            if ("sanka".equals(type)) {
                Identifier tex = Identifier.of("entstupidstuff", "textures/entity/koi/pattern_sanke_" + sec.getName().toLowerCase() + ".png");
                VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex));
                this.getContextModel().render(matrices, vc, light, LivingEntityRenderer.getOverlay(koi, 0.0F));
            } else if ("showa".equals(type)) {
                Identifier tex = Identifier.of("entstupidstuff", "textures/entity/koi/pattern_showa_" + sec.getName().toLowerCase() + ".png");
                VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex));
                this.getContextModel().render(matrices, vc, light, LivingEntityRenderer.getOverlay(koi, 0.0F));
            }
        } */
        

        /*LegacyKoiVariant variant = koi.getVariant();

        // --- Pattern 1 ---
        if (variant.getPattern() == LegacyKoiPattern.PATTERN_1) {
            Identifier tex = Identifier.of("entstupidstuff",
                    "textures/entity/koi/pattern_" + variant.getPatternColor1().get().getName() + ".png");

            VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex));
            this.getContextModel().render(matrices, vc, light, LivingEntityRenderer.getOverlay(koi, 0.0F));
        }

        // --- Pattern 2 ---
        if (variant.getPattern() == LegacyKoiPattern.PATTERN_2) {
            Identifier tex1 = Identifier.of("entstupidstuff",
                    "textures/entity/koi/pattern_main_" + variant.getPatternColor1().get().getName() + ".png");
            Identifier tex2 = Identifier.of("entstupidstuff",
                    "textures/entity/koi/pattern_secondary_" + variant.getPatternColor2().get().getName() + ".png");

            VertexConsumer vc1 = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex1));
            this.getContextModel().render(matrices, vc1, light, LivingEntityRenderer.getOverlay(koi, 0.0F));

            VertexConsumer vc2 = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(tex2));
            this.getContextModel().render(matrices, vc2, light, LivingEntityRenderer.getOverlay(koi, 0.0F));
        }
    }*/
//}
