package me.mlg.rat;

import me.mlg.rat.commands.ReloadConfigCommand;
import me.mlg.rat.commands.SetPosition;
import me.mlg.rat.commands.ToggleCommand;
import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.modules.GreetGuild;
import me.mlg.rat.modules.MessageResponder;
import me.mlg.rat.modules.RabbitReminder;
import me.mlg.rat.modules.WatcherDisplay;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = RatAddons.MODID, version = RatAddons.VERSION)
public class RatAddons {
    public static final String MODID = "RatAddons";
    public static final String VERSION = "0.1.2";

    public static int defaultDisplayTimeTick;
    public static int defaultDisplayTimeSecond;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new SetPosition());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
        ClientCommandHandler.instance.registerCommand(new ReloadConfigCommand());
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        FMLCommonHandler.instance().bus().register(new RabbitReminder());
        FMLCommonHandler.instance().bus().register(new GreetGuild());
        FMLCommonHandler.instance().bus().register(new MessageResponder());
        FMLCommonHandler.instance().bus().register(new WatcherDisplay());

        ConfigHandler.reloadConfig();
        }
}
