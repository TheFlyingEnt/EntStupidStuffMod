package net.ent.entstupidstuff.mixin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.model.HumanoidModel;

@Mixin(HumanoidModel.ArmPose.class)
public class ArmPoseMixin {
    @Invoker("<init>")
    public static HumanoidModel.ArmPose createArmPose(String name, int ordinal, boolean twoHanded) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable
    private static HumanoidModel.ArmPose[] $VALUES;

    // Use a unique name here to avoid conflicts
    @Unique
    public static HumanoidModel.ArmPose ent$addPose(String name, boolean twoHanded) {
        List<HumanoidModel.ArmPose> poses = new ArrayList<>(Arrays.asList($VALUES));
        HumanoidModel.ArmPose newPose = createArmPose(name, poses.get(poses.size() - 1).ordinal() + 1, twoHanded);
        poses.add(newPose);
        $VALUES = poses.toArray(new HumanoidModel.ArmPose[0]);
        return newPose;
    }
}
