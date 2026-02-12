package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.MountaineerVindicatorEntity;
import net.ent.entstupidstuff.client.render.entity.model.illager.MountaineerVindicatorModel;
import net.ent.entstupidstuff.client.render.entity.state.MountaineerVindicatorRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MountaineerVindicatorEntityRenderer extends IllagerRenderer<MountaineerVindicatorEntity, MountaineerVindicatorRenderState> {

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_diamond.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_gold.png");
    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_default.png");

    public MountaineerVindicatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MountaineerVindicatorModel<>(context.bakeLayer(ModEntityModelLayers.MOUNTAINEER_VINDICATOR)), 0.5F);
        this.addLayer(new ItemInHandLayer<MountaineerVindicatorRenderState, IllagerModel<MountaineerVindicatorRenderState>>(this) {
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, MountaineerVindicatorRenderState MountaineerVindicatorRenderState, float f, float g) {
				if (MountaineerVindicatorRenderState.isAggressive) {
					super.submit(poseStack, submitNodeCollector, i, MountaineerVindicatorRenderState, f, g);
				}
			}
		});
    }

    @Override
    public ResourceLocation getTextureLocation(MountaineerVindicatorRenderState state) {
        return switch (state.variant) {
			case DIAMOND -> DIAMOND_TEXTURE;
			case GOLD -> GOLD_TEXTURE;
			case DEFAULT -> DEFAULT_TEXTURE;
			default -> DEFAULT_TEXTURE;
		};
    }

    @Override
    public MountaineerVindicatorRenderState createRenderState() {
        return new MountaineerVindicatorRenderState();
    }

    @Override
    public void extractRenderState(MountaineerVindicatorEntity entity, MountaineerVindicatorRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant();
        if (state.variant == MountaineerVindicatorEntity.Variant.DIAMOND) {
            state.isCaptain = true;
        }
        else {
            state.isCaptain = false;
        }
        
    }
    
}