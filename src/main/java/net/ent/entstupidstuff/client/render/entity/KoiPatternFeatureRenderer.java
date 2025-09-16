package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.client.render.entity.model.KoiModel;
import net.ent.entstupidstuff.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.entity.passive.KoiEntity;
import net.ent.entstupidstuff.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.entity.passive.KoiVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KoiPatternFeatureRenderer
        extends FeatureRenderer<KoiEntity, KoiModel<KoiEntity>> {

    public KoiPatternFeatureRenderer(FeatureRendererContext<KoiEntity, KoiModel<KoiEntity>> ctx) {
        super(ctx);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, KoiEntity koi, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) 
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
        }
        

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
        }*/
    }
}
