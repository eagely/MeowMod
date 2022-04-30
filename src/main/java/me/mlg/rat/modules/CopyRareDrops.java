package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
//0.3 added diamond trophy fish
public class CopyRareDrops {
    public void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.copyRareDropsToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if(message.contains(":") || event.type != 2)
                return;
            if(message.contains(" DROP!") || message.contains(" has obtained ") || message.contains(" CATCH!") || (message.startsWith("TROPHY FISH! ") && message.endsWith("DIAMOND."))) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(message), null);
            }
        }
    }
}
