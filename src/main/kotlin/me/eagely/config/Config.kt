package me.eagely.config

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import java.io.File


object Config : Vigilant(File("./config/meowmod/config.toml"), "MeowMod", sortingBehavior = ConfigSorting) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Gui Cursor Position",
        description = "Change where the cursor appears when you open a gui, to set the position put your cursor where you want it to be and run /meow setcursor",
        category = "General"
    )
    var guiCursorPosition = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Jerry Box Only",
        description = "Only change the cursor position in jerry boxes",
        category = "General"
    )
    var guiCursorPositionJerryBoxOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Timer",
        description = "Display a timer (run /rat timer for more info)",
        category = "General"
    )
    var timer = false

    @Property(
        type = PropertyType.SELECTOR,
        name = "Timer Type",
        description = "Select the Type of timer",
        category = "General",
        options = ["Countdown", "Stopwatch"]
    )
    var timerType = 0

    @Property(
        type = PropertyType.SWITCH,
        name = "Endstone Protector",
        description = "Invite your friends to Stage 5 Endstone Protector, specify who to invite using /golemlist",
        category = "Bestiary"
    )
    var endstoneProtector = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Endstone Protector Guild Party",
        description = "Select if the message should be sent to guild as well",
        category = "Bestiary"
    )
    var endstoneProtectorGuild = false

    @Property(
        type = PropertyType.TEXT,
        name = "Endstone protector Message",
        description = "Enter the message to send",
        category = "Bestiary"
    )
    var endstoneProtectorMessage = "Stage 5 Endstone Protector, /p join or type 321"

    @Property(
        type = PropertyType.SWITCH,
        name = "Copy Rare Drops",
        description = "Copies Rare Drops to Clipboard",
        category = "Chat"
    )
    var copyRareDrops = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Guild GG",
        description = "Sends a gg message when the guild levels up or completes a quest",
        category = "Chat"
    )
    var guildGG = false

    @Property(
        type = PropertyType.TEXT,
        name = "Guild GG Message",
        description = "Enter the message to send",
        category = "Chat",
    )
    var guildGGMessage = "gg"


    @Property(
        type = PropertyType.SWITCH,
        name = "Guild Welcome",
        description = "Sends a welcome message when someone joins the guild",
        category = "Chat"
    )
    var guildWelcome = false

    @Property(
        type = PropertyType.TEXT,
        name = "Guild Welcome Message",
        description = "Enter the message to send",
        category = "Chat"
    )
    var guildWelcomeMessage = "Welcome"


    @Property(
        type = PropertyType.SWITCH,
        name = "Message Responder",
        description = "Responds to chat messages UNFINISHED",
        category = "Chat"
    )
    var messageResponder = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Sea Creature Kill Timer",
        description = "Displays the time it took to kill Lord Jawbus or Thunder",
        category = "Fishing"
    )
    var seaCreatureKillTimer = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Kuudra Splits",
        description = "Timed splits for the Kuudra bossfight",
        category = "Kuudra"
    )
    var kuudraSplits = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Kuudra Reparty",
        description = "Reparties your Kuudra party to skip cooldown",
        category = "Kuudra"
    )
    var kuudraReparty = false

    @Property(
        type = PropertyType.TEXT,
        name = "Other Leader",
        description = "Name of the other party leader, they must have the mod installed or manually do all the steps correctly. Not case sensitive",
        category = "Kuudra"
    )
    var otherLeader = ""

    @Property(
        type = PropertyType.TEXT,
        name = "Player that is entering with you",
        description = "Not case sensitive",
        category = "Kuudra"
    )
    var enterPlayer = ""

    @Property(
        type = PropertyType.TEXT,
        name = "Player that is entering with the other leader",
        description = "Not case sensitive",
        category = "Kuudra"
    )
    var invitePlayer = ""

    @Property(
        type = PropertyType.SWITCH,
        name = "Dropship Warning",
        description = "Warns you when the dropship is about to drop the bomb",
        category = "Kuudra"
    )
    var dropshipWarning = false

    @Property(
        type = PropertyType.SLIDER,
        name = "When to warn",
        description = "Amount of seconds before the dropship lands that you should be warned at",
        category = "Kuudra",
        min = 0,
        max = 48
    )
    var dropshipWarnDelay = 5

    @Property(
        type = PropertyType.SWITCH,
        name = "Nice PB",
        description = "Says \"nice pb\" in chat when you get a new time pb in dungeons",
        category = "Dungeons"
    )
    var nicePB = false

    @Property(
        type = PropertyType.TEXT,
        name = "Nice PB Message",
        description = "Enter the message to send",
        category = "Dungeons"
    )
    var nicePBMessage = "nice pb"

    @Property(
        type = PropertyType.SWITCH,
        name = "Dodge List",
        description = "Kicks people who join via party finder using specified /dodgelist",
        category = "Dungeons"
    )
    var dodgeList = false

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Title size",
        description = "Change the size of the title display",
        category = "Settings"
    )
    var titleScale = 1F

    @Property(
        type = PropertyType.SELECTOR,
        name = "Title color",
        description = "Change the color of the title display",
        category = "Settings",
        options = ["§0Black",
            "§1Dark Blue",
            "§2Dark Green",
            "§3Dark Aqua",
            "§4Dark Red",
            "§5Dark Purple",
            "§6Gold",
            "§7Gray",
            "§8Dark Gray",
            "§9Blue",
            "§aGreen",
            "§bAqua",
            "§cRed",
            "§dLight Purple",
            "§eYellow",
            "§fWhite",
            "§zSBA Chroma"
        ]
    )
    var titleColor = 15

    fun init() {
        initialize()
        addDependency(javaClass.getDeclaredField("timerType"), javaClass.getDeclaredField("timer"))
        addDependency(javaClass.getDeclaredField("guildWelcomeMessage"), javaClass.getDeclaredField("guildWelcome"))
        addDependency(javaClass.getDeclaredField("guildGGMessage"), javaClass.getDeclaredField("guildGG"))
        addDependency(javaClass.getDeclaredField("nicePBMessage"), javaClass.getDeclaredField("nicePB"))
        addDependency(javaClass.getDeclaredField("guiCursorPositionJerryBoxOnly"), javaClass.getDeclaredField("guiCursorPosition"))
        addDependency(javaClass.getDeclaredField("otherLeader"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("enterPlayer"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("invitePlayer"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("dropshipWarnDelay"), javaClass.getDeclaredField("dropshipWarning"))

        setCategoryDescription(
            "General",
            "General settings"
        )

        setCategoryDescription(
            "Chat",
            "Chat related settings"
        )

        setCategoryDescription(
            "Fishing",
            "Fishing related settings"
        )

        setCategoryDescription(
            "Kuudra",
            "Kuudra related settings"
        )

        setCategoryDescription(
            "Dungeons",
            "Dungeon related settings"
        )

        setCategoryDescription(
            "Settings",
            "Mod Settings"
        )
    }
    private object ConfigSorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> = Comparator { o1, o2 ->
            if (o1.name == "General") return@Comparator -1
            if (o2.name == "General") return@Comparator 1
            else compareValuesBy(o1, o2) { it.name }
        }
    }
}
