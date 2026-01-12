package net.ent.entstupidstuff.client.entity.animation;

import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class CombatPosing {

    public static void longsword(ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed) {
		ModelPart modelPart = rightArmed ? holdingArm : otherArm; //Holding Hand
		ModelPart modelPart2 = rightArmed ? otherArm : holdingArm;
		modelPart.yRot = (rightArmed ? /*-0.3F*/-17.5F : 17.5F) + head.yRot;
		modelPart2.yRot = (rightArmed ? 17.5F : -17.5F) + head.yRot;
		modelPart.xRot = (float) (-Math.PI / 2) + head.xRot + 0.1F;
		modelPart2.xRot = (float) (-Math.PI / 2) + head.xRot + 0.1F;;
	}

}
