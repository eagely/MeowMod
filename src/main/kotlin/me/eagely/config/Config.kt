package me.eagely.config

import gg.essential.elementa.utils.withIndex
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

    fun init() {
        initialize()
        addDependency(javaClass.getDeclaredField("timerType"), javaClass.getDeclaredField("timer"))
        addDependency(javaClass.getDeclaredField("guildWelcomeMessage"), javaClass.getDeclaredField("guildWelcome"))
        addDependency(javaClass.getDeclaredField("guildGGMessage"), javaClass.getDeclaredField("guildGG"))
        addDependency(javaClass.getDeclaredField("nicePBMessage"), javaClass.getDeclaredField("nicePB"))
        addDependency(javaClass.getDeclaredField("guiCursorPositionJerryBoxOnly"), javaClass.getDeclaredField("guiCursorPosition"))

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
