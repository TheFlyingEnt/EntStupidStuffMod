package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.HondaCivicTypeREntity;
import net.ent.entstupidstuff.api.car.models.AE68EntityModel;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class AE86EntityRenderer extends BaseCarEntityRenderer<HondaCivicTypeREntity, AE68RenderState> {

    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ae86/ae86.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ae86/ae86_glow.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ae86/ae86_glow.png");

    public AE86EntityRenderer(Context context) {
        super(context, new AE68EntityModel(context.bakeLayer(ModEntityModelLayers.HONDACIVICR)));
    }

    @Override protected ResourceLocation texture(AE68RenderState state)            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture(AE68RenderState state)        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture(AE68RenderState state)  { return GLOW_BACKUP; }

    @Override
    public AE68RenderState createRenderState() {
        return new AE68RenderState();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void extractRenderState(HondaCivicTypeREntity entity, AE68RenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        float f = entity.getLightLevelDependentMagicValue();
        if (f >= 0.5F) {
            state.popup = true;
        } else state.popup = false;

    }
    
}
