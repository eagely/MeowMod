package me.mlg.rat.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utils {

    public static int chatDelay = 500; //idk this, need to find
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void printErrorMessage(String error) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.RED + error));
    }

    public static void printRatMessage(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[RAT] " + EnumChatFormatting.GREEN + message));
    }

    public static String formatTickAsSecond(int tick) {
        return Integer.toString(tick / 20);
    }

    public static String formatTickAsMinute(int tick) {
        return Integer.toString(tick / 1200) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static String formatTickAsHour(int tick) {
        return Integer.toString(tick / 72000) + ":" + Integer.toString(tick / 1200 % 60) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static String formatTickAsDay(int tick) {
        return Integer.toString(tick / 20 / 86400) + ":" + Integer.toString(tick / 72000 % 60) + ":" + Integer.toString(tick / 1200 % 60) + ":" + Integer.toString(tick / 20 % 60);
    }

    public static boolean isOnHypixel() {
        if(mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
        else
            return false;
    }
}
