package me.mlg.rat;

import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Default {
    public static boolean isPartyBug;
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.startsWith("From ")) {
            if(message.contains("@rat"))
                Utils.mc.thePlayer.sendChatMessage("/r hi");
            if(message.contains("@mute") || message.contains("@partybug"))
                isPartyBug = true;
        }
    }
}
