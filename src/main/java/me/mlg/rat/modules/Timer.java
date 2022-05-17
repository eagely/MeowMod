package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.utils.Utils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
//0.3 added this
// (DONE) p0.4 add changeable color on render
public class Timer {
    public static int tick = 0;
    private boolean timerStarted;
    public static int x;
    public static int y;
    public static int color;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(RatAddons.keyBindings[2].isPressed())
            timerStarted = true;
        if(timerStarted)
            tick--;
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if(tick > 0)
            Utils.mc.fontRendererObj.drawStringWithShadow(Utils.tickToTime(tick), x, y, color);
    }
}
