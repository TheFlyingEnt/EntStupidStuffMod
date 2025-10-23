package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class AncientDrownedRenderer extends ZombieBaseEntityRenderer<DrownedEntity, ZombieEntityRenderState, AncientDrownedModel>{
   private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned.png");
   //private static final Identifier GLOW_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ancient_drowned_e.png");

   public AncientDrownedRenderer(EntityRendererFactory.Context context) {
		super(
			context,
			new AncientDrownedModel(context.getPart(ModEntityModelLayers.ANCIENT_DROWNED_OUTER)),
			new AncientDrownedModel(context.getPart(ModEntityModelLayers.ANCIENT_DROWNED_OUTER)),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_EQUIPMENT, context.getEntityModels(), AncientDrownedModel::new),
			EquipmentModelData.mapToEntityModel(EntityModelLayers.DROWNED_BABY_EQUIPMENT, context.getEntityModels(), AncientDrownedModel::new)
		);
		this.addFeature(new AncientDrownedOverlayFeatureRenderer(this, context.getEntityModels()));
	}

   public ZombieEntityRenderState createRenderState() {
		return new ZombieEntityRenderState();
	}

	@Override
	public Identifier getTexture(ZombieEntityRenderState zombieEntityRenderState) {
		return TEXTURE;
	}

	protected void setupTransforms(ZombieEntityRenderState zombieEntityRenderState, MatrixStack matrixStack, float f, float g) {
		super.setupTransforms(zombieEntityRenderState, matrixStack, f, g);
		float h = zombieEntityRenderState.leaningPitch;
		if (h > 0.0F) {
			float i = -10.0F - zombieEntityRenderState.pitch;
			float j = MathHelper.lerp(h, 0.0F, i);
			matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(j), 0.0F, zombieEntityRenderState.height / 2.0F / g, 0.0F);
		}
	}

	protected BipedEntityModel.ArmPose getArmPose(DrownedEntity drownedEntity, Arm arm) {
		ItemStack itemStack = drownedEntity.getStackInArm(arm);
		return drownedEntity.getMainArm() == arm && drownedEntity.isAttacking() && itemStack.isOf(Items.TRIDENT)
			? BipedEntityModel.ArmPose.THROW_SPEAR
			: BipedEntityModel.ArmPose.EMPTY;
	}
}
