package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

//public class SoulSkeletonGlowRender<T extends SoulSkeletonEntity> extends EyesFeatureRenderer<T, SkeletonEntityModel<T>> {

public class SoulSkeletonGlowRender<T extends MobEntity & RangedAttackMob, M extends EntityModel<T>> extends EyesFeatureRenderer<T, SkeletonEntityModel<T>> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of("entstupidstuff", "textures/entity/soul_skeleton_e.png"));

    public SoulSkeletonGlowRender(FeatureRendererContext<T, SkeletonEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }

}
