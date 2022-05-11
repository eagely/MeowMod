package me.mlg.rat.handlers;

import me.mlg.rat.RatAddons;
import me.mlg.rat.modules.LavaFishingLoot;
import me.mlg.rat.modules.Timer;
import me.mlg.rat.modules.WatcherDisplay;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/*
Set up config
Credits to decendium and DSM
*/

public class ConfigHandler {
    public static Configuration config;
    private final static String file = "config/ratconfig.cfg";

    public static void init() {
        config = new Configuration(new File(file));
        try {
            config.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static int getInt(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0).getInt();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0;
    }

    public static double getDouble(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0D).getDouble();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0D;
    }

    public static String getString(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, "").getString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return "";
    }

    public static boolean getBoolean(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, false).getBoolean();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return true;
    }

    public static void writeIntConfig(String category, String key, int value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            int set = config.get(category, key, value).getInt();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeDoubleConfig(String category, String key, double value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            double set = config.get(category, key, value).getDouble();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeStringConfig(String category, String key, String value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            String set = config.get(category, key, value).getString();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static void writeBooleanConfig(String category, String key, boolean value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            boolean set = config.get(category, key, value).getBoolean();
            config.getCategory(category).get(key).set(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static boolean hasKey(String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (!config.hasCategory(category)) return false;
            return config.getCategory(category).containsKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return false;
    }

    public static void deleteCategory(String category) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.hasCategory(category)) {
                config.removeCategory(new ConfigCategory(category));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static int initInt(String category, String key, int defaultValue) {
        if (!hasKey(category, key)) {
            writeIntConfig(category, key, defaultValue);
            return defaultValue;
        } else
            return getInt(category, key);
    }

    public static double initDouble(String category, String key, double defaultValue) {
        if (!hasKey(category, key)) {
            writeDoubleConfig(category, key, defaultValue);
            return defaultValue;
        } else
            return getDouble(category, key);
    }

    public static String initString(String category, String key, String defaultValue) {
        if (!hasKey(category, key)) {
            writeStringConfig(category, key, defaultValue);
            return defaultValue;
        } else
            return getString(category, key);
    }

    public static boolean initBoolean(String category, String key, boolean defaultValue) {
        if (!hasKey(category, key)) {
            writeBooleanConfig(category, key, defaultValue);
            return defaultValue;
        } else
            return getBoolean(category, key);
    }

    public static void reloadConfig() {
        RatAddons.copyLatestToggle = initBoolean("toggle", "copyraredrops", false);
        RatAddons.copyRareDropsToggle = initBoolean("toggle", "copyraredrops", false);
        RatAddons.greetToggle = initBoolean("toggle", "greet", false);
        RatAddons.lavaFishingLootToggle = initBoolean("toggle", "lavafishingloot", false);
        RatAddons.nicePbToggle = initBoolean("toggle", "nicepb", false);
        RatAddons.overflowSkillDislayToggle = initBoolean("toggle", "overflowskilldisplay", false);
        RatAddons.responderToggle = initBoolean("toggle", "responder", false);
        RatAddons.seaCreatureKillTimerToggle = initBoolean("toggle", "seacreaturekilltimer", false);
        RatAddons.seaCreatureLastHitToggle = initBoolean("toggle", "seacreaturelasthit", false);
        RatAddons.watcherToggle = initBoolean("toggle", "watcher", false);
        RatAddons.maxLevel.put("alchemy", initInt("maxlevel", "alchemy", 50));
        RatAddons.maxLevel.put("combat", initInt("maxlevel", "combat", 60));
        RatAddons.maxLevel.put("carpentry", initInt("maxlevel", "carpentry", 50));
        RatAddons.maxLevel.put("enchanting", initInt("maxlevel", "enchanting", 60));
        RatAddons.maxLevel.put("farming", initInt("maxlevel", "farming", 60));
        RatAddons.maxLevel.put("fishing", initInt("maxlevel", "fishing", 50));
        RatAddons.maxLevel.put("foraging", initInt("maxlevel", "foraging", 50));
        RatAddons.maxLevel.put("mining", initInt("maxlevel", "mining", 60));
        RatAddons.maxLevel.put("taming", initInt("maxlevel", "taming", 50));
        LavaFishingLoot.lavaLeechCounter = initInt("tracker", "lavaleech", 0);
        LavaFishingLoot.moogmaCounter = initInt("tracker", "moogma", 0);
        LavaFishingLoot.lavaFlameCounter = initInt("tracker", "lavaflame", 0);
        LavaFishingLoot.magmaSlugCounter = initInt("tracker", "magmaslug", 0);
        LavaFishingLoot.pyroclasticWormCounter = initInt("tracker", "pyroclasticworm", 0);
        LavaFishingLoot.fireEelCounter = initInt("tracker", "fireeel", 0);
        LavaFishingLoot.taurusCounter = initInt("tracker", "taurus", 0);
        LavaFishingLoot.thunderCounter = initInt("tracker", "thunder", 0);
        LavaFishingLoot.lordJawbusCounter = initInt("tracker", "lordjawbus", 0);
        LavaFishingLoot.vanquisherCounter = initInt("tracker", "vanquisher", 0);
        LavaFishingLoot.x = initInt("position", "lavafishingloot-x", 0);
        LavaFishingLoot.y = initInt("position", "lavafishingloot-y", 0);
        Timer.x = initInt("position", "timer-x", 0);
        Timer.y = initInt("position", "timer-y", 0);
        WatcherDisplay.x = initInt("position", "watcher-x", 0);
        WatcherDisplay.y = initInt("position", "watcher-y", 0);
    }
}
