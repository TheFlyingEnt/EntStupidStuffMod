package net.ent.entstupidstuff.client.render.entity;

import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.KoiModel;
import net.ent.entstupidstuff.entity.passive.KoiEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class KoiRenderer extends MobEntityRenderer<KoiEntity, KoiModel<KoiEntity>> {
    //private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi.png");

    private static final Map<KoiColor, Identifier> BASE_TEXTURES = Map.of(
        KoiColor.WHITE, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_white.png"),
        KoiColor.RED, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_red.png"),
        KoiColor.ORANGE, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_orange.png"),
        KoiColor.YELLOW, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_yellow.png"),
        KoiColor.BLACK, Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/koi/base_black.png")
    );

    public KoiRenderer(EntityRendererFactory.Context context) {
       super(context, new KoiModel(context.getPart(ModEntityModelLayers.KOI)), 0.3F);
       this.addFeature(new KoiPatternFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(KoiEntity entity) {
        KoiVariant variant = entity.getVariantObject();
        return BASE_TEXTURES.get(variant.getBase());
    }

    protected void setupTransforms(KoiEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
        float j = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
        if (!fishEntity.isTouchingWater()) {
            matrixStack.translate(0.1F, 0.1F, -0.1F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }

    }


    
}
