package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.ArmoredVindicatorEntity;
import net.ent.entstupidstuff.client.render.entity.state.ArmoredVindicatorRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class ArmoredVindicatorEntityRenderer extends IllagerRenderer<ArmoredVindicatorEntity, ArmoredVindicatorRenderState> {

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/vindicator_diamond_armored.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/vindicator_gold_armored.png");

    public ArmoredVindicatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
        this.addLayer(new ItemInHandLayer<ArmoredVindicatorRenderState, IllagerModel<ArmoredVindicatorRenderState>>(this) {
			public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, ArmoredVindicatorRenderState ArmoredVindicatorRenderState, float f, float g) {
				if (ArmoredVindicatorRenderState.isAggressive) {
					super.submit(poseStack, submitNodeCollector, i, ArmoredVindicatorRenderState, f, g);
				}
			}
		});
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredVindicatorRenderState state) {
        return state.variant == ArmoredVindicatorEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }

    @Override
    public ArmoredVindicatorRenderState createRenderState() {
        return new ArmoredVindicatorRenderState();
    }

    @Override
    public void extractRenderState(ArmoredVindicatorEntity entity, ArmoredVindicatorRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant();      
    }

}
