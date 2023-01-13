package me.eagely.commands

import me.eagely.MeowMod
import me.eagely.config.Config
import me.eagely.features.GuiCursorPosition
import me.eagely.features.KuudraReparty
import net.minecraft.client.Minecraft
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText
import org.lwjgl.input.Mouse


class Meow : ClientCommandBase("meow") {
    override fun getCommandUsage(sender: ICommandSender): String {
        return "${MeowMod.MEOW_PREFIX} Usage: /meow [help/timer/xxx]"
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
                    """${MeowMod.MEOW_PREFIX} /meow for gui
                        |${MeowMod.MEOW_PREFIX} /meow setcursor for jerry box cursor (unfinished)
                        |${MeowMod.MEOW_PREFIX} /meow reparty or press the set (in settings) reparty key, K by default""".trimMargin()
                )
            )
            "setcursor" -> {
                GuiCursorPosition.setPosition(Mouse.getX(), Mouse.getY())
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                    ChatComponentText(
                        "${MeowMod.MEOW_PREFIX} Set your cursor position to X${GuiCursorPosition.getX()} Y${GuiCursorPosition.getY()}"
                    )
                )
            }
            "reparty" -> {
                KuudraReparty.reparty()
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                    ChatComponentText(
                        "${MeowMod.MEOW_PREFIX} Repartying"
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
