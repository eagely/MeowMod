package me.mlg.rat.handlers;

import me.mlg.rat.RatAddons;
import me.mlg.rat.commands.ToggleCommand;
import me.mlg.rat.modules.RabbitReminder;
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
        } else {
            return getInt(category, key);
        }
    }

    public static double initDouble(String category, String key, double defaultValue) {
        if (!hasKey(category, key)) {
            writeDoubleConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getDouble(category, key);
        }
    }

    public static String initString(String category, String key, String defaultValue) {
        if (!hasKey(category, key)) {
            writeStringConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getString(category, key);
        }
    }

    public static boolean initBoolean(String category, String key, boolean defaultValue) {
        if (!hasKey(category, key)) {
            writeBooleanConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getBoolean(category, key);
        }
    }

    public static void reloadConfig() {
        ToggleCommand.rabbitToggle = initBoolean("toggle", "rabbit", false);
        ToggleCommand.responderToggle = initBoolean("toggle", "responder", false);
        ToggleCommand.greetToggle = initBoolean("toggle", "greet", false);
        ToggleCommand.watcherToggle = initBoolean("toggle", "watcher", false);
        RabbitReminder.x = initInt("position", "rabbit-x", 5);
        RabbitReminder.y = initInt("position", "rabbit-y", 5);
        WatcherDisplay.x = initInt("position", "watcher-x", 5);
        WatcherDisplay.y = initInt("position", "watcher-y", 5);
        RatAddons.defaultDisplayTimeTick = initInt("default", "displayTime", 100);
        RatAddons.defaultDisplayTimeSecond = initInt("default", "displayTime", 5);
    }
}
