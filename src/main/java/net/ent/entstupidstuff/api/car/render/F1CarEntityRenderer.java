package net.ent.entstupidstuff.api.car.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.F1CarEntity;
import net.ent.entstupidstuff.api.car.models.F1CarEntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class F1CarEntityRenderer extends BaseCarEntityRenderer<F1CarEntity, F1CarRenderState> {
 
    private static final ResourceLocation AUDI = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_audi.png");
    private static final ResourceLocation REDBULL_JAPAN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_redbull_japan.png");
    private static final ResourceLocation CAMEL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_camel.png");
    private static final ResourceLocation DEMON_SLAYER = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_demonslayer.png");
    private static final ResourceLocation SENNA = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_senna.png");
    private static final ResourceLocation REDBULL = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_redbull.png");
    private static final ResourceLocation BASE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_base_updated.png");

    private static final ResourceLocation JURASSICSTUDIO = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_jurassic_studios.png");
    private static final ResourceLocation ENTITY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_entity.png");
    private static final ResourceLocation LEXUS = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_lexus.png");
    private static final ResourceLocation STAKE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_stake.png");
    private static final ResourceLocation ASTON = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_aston.png");

    private static final ResourceLocation MCLAREN = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_mclaren.png");
    private static final ResourceLocation FERRARI24 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_ferrari_sf24.png");
    private static final ResourceLocation FERRARI26 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_ferrari_sf26.png");
    private static final ResourceLocation CADILLAC = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_cadillac.png");
    private static final ResourceLocation HAAS24 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_haas_vf24.png");
    private static final ResourceLocation MERCADES = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_mercades_w15.png");
    private static final ResourceLocation VCARB = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_vcarb_01.png");
    private static final ResourceLocation BLAST = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_blast.png");
    private static final ResourceLocation BEAMY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_beamy.png");
    private static final ResourceLocation FORD = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_ford.png");
    private static final ResourceLocation HAAS26 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_haas_vf26.png");
    private static final ResourceLocation BENTLEY = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_bentley.png");
    private static final ResourceLocation CLT = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_clt.png");
    private static final ResourceLocation BRAWNGP = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_brawngp.png");

    private static final ResourceLocation BLANK = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_blank.png");
 
    private static final ResourceLocation GLOW_BASE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light.png");
    private static final ResourceLocation GLOW_CYBERPUNK= ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_cyberpunk_lights.png");
    private static final ResourceLocation GLOW_REVERSE_CYBERPUNK= ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/fone_cyberpunk_reverse.png");
    
    private static final ResourceLocation GLOW_BACKUP = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light_backup.png");
    private static final ResourceLocation GLOW_REVERSE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/car/f1one_base_light_reverse.png");
 
    public F1CarEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new F1CarEntityModel(context.bakeLayer(F1CarEntityModel.LAYER_LOCATION)));
    }
 
    @Override protected ResourceLocation texture(F1CarRenderState state) { return texturelist(state); }
    @Override protected ResourceLocation glowTexture(F1CarRenderState state)       { return glowTextureList(state); }
    @Override protected ResourceLocation glowBackupTexture(F1CarRenderState state) { return reverseTextureList(state); }

    @Override
    public F1CarRenderState createRenderState() {
        return new F1CarRenderState();
    }

    @Override
    public void extractRenderState(F1CarEntity entity, F1CarRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.variant = entity.getVariant();
        state.wrapId      = entity.getCurrentWrap();
        state.isBreaking = entity.isBraking();
    }


    public ResourceLocation glowTextureList(F1CarRenderState state) {
        return switch (state.wrapId) {
            case "fone_cyberpunk" -> GLOW_CYBERPUNK;
            default -> GLOW_BASE;
        };
    }

    public ResourceLocation reverseTextureList(F1CarRenderState state) {
        return switch (state.wrapId) {
            case "fone_cyberpunk" -> GLOW_REVERSE_CYBERPUNK;
            default -> GLOW_BACKUP;
        };
    }
    
    
    public ResourceLocation texturelist(F1CarRenderState state) {
        //System.out.println(state.variant);
        return switch (state.variant) {
			case AUDI -> AUDI; //5
			case REDBULL_JAPAN -> REDBULL_JAPAN; //1
            case CAMEL -> CAMEL; //7
            case DEMON_SLAYER -> DEMON_SLAYER; //8
            case SENNA -> SENNA; // - N/A
            case REDBULL -> REDBULL; //1
            case JURASSICSTUDIO -> JURASSICSTUDIO; //9
            case ENTITY -> ENTITY; //9
            case LEXUS -> LEXUS; //9
            case STAKE -> STAKE; //4
            case ASTON -> ASTON; //14
            case MCLAREN -> MCLAREN; // - NA
            case FERRARI24 -> FERRARI24; //15
            case FERRARI26 -> FERRARI26; //16
            case CADILLAC -> CADILLAC; //11
            case HAAS24 -> HAAS24; //10
            case MERCADES -> MERCADES; //44
            case VCARB -> VCARB; //3
            case BLAST -> BLAST; //10
            case BEAMY -> BEAMY;
            case FORD -> FORD; //2
            case HAAS26 -> HAAS26; //17
            case BENTLEY -> BENTLEY; //17
            case CLT -> CLT; //17
            case BRAWNGP -> BRAWNGP; //17
            case BLANK -> BLANK;
			default -> BASE;

            /*
            REDBULL           1
            REDBULL_JAPAN     1
            FORD              2
            VCARB             3
            STAKE             4
            BENTLEY           4 - SPECIAL
            AUDI              5
            TBA               6 - TBA....... BMW
            TBA               7 - TBA....... Alpine
            CAMEL             7 - SPECIAL
            DEMON_SLAYER      8 - SPECIAL
            TBA               8 - TBA....... HONDA
            ENTITY            9 - SPECIAL
            LEXUS             9 - SPECIAL
            JURASSICSTUDIO    9 - SPECIAL
            TBA               9 - TBA....... Bentley (Concept)
            HAAS24            10
            BLAST             10 - SPECIAL
            CADILLAC          11
            SENNA................12 - SPECIAL
            TBA               13 - TBA.......... TESLA?
            ASTON             14
            FERRARI24         15
            FERRARI26         16
            HAAS26            17
            TBA..................18 JORDAN - SCHUMACHER (7up)
            MERCADES          44
            CLT               49 - SPECIAL
            TBA..................22 - BRAWNGP
            TBA               55 - TBA....... Williams
            */
		};
    }
}
