package me.eagely

import me.eagely.commands.Meow
import me.eagely.config.Config
import me.eagely.features.*
import me.eagely.features.KuudraSplits.Companion.currentSplit
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard
import java.io.File

@Mod(
    modid = MeowMod.MOD_ID,
    name = MeowMod.MOD_NAME,
    version = MeowMod.MOD_VERSION,
    clientSideOnly = true
)
class MeowMod {
    @Mod.EventHandler
    fun init(event: FMLPreInitializationEvent) {
        val directory = File(event.modConfigurationDirectory, "meowmod")
        directory.mkdirs()

        ClientCommandHandler.instance.registerCommand(Meow())
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        Config.init()
        MinecraftForge.EVENT_BUS.register(this)
        MinecraftForge.EVENT_BUS.register(EndstoneProtector())
        MinecraftForge.EVENT_BUS.register(Guild())
        MinecraftForge.EVENT_BUS.register(NicePB())
        MinecraftForge.EVENT_BUS.register(SeaCreatureKillTimer())
        MinecraftForge.EVENT_BUS.register(GuiCursorPosition())
        MinecraftForge.EVENT_BUS.register(KuudraSplits())
        keyBinds.forEach(ClientRegistry::registerKeyBinding)
    }

    @SubscribeEvent
    fun onKey(event: InputEvent.KeyInputEvent) {
        if (keyBinds[0].isPressed) display = Config.gui()
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if(event.phase == TickEvent.Phase.START) return
        if (display != null) {
            Minecraft.getMinecraft().displayGuiScreen(display)
            display = null
        }
        if(queuedMessage != null && chatDelay == 0) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(queuedMessage)
            queuedMessage = null
            chatDelay = HYPIXEL_CHAT_DELAY
        }
        if(KuudraSplits.currentSplit != - 1 && KuudraSplits.currentSplit < 5)
            KuudraSplits.splits[currentSplit]++
        if(chatDelay > 0) chatDelay--
    }

    companion object {
        const val MOD_ID = "meowmod"
        const val MOD_NAME = "Meow Mod"
        const val MOD_VERSION = "0.5"
        const val CHAT_PREFIX = "§f[§bMEOW§f]"
        const val ERROR = "§f[§bMEOW§f]&c"
        const val HYPIXEL_CHAT_DELAY = 4

        val keyBinds = arrayOf(
            KeyBinding("Open Settings", Keyboard.KEY_NONE, "Meow Mod")
        )

        var display: GuiScreen? = null
        var chatDelay = 0
        private var queuedMessage: String? = null

        fun queueChatMessage(message: String) {
            if(chatDelay == 0) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(message)
                chatDelay = HYPIXEL_CHAT_DELAY
            }
            else
                queuedMessage = message
        }
    }
}
