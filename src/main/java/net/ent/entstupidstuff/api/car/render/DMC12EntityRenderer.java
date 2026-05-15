package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.models.DMCModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DMC12EntityRenderer extends BaseCarEntityRenderer {
 
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13_glow.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13_glow_backup.png");
 
    public DMC12EntityRenderer(EntityRendererProvider.Context context) {
        super(context, new DMCModel(context.bakeLayer(DMCModel.LAYER_LOCATION)));
    }
 
    @Override protected ResourceLocation texture(CarRenderState state)            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture(CarRenderState state)        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture(CarRenderState state)  { return GLOW_BACKUP; }
}
