package me.eagely.commands

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.features.GuiCursorPosition
import net.minecraft.client.Minecraft
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import org.lwjgl.input.Mouse


class Meow : ClientCommandBase("meow") {
    override fun getCommandUsage(sender: ICommandSender): String {
        return "${MeowMod.CHAT_PREFIX} Usage: /meow [help/timer/xxx]"
    }

    @Throws(CommandException::class)
    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            MeowMod.display = Config.gui()
            return
        }

        when (args[0]) {
            "help" -> Minecraft.getMinecraft().thePlayer.addChatMessage(
                ChatComponentText(
                    "${MeowMod.CHAT_PREFIX} https://gist.github.com/eagely/8d903176e2a1a74ac898360fc8a56063"
                )
            )
            "setcursor" -> {
                GuiCursorPosition.setPosition(Mouse.getX(), Mouse.getY())
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                    ChatComponentText(
                        "${MeowMod.CHAT_PREFIX} Set your cursor position to X${GuiCursorPosition.getX()} Y${GuiCursorPosition.getY()}"
                    )
                )
            }
            else -> Minecraft.getMinecraft().thePlayer.addChatMessage(
                ChatComponentText(
                    getCommandUsage(sender)
                )
            )
        }
    }
}
