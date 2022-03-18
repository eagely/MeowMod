package me.mlg.rat.modules;

import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.commands.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GreetGuild {
    int date = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("dMy")));

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.startsWith("Guild > ") && message.endsWith("joined.") && !message.contains(":") && ToggleCommand.greetToggle) {
            String username = message.split(" ", 0)[2];
            ConfigHandler.initInt("lastSeen", username, 111970);
            if (ConfigHandler.getInt("lastSeen", username) != date) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gc Good morning " + username);
                ConfigHandler.writeIntConfig("lastSeen", username, date);
            }
        }
    }
}
