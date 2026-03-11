package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SilkmothEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SilkmothGlowRenderer;
import net.ent.entstupidstuff.client.render.entity.model.SilkmothModel;
import net.ent.entstupidstuff.client.render.entity.state.SilkmothRenderstate;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilkmothRenderer extends MobRenderer<SilkmothEntity, SilkmothRenderstate, SilkmothModel>{

    public SilkmothRenderer(Context context) {
        super(context, new SilkmothModel(context.bakeLayer(ModEntityModelLayers.SILKMOTH)), 1F); //Change
        this.addLayer(new SilkmothGlowRenderer(this));
    }

    public SilkmothRenderer(Context context, SilkmothModel entityModel, float f) {
        super(context, entityModel, f);
        this.addLayer(new SilkmothGlowRenderer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SilkmothRenderstate SilkmothRenderstate) {
        return ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/silkmoth/silkmoth_mushroom.png");
    }

    @Override
    public SilkmothRenderstate createRenderState() {
        return new SilkmothRenderstate();
    }
    
}
