package me.itzispyder.explosionscontrol;

import me.itzispyder.explosionscontrol.commands.Commands;
import me.itzispyder.explosionscontrol.events.Explosions;
import me.itzispyder.explosionscontrol.events.ToggleMenu;
import me.itzispyder.explosionscontrol.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ExplosionsControl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Messages.starter + "6Explosion control §cenabled.");
        }

        // Plugin config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        List<String> worlds = getConfig().getStringList("server.worlds");
        for (World world : Bukkit.getServer().getWorlds()) {
            if (!getConfig().getStringList("server.worlds").contains(world.getName())) {
                worlds.add(world.getName());
            }
        }
        getConfig().set("server.worlds",worlds);
        saveConfig();

        // Commands
        getCommand("loadworlds").setExecutor(new Commands(this));
        getCommand("explosions").setExecutor(new Commands(this));
        getCommand("explosionscontrol").setExecutor(new Commands(this));

        // Events
        getServer().getPluginManager().registerEvents(new ToggleMenu(this),this);
        getServer().getPluginManager().registerEvents(new Explosions(this),this);

        // Items
        ToggleMenu.setX();
        ToggleMenu.setY();
        ToggleMenu.setZ();
        ToggleMenu.setClose();
        ToggleMenu.setBack();
        ToggleMenu.setReload();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Messages.starter + "6Explosion control §cdisabled.");
        }

    }


}
