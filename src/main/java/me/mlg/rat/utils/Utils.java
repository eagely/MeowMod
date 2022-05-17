package me.mlg.rat.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static int hypixelChatDelay = 100;
    public static int defaultDisplayTimeTick = 100;
    public static void printErrorMessage(String error) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.RED + "Error: " + error));
    }
    public static void printRatMessage(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.GREEN + message));
    }
    public static String tickToTime(int tick) {
        tick /= 20;
        if(tick / 60 < 1)
            return "" + tick;
        else if(tick / 3600 < 1)
            return tick / 60 + ":" + tick % 60;
        else if(tick / 216000 < 1)
            return tick / 3600 + ":" + tick % 3600 + ":" + tick % 60;
        else if(tick / 5184000 < 1)
            return tick / 216000 + ":" + tick % 216000 + ":" + tick % 3600 + ":" + tick % 60;
        return null;
    }
    public static double getSkillLevel(long xp) {
        return (double) ((xp - 4000000) / 300000) + 50;
    }

    public static boolean isOnHypixel() {
        if(mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel.net");
        else
            return false;
    }
}
