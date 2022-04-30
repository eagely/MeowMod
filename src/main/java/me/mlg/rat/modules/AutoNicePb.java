package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoNicePb {
    public static void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.nicePbToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if (message.contains(":"))
                return;
            if (message.contains("Defeated") && message.contains("s (NEW RECORD!)")) {
                Utils.mc.thePlayer.sendChatMessage("/pc nice pb");
            }
        }
    }
}
