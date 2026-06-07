package net.ent.entstupidstuff.api.hat;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class HatRenderLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
 
    /** Reused across frames — safe because client rendering is single-threaded. */
    private final ItemStackRenderState hatRenderState = new ItemStackRenderState();
 
    public HatRenderLayer(RenderLayerParent<AvatarRenderState, PlayerModel> parent) {
        super(parent);
    }
 
    @Override
    public void submit(
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int packedLight,
        AvatarRenderState renderState,
        float f,
        float g
    ) {
        // ── 1. Resolve hat from the live player entity ────────────────────────
        var mc = Minecraft.getInstance();
        var level = mc.level;
        if (level == null) return;
 
        var entity = level.getEntity(renderState.id);
        if (!(entity instanceof Player player)) return;
 
        String hatName = player.getAttachedOrElse(ModAttachments.HAT, "");
        if (hatName.isEmpty()) return;
 
        Item hatItem = HatRegistry.getHat(hatName);
        if (hatItem == null) return;
 
        ItemStack stack = new ItemStack(hatItem);
 
        // ── 2. Populate ItemStackRenderState ──────────────────────────────────
        mc.getItemModelResolver().updateForTopItem(
            hatRenderState,
            stack,
            ItemDisplayContext.HEAD,
            level,
            player,
            player.getId() + ItemDisplayContext.HEAD.ordinal()
        );
 
        if (hatRenderState.isEmpty()) return;
 
        // ── 3. Render in head-bone local space ────────────────────────────────
        poseStack.pushPose();
 
        // Move into head-bone space (applies head yaw/pitch rotation).
        getParentModel().head.translateAndRotate(poseStack);
 
        // Apply vanilla's standard head-item transform:
        //   translate(0, -0.25, 0)
        //   rotate 180° around Y
        //   scale(0.625, -0.625, -0.625)
        //
        // The negative Y and Z scales are critical — they cause the normal matrix
        // (inverse transpose of the pose matrix) to flip those axes back into the
        // correct orientation for directional lighting, which is why vanilla uses
        // this exact path in CustomHeadLayer rather than a manual normal reset.
        CustomHeadLayer.translateToHead(poseStack, CustomHeadLayer.Transforms.DEFAULT);
        poseStack.last().normal().identity();
 
        hatRenderState.submit(
            poseStack,
            submitNodeCollector,
            packedLight,
            OverlayTexture.NO_OVERLAY,
            renderState.outlineColor
        );
 
        poseStack.popPose();
    }
}
 