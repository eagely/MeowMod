package me.eagely.features

import me.eagely.config.Config
import me.eagely.utils.Title
import me.eagely.utils.Utils
import me.eagely.utils.Utils.extractInts
import me.eagely.utils.Utils.stripControlCodes
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class KuudraNotifications {
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.kuudraNotifications || event.type == 2.toByte()) return

        val message = event.message.unformattedText.stripControlCodes()

        if(Config.supplyNotification && message == "[NPC] Elle: OMG! Great work collecting my supplies!")
            Title.draw("${Utils.getColorString(Config.titleColor)}Supplies Done", 1000)
        else if(Config.ballistaNotification && message == "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!")
            Title.draw("${Utils.getColorString(Config.titleColor)}Ballista Done", 1000)
        else if(Config.blazesSpawnedNotification && message == "[NPC] Elle: A group of Wandering Blazes are emerging from Kuudra's mouth!")
            Title.draw("${Utils.getColorString(Config.titleColor)}Blazes", 1000)
        else if(Config.allFuelNotification && message.matches("${Utils.hypixelPlayer} recovered a Fuel Cell and charged the Ballista! \\(\\d*%\\)".toRegex()))
            Title.draw("${Utils.getColorString(Config.titleColor)}${message.substringAfter("(").extractInts()}%", 1000)
        else if((Config.allFuelNotification || Config.lastFuelNotification) && message.matches("${Utils.hypixelPlayer} recovered a Fuel Cell and charged the Ballista! \\(100%\\)".toRegex()))
            Title.draw("${Utils.getColorString(Config.titleColor)}Fuel Done", 1000)
        else if(Config.stunNotification && message.matches("${Utils.hypixelPlayer} destroyed one of Kuudra's pods!".toRegex()))
            Title.draw("${Utils.getColorString(Config.titleColor)}Stunned", 1000)
        else if(Config.stunKillNotification && message == "[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!")
            Title.draw("${Utils.getColorString(Config.titleColor)}Stun Killed", 1000)
        else if(Config.kuudraDownNotification && message == "[NPC] Elle: Good job everyone. A hard fought battle come to an end. Let's get out of here before we run into any more trouble!")
            Title.draw("${Utils.getColorString(Config.titleColor)}Kuudra Down", 1000)


    }
}
