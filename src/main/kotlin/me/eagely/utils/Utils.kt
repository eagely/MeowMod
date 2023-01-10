package me.eagely.utils

import net.minecraft.util.EnumChatFormatting
 object Utils {
    fun getColorString(color: Int): String {
        return if (color == 16) "§z" else EnumChatFormatting.values()[color].toString()
    }
}
