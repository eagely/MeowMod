package me.mlg.rat;

import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class Default {
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.startsWith("From ")) {
            if(message.contains("@rat"))
                Utils.mc.thePlayer.sendChatMessage("/r hi");
        }
    }
}
