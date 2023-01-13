package me.eagely

import me.eagely.commands.Meow
import me.eagely.config.Config
import me.eagely.features.*
import me.eagely.utils.Title
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
import org.jetbrains.annotations.NotNull
import org.lwjgl.input.Keyboard
import java.io.File
import java.lang.Math.max
import kotlin.collections.ArrayDeque

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
        MinecraftForge.EVENT_BUS.register(SeaCreatureKillTimer())
        MinecraftForge.EVENT_BUS.register(GuiCursorPosition())
        MinecraftForge.EVENT_BUS.register(KuudraSplits())
        MinecraftForge.EVENT_BUS.register(KuudraReparty())
        MinecraftForge.EVENT_BUS.register(DropshipWarning())
        MinecraftForge.EVENT_BUS.register(Title())
        MinecraftForge.EVENT_BUS.register(KuudraNotifications())
        keyBinds.forEach(ClientRegistry::registerKeyBinding)
    }

    @SubscribeEvent
    fun onKey(event: InputEvent.KeyInputEvent) {
        if (keyBinds[0].isPressed) display = Config.gui()
        if (keyBinds[1].isPressed) KuudraReparty.reparty()
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if(event.phase == TickEvent.Phase.START) return
        if (display != null) {
            Minecraft.getMinecraft().displayGuiScreen(display)
            display = null
        }
        if(messageQueue.isNotEmpty() && chatDelay == 0) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(messageQueue.first())
            messageQueue.removeFirst()
            chatDelay = HYPIXEL_CHAT_DELAY + safeChatDelay
        }
        if(safeMessageQueue.isNotEmpty() && safeChatDelay == 0) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(safeMessageQueue.first())
            safeMessageQueue.removeFirst()
            safeChatDelay = SAFE_HYPIXEL_CHAT_DELAY + chatDelay
        }
        if(chatDelay > 0) chatDelay--
        if(safeChatDelay > 0) safeChatDelay--
    }

    companion object {
        const val MOD_ID = "meowmod"
        const val MOD_NAME = "Meow Mod"
        const val MOD_VERSION = "1.0"
        const val MEOW_PREFIX = "§f[§bMEOW§f]"
        const val ERROR_PREFIX = "§f[§bMEOW§f]&c"
        const val DROPSHIP_DELAY = 960
        private const val HYPIXEL_CHAT_DELAY = 4
        private const val SAFE_HYPIXEL_CHAT_DELAY = 20

        val keyBinds = arrayOf(
            KeyBinding("Open Settings", Keyboard.KEY_NONE, "Meow Mod"),
            KeyBinding("Kuudra Reparty", Keyboard.KEY_K, "Meow Mod")
        )

        var display: GuiScreen? = null
        private var chatDelay = 0
        private var safeChatDelay = 0
        var messageQueue: ArrayDeque<String> = ArrayDeque()
        var safeMessageQueue: ArrayDeque<String> = ArrayDeque()
    }
}
