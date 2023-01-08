package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class Guild {
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (event.type == 2.toByte()) return
        var message = StringUtils.stripControlCodes(event.message.unformattedText)
        if (message.contains(":")) return

        if (Config.guildWelcome && message.endsWith(" joined the Guild!"))
            MeowMod.queueChatMessage("/gc ${Config.guildWelcomeMessage}")
        else if (Config.guildGG && message.startsWith("                   The Guild has "))
            MeowMod.queueChatMessage("/gc ${Config.guildGGMessage}")
    }
}
