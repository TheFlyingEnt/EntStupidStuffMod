package net.ent.entstupidstuff.api.hat;

import java.util.HashSet;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;

public class ModAttachments {
 
    /**
     * Stores the active hat name (e.g. "party_hat") on a player entity.
     * Empty string means "no hat".
     *
     * persistent() → saved to NBT so it survives relog.
     * copyOnDeath() → hat re-equips after death (remove if you don't want this).
     */
    public static final AttachmentType<String> HAT = AttachmentRegistry.create(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hat"),
        builder -> builder
            .persistent(Codec.STRING)
            .copyOnDeath()
            .initializer(() -> "")
    );

    public static final AttachmentType<String> EMOTE = AttachmentRegistry.create(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "emote"),
        builder -> builder
            .initializer(() -> "")
    );

    public static final AttachmentType<Set<String>> UNLOCKED_HATS = AttachmentRegistry.create(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "unlocked_hats"),
        builder -> builder.initializer(HashSet::new)
    );

    public static void init() {}
}
 