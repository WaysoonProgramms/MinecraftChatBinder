package ru.waysoonprogramms.chat_binder.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.command.CommandManager;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;

import org.jetbrains.annotations.NotNull;

public class MinecraftChatBinderClient implements ClientModInitializer {
    public static final String MOD_ID = "assets/chat_binder";
    private static KeyBinding keyBinding;
    public static String chatMessage = "Hello world!";

    @Override
    public void onInitializeClient() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        // TODO: Реализовать нормальное восприятие команды и смену сообщения отправляемого в чат
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("binder").then(CommandManager.literal("gg")).executes(context -> {
                player.sendMessage(Text.literal("Changed binded message: GG"));
                chatMessage = "GG";
                return 1;
            }));
        });

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.chat_binder.spook",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.chat_binder.test"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                sendChatMessage(chatMessage);
            }
        });
    }

    public static void sendChatMessage(@NotNull String text) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || text.trim().isEmpty()) {
            return;
        }
        if (text.startsWith("/")) {
            player.networkHandler.sendChatCommand(text.substring(1).trim());

        } else {
            player.networkHandler.sendChatMessage(text.trim());
        }

    }
}
