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
    private var isDead = false
    private var world = Minecraft.getMinecraft().theWorld

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.dropshipWarning || event.type == 2.toByte()) return
        val message = StringUtils.stripControlCodes(event.message.unformattedText)

        if (message == "[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!" || message == "[NPC] Elle: I knew you could do it!") isDead = true
        if (message == "[NPC] Elle: Talk with me to begin!") isDead = false
        if (message.matches("\\[NPC\\] Elle: A (fleet of )?Dropship(s)? (is|are) approaching! Take (it|them) down before it's too late!".toRegex())) {
            ticksUntilDropship = MeowMod.DROPSHIP_DELAY
            world = Minecraft.getMinecraft().theWorld
        }
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase == TickEvent.Phase.START) return
        if (world == Minecraft.getMinecraft().theWorld && !isDead && ticksUntilDropship in 1 until Config.dropshipWarnDelay * 20) {
            Title.draw("${Utils.getColorString(Config.titleColor)}Dropship in ${"%.1f".format(ticksUntilDropship.toFloat() / 20)}s", 50)
        }
        if (Config.warnParty && world == Minecraft.getMinecraft().theWorld && !isDead && ticksUntilDropship == Config.dropshipWarnDelay * 20) {
            MeowMod.messageQueue.add("/ac Dropship bomb landing in ${ticksUntilDropship / 20}s")
        }
        if (ticksUntilDropship > 0) ticksUntilDropship--
    }
}
