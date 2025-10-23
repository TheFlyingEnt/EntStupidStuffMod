package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.entity.passive.KoiPatternMain;
import net.ent.entstupidstuff.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.entity.passive.KoiVariant;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class KoiEntityRenderState extends LivingEntityRenderState {
    public KoiVariant variant = new KoiVariant(KoiBaseColor.WHITE, null, null);
    public KoiPatternMain mainPatter;
    public KoiPatternSecondary secondaryPattern;
    public boolean invisible;
    
}
