package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GreetGuild {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.greetToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if(message.contains(":") || event.type != 2)
                return;
            if (message.startsWith("Guild > ") && message.endsWith("joined.")) {
                String username = message.split(" ", 0)[2];
                RatAddons.currentDate = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("dMy")));
                if (ConfigHandler.initInt("lastseen", username, 0) != RatAddons.currentDate) {
                    Utils.mc.thePlayer.sendChatMessage("/gc Good morning " + username);
                    ConfigHandler.writeIntConfig("lastseen", username, RatAddons.currentDate);
                }
            }
        }
    }
}
