package me.mlg.rat.commands;

import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class DebugCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "rat";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "rat istoggled/addchatmessage value type";
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
        switch(args[0]) {
            case "istoggled":
                switch(args[2]) {
                    case "boolean":
                        Utils.mc.thePlayer.addChatMessage(new ChatComponentText("" + ConfigHandler.getBoolean("toggle", args[1])));
                        break;
                    case "string":
                        Utils.mc.thePlayer.addChatMessage(new ChatComponentText(ConfigHandler.getString("toggle", args[1])));
                        break;
                    case "integer":
                        Utils.mc.thePlayer.addChatMessage(new ChatComponentText("" + ConfigHandler.getInt("toggle", args[1])));
                        break;
                    case "double":
                        Utils.mc.thePlayer.addChatMessage(new ChatComponentText("" + ConfigHandler.getDouble("toggle", args[1])));
                        break;
                    default:
                        Utils.printErrorMessage("Invalid argument at istoggled; args[1]");
                }
                break;
            case "addchatmessage":
                Utils.mc.thePlayer.addChatMessage(new ChatComponentText(args[1]));
                break;
            default:
                Utils.mc.thePlayer.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
                break;
        }
    }
}
