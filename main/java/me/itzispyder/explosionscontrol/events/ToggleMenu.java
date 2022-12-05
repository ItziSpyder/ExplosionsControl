package me.itzispyder.explosionscontrol.events;

import me.itzispyder.explosionscontrol.ExplosionsControl;
import me.itzispyder.explosionscontrol.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ToggleMenu implements Listener {


    // instance of the main class
    static ExplosionsControl plugin;
    public ToggleMenu(ExplosionsControl plugin) {
        this.plugin = plugin;
    }

    // Events
    @EventHandler
    public static void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        Inventory inv = e.getClickedInventory();

        if (title.contains(Messages.starter) && !inv.getType().equals(InventoryType.PLAYER)) {
            e.setCancelled(true);

            try {
                ItemStack item = e.getCurrentItem();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = meta.getLore();
                String display = meta.getDisplayName();

                if (!display.equalsIgnoreCase(" ")) {
                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1,10);

                    if (display.equalsIgnoreCase("§c§l§oClose")) {
                        p.closeInventory();
                    } else if (display.equalsIgnoreCase("§b§l§oReload")) {
                        List<String> worlds = plugin.getConfig().getStringList("server.worlds");
                        for (World world : Bukkit.getServer().getWorlds()) {
                            if (!plugin.getConfig().getStringList("server.worlds").contains(world.getName())) {
                                worlds.add(world.getName());
                            }
                        }

                        plugin.getConfig().set("server.worlds",worlds);
                        plugin.saveConfig();

                        p.closeInventory();
                        openExplosionsMenu(p);
                    } else if (display.equalsIgnoreCase("§7§l§oBack")) {
                        openExplosionsMenu(p);
                    } else if (item.getType().equals(Material.OAK_SIGN)){
                        if (p.isOp()) {
                            configWorld(item,p);
                        } else {
                            p.playSound(p.getLocation(),Sound.ENTITY_SHULKER_TELEPORT,1,10);
                            Messages.send(p, Messages.noPerms);
                        }
                    }
                }

                if (title.contains("§6Editing...")) {
                    if (!display.equalsIgnoreCase(" ")) {
                        String substring = display.substring(0, display.length() - 3);
                        String worldname = inv.getItem(0).getItemMeta().getDisplayName().substring(2);
                        switch (display.substring(display.length() - 3)) {
                            case "§a✔":
                                meta.setDisplayName(substring + "§c✕");
                                item.setItemMeta(meta);
                                plugin.getConfig().set("server.explosions." + worldname + "." + item.getType().name().toLowerCase(),"off");
                                plugin.saveConfig();
                                break;
                            case "§c✕":
                                meta.setDisplayName(substring + "§4▼");
                                item.setItemMeta(meta);
                                plugin.getConfig().set("server.explosions." + worldname + "." + item.getType().name().toLowerCase(),"none");
                                plugin.saveConfig();
                                break;
                            case "§4▼":
                                meta.setDisplayName(substring + "§6☀");
                                item.setItemMeta(meta);
                                plugin.getConfig().set("server.explosions." + worldname + "." + item.getType().name().toLowerCase(),"dynamic");
                                plugin.saveConfig();
                                break;
                            case "§6☀":
                                meta.setDisplayName(substring + "§a✔");
                                item.setItemMeta(meta);
                                plugin.getConfig().set("server.explosions." + worldname + "." + item.getType().name().toLowerCase(),"on");
                                plugin.saveConfig();
                                break;
                        }
                    }
                }

                // end of menus list
            } catch (NullPointerException exception) {
                // empty
            }
        }

        // end of click events
    }

    // Methods
    public static void configWorld(ItemStack item, Player player) {
        Inventory menu = Bukkit.createInventory(player,36, Messages.starter + "6Editing...");
        String worldname = item.getItemMeta().getDisplayName().substring(2);

        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("§a✔ §7= §oDefault/Enabled");
        lore.add("§c✕ §7= §oDisabled");
        lore.add("§4▼ §7= §oNone");
        lore.add("§6☀ §7= §oDynamic §cNOT RECOMMENDED ON LOW END SERVERS");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        ItemStack tnt = new ItemStack(Material.TNT);
        ItemMeta tntM = tnt.getItemMeta();
        tntM.setDisplayName("§6TNT: " + getExplosionMode(worldname,"tnt"));
        tnt.setItemMeta(tntM);

        ItemStack minecart = new ItemStack(Material.TNT_MINECART);
        ItemMeta minecartM = minecart.getItemMeta();
        minecartM.setDisplayName("§6TNT Mincart: " + getExplosionMode(worldname,"tnt_minecart"));
        minecart.setItemMeta(minecartM);

        ItemStack crystal = new ItemStack(Material.END_CRYSTAL);
        ItemMeta crystalM = crystal.getItemMeta();
        crystalM.setDisplayName("§6End Crystal: " + getExplosionMode(worldname,"end_crystal"));
        crystal.setItemMeta(crystalM);

        ItemStack anchor = new ItemStack(Material.RESPAWN_ANCHOR);
        ItemMeta anchorM = anchor.getItemMeta();
        anchorM.setDisplayName("§6All Block Explosions: " + getExplosionMode(worldname,"respawn_anchor"));
        anchor.setItemMeta(anchorM);

        ItemStack creeper = new ItemStack(Material.CREEPER_HEAD);
        ItemMeta creeperM = creeper.getItemMeta();
        creeperM.setDisplayName("§6Creepers: " + getExplosionMode(worldname,"creeper_head"));
        creeper.setItemMeta(creeperM);

        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta fireballM = fireball.getItemMeta();
        fireballM.setDisplayName("§6Fireballs: " + getExplosionMode(worldname,"fire_charge"));
        fireball.setItemMeta(fireballM);

        ItemStack wither = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemMeta witherM = wither.getItemMeta();
        witherM.setDisplayName("§6Wither Skulls: " + getExplosionMode(worldname,"wither_skeleton_skull"));
        wither.setItemMeta(witherM);

        ItemStack[] contents = {
                item,y,y,y,y,y,y,back,close,
                z,z,z,z,z,z,z,z,z,
                tnt,minecart,crystal,anchor,creeper,fireball,wither,x,x,
                x,x,x,x,x,x,x,x,x
        };

        menu.setContents(contents);
        player.openInventory(menu);
    }

    public static void openExplosionsMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player,54, Messages.starter + "6v1.0");
        List<String> worlds = plugin.getConfig().getStringList("server.worlds");

        ItemStack[] top = {
                y,y,y,y,y,y,y,reload,close,
                z,z,z,z,z,z,z,z,z,
        };
        menu.setContents(top);

        for (String world : worlds) {
            ItemStack item = new ItemStack(Material.OAK_SIGN);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName("§6" + world);
            List<String> lore = new ArrayList<>();
            lore.add("§8§o(Click to config)");
            meta.setLore(lore);

            item.setItemMeta(meta);
            menu.setItem(menu.firstEmpty(), item);
        }

        fillEmpty(menu);
        player.openInventory(menu);

    }

    public static void fillEmpty(Inventory inventory) {
        while (inventory.firstEmpty() != -1) {
            inventory.setItem(inventory.firstEmpty(),x);
        }
    }

    public static String getExplosionMode(String world, String explosionSource) {
        String source = plugin.getConfig().getString("server.explosions." + world + "." + explosionSource);

        if (source != null) {
            switch (source) {
                case "on":
                    return "§a✔";
                case "off":
                    return "§c✕";
                case "none":
                    return "§4▼";
                case "dynamic":
                    return "§6☀";
            }
        } else {
            plugin.getConfig().set("server.explosions." + world + "." + explosionSource,"on");
            plugin.saveConfig();
            return "§a✔";
        }

        return "§a✔";
    }

    // Items
    public static ItemStack x;
    public static ItemStack y;
    public static ItemStack z;
    public static ItemStack close;
    public static ItemStack back;
    public static ItemStack reload;

    public static void setX() {
        ItemStack item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        x = item;
    }

    public static void setY() {
        ItemStack item = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        y = item;
    }

    public static void setZ() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(" ");

        item.setItemMeta(meta);
        z = item;
    }

    public static void setClose() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§c§l§oClose");

        item.setItemMeta(meta);
        close = item;
    }

    public static void setBack() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l§oBack");

        item.setItemMeta(meta);
        back = item;
    }

    public static void setReload() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§b§l§oReload");

        item.setItemMeta(meta);
        reload = item;
    }
}
