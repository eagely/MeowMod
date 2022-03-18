package me.mlg.rat.commands;

import me.mlg.rat.handlers.ConfigHandler;
import me.mlg.rat.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetPosition extends CommandBase {
    @Override
    public String getCommandName() {
        return "ratsetpos";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "ratsetpos module x y";
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
        int x;
        int y;
        try {
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
        } catch(IllegalArgumentException exception) {
            Utils.printErrorMessage("Illegal argument");
            return;
        }
        if(args[0].equals("rabbit"))
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[SBP] " + EnumChatFormatting.GREEN + "Set Position of " + EnumChatFormatting.AQUA + args[0] + EnumChatFormatting.GREEN + " to " + EnumChatFormatting.AQUA + args[1] + " " +  args[2]));
        ConfigHandler.writeIntConfig("xpos", args[0], x);
        ConfigHandler.writeIntConfig("ypos", args[0], y);
    }
}
