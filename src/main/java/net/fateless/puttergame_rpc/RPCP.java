package net.fateless.rpc;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPCP implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("FATELESS DISCORD RPC");

    @Override
    public void onInitialize() {
        LOGGER.info("Fateless RPC LOADING..");
        LOGGER.info("Puttergamerpc loading..");
    }
}
