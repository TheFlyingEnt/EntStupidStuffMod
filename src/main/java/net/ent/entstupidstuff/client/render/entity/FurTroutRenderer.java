package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.FurTroutModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class FurTroutRenderer extends MobEntityRenderer<CodEntity, FurTroutModel<CodEntity>>{
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/furtrout.png");

    public FurTroutRenderer(EntityRendererFactory.Context context) {
        super(context, new FurTroutModel(context.getPart(ModEntityModelLayers.FURTROUT)), 0.3F);
    }

    @Override
    public Identifier getTexture(CodEntity entity) {
        return TEXTURE;
    }

    protected void setupTransforms(CodEntity fishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(fishEntity, matrixStack, f, g, h, i);
        float j = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
        if (!fishEntity.isTouchingWater()) {
            matrixStack.translate(0.1F, 0.1F, -0.1F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }

    }
    
}
