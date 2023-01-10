package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.utils.Title
import me.eagely.utils.Utils
import net.minecraft.client.Minecraft
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class DropshipWarning {
    private var ticksUntilDropship = 0

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        val message = StringUtils.stripControlCodes(event.message.unformattedText)

        if (!Config.dropshipWarning) return

        if (message == "[NPC] Elle: A Dropship is approaching! Take it down before it's too late!" || message == "[NPC] Elle: A fleet of Dropships are approaching! Take them down before it's too late!" || message.contains("dropship warn test")) {
            ticksUntilDropship = MeowMod.DROPSHIP_DELAY
        }
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if(event.phase == TickEvent.Phase.START) return
        if (ticksUntilDropship in 1 until Config.dropshipWarnDelay * 20) {
            Title.draw("${Utils.getColorString(Config.titleColor)}Dropship in ${"%.1f".format(ticksUntilDropship.toFloat() / 20)}", 50)
        }
        if (ticksUntilDropship > 0) ticksUntilDropship--
    }
}
