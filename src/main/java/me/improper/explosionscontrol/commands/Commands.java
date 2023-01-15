package me.improper.explosionscontrol.commands;

import me.improper.explosionscontrol.data.ExplosionConfigFile;
import me.improper.explosionscontrol.data.ExplosionToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase().trim();

        try {
            switch (commandName) {
                case "explosions" -> {
                    ((Player) sender).openInventory(ExplosionToggle.EXPLOSIONMENU);
                    return true;
                }
                case "loadworlds" -> {
                    ExplosionConfigFile.setup();
                    return true;
                }
            }
        } catch (Exception exception) {
            String message = ChatColor.DARK_RED + "Command error: " + ChatColor.RED;
            if (exception instanceof NullPointerException) message += "Command contains a null value!";
            else if (exception instanceof IndexOutOfBoundsException) message += "Incomplete command! Not enough information was provided!";
            else message += exception.getMessage();
            sender.sendMessage(message);
            return true;
        }

        return false;
    }
}
