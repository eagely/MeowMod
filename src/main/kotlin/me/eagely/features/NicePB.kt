package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class NicePB {
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (event.type == 2.toByte()) return
        val message = StringUtils.stripControlCodes(event.message.unformattedText)
        if (message.contains(":")) return

        if (Config.nicePB && message.contains("Defeated") && message.contains("s (NEW RECORD!)")) {
            MeowMod.queueChatMessage("/pc ${Config.nicePBMessage}")
        }
    }
}
