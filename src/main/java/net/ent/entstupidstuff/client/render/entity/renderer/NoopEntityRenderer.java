package net.ent.entstupidstuff.client.render.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.ent.entstupidstuff.api.ship.ShipPartEntity;
import net.ent.entstupidstuff.client.render.entity.state.RedStoneGolemRenderState;

public class NoopEntityRenderer extends EntityRenderer<ShipPartEntity, EntityRenderState> {
    public NoopEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    /*@Override
    public ResourceLocation getTextureLocation(EntityRenderState state) {
        return ResourceLocation.withDefaultNamespace("textures/misc/white.png");
    }*/
    // No render() override — base class renders nothing visible
}
