package me.mlg.rat.commands;

import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ReloadConfigCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "ratreloadconfig";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "ratreloadconfig";
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
        Utils.printRatMessage("Reloaded Config!");
        ConfigHandler.reloadConfig();
    }
}
