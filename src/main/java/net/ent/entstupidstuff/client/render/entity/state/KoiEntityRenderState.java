package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.passive.KoiBaseColor;
import net.ent.entstupidstuff.client.entity.passive.KoiPatternMain;
import net.ent.entstupidstuff.client.entity.passive.KoiPatternSecondary;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class KoiEntityRenderState extends LivingEntityRenderState {
    public KoiVariant variant = new KoiVariant(null, KoiBaseColor.WHITE, null, null);
    public KoiPatternMain mainPatter;
    public KoiPatternSecondary secondaryPattern;
    public boolean invisible;
    
}
