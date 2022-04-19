package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CopyRareDrops {
    public void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.copyRareDropsToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if(message.contains(":"))
                return;
            if(message.contains(" DROP!") || message.contains(" has obtained ") || message.contains(" CATCH!")) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(message), null);
            }
        }
    }
}
