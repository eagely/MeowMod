package me.mlg.rat.modules;

import me.mlg.rat.Default;
import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MessageResponder {
//0.3 removed hosted is a shitter & i love m7 <3

    private int tick = 0;
    private boolean send;
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(send)
            tick++;
        if(tick > RatAddons.hypixelChatDelay) {
            Utils.mc.thePlayer.sendChatMessage("/p mute");
            send = false;
            tick = 0;
        }
    }
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
            else if (message.toLowerCase().contains("i hate bingo"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "I love bingo <3");
            else if (message.contains("BennettArthur") && !message.contains("BennettArthur is my favorite mage") && !message.contains("] BennettArthur: "))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "BennettArthur is my favorite mage <3");
            else if (message.toLowerCase().contains("shut up"))
                Utils.mc.thePlayer.sendChatMessage("/" + message.split(" ", 0)[0] + " chat " + "no u");
        }
        else if(message.startsWith("Party > ")) {
            if(message.contains("@mute") || message.contains("@partybug") || Default.isPartyBug) {
                Utils.mc.thePlayer.sendChatMessage("/p mute");
                send = true;
            }
        }
    }
}
