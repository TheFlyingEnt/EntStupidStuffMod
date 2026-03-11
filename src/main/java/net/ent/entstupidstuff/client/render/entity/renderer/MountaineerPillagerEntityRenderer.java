package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.MountaineerPillagerEntity;
import net.ent.entstupidstuff.client.render.entity.model.illager.MountaineerPillagerModel;
import net.ent.entstupidstuff.client.render.entity.state.MountaineerPillagerRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MountaineerPillagerEntityRenderer extends IllagerRenderer<MountaineerPillagerEntity, MountaineerPillagerRenderState> {

    private static final ResourceLocation DIAMOND_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_pillager.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/illager/mountaineer_pillager.png");

    public MountaineerPillagerEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MountaineerPillagerModel<>(context.bakeLayer(ModEntityModelLayers.MOUNTAINEER_PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MountaineerPillagerRenderState state) {
        return state.variant == MountaineerPillagerEntity.Variant.DIAMOND ? DIAMOND_TEXTURE : GOLD_TEXTURE;
    }

    @Override
    public MountaineerPillagerRenderState createRenderState() {
        return new MountaineerPillagerRenderState();
    }

    @Override
    public void extractRenderState(MountaineerPillagerEntity entity, MountaineerPillagerRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant();
        if (state.variant == MountaineerPillagerEntity.Variant.DIAMOND) {
            state.isCaptain = true;
        }
        else {
            state.isCaptain = false;
        }
        
    }
}
