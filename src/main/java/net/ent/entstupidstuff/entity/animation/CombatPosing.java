package net.ent.entstupidstuff.entity.animation;

import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class CombatPosing {

    public static void longsword(ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed) {
		ModelPart modelPart = rightArmed ? holdingArm : otherArm; //Holding Hand
		ModelPart modelPart2 = rightArmed ? otherArm : holdingArm;
		modelPart.yaw = (rightArmed ? /*-0.3F*/-17.5F : 17.5F) + head.yaw;
		modelPart2.yaw = (rightArmed ? 17.5F : -17.5F) + head.yaw;
		modelPart.pitch = (float) (-Math.PI / 2) + head.pitch + 0.1F;
		modelPart2.pitch = (float) (-Math.PI / 2) + head.pitch + 0.1F;;
	}

}
