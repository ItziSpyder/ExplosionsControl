package me.improper.explosionscontrol.events;

import me.improper.explosionscontrol.ExplosionsControl;
import me.improper.explosionscontrol.data.ExplosionConfigFile;
import me.improper.explosionscontrol.data.ExplosionConfiguration;
import me.improper.explosionscontrol.data.ExplosionMode;
import me.improper.explosionscontrol.data.ExplosionToggle;
import me.improper.explosionscontrol.other.Item;
import me.improper.explosionscontrol.other.ServerSound;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OnInventory implements Listener {

    @EventHandler
    public static void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        String title = e.getView().getTitle();

        try {
            Item item = new Item(e.getCurrentItem());
            ServerSound reload = new ServerSound(p.getLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE,10,10);
            ServerSound click = new ServerSound(p.getLocation(),Sound.UI_BUTTON_CLICK,10,1);
            ServerSound edit = new ServerSound(p.getLocation(),Sound.ITEM_DYE_USE,10,10);

            if (title.contains(ExplosionsControl.STARTER + "eConfigurations")) {
                if (inv.getType().equals(InventoryType.PLAYER)) return;
                e.setCancelled(true);
                if (item.getDisplayName().equals(" ")) return;
                if (item.getDisplayName().equals(ChatColor.AQUA + "Load Worlds")) {
                    p.closeInventory();
                    ExplosionConfigFile.setup();
                    ExplosionToggle.setup();
                    p.openInventory(ExplosionToggle.EXPLOSIONMENU);
                    reload.play(p);
                    return;
                }
                if (item.getType().equals(Material.DARK_OAK_SIGN)) return;
                if (item.getType().equals(Material.OAK_SIGN)) {
                    World world = Bukkit.getWorld(item.getDisplayName().replaceAll(ChatColor.GRAY + "",""));
                    ExplosionToggle.openToggleMenu(p,world);
                    click.play(p);
                }
            }
            if (title.contains(ExplosionsControl.STARTER + "eEditing ")) {
                if (inv.getType().equals(InventoryType.PLAYER)) return;
                e.setCancelled(true);
                if (item.getDisplayName().equals(" ")) return;
                if (item.getType().equals(Material.DARK_OAK_SIGN)) return;
                if (item.getDisplayName().equals("<< Back to world menu")) {
                    p.openInventory(ExplosionToggle.EXPLOSIONMENU);
                    click.play(p);
                    return;
                }
                World world = Bukkit.getWorld(title.substring((ExplosionsControl.STARTER + "eEditing ").length()));
                ExplosionConfiguration configuration = new ExplosionConfiguration(world);

                switch (item.getType()) {
                    case FIRE_CHARGE -> {
                        configuration.setAllowFireball(ExplosionMode.fromIndex(configuration.getAllowFireball().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case CREEPER_HEAD -> {
                        configuration.setAllowCreeper(ExplosionMode.fromIndex(configuration.getAllowCreeper().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case TNT -> {
                        configuration.setAllowTnt(ExplosionMode.fromIndex(configuration.getAllowTnt().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case TNT_MINECART -> {
                        configuration.setAllowMinecart(ExplosionMode.fromIndex(configuration.getAllowMinecart().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case WITHER_SKELETON_SKULL -> {
                        configuration.setAllowWither(ExplosionMode.fromIndex(configuration.getAllowWither().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case END_CRYSTAL -> {
                        configuration.setAllowCrystal(ExplosionMode.fromIndex(configuration.getAllowCrystal().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                    case RESPAWN_ANCHOR -> {
                        configuration.setAllowBlock(ExplosionMode.fromIndex(configuration.getAllowBlock().getIndex() + 1));
                        configuration.save();
                        ExplosionToggle.setup();
                        ExplosionToggle.openToggleMenu(p,world);
                        edit.play(p);
                    }
                }
            }
        } catch (Exception exception) {}
    }
}
