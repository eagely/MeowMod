package me.mlg.rat.modules;

import me.mlg.rat.utils.Utils;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
//0.3 added this
public class SeaCreatureAddons {
    private static int tickSinceThunder = 0;
    private static int tickSinceJawbus = 0;
    private static boolean isThunder;
    private static boolean isJawbus;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(isThunder)
            tickSinceThunder++;
        else if(isJawbus)
            tickSinceJawbus++;

    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(message.equals("You hear a massive rumble as Thunder emerges."))
            isThunder = true;
        else if(message.equals("You have angered a legendary creature... Lord Jawbus has arrived"))
            isJawbus = true;
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if(isThunder && event.entityLiving.getName().equals("Guardian")) {
            Utils.printRatMessage("Thunder took " + (double) tickSinceThunder / 20 + "s to kill");
            tickSinceThunder = 0;
            isThunder = false;
        }
        if(isJawbus && event.entityLiving.getName().equals("Iron Golem")) {
            Utils.printRatMessage("Lord Jawbussy took " + (double) tickSinceJawbus / 20 + "s to kill");
            tickSinceJawbus = 0;
            isJawbus = false;
        }
        if(event.entityLiving.getLastAttacker() != null)
            Utils.printRatMessage("Last Hit: " + event.entityLiving.getLastAttacker());
    }
}
