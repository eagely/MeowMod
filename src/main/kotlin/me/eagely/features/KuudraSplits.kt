package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.utils.Utils
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent

class KuudraSplits {
    private var currentSplit = -1
    private val splits: ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0)

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        if (!Config.kuudraSplits || event.type == 2.toByte()) return
        val message = StringUtils.stripControlCodes(event.message.unformattedText)

        if(Config.kuudraSplitsShow in 1..2 && message == "[NPC] Elle: Good job everyone. A hard fought battle come to an end. Let's get out of here before we run into any more trouble!") {
            currentSplit = -1
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.YELLOW + " Supplies" + EnumChatFormatting.WHITE + " took ${splits[0] / 20}s"))
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.AQUA + " Ballista" + EnumChatFormatting.WHITE + " took ${splits[1] / 20}s"))
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.GOLD + " Fuel and Stun" + EnumChatFormatting.WHITE + " took ${splits[2] / 20}s"))
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.DARK_RED + " Kuudra Down" + EnumChatFormatting.WHITE + " took ${splits[3] / 20}s"))
            for (i in splits.indices) splits[i] = 0
            return
        }

        if(Config.kuudraSplitsShow == 1) return

        when (message) {
            "[NPC] Elle: Okay adventurers, I will go and fish up Kuudra!" -> {
                currentSplit++
            }

            "[NPC] Elle: OMG! Great work collecting my supplies!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.YELLOW + " Supplies" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s "))
                currentSplit++
            }

            "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.AQUA + " Ballista" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s"))
                currentSplit++
            }

            "[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.MEOW_PREFIX + EnumChatFormatting.GOLD + " Fuel and Stun" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s "))
                currentSplit++
            }

            else -> {
                if (message.trim() == "DEFEAT") {
                    currentSplit = -1
                    for (i in splits.indices) splits[i] = 0
                }
            }
        }
    }

    @SubscribeEvent
    fun onTick(event: ClientTickEvent) {
        if(event.phase == TickEvent.Phase.START) return
        if (currentSplit in 0 until 4)
            splits[currentSplit]++
    }
}
