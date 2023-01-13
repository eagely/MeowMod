package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class EndstoneProtector {
    private var isAlive = false

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.endstoneProtector || event.type == 2.toByte()) return
        val message = StringUtils.stripControlCodes(event.message.unformattedText)
        if (message.contains(":")) return

        if (message.equals("The ground begins to shake as an Endstone Protector rises from below!")) {
            MeowMod.messageQueue.add("/stream open 24")
            MeowMod.messageQueue.add("/gc ${Config.endstoneProtectorMessage}")
            isAlive = true
        } else if (isAlive && message.equals("BEWARE - An Endstone Protector has risen!")) {
            MeowMod.messageQueue.add("/p warp")
            MeowMod.messageQueue.add("/p disband")
            isAlive = false
        }
    }
}
