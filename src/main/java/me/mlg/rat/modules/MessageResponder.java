package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class MessageResponder {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        RatAddons.responderToggle = ConfigHandler.getBoolean("toggle", "responder");
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.startsWith("Guild > ") && RatAddons.responderToggle || message.startsWith("Party > ") && RatAddons.responderToggle) {
            if (message.toLowerCase().contains("@smiler"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + ":)");
            else if (message.toLowerCase().contains("@truer"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "True!");
            else if (message.toLowerCase().contains("@frowner"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + ":(");
            else if (message.toLowerCase().contains("@samer"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "Same!");
            else if (message.toLowerCase().contains("m7") && !message.contains("I love m7 <3"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "I love m7 <3");
            else if (message.toLowerCase().contains("i hate bingo"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "I love bingo <3");
            else if (message.contains("Hosted") && !message.contains("Hosted is a shitter") && !message.contains("] Hosted: "))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "Hosted is a shitter");
            else if (message.contains("BennettArthur") && !message.contains("BennettArthur is my favorite mage") && !message.contains("] BennettArthur: "))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "BennettArthur is my favorite mage <3");
            else if (message.toLowerCase().contains("shut up"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "no u");
        }
    }
}
