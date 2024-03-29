package io.github.itzispyder.explosionscontrol;

import io.github.itzispyder.explosionscontrol.commands.BukkitCommand;
import io.github.itzispyder.explosionscontrol.commands.commands.ConfigWorldCommand;
import io.github.itzispyder.explosionscontrol.commands.commands.UpdateWorldsCommand;
import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.data.JsonSerializable;
import io.github.itzispyder.explosionscontrol.data.PluginConfig;
import io.github.itzispyder.explosionscontrol.events.ExplosionListener;
import io.github.itzispyder.explosionscontrol.events.InventoryListener;
import io.github.itzispyder.explosionscontrol.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ExplosionsControl extends JavaPlugin {

    public static final Logger logger = Bukkit.getLogger();
    public static final PluginManager pm = Bukkit.getPluginManager();
    public static final String starter = Text.color("&7[&6Ex&eC&7]&r ");
    public static final PluginConfig config = JsonSerializable.load(PluginConfig.PATH, PluginConfig.class, new PluginConfig());
    public static ExplosionsControl instance;

    @Override
    public void onEnable() {
        instance = this;
        this.init();
        ExplosionConfig.updateAllWorlds();
        config.save();
    }

    @Override
    public void onDisable() {
        ExplosionConfig.updateAllWorlds();
    }

    public void init() {
        // listeners
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new ExplosionListener(), this);

        // commands
        addCommand(new UpdateWorldsCommand());
        addCommand(new ConfigWorldCommand());
    }

    public void addCommand(BukkitCommand command) {
        getCommand(command.getName()).setExecutor(command);
        getCommand(command.getName()).setTabCompleter(command);
    }
}
