package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class MinistrosityRenderer {

    private static final ResourceLocation BLACKSTONE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/blackstone_ministrosity.png");
    private static final ResourceLocation BLACKSTONE_ALT = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/blackstone_ministrosity_alt.png");
    private static final ResourceLocation BLACKSTONE_ALT2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/blackstone_ministrosity_alt_2.png");

    private static final ResourceLocation DE_REDSTONE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/deactivted_redstone_ministrosity.png");
    private static final ResourceLocation DEEPSLATE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/deepslate_ministrosity.png");

    private static final ResourceLocation GLOWING = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/glowing_ministrosity.png");
    private static final ResourceLocation REDSTONE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/redstone_ministrosity.png");
    private static final ResourceLocation STONE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/stone_ministrosity.png");
    private static final ResourceLocation UNUSED = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/used_ministrosity.png");
    private static final ResourceLocation OBSIDIAN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/obsidian_ministrosity.png");

    private static final ResourceLocation GLOWING_GLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/glowing_ministrosity_glow.png");
    private static final ResourceLocation REDSTONE_GLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/redstone_ministrosity_glow.png");
    private static final ResourceLocation GENERIC_GLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/ministrosity_glow.png");
    private static final ResourceLocation OBSIDIAN_GLOW = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/ministrosity/obsidian_ministrosity_glow.png");


    //@Override
    public ResourceLocation getTextureLocation(String state) {
        switch(state) {
            case "blackstone":
                return BLACKSTONE;
            case "blackstone_alt":
                return BLACKSTONE_ALT;
            case "blackstone_alt_2":
                return BLACKSTONE_ALT2;
            case "deactivted":
                return DE_REDSTONE;
            case "deepslate":
                return DEEPSLATE;
            case "glowing":
                return GLOWING;
            case "redstone":
                return REDSTONE;
            case "stone":
                return STONE;
            case "used":
                return UNUSED;
            case "obsidian":
                return OBSIDIAN;
            default:
                return STONE;
        }
    }

    public ResourceLocation getGlowTextureLocation(String state) {

        if (state == "redstone") {
            return REDSTONE_GLOW;
        } else if (state == "glowing") {
            return GLOWING_GLOW;
        } else if (state == "obsidian") {
            return OBSIDIAN_GLOW;
        } else {
            return GENERIC_GLOW;
        }    
    }


    /*&public void submit(ZombieRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {

        //super.submit(state, poseStack, collector, cameraState);

        int alphaByte = Math.round(1 * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;

        collector.order(1)
			.submitModel(
				this.getModel(), state, poseStack, RenderType.eyes(GLOW_TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);


    }*/
    
}
