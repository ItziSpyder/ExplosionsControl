package me.improper.explosionscontrol.data;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ExplosionConfigFile {

    /**
     * Creates a configuration for each existing world.
     * These are saved to "plugins/ExplosionsControl/worldconfigurations".
     */
    public static void setup() {
        for (World world : Bukkit.getWorlds()) {
            ExplosionConfiguration configuration = new ExplosionConfiguration(world);
            configuration.save();
        }
    }
}
