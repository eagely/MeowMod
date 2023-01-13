package me.eagely.features

import me.eagely.MeowMod
import me.eagely.config.Config
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraft.util.ChatComponentText
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Robot


class GuiCursorPosition {

    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {
        if(!Config.guiCursorPosition) return

        if (event.gui is GuiChest) {
            val robot = Robot()
            val chest = (event.gui as GuiChest).inventorySlots as ContainerChest
            val chestName = chest.lowerChestInventory.displayName.unformattedText.trim()
            if ((chestName.contains("Jerry Box") || Config.jerryBoxOnly) && x != null && y != null)
                robot.mouseMove(x!!, y!!)
        }
    }

    companion object {
        private var x: Int? = null
        private var y: Int? = null

        fun getX(): Int? {
            return this.x
        }

        fun getY(): Int? {
            return this.y
        }

        fun setPosition(x: Int, y: Int) {
            try {
                this.x = x
                this.y = y
            } catch (exception: IllegalArgumentException) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(
                    ChatComponentText(
                        "${MeowMod.ERROR_PREFIX} IllegalArgumentException at GuiCursorPosition.kt:17. Something is wrong with your cursor position as it is not a number"
                    )
                )
            }
        }
    }
}
