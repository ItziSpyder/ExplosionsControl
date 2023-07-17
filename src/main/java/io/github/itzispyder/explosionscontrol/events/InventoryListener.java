package io.github.itzispyder.explosionscontrol.events;

import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.data.ExplosionGui;
import io.github.itzispyder.explosionscontrol.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.itzispyder.explosionscontrol.ExplosionsControl.starter;

public class InventoryListener implements Listener {

    @EventHandler
    private void onInvClick(InventoryClickEvent e) {
        try {
            this.handleConfigGui(e);
        }
        catch (Exception ignore) {}
    }

    private void handleConfigGui(InventoryClickEvent e) {
        String title = e.getView().getTitle();
        ItemStack item = e.getCurrentItem();
        Player p = (Player)e.getWhoClicked();

        if (title.contains(starter) && item != null) {
            e.setCancelled(true);
            String name = title.split(" ")[title.split(" ").length - 1];
            World world = Bukkit.getWorld(name);

            if (world == null) {
                p.closeInventory();
                p.sendMessage(Text.ofAll("&cWorld &7\"" + name + "&7\" &cis not found!"));
                return;
            }

            ExplosionConfig config = ExplosionConfig.load(world);
            ExplosionGui gui = new ExplosionGui(config);

            switch (item.getType()) {
                case END_CRYSTAL -> {
                    config.setCrystalMode(config.getCrystalMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case TNT_MINECART -> {
                    config.setMinecartMode(config.getMinecartMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case TNT -> {
                    config.setTntMode(config.getTntMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case FIRE_CHARGE -> {
                    config.setFireballMode(config.getFireballMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case WITHER_SKELETON_SKULL -> {
                    config.setWitherMode(config.getWitherMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case CREEPER_HEAD -> {
                    config.setCreeperMode(config.getCreeperMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
                case RESPAWN_ANCHOR -> {
                    config.setBlockMode(config.getBlockMode().next());
                    config.save();
                    p.openInventory(gui.loadGui());
                }
            }
        }
    }
}
