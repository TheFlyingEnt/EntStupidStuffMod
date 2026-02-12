package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonBow;
import net.ent.entstupidstuff.client.render.entity.state.SunkenSkeletonRenderSlate;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SunkenSkeletonEntityRenderer extends AbstractSkeletonRenderer<GenericSkeletonBow, SunkenSkeletonRenderSlate> {

    public SunkenSkeletonEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModEntityModelLayers.SUNKEN_SKELTON, ModelLayers.SKELETON_ARMOR);
	}

    public SunkenSkeletonEntityRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer) {
		super(context, layer, ModelLayers.SKELETON_ARMOR);
	}

    public SunkenSkeletonEntityRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, ArmorModelSet<ModelLayerLocation> equipmentModelData) {
		super(context, layer, equipmentModelData);
	}

    @Override
    public ResourceLocation getTextureLocation(SunkenSkeletonRenderSlate state) {
        return switch (state.variant) {
            case Variant1 -> ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/sunken_skeleton_1.png");
            case Variant2 -> ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/sunken_skeleton_2.png");
            default -> ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/sunken_skeleton_3.png");
        };
    }

    @Override
    public SunkenSkeletonRenderSlate createRenderState() {
        return new SunkenSkeletonRenderSlate();
    }
}
