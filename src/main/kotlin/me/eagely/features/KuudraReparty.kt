package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.utils.Utils
import me.eagely.utils.Utils.stripControlCodes
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent

class KuudraReparty {
    companion object {
        var repartying = false
        var otherLeaderJoined = false
        var invitePlayerJoined = false
        fun reparty() {
            repartying = true
            MeowMod.messageQueue.add("/p ${Config.otherLeader}")
            MeowMod.messageQueue.add("/p ${Config.invitePlayer}")
        }
    }

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if(!Config.kuudraReparty || !repartying || event.type == 2.toByte()) return
        val message = event.message.unformattedText.stripControlCodes()

         if(message.lowercase().endsWith("${Config.otherLeader.lowercase()} joined the party."))
            otherLeaderJoined = true
         else if (message.lowercase().endsWith("${Config.invitePlayer.lowercase()} joined the party."))
            invitePlayerJoined = true

        if(otherLeaderJoined && invitePlayerJoined) {
            MeowMod.safeMessageQueue.add("/p warp")
            MeowMod.safeMessageQueue.add("/p kick ${Config.enterPlayer}")
            MeowMod.safeMessageQueue.add("/p transfer ${Config.otherLeader}")
            MeowMod.safeMessageQueue.add("/p leave")
            otherLeaderJoined = false
            invitePlayerJoined = false
            repartying = false
        }
    }
}

/*
class KuudraReparty {
    companion object {
        private var waitingForJoin = false
        private var otherLeaderJoined = false
        private var invitePlayerJoined = false
        private var invited = false

        fun reparty() {
            MeowMod.safeMessageQueue.add("/p warp")
            MeowMod.safeMessageQueue.add("/p kick ${Config.enterPlayer}")
            MeowMod.safeMessageQueue.add("/p transfer ${Config.otherLeader}")
            MeowMod.safeMessageQueue.add("/p leave")
            waitingForJoin = false
            otherLeaderJoined = false
            invitePlayerJoined = false
        }
    }

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.kuudraReparty || event.type == 2.toByte()) return
        val message = event.message.unformattedText.stripControlCodes()

        if (message.lowercase().contains("${Config.otherLeader.lowercase()} has invited you to join their party!")) {
            MeowMod.messageQueue.add("/p join ${Config.otherLeader}")
            invited = true
        }
        else if (!invited && message == "[NPC] Elle: Talk with me to begin!") {
            MeowMod.messageQueue.add("/p ${Config.otherLeader}")
            MeowMod.messageQueue.add("/p ${Config.invitePlayer}")
            waitingForJoin = true
        }
        else if (waitingForJoin && (message.lowercase().endsWith("${Config.otherLeader.lowercase()} joined the party.")))
            otherLeaderJoined = true
        else if (waitingForJoin && (message.lowercase().endsWith("${Config.invitePlayer.lowercase()} joined the party.")))
            invitePlayerJoined = true

        if (otherLeaderJoined && invitePlayerJoined) {
            reparty()
        }
    }

}
*/
