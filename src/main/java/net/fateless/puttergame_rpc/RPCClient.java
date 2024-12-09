package net.fateless.rpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import com.mojang.authlib.GameProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPCClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("FATELESS RPC");
    private static final String ID = "1304123004560736378";
    private static final String Name = "{GVZ Project}";

    private static final String APPLICATION_ID = ID;
    private static final boolean AUTO_REGISTER = true;
    private static final String STEAM_ID = null;
    private static final String SMALL_IMAGE_KEY = "";
    private static final String DETAILS = Name;
    private static final String DEFAULT_STATE = "";
    private static final boolean USE_USERNAME = true;

    @Override
    public void onInitializeClient() {
        try {
            updatePresence();
            LOGGER.info("Puttergame RPC LOADING..");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Discord RPC", e);
        }
    }

    private void updatePresence() {
        Minecraft client = Minecraft.getInstance();
        GameProfile profile = client.getUser().getGameProfile();
        String username = profile.getName();

        String state;
        if (USE_USERNAME) {
            state = "ID Minecraft: " + username;
        } else {
            state = DEFAULT_STATE;
        }

        DiscordRPC lib = DiscordRPC.INSTANCE;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(APPLICATION_ID, handlers, AUTO_REGISTER, STEAM_ID);

        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = DETAILS;
        presence.state = state;
        presence.largeImageKey = "large_image_key";
        presence.largeImageText = "Minecraft";
        presence.smallImageKey = SMALL_IMAGE_KEY;
        presence.smallImageText = "Playing";

        lib.Discord_UpdatePresence(presence);
    }
}
