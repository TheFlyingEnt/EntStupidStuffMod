package net.ent.entstupidstuff.api.ship;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * A single chain link — a small rectangular ring made of 4 bars.
 * Rendered at ~0.2 scale and alternated 90° per link, it creates
 * the interlocking chain pattern from anchor to ship.
 */
public class ChainLinkModel {

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Rectangular ring: 2 vertical sides + 2 horizontal bars
        // Total size ~4×6×1 pixels — renders tiny after scaling
        /*root.addOrReplaceChild("left",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-2f, -3f, -0.5f, 1f, 6f, 1f),
            PartPose.ZERO);
        root.addOrReplaceChild("right",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(1f, -3f, -0.5f, 1f, 6f, 1f),
            PartPose.ZERO);
        root.addOrReplaceChild("top",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-2f, -3f, -0.5f, 4f, 1f, 1f),
            PartPose.ZERO);
        root.addOrReplaceChild("bottom",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-2f, 2f, -0.5f, 4f, 1f, 1f),
            PartPose.ZERO);*/

        root.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(6, 3).addBox(0.0F, -1.5F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 1).addBox(0.0F, -3.5F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 1).addBox(0.0F, 1.5F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 10).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(6, 6).addBox(-1.5F, -3.5F, -1.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(6, 1).addBox(0.0F, 1.5F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 10).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(6, 3).addBox(0.0F, -1.5F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.5F, 1.5F));


        return LayerDefinition.create(mesh, 16, 16);
    }
}