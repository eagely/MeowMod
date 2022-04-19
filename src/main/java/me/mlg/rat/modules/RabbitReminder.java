package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class RabbitReminder {
    public static int x;
    public static int y;
    private static int tickSinceCleared = 0;
    private static boolean isCleared;
    private static boolean draw;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        tickSinceCleared++;
        if(isCleared && tickSinceCleared > Utils.hypixelChatDelayTick) {
            isCleared = false;
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(RatAddons.rabbitToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if (message.equals("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
                if(!RatAddons.watcherToggle)
                    Utils.mc.thePlayer.playSound("random.orb", 1, (float) 0.5);
                Utils.mc.thePlayer.sendChatMessage("/pc Rabbit Hat");
                isCleared = true;
                draw = true;
                tickSinceCleared = 0;
            }
        }
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if(draw) {
            Utils.mc.fontRendererObj.drawStringWithShadow("Rabbit Hat", x, y, 0xFF0000);
            if(tickSinceCleared >= Utils.defaultDisplayTimeTick) {
                draw = false;
                tickSinceCleared = 0;
            }
        }
    }
}
