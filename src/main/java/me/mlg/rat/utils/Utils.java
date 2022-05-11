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
        return new SimpleDateFormat("m:s").format(new Date(tick * 50L));
    }
    public static double getSkillLevel(long xp) {
        return (double) ((xp - 4000000) / 300000) + 50;
    }
    public static void sendToWebhook(String message, String webhook) {}
    public static boolean isOnHypixel() {
        if(mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        else
            return false;
    }
}
