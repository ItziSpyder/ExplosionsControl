package me.improper.explosionscontrol.data;

import me.improper.explosionscontrol.ExplosionsControl;
import me.improper.explosionscontrol.other.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ExplosionToggle {

    public static Inventory EXPLOSIONMENU;

    /**
     * This will prepare the ExplosionsControl main toggle menu.
     * Server staff members will be able to edit explosion configurations
     * through this menu!
     */
    public static void setup() {
        Inventory menu = Bukkit.createInventory(null,54, ExplosionsControl.STARTER + "eConfigurations");

        Item reload = new Item(new ItemStack(Material.COMPASS));
        reload.setDisplayName(ChatColor.AQUA + "Load Worlds");
        Item blank = new Item(new ItemStack(Material.DARK_OAK_SIGN));
        blank.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Blank World");
        Item b = new Item(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        b.setDisplayName(" ");
        Item o = new Item(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE));
        o.setDisplayName(" ");
        Item y = new Item(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        y.setDisplayName(" ");
        Item a = new Item(new ItemStack(Material.AIR));

        menu.setContents(new ItemStack[]{
                y,y,y,y,y,y,y,y,reload,
                b,b,b,b,b,b,b,b,b,
                o,a,a,a,a,a,a,a,o,
                o,a,a,a,a,a,a,a,o,
                o,a,a,a,a,a,a,a,o,
                o,o,o,o,o,o,o,o,o
        });

        for (World world : Bukkit.getWorlds()) {
            Item worldIcon = new Item(new ItemStack(Material.OAK_SIGN));
            worldIcon.setDisplayName(ChatColor.GRAY + world.getName());
            worldIcon.setLore(List.of(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "(Click to config)"));
            menu.setItem(menu.firstEmpty(),worldIcon);
        }

        while (menu.firstEmpty() != -1) menu.setItem(menu.firstEmpty(),blank);

        EXPLOSIONMENU = menu;
    }

    /**
     * Open the toggle configuration menu for a player!
     *
     * @param player Player
     * @param world World
     */
    public static void openToggleMenu(Player player, World world) {
        Inventory menu = Bukkit.createInventory(null,54, ExplosionsControl.STARTER + "eEditing " + world.getName());
        ExplosionConfiguration configuration = new ExplosionConfiguration(world);

        Item b = new Item(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        b.setDisplayName(" ");
        Item o = new Item(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE));
        o.setDisplayName(" ");
        Item y = new Item(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        y.setDisplayName(" ");
        Item blank = new Item(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE));
        blank.setDisplayName(" ");
        Item a = new Item(new ItemStack(Material.AIR));
        Item back = new Item(new ItemStack(Material.ARROW));
        back.setDisplayName("<< Back to world menu");
        // toggles
        Item creeper = new Item(new ItemStack(Material.CREEPER_HEAD));
        creeper.setDisplayName(configuration.getAllowCreeper().name());
        Item crystal = new Item(new ItemStack(Material.END_CRYSTAL));
        crystal.setDisplayName(configuration.getAllowCrystal().name());
        Item tnt = new Item(new ItemStack(Material.TNT));
        tnt.setDisplayName(configuration.getAllowTnt().name());
        Item minecart = new Item(new ItemStack(Material.TNT_MINECART));
        minecart.setDisplayName(configuration.getAllowMinecart().name());
        Item anchor = new Item(new ItemStack(Material.RESPAWN_ANCHOR));
        anchor.setDisplayName(configuration.getAllowBlock().name());
        Item fireball = new Item(new ItemStack(Material.FIRE_CHARGE));
        fireball.setDisplayName(configuration.getAllowFireball().name());
        Item wither = new Item(new ItemStack(Material.WITHER_SKELETON_SKULL));
        wither.setDisplayName(configuration.getAllowWither().name());

        menu.setContents(new ItemStack[]{
                back,y,y,y,y,y,y,y,y,
                b,b,b,b,b,b,b,b,b,
                o,a,a,a,a,a,a,a,o,
                o,creeper,crystal,tnt,minecart,anchor,fireball,wither,o,
                o,a,a,a,a,a,a,a,o,
                o,o,o,o,o,o,o,o,o
        });

        while (menu.firstEmpty() != -1) menu.setItem(menu.firstEmpty(),blank);

        player.openInventory(menu);
    }
}
