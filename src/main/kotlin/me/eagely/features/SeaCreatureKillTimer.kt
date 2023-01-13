package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent


class SeaCreatureKillTimer {
    private var tickSinceJawbus = 0
    private var tickSinceThunder = 0
    private var isJawbus = false
    private var isThunder = false

    @SubscribeEvent
    fun onTick(event: TickEvent.ServerTickEvent) {
        if(event.phase != TickEvent.Phase.START) return
        if (isJawbus)
            tickSinceJawbus++
        else if (isThunder)
            tickSinceThunder++
    }

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if(!Config.seaCreatureKillTimer || event.type == 2.toByte()) return
        val message: String = StringUtils.stripControlCodes(event.message.unformattedText)
        if (message.contains(":")) return

            when (message) {
                "You have angered a legendary creature... Lord Jawbus has arrived" -> isJawbus = true
                "You hear a massive rumble as Thunder emerges." -> isThunder = true
            }
    }

    @SubscribeEvent
    fun onEntityDeath(event: LivingDeathEvent) {
        if (isJawbus && event.entityLiving.name.equals("Iron Golem")) {
            MeowMod.queueChatMessage("Lord Jawbus took " + tickSinceJawbus.toFloat() / 20 + "s to kill")
            tickSinceJawbus = 0
            isJawbus = false
        }
        else if (isThunder && event.entityLiving.name.equals("Guardian")) {
            MeowMod.queueChatMessage("Thunder took " + tickSinceThunder.toFloat() / 20 + "s to kill")
            tickSinceThunder = 0
            isThunder = false
        }
    }
}
