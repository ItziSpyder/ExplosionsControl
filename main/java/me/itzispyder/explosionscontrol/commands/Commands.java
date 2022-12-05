package me.itzispyder.explosionscontrol.commands;

import me.itzispyder.explosionscontrol.ExplosionsControl;
import me.itzispyder.explosionscontrol.events.ToggleMenu;
import me.itzispyder.explosionscontrol.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor {

    // instance of the main class
    static ExplosionsControl plugin;
    public Commands(ExplosionsControl plugin) {
        this.plugin = plugin;
    }

    // commands
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "explosions":
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    ToggleMenu.openExplosionsMenu(p);
                }
                break;
            case "explosionscontrol":
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(
                            "   \n" + Messages.starter +
                                    "\n       §6Plugin version: §ev1.0" +
                                    "\n       §6Minimum version: §emc1.17.1" +
                                    "\n       §6Author: §eItziSpyder" +
                                    "\n       §6Idea credit: §eTheTelly" +
                                    "\n       §6Description: §eControl world explosions! Custom worlds supported!" +
                                    "\n       §6Plugin commands:" +
                                    "\n       §e-explosions" +
                                    "\n       §e-explosionscontrol" +
                                    "\n       §e-loadworlds \n "
                    );
                }
                break;
            case "loadworlds":
                List<String> worlds = plugin.getConfig().getStringList("server.worlds");
                for (World world : Bukkit.getServer().getWorlds()) {
                    if (!plugin.getConfig().getStringList("server.worlds").contains(world.getName())) {
                        worlds.add(world.getName());
                    }
                }

                Bukkit.getServer().getLogger().info("Saving world configurations to plugin config...");
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(Messages.starter + "§6Saving world configurations to plugin config...");
                }
                plugin.getConfig().set("server.worlds",worlds);
                plugin.saveConfig();
                Bukkit.getServer().getLogger().info("Saved world configurations!");
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(Messages.starter + "§6Saved world configurations!");
                }


                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(Messages.starter + "§6Successfully loaded all server worlds!");
                    for (String world : worlds) {
                        p.sendMessage("  §7-§e" + world);
                    }
                }
                break;
        }
        return true;
    }
}
