package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.models.NissanZEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NissanZEntityRenderer extends BaseCarEntityRenderer<BaseCarEntity, BaseCarRenderState> {
 
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/nissanz.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/nissanz_glow.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/nissanz_glow_reverse.png");
 
    public NissanZEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new NissanZEntityModel(context.bakeLayer(NissanZEntityModel.LAYER_LOCATION)));
    }
 
    @Override protected ResourceLocation texture(BaseCarRenderState state)            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture(BaseCarRenderState state)        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture(BaseCarRenderState state)  { return GLOW_BACKUP; }
}
