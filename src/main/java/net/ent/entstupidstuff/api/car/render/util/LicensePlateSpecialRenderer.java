package net.ent.entstupidstuff.api.car.render.util;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.item.base.car.LicensePlateItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class LicensePlateSpecialRenderer {

    public void render(
            ItemStack stack,
            ItemDisplayContext context,
            PoseStack pose,
            MultiBufferSource buffers,
            int light,
            int overlay
    ) {

        // Render normal flat item first

        Minecraft mc = Minecraft.getInstance();

        /*mc.getItemRenderer().rend .renderStatic(
                stack,
                context,
                light,
                overlay,
                pose,
                buffers,
                mc.level,
                0
        );*/

        // Plate text
        String text = LicensePlateItem.getPlateText(stack);

        Font font = mc.font;

        pose.pushPose();

        pose.translate(0.5f, 0.5f, 0.01f);

        float scale = 0.01f;
        pose.scale(scale, -scale, scale);

        float width = font.width(text);

        font.drawInBatch(
                text,
                -width / 2f,
                0,
                0xFF111111,
                false,
                pose.last().pose(),
                buffers,
                Font.DisplayMode.NORMAL,
                0,
                light
        );

        pose.popPose();
    }
}
