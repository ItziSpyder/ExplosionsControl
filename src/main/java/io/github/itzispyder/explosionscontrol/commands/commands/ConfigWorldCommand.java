package io.github.itzispyder.explosionscontrol.commands.commands;

import io.github.itzispyder.explosionscontrol.commands.BukkitCommand;
import io.github.itzispyder.explosionscontrol.commands.CmdExHandler;
import io.github.itzispyder.explosionscontrol.commands.TabComplBuilder;
import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.data.ExplosionGui;
import io.github.itzispyder.explosionscontrol.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ConfigWorldCommand implements BukkitCommand {

    @Override
    public String getName() {
        return "configworld";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player player = (Player)sender;
            World world = Bukkit.getWorld(args[0]);
            ExplosionConfig config = ExplosionConfig.load(world);
            ExplosionGui gui = new ExplosionGui(config);

            player.openInventory(gui.loadGui());
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex, command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new TabComplBuilder(sender, command, alias, args)
                .add(1, ArrayUtils.toNewList(Bukkit.getWorlds(), World::getName))
                .build();
    }
}
