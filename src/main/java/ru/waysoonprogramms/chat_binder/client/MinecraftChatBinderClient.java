package ru.waysoonprogramms.chat_binder.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecraftChatBinderClient implements ClientModInitializer {
    public static final String MOD_ID = "chat_binder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.debug("Init");
    }
}
