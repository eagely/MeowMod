package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LavaFishingLoot {
    public static int lavaLeechCounter = ConfigHandler.getInt("tracker", "lavaleech");
    public static int moogmaCounter = ConfigHandler.getInt("tracker", "moogma");
    public static int lavaFlameCounter = ConfigHandler.getInt("tracker", "lavaflame");
    public static int magmaSlugCounter = ConfigHandler.getInt("tracker", "magmaslug");
    public static int pyroclasticWormCounter = ConfigHandler.getInt("tracker", "pyroclasticworm");
    public static int fireEelCounter = ConfigHandler.getInt("tracker", "fireeel");
    public static int taurusCounter = ConfigHandler.getInt("tracker", "tauruscounter");
    public static int thunderCounter = ConfigHandler.getInt("tracker", "thunder");
    public static int lordJawbusCounter = ConfigHandler.getInt("tracker", "lordjawbus");
    public static int vanquisherCounter = ConfigHandler.getInt("tracker", "vanquisher");

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.contains(":"))
            return;
        switch(message) {
            case "A small but fearsome Lava Leech emerges.":
                ConfigHandler.writeIntConfig("tracker", "lavaleech", ++lavaLeechCounter);
                Utils.printRatMessage("debug" + lavaLeechCounter);
                break;
            case "You hear a faint Moo from the lava... A Moogma appears.":
                ConfigHandler.writeIntConfig("tracker", "moogma", ++moogmaCounter);
                break;
            case "A Lava Flame flies out from beneath the lava.":
                ConfigHandler.writeIntConfig("tracker", "lavaflame", ++lavaFlameCounter);
                break;
            case "From beneath the lava appears a Magma Slug.":
                ConfigHandler.writeIntConfig("tracker", "magmaslug", ++magmaSlugCounter);
                break;
            case "You feel the heat radiating as a Pyroclastic Worm surfaces.":
                ConfigHandler.writeIntConfig("tracker", "pyroclastic", ++pyroclasticWormCounter);
                break;
            case "A Fire Eel slithers out from the depths.":
                ConfigHandler.writeIntConfig("tracker", "fireeel", ++fireEelCounter);
                break;
            case "Taurus and his steed emerge.":
                ConfigHandler.writeIntConfig("tracker", "taurus", ++taurusCounter);
                break;
            case "You hear a massive rumble as Thunder emerges.":
                ConfigHandler.writeIntConfig("tracker", "thunder", ++thunderCounter);
                break;
            case "You have angered a legendary creature... Lord Jawbus has arrived":
                ConfigHandler.writeIntConfig("tracker", "lordjawbus", ++lordJawbusCounter);
                break;
            case "A Vanquisher is spawning nearby!":
                ConfigHandler.writeIntConfig("tracker", "vanquisher", ++vanquisherCounter);
                break;
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
