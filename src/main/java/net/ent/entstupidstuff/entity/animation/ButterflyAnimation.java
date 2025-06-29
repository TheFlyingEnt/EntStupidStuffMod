package net.ent.entstupidstuff.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ButterflyAnimation {

    	public static final Animation IDLE = Animation.Builder.create(1.0F)//.looping()
		.addBoneAnimation("leftWing", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -37.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(10.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 37.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(20.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -37.5F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("rightWing", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 37.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(10.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -37.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(10.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 37.5F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("butterfly", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(10.0F, AnimationHelper.createTranslationalVector(0.0F, 5.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(20.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation SITTING = Animation.Builder.create(0.0F)//.looping()
		.addBoneAnimation("leftWing", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -75.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("rightWing", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 75.0F), Transformation.Interpolations.LINEAR)
		))
		.build();
    
}
