package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GreetGuild {

    private static int tick = 0;
    private static boolean joined;
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(joined)
            tick++;
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.greetToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if(message.contains(":")) return;
            if (message.startsWith("Guild > ") && message.endsWith("joined.")) {
                String username = message.split(" ", 0)[2];
                RatAddons.currentDate = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("dMy")));
                joined = true;
                if (ConfigHandler.initInt("lastseen", username, 0) != RatAddons.currentDate && tick / 20 > 2) {
                    Utils.mc.thePlayer.sendChatMessage("/gc Good morning " + username);
                    ConfigHandler.writeIntConfig("lastseen", username, RatAddons.currentDate);
                    joined = false;
                }
            }
        }
    }
}
