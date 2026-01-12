package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.FurTroutModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cod;

public class FurTroutRenderer extends MobRenderer<Cod, LivingEntityRenderState, FurTroutModel>{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/furtrout.png");

    public FurTroutRenderer(EntityRendererProvider.Context context) {
        super(context, new FurTroutModel(context.bakeLayer(ModEntityModelLayers.FURTROUT)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack matrices, float bodyYaw, float baseHeight) {
		super.setupRotations(state, matrices, bodyYaw, baseHeight);
		float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
		matrices.mulPose(Axis.YP.rotationDegrees(f));
		if (!state.isInWater) {
			matrices.translate(0.1F, 0.1F, -0.1F);
			matrices.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
    
}
