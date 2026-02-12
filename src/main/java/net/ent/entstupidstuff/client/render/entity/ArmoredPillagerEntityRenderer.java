package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.client.render.entity.model.PillagerHelmetModel;
import net.ent.entstupidstuff.client.render.entity.state.ArmoredPillagerRenderState;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ArmoredPillagerEntityRenderer extends IllagerRenderer<ArmoredPillagerEntity, ArmoredPillagerRenderState> {

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/pillager_diamond_armored.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/pillager_gold_armored.png");

    public ArmoredPillagerEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new PillagerHelmetModel<>(context.bakeLayer(ModEntityModelLayers.PILLAGER_ARMORED)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ArmoredPillagerRenderState state) {
        return state.variant == ArmoredPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }

    @Override
    public ArmoredPillagerRenderState createRenderState() {
        return new ArmoredPillagerRenderState();
    }

    @Override
    public void extractRenderState(ArmoredPillagerEntity entity, ArmoredPillagerRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant();
        if (state.variant == ArmoredPillagerEntity.Variant.DIAMOND) {
            state.isCaptain = true;
        }
        else {
            state.isCaptain = false;
        }
    }
}
