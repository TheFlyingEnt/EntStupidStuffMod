package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.models.Mustang77EntityModel;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class Mustang77EntityRenderer extends BaseCarEntityRenderer<BaseCarEntity, BaseCarRenderState> {
 
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/mustang77/mustang_77.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/mustang77/mustang_77_lights.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/mustang77/mustang_77_lights.png");
 
    public Mustang77EntityRenderer(EntityRendererProvider.Context context) {
        super(context, new Mustang77EntityModel(context.bakeLayer(ModEntityModelLayers.MUSTANG77)));
    }
 
    @Override protected ResourceLocation texture(BaseCarRenderState state)            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture(BaseCarRenderState state)        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture(BaseCarRenderState state)  { return GLOW_BACKUP; }


}

