package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.minecraft.util.StringUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class KuudraSplits {
    companion object {
        var currentSplit = -1
        var splits: ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0)
    }

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent) {
        val message = StringUtils.stripControlCodes(event.message.unformattedText)

        if (!Config.kuudraSplits) return

        when (message) {
            "[NPC] Elle: Okay adventurers, I will go and fish up Kuudra!" -> {
                currentSplit++
            }

            "[NPC] Elle: OMG! Great work collecting my supplies!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.YELLOW + " Supplies" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s "))
                currentSplit++
            }

            "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.AQUA + " Ballista" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s"))
                currentSplit++
            }

            "[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!" -> {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.GOLD + " Stun Kill" + EnumChatFormatting.WHITE + "took ${splits[currentSplit] / 20}s "))
                currentSplit++
            }

            "[NPC] Elle: Good job everyone. A hard fought battle come to an end. Let's get out of here before we run into any more trouble!" -> {
                currentSplit = -1
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.YELLOW + " Supplies" + EnumChatFormatting.WHITE + " took ${splits[0] / 20}s"))
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.AQUA + " Ballista" + EnumChatFormatting.WHITE + " took ${splits[1] / 20}s"))
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.GRAY + " Fuel" + EnumChatFormatting.WHITE + " took ${splits[2] / 20}s"))
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.GOLD + " Stun Kill" + EnumChatFormatting.WHITE + " took ${splits[3] / 20}s"))
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.GRAY + " Fuel" + EnumChatFormatting.WHITE + " and" + EnumChatFormatting.GOLD + " Stun Kill" + EnumChatFormatting.WHITE + " took ${splits[2] / 20 + splits[3] / 20}s"))
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.DARK_RED + " Kuudra Down" + EnumChatFormatting.WHITE + " took ${splits[4] / 20}s"))
                for (i in splits.indices)
                    splits[i] = 0
            }

            else -> {
                if (message.endsWith(" recovered a Fuel Cell and charged the Ballista! (100%)")) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText(MeowMod.CHAT_PREFIX + EnumChatFormatting.GRAY + " Fuel" + EnumChatFormatting.WHITE + " took ${splits[currentSplit] / 20}s "))
                    currentSplit++
                }
                if (message.contains("DEFEAT")) {
                    currentSplit = -1
                    for (i in splits.indices)
                        splits[i] = 0

                }
            }
        }


    }
}
