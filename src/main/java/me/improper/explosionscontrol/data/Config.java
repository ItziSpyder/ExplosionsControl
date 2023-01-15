package me.improper.explosionscontrol.data;

import me.improper.explosionscontrol.ExplosionsControl;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static FileConfiguration CONFIG = ExplosionsControl.getInstance().getConfig();

    /**
     * This will return the current plugin prefix set
     * in the plugin's configuration file.
     *
     * @return The plugin prefix
     */
    public static String getPluginPrefix() {
        return CONFIG.getString("config.plugin.prefix");
    }
}
