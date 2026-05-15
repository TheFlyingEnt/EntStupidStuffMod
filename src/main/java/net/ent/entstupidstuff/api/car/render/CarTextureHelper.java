package net.ent.entstupidstuff.api.car.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.item.base.car.LicensePlateItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
 
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
 
/**
 * Utility for wrap textures and license plate rendering.
 *
 * WRAP INTEGRATION — update your existing renderers like this:
 *
 * For BaseCarEntityRenderer subclasses (DMC12, GR86, etc.):
 *   Change texture() from a static field to a dynamic lookup:
 *
 *   // OLD:
 *   @Override protected ResourceLocation texture() { return TEXTURE; }
 *
 *   // NEW:
 *   private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(...)
 *   @Override protected ResourceLocation texture() { return DEFAULT_TEXTURE; }
 *
 *   Then override renderType() in BaseCarEntityRenderer to support wraps:
 *   (see example below)
 *
 * For F1CarEntityRenderer (variant-based):
 *   The variant system already handles texture selection.
 *   Wraps are an ALTERNATIVE to variants — if a wrap is set, it overrides.
 *
 * ────────────────────────────────────────────────────────────────────
 *
 * EXAMPLE: Adding wrap support to BaseCarEntityRenderer.
 *
 * Add this field + method to BaseCarEntityRenderer:
 *
 *   // In extractRenderState(), add:
 *   state.wrapId = entity.getCurrentWrap();
 *   state.carTypeId = entity.getCarTypeId();
 *
 *   // Change renderType() to:
 *   protected RenderType renderType(CarRenderState state) {
 *       if (state.wrapId != null && !state.wrapId.equals("default")) {
 *           ResourceLocation wrapTex = CarTextureHelper.getWrapTexture(state.carTypeId, state.wrapId);
 *           return this.model.renderType(wrapTex);
 *       }
 *       return this.model.renderType(texture());
 *   }
 *
 *   // Update submit() to pass state:
 *   this.renderType(state) instead of this.renderType()
 *
 * And add to CarRenderState:
 *   public String wrapId = "default";
 *   public String carTypeId = "car";
 */
public final class CarTextureHelper {
 
    /**
     * Returns the texture ResourceLocation for a car's current wrap.
     * Path: assets/entstupidstuff/textures/entity/{carTypeId}/{wrapId}.png
     */
    public static ResourceLocation getWrapTexture(String carTypeId, String wrapId) {
        if (wrapId == null || wrapId.isEmpty()) wrapId = "default";
        return ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,
            "textures/entity/" + carTypeId + "/" + wrapId + ".png");
    }
 
    /**
     * Renders the license plate text on the car's rear face.
     *
     * Call from your entity renderer's submit() method after positioning
     * the PoseStack to the plate location. The text is rendered as white
     * characters centered horizontally.
     *
     * @param plateStack the license plate item from car.getLicensePlate()
     * @param pose       PoseStack positioned at plate location on car
     * @param buffers    render buffer source
     * @param light      packed light value
     * @param font       the font renderer
     */
    public static void renderLicensePlate(ItemStack plateStack, PoseStack pose,
                                           MultiBufferSource buffers, int light, Font font) {
        if (plateStack.isEmpty()) return;
 
        String text = LicensePlateItem.getPlateText(plateStack);
        if (text.isEmpty()) return;
 
        pose.pushPose();
        pose.scale(0.015f, -0.015f, 0.015f);
        pose.mulPose(Axis.YP.rotationDegrees(180f));
 
        float textWidth = font.width(text);
        float x = -textWidth / 2f;
 
        font.drawInBatch(text, x, 0f, 0xFFFFFFFF, false,
            pose.last().pose(), buffers, Font.DisplayMode.NORMAL, 0, light);
 
        pose.popPose();
    }
 
    private CarTextureHelper() {}
}
