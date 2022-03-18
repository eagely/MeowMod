package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.commands.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WatcherDisplay {
    public static int x;
    public static int y;
    private static int tickSinceCleared = 0;
    private static boolean draw;

    @SubscribeEvent
    public void onTick(TickEvent event) {
        tickSinceCleared++;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(ToggleCommand.watcherToggle) {
            String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
            if (message.equals("[BOSS] The Watcher: That will be enough for now.")) {
                if(!ToggleCommand.rabbitToggle)
                    Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1, (float) 0.5);
                draw = true;
                tickSinceCleared = 0;
            }
        }
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if(draw) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Watcher Cleared", x, y, 0xFF0000);
            if(tickSinceCleared >= RatAddons.defaultDisplayTimeTick) {
                draw = false;
                tickSinceCleared = 0;
            }
        }
    }
}
