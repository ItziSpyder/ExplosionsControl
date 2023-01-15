package me.improper.explosionscontrol;

import me.improper.explosionscontrol.commands.Commands;
import me.improper.explosionscontrol.commands.Tabs;
import me.improper.explosionscontrol.data.ExplosionConfigFile;
import me.improper.explosionscontrol.data.ExplosionToggle;
import me.improper.explosionscontrol.events.OnExplode;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosionsControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.broadcastMessage("");
        ExplosionConfigFile.setup();
        ExplosionToggle.setup();

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnExplode(),this);

        // Commands
        getCommand("explosions").setExecutor(new Commands());
        getCommand("explosions").setTabCompleter(new Tabs());
        getCommand("loadworlds").setExecutor(new Commands());
        getCommand("loadworlds").setTabCompleter(new Tabs());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("ExplosionsControl");
    }
}
