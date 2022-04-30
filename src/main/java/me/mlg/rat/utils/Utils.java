package me.mlg.rat.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utils {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static int hypixelChatDelayTick = 100;
    public static int defaultDisplayTimeTick = 100;

    public static void printErrorMessage(String error) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.RED + "Error: " + error));
    }

    public static void printRatMessage(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.GREEN + message));
    }

    public static boolean isOnHypixel() {
        if(mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        else
            return false;
    }
}
