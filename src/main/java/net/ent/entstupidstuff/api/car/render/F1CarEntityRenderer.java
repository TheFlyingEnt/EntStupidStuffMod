package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.models.F1CarEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class F1CarEntityRenderer extends BaseCarEntityRenderer {
 
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/concept_f1.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/nissanz_glow.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/nissanz_glow_reverse.png");
 
    public F1CarEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new F1CarEntityModel(context.bakeLayer(F1CarEntityModel.LAYER_LOCATION)));
    }
 
    @Override protected ResourceLocation texture()            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture()        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture()  { return GLOW_BACKUP; }
}
