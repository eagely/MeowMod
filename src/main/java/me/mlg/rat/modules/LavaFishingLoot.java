package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LavaFishingLoot {
    public static int lavaLeechCounter;
    public static int moogmaCounter;
    public static int lavaFlameCounter;
    public static int magmaSlugCounter;
    public static int pyroclasticWormCounter;
    public static int fireEelCounter;
    public static int taurusCounter;
    public static int thunderCounter;
    public static int lordJawbusCounter;
    public static int vanquisherCounter;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(event.type != 2)
            return;
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.contains(":"))
            return;
        switch(message) {
            case "A small but fearsome Lava Leech emerges.":
                lavaLeechCounter++;
                ConfigHandler.writeIntConfig("tracker", "lavaleech", lavaLeechCounter);
            case "You hear a faint Moo from the lava... A Moogma appears.":
                moogmaCounter++;
                ConfigHandler.writeIntConfig("tracker", "moogma", moogmaCounter);
            case "A Lava Flame flies out from beneath the lava.":
                lavaFlameCounter++;
                ConfigHandler.writeIntConfig("tracker", "lavaflame", lavaFlameCounter);
            case "From beneath the lava appears a Magma Slug.":
                magmaSlugCounter++;
                ConfigHandler.writeIntConfig("tracker", "magmaslug", magmaSlugCounter);
            case "You feel the heat radiating as a Pyroclastic Worm surfaces.":
                pyroclasticWormCounter++;
                ConfigHandler.writeIntConfig("tracker", "pyroclastic", pyroclasticWormCounter);
            case "A Fire Eel slithers out from the depths.":
                fireEelCounter++;
                ConfigHandler.writeIntConfig("tracker", "fireeel", fireEelCounter);
            case "Taurus and his steed emerge.":
                taurusCounter++;
                ConfigHandler.writeIntConfig("tracker", "taurus", taurusCounter);
            case "You hear a massive rumble as Thunder emerges.":
                thunderCounter++;
                ConfigHandler.writeIntConfig("tracker", "thunder", thunderCounter);
            case "You have angered a legendary creature... Lord Jawbus has arrived":
                lordJawbusCounter++;
                ConfigHandler.writeIntConfig("tracker", "lordjawbus", lordJawbusCounter);
            case "A Vanquisher is spawning nearby!":
                vanquisherCounter++;
                ConfigHandler.writeIntConfig("tracker", "", vanquisherCounter);
            default:
                break;
        }
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if(RatAddons.lavaFishingLootToggle) {
            Utils.mc.fontRendererObj.drawStringWithShadow("Lava Leech: " + lavaLeechCounter, 0, 0, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Moogma: " + moogmaCounter, 0, 10, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Lava Flame: " + lavaFlameCounter, 0, 20, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Magma Slug: " + magmaSlugCounter, 0, 30, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Pyroclastic Worm: " + pyroclasticWormCounter, 0, 40, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Fire Eel: " + fireEelCounter, 0, 50, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Taurus: " + taurusCounter, 0, 60, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Thunder: " + thunderCounter, 0, 70, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Lord Jawbussy: " + lordJawbusCounter, 0, 80, 0xFFFFFF);
            Utils.mc.fontRendererObj.drawStringWithShadow("Vanquisher: " + vanquisherCounter, 0, 90, 0xFFFFFF);

        }
    }
}
