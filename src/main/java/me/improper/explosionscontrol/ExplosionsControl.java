package me.improper.explosionscontrol;

import me.improper.explosionscontrol.commands.Commands;
import me.improper.explosionscontrol.commands.Tabs;
import me.improper.explosionscontrol.data.Config;
import me.improper.explosionscontrol.data.ExplosionConfigFile;
import me.improper.explosionscontrol.data.ExplosionToggle;
import me.improper.explosionscontrol.events.OnExplode;
import me.improper.explosionscontrol.events.OnInventory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosionsControl extends JavaPlugin {

    public static String STARTER;

    @Override
    public void onEnable() {
        // Plugin startup logic
        STARTER = Config.getPluginPrefix();
        Bukkit.broadcastMessage(STARTER + "aExplosionsControls has loaded!");
        ExplosionConfigFile.setup();
        ExplosionToggle.setup();

        // Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Events
        Bukkit.getPluginManager().registerEvents(new OnExplode(),this);
        Bukkit.getPluginManager().registerEvents(new OnInventory(),this);

        // Commands
        getCommand("explosions").setExecutor(new Commands());
        getCommand("explosions").setTabCompleter(new Tabs());
        getCommand("loadworlds").setExecutor(new Commands());
        getCommand("loadworlds").setTabCompleter(new Tabs());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.broadcastMessage(STARTER + "cExplosionsControls has disabled! If this isn't a reload please consider restarting" +
                " as your server is no longer being protected from explosions!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("ExplosionsControl");
    }
}
