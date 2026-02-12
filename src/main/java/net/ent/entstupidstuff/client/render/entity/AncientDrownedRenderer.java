package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.feature.AncientDrownedOverlayFeatureRenderer;
import net.ent.entstupidstuff.client.render.entity.model.AncientDrownedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AncientDrownedRenderer extends AbstractZombieRenderer<Drowned, ZombieRenderState, AncientDrownedModel>{
   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/zombie/ancient_drowned.png");

   public AncientDrownedRenderer(EntityRendererProvider.Context context) {
		super(
			context,
			new AncientDrownedModel(context.bakeLayer(ModEntityModelLayers.ANCIENT_DROWNED)),
			new AncientDrownedModel(context.bakeLayer(ModEntityModelLayers.ANCIENT_DROWNED_BABY)),
			ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), AncientDrownedModel::new),
			ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), AncientDrownedModel::new)
		);
		this.addLayer(new AncientDrownedOverlayFeatureRenderer(this, context.getModelSet()));
	}

   public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieRenderState zombieEntityRenderState) {
		return TEXTURE;
	}

	protected void setupRotations(ZombieRenderState zombieEntityRenderState, PoseStack matrixStack, float f, float g) {
		super.setupRotations(zombieEntityRenderState, matrixStack, f, g);
		float h = zombieEntityRenderState.swimAmount;
		if (h > 0.0F) {
			float i = -10.0F - zombieEntityRenderState.xRot;
			float j = Mth.lerp(h, 0.0F, i);
			matrixStack.rotateAround(Axis.XP.rotationDegrees(j), 0.0F, zombieEntityRenderState.boundingBoxHeight / 2.0F / g, 0.0F);
		}
	}

	protected HumanoidModel.ArmPose getArmPose(Drowned drownedEntity, HumanoidArm arm) {
		ItemStack itemStack = drownedEntity.getItemHeldByArm(arm);
		return drownedEntity.getMainArm() == arm && drownedEntity.isAggressive() && itemStack.is(Items.TRIDENT)
			? HumanoidModel.ArmPose.THROW_SPEAR
			: HumanoidModel.ArmPose.EMPTY;
	}
}
