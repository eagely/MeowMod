package me.eagely.utils

import net.minecraft.util.EnumChatFormatting
import net.minecraft.util.StringUtils

object Utils {
    var hypixelRank: Regex = "(\\[.{0,7}\\]\\s)?".toRegex()
    var hypixelPlayer: Regex = "(\\[.{0,7}\\]\\s)?([a-zA-Z_0-9]{1,16})".toRegex()
    fun getColorString(color: Int): String {
        return if (color == 16) "§z" else EnumChatFormatting.values()[color].toString()
    }
    fun String.stripControlCodes(): String {
        return StringUtils.stripControlCodes(this)
    }
    fun String.extractInts(): String {
        return this.replace("\\D".toRegex(), "")
    }

}
