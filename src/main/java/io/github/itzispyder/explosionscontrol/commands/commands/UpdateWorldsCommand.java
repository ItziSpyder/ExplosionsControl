package io.github.itzispyder.explosionscontrol.commands.commands;

import io.github.itzispyder.explosionscontrol.commands.BukkitCommand;
import io.github.itzispyder.explosionscontrol.commands.CmdExHandler;
import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.utils.Text;
import io.github.itzispyder.explosionscontrol.utils.Timer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class UpdateWorldsCommand implements BukkitCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Timer timer = Timer.start();
            ExplosionConfig.updateAllWorlds();
            Timer.End end = timer.end();

            sender.sendMessage(Text.ofAll("&eUpdated all world configs, took &7" + end.getStampPrecise()));
        }
        catch (Exception ex) {
            CmdExHandler handler = new CmdExHandler(ex, command);
            sender.sendMessage(handler.getHelp());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return "updateworlds";
    }
}
