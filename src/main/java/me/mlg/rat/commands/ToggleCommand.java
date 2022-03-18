package me.mlg.rat.commands;

import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ToggleCommand extends CommandBase {
    public static boolean greetToggle = false;
    public static boolean rabbitToggle = false;
    public static boolean responderToggle = false;
    public static boolean watcherToggle = false;

    @Override
    public String getCommandName() {
        return "rattoggle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "rattoggle rabbit/responder/greet";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        switch(args[0].toLowerCase()) {
            case "greet":
                greetToggle = !greetToggle;
                ConfigHandler.writeBooleanConfig("toggle", "greet", greetToggle);
                Utils.printRatMessage("Toggled Greet to " + greetToggle);
                break;
            case "rabbit":
                rabbitToggle = !rabbitToggle;
                ConfigHandler.writeBooleanConfig("toggle", "rabbit", rabbitToggle);
                Utils.printRatMessage("Toggled Rabbit to " + rabbitToggle);
                break;
            case "responder":
                responderToggle = !responderToggle;
                ConfigHandler.writeBooleanConfig("toggle", "responder", responderToggle);
                Utils.printRatMessage("Toggled Responder to " + responderToggle);
                break;
            case "watcher":
                responderToggle = !responderToggle;
                ConfigHandler.writeBooleanConfig("toggle", "watcher", watcherToggle);
                Utils.printRatMessage("Toggled Watcher to " + watcherToggle);
                break;
            default:
                Utils.printRatMessage("Usage: " + getCommandUsage(sender));
        }
    }
}
