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

    public static String tickAsSecond(int tick) {
        return Integer.toString(tick / 20);
    }

    public static String tickAsMinute(int tick) {
        return Integer.toString(tick / 1200) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static String tickAsHour(int tick) {
        return Integer.toString(tick / 72000) + ":" + Integer.toString(tick / 1200 % 60) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static String tickAsDay(int tick) {
        return Integer.toString(tick / 20 / 86400) + ":" + Integer.toString(tick / 72000 % 60) + ":" + Integer.toString(tick / 1200 % 60) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static boolean isOnHypixel() {
        if(mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        else
            return false;
    }
}
