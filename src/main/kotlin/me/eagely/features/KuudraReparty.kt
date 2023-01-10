package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class KuudraReparty {
    private var waitingForWarp = false
    private var otherLeaderJoined = false
    private var invitePlayerJoined = false

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        val message = StringUtils.stripControlCodes(event.message.unformattedText)

        if (!Config.kuudraReparty) return

        if (message.lowercase().contains("${Config.otherLeader.lowercase()} has invited you to join their party!")) {
            MeowMod.queueChatMessage("/p join ${Config.otherLeader}")
            return
        }

        if (message == "[NPC] Elle: Talk with me to begin!") {
            MeowMod.queueChatMessage("/pc [MEOW] Repartying")
            MeowMod.queueChatMessage("/p ${Config.otherLeader}")
            MeowMod.queueChatMessage("/p ${Config.invitePlayer}")
            waitingForWarp = true
            return
        }

        if (waitingForWarp && (message.lowercase().endsWith("${Config.otherLeader.lowercase()} joined the party.")))
            otherLeaderJoined = true
        else if (waitingForWarp && (message.lowercase()
                .endsWith("${Config.invitePlayer.lowercase()} joined the party."))
        )
            invitePlayerJoined = true

        if (otherLeaderJoined && invitePlayerJoined) {
            MeowMod.queueChatMessage("/p warp")
            MeowMod.queueChatMessage("/p kick ${Config.enterPlayer}")
            MeowMod.queueChatMessage("/p transfer ${Config.otherLeader}")
            MeowMod.queueChatMessage("/p leave")
            waitingForWarp = false
            otherLeaderJoined = false
            invitePlayerJoined = false
        }
    }
}
