package me.mlg.rat.modules;

import me.mlg.rat.RatAddons;
import me.mlg.rat.utils.Utils;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//p0.4 this doesnt even work idk why
public class OverflowSkillDisplay {

    @SubscribeEvent
    public void onActionBar(ClientChatReceivedEvent event) {
        if (event.type != 2)
            return;

        if (RatAddons.overflowSkillDislayToggle) {
            String[] actionBarSection = StringUtils.stripControlCodes(event.message.getUnformattedText()).split(" {3,}");
            for (String section : actionBarSection) {
                if (section.contains("+") && section.contains("/0") && section.contains("(") && section.contains(")")) {
                    long xp = 0;
                    int xpGained = 0;
                    double level = Utils.getSkillLevel(xp);
                    String skill = section.substring(section.indexOf(" "), section.indexOf("(") - 1);
                    if(level <= RatAddons.maxLevel.get(skill)) return;
                    try {
                        xp = Integer.parseInt(section.substring(section.indexOf("(") + 1, section.indexOf("/")));
                        xpGained = Integer.parseInt(section.substring(section.indexOf("+") + 1, section.indexOf(" ")));
                    } catch (IllegalArgumentException exception) {
                        Utils.printErrorMessage("Internal error " + exception);
                    }
                    if (((int) level > (int) Utils.getSkillLevel(xp - xpGained)) || RatAddons.debugToggle){
                        Utils.mc.thePlayer.addChatMessage(new ChatComponentText("" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD
                                + "-----------------------------------------------------"
                                + "\nOVERFLOW SKILL LEVEL UP " + EnumChatFormatting.RESET + EnumChatFormatting.AQUA
                                + skill + " " + EnumChatFormatting.GRAY + --level
                                + EnumChatFormatting.BOLD + "➜"
                                + EnumChatFormatting.RESET + EnumChatFormatting.AQUA + ++level
                                + EnumChatFormatting.RESET + EnumChatFormatting.AQUA
                                + "\n[RAT] " + EnumChatFormatting.GREEN
                                + "Assumed skill Level up with current scaling"
                                + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD
                                + "\n-----------------------------------------------------"));
                    }
                }
            }
        }
    }
}
