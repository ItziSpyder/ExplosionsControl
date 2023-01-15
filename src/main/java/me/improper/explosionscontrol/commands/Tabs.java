package me.improper.explosionscontrol.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        String commandName = command.getName().toLowerCase().trim();

        switch (commandName) {
            case "explosions","loadworlds" -> {
                break;
            }
        }

        list.removeIf(i -> !i.toLowerCase().contains(args[args.length - 1].toLowerCase()));
        return list;
    }
}
