package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.client.render.entity.state.ArmoredPillagerRenderState;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ArmoredPillagerEntityRenderer extends IllagerEntityRenderer<ArmoredPillagerEntity, ArmoredPillagerRenderState> {

    private static final Identifier DIAMOND_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/pillager_diamond_armored.png");
    private static final Identifier GOLD_TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/pillager_gold_armored.png");

    public ArmoredPillagerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new IllagerEntityModel<>(context.getPart(EntityModelLayers.PILLAGER)), 0.5F);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(ArmoredPillagerRenderState state) {
        return state.variant == ArmoredPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }

    @Override
    public ArmoredPillagerRenderState createRenderState() {
        return new ArmoredPillagerRenderState();
    }

    @Override
    //public void updateRenderState(ArmoredPillagerEntity entity, ArmoredPillagerRenderState state, float tickDelta) {
    public void updateRenderState(ArmoredPillagerEntity entity, ArmoredPillagerRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant(); // Copy the variant for rendering
    }

}
