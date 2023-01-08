package me.eagely.features

import me.eagely.MeowMod
import me.eagely.commands.Meow
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent

class EndstoneProtector {
    private var isAlive = false
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (event.type == 2.toByte()) return
        val message = StringUtils.stripControlCodes(event.message.unformattedText)
        if (message.contains(":")) return

        if (Config.endstoneProtector) {
            if (message.equals("The ground begins to shake as an Endstone Protector rises from below!")) {
                MeowMod.queueChatMessage("/stream open 24")
                MeowMod.queueChatMessage("/gc ${Config.endstoneProtectorMessage}")
                isAlive = true
            }
            else if (isAlive && message.equals("BEWARE - An Endstone Protector has risen!")) {
                MeowMod.queueChatMessage("/p warp")
                MeowMod.queueChatMessage("/p disband")
                isAlive = false
            }
        }
    }
}
