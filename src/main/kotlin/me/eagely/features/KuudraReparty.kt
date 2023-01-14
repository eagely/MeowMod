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
            MeowMod.safeMessageQueue.add("/p ${Config.otherLeader}")
            MeowMod.safeMessageQueue.add("/p ${Config.invitePlayer}")
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
