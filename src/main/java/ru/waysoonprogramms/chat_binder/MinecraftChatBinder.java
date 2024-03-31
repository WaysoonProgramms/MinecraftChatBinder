package ru.waysoonprogramms.chat_binder;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecraftChatBinder implements ModInitializer {
    public static final String MOD_ID = "chat_binder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.debug("Init");
    }

}
