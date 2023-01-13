package me.eagely.utils

object Skyblock {

    enum class Island(val formattedName: String, val mode: String) {
        PrivateIsland("Private Island", "dynamic"),
        SpiderDen("Spider's Den", "combat_1"),
        CrimsonIsle("Crimson Isle", "crimson_isle"),
        TheEnd("The End", "combat_3"),
        GoldMine("Gold Mine", "mining_1"),
        DeepCaverns("Deep Caverns", "mining_2"),
        DwarvenMines("Dwarven Mines", "mining_3"),
        CrystalHollows("Crystal Hollows", "crystal_hollows"),
        FarmingIsland("The Farming Islands", "farming_1"),
        ThePark("The Park", "foraging_1"),
        Dungeon("Dungeon", "dungeon"),
        DungeonHub("Dungeon Hub", "dungeon_hub"),
        Hub("Hub", "hub"),
        DarkAuction("Dark Auction", "dark_auction"),
        JerryWorkshop("Jerry's Workshop", "winter"),
        Instanced("Instanced", "instanced"),
        Unknown("(Unknown)", "");
    }
}
