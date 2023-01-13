package me.eagely.utils

import me.eagely.config.Config
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import kotlin.math.min

class Title {
    companion object {
        private lateinit var title: String
        private var endTime: Long = 0
        fun draw(string: String, time: Int) {
            this.title = string
            this.endTime = time.toLong() + System.currentTimeMillis()
        }
    }

    @SubscribeEvent
    fun onRender(event: RenderGameOverlayEvent.Text) {
        if (System.currentTimeMillis() > endTime) return
        val width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(title)
        val screenWidth = ScaledResolution(Minecraft.getMinecraft()).scaledWidth_double
        val screenHeight = ScaledResolution(Minecraft.getMinecraft()).scaledHeight_double
        var scale: Double = ((screenWidth - 100) * Config.titleSize) / width
        scale = min(scale, 10.0)
        GlStateManager.pushMatrix()
        GlStateManager.translate((screenWidth / 2 - width * scale / 2), screenHeight / 2 - (4.5 * scale), 0.0)
        GlStateManager.scale(scale, scale, scale)
        Minecraft.getMinecraft().fontRendererObj.drawString(title, 0f, 0f, 0, true)
        GlStateManager.popMatrix()
    }
}
