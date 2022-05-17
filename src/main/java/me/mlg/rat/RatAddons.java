package me.mlg.rat;

import me.mlg.rat.commands.RatCommand;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.modules.*;
import me.mlg.rat.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
//Your new API key is xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
//0.3: rest in peace rabbit hat reminder you will be missed
@Mod(modid = RatAddons.MODID, version = RatAddons.VERSION)
public class RatAddons {
    public static final String MODID = "RatAddons";
    public static final String VERSION = "0.3";

    public static boolean copyRareDropsToggle;
    public static boolean greetToggle;
    public static boolean lavaFishingLootToggle;
    public static boolean responderToggle;
    public static boolean watcherToggle;
    public static boolean copyLatestToggle;
    public static boolean nicePbToggle;
    public static boolean overflowSkillDislayToggle;
    public static boolean seaCreatureKillTimerToggle;
    public static boolean seaCreatureLastHitToggle;
    public static boolean timerToggle;
    public static boolean debugToggle;
    public static KeyBinding[] keyBindings = new KeyBinding[3];
    public static HashMap<String, Integer> maxLevel = new HashMap<>();
    public static int currentDate = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("dMy")));
    public static int hypixelChatDelay = 200;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new RatCommand());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ConfigHandler.reloadConfig();
        FMLCommonHandler.instance().bus().register(this);
        FMLCommonHandler.instance().bus().register(new AutoNicePb());
        FMLCommonHandler.instance().bus().register(new CopyLatestChatMessage());
        FMLCommonHandler.instance().bus().register(new CopyRareDrops());
        FMLCommonHandler.instance().bus().register(new Default());
        FMLCommonHandler.instance().bus().register(new GreetGuild());
        FMLCommonHandler.instance().bus().register(new LavaFishingLoot());
        FMLCommonHandler.instance().bus().register(new MessageResponder());
        FMLCommonHandler.instance().bus().register(new OverflowSkillDisplay());
        FMLCommonHandler.instance().bus().register(new SeaCreatureAddons());
        FMLCommonHandler.instance().bus().register(new Timer());
        FMLCommonHandler.instance().bus().register(new WatcherDisplay());

        keyBindings[0] = new KeyBinding("Copy Latest Chat Message to Clipboard", Keyboard.KEY_L, "Rat Addons");
        keyBindings[1] = new KeyBinding("Open GUI", Keyboard.KEY_RSHIFT, "Rat Addons");
        keyBindings[2] = new KeyBinding("Start Fishing Timer", Keyboard.KEY_F, "Rat Addons");

        for (KeyBinding keyBinding : keyBindings)
            ClientRegistry.registerKeyBinding(keyBinding);
    }
}
