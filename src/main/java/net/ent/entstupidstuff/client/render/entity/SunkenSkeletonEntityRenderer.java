package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.state.SunkenSkeletonRenderSlate;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonBow;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.util.Identifier;

public class SunkenSkeletonEntityRenderer extends AbstractSkeletonEntityRenderer<GenericSkeletonBow, SunkenSkeletonRenderSlate> {

    public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		super(context, ModEntityModelLayers.SUNKEN_SKELTON, EntityModelLayers.SKELETON_EQUIPMENT);
	}

    public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) {
		super(context, layer, EntityModelLayers.SKELETON_EQUIPMENT);
	}

    public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer, EquipmentModelData<EntityModelLayer> equipmentModelData) {
		super(context, layer, equipmentModelData);
	}

    @Override
    public Identifier getTexture(SunkenSkeletonRenderSlate state) {
        return switch (state.variant) {
            case Variant1 -> Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_1.png");
            case Variant2 -> Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_2.png");
            default -> Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png");
        };
    }

    @Override
    public SunkenSkeletonRenderSlate createRenderState() {
        return new SunkenSkeletonRenderSlate();
    }
}
