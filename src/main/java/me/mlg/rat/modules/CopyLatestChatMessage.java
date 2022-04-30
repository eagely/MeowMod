package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CopyLatestChatMessage {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(event.type != 2)
            return;
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(RatAddons.keyBindings[0].isPressed())
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(message), null);
    }

}
