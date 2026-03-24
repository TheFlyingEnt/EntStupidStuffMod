package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class ModJukeboxSongs {

    public static final ResourceKey<JukeboxSong> PIRATE_TAVERN = create("pirate_tavern");
    public static final ResourceKey<JukeboxSong> FUNGALDELIC = create("fungaldelic");
    public static final ResourceKey<JukeboxSong> CANIBEHONEST = create("canibehonest");
    public static final ResourceKey<JukeboxSong> CANIBEHONEST_LOW = create("canibehonest_high");
    public static final ResourceKey<JukeboxSong> CANIBEHONEST_HIGH = create("canibehonest_low");
    //public static final ResourceKey<JukeboxSong> BRAIN_SPLOSHED = create("brain_spolished");
    //public static final ResourceKey<brain> SEAVOLUTION = create("seavolution");
    //public static final ResourceKey<JukeboxSong> SEAVOLUTION = create("seavolution");
    //public static final ResourceKey<JukeboxSong> SEAVOLUTION = create("seavolution");


    private static ResourceKey<JukeboxSong> create(String string) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, string));
	}

	private static void register(
		BootstrapContext<JukeboxSong> bootstrapContext, ResourceKey<JukeboxSong> resourceKey, Holder.Reference<SoundEvent> reference, int i, int j
	) {
		bootstrapContext.register(
			resourceKey, new JukeboxSong(reference, Component.translatable(Util.makeDescriptionId("jukebox_song", resourceKey.location())), i, j)
		);
	}

	public static void bootstrap(BootstrapContext<JukeboxSong> bootstrapContext) {
		register(bootstrapContext, PIRATE_TAVERN, SoundFactory.MUSIC_DISC_PIRATE_TAVERN, 73, 9);
        register(bootstrapContext, FUNGALDELIC, SoundFactory.MUSIC_DISC_FUNGALDELIC, 182, 5);
        
        register(bootstrapContext, CANIBEHONEST, SoundFactory.MUSIC_DISC_CANIBEHONEST, 148, 5);
        register(bootstrapContext, CANIBEHONEST_LOW, SoundFactory.MUSIC_DISC_CANIBEHONEST_LOW, 217, 4);
        register(bootstrapContext, CANIBEHONEST_HIGH, SoundFactory.MUSIC_DISC_CANIBEHONEST_HIGH, 216, 6);

	}
    
}
