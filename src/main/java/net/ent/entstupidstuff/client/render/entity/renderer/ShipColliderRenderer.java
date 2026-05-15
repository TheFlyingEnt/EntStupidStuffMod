package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.api.ship.ShipCollider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

/**
 * Renders nothing — the collider exists only as an invisible solid for
 * walking on. We still need a real renderer registered, otherwise the
 * EntityRenderDispatcher returns null and crashes the render loop.
 */
public class ShipColliderRenderer
        extends EntityRenderer<ShipCollider, EntityRenderState> {

    public ShipColliderRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0f; // no shadow either
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void extractRenderState(ShipCollider entity,
                                   EntityRenderState state,
                                   float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }


}