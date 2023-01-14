package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.utils.Utils.stripControlCodes
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class VanquisherAlert {
    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if(!Config.vanquisherAlert || event.type == 2.toByte()) return
        val message = event.message.unformattedText.stripControlCodes()

        if(message == "A Vanquisher is spawning nearby!") {
            MeowMod.safeMessageQueue.add("/pc Vanquisher spawned at")
            MeowMod.safeMessageQueue.add("/pc x: ${Minecraft.getMinecraft().thePlayer.posX.toInt()}, y: ${Minecraft.getMinecraft().thePlayer.posY.toInt()}, z: ${Minecraft.getMinecraft().thePlayer.posZ.toInt()}")
        }
    }

    @SubscribeEvent
    fun onEntityDeath(event: LivingDeathEvent) {
        if(!Config.vanquisherAlert) return

        if(event.entityLiving.name.equals("Wither"))
            MeowMod.safeMessageQueue.add("/pc Vanquisher dead")
    }
}
