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
        name = "Gui Cursor Position // DOESNT WORK RN",
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
    var jerryBoxOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Endstone Protector",
        description = "Invite your friends to Stage 5 Endstone Protector, specify who to invite using /golemlist",
        category = "Bestiary"
    )
    var endstoneProtector = false

    @Property(
        type = PropertyType.TEXT,
        name = "Endstone protector Message",
        description = "Enter the message to send",
        category = "Bestiary"
    )
    var endstoneProtectorMessage = "Stage 5 Endstone Protector, /p join or type 321"

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
        name = "Sea Creature Kill Timer",
        description = "Displays the time it took to kill Lord Jawbus or Thunder",
        category = "Fishing"
    )
    var seaCreatureKillTimer = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Splits",
        description = "Timed splits for every phase",
        category = "Kuudra"
    )
    var kuudraSplits = false

    @Property(
        type = PropertyType.SELECTOR,
        name = "When to show",
        description = "When to show kuudra splits",
        category = "Kuudra",
        options = ["During run", "End of run", "Both"]
    )
    var kuudraSplitsShow = 0

    @Property(
        type = PropertyType.SWITCH,
        name = "Reparty",
        description = "Reparties your Kuudra party to skip cooldown. This will likely break if the other leader isnt using meowmod",
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
        name = "Warn party",
        description = "Warn the party in all chat about an incoming dropship",
        category = "Kuudra"
    )
    var warnParty = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Vanquisher Alert",
        description = "Sends an alert in party chat when you spawn a vanquisher",
        category = "Kuudra"
    )
    var vanquisherAlert = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Notifications",
        description = "Show notifications for different kuudra phases and events",
        category = "Kuudra"
    )
    var kuudraNotifications = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Supply Notification",
        description = "[NPC] Elle: OMG! Great work collecting my supplies!",
        category = "Kuudra"
    )
    var supplyNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Ballista Notification",
        description = "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!",
        category = "Kuudra"
    )
    var ballistaNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Blazes Spawned Notification",
        description = "[NPC] Elle: Phew! The Ballista is finally ready! It should be strong enough to tank Kuudra's blows now!",
        category = "Kuudra"
    )
    var blazesSpawnedNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "All Fuel Notification",
        description = "Player recovered a Fuel Cell and charged the Ballista! (x%)",
        category = "Kuudra",
    )
    var allFuelNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Last Fuel Notification",
        description = "Player recovered a Fuel Cell and charged the Ballista! (100%)",
        category = "Kuudra",
    )
    var lastFuelNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Cannon Mount Notification",
        description = "Player mounted a Cannon",
        category = "Kuudra"
    )
    var cannonMountNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Stun Notification",
        description = "Player destroyed one of Kuudra's pods!",
        category = "Kuudra"
    )
    var stunNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Stun Kill Notification",
        description = "[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!",
        category = "Kuudra"
    )
    var stunKillNotification = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Kuudra Down Notification",
        description = "[NPC] Elle: Good job everyone. A hard fought battle come to an end. Let's get out of here before we run into any more trouble!",
        category = "Kuudra"
    )
    var kuudraDownNotification = true

    @Property(
        type = PropertyType.PERCENT_SLIDER,
        name = "Title size",
        description = "Change the size of the title display",
        category = "Settings"
    )
    var titleSize = 1f

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
        addDependency(javaClass.getDeclaredField("guildWelcomeMessage"), javaClass.getDeclaredField("guildWelcome"))
        addDependency(javaClass.getDeclaredField("guildGGMessage"), javaClass.getDeclaredField("guildGG"))
        addDependency(javaClass.getDeclaredField("jerryBoxOnly"), javaClass.getDeclaredField("guiCursorPosition"))
        addDependency(javaClass.getDeclaredField("endstoneProtectorMessage"), javaClass.getDeclaredField("endstoneProtector"))
        addDependency(javaClass.getDeclaredField("kuudraSplitsShow"), javaClass.getDeclaredField("kuudraSplits"))
        addDependency(javaClass.getDeclaredField("otherLeader"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("enterPlayer"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("invitePlayer"), javaClass.getDeclaredField("kuudraReparty"))
        addDependency(javaClass.getDeclaredField("dropshipWarnDelay"), javaClass.getDeclaredField("dropshipWarning"))
        addDependency(javaClass.getDeclaredField("warnParty"), javaClass.getDeclaredField("dropshipWarning"))
        addDependency(javaClass.getDeclaredField("supplyNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("ballistaNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("blazesSpawnedNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("allFuelNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("lastFuelNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("stunNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("cannonMountNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("stunKillNotification"), javaClass.getDeclaredField("kuudraNotifications"))
        addDependency(javaClass.getDeclaredField("kuudraDownNotification"), javaClass.getDeclaredField("kuudraNotifications"))

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
