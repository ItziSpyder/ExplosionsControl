package io.github.itzispyder.explosionscontrol;

import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ExplosionsControl extends JavaPlugin {

    public static final Logger logger = Bukkit.getLogger();
    public static final String starter = Text.color("&7[&6Ex&eC&7] &r");
    public static ExplosionsControl instance;

    @Override
    public void onEnable() {
        instance = this;
        ExplosionConfig.updateAllWorlds();
    }

    @Override
    public void onDisable() {
        ExplosionConfig.updateAllWorlds();
    }
}
