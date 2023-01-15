package me.improper.explosionscontrol.data;

import me.improper.explosionscontrol.other.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExplosionToggle {

    public static Inventory EXPLOSIONMENU;

    /**
     * This will prepare the ExplosionsControl main toggle menu.
     * Server staff members will be able to edit explosion configurations
     * through this menu!
     */
    public static void setup() {
        Inventory menu = Bukkit.createInventory(null,54,"Explosions");

        Item reload = new Item(new ItemStack(Material.COMPASS));
        reload.setDisplayName(ChatColor.AQUA + "Load Worlds");



        menu.setContents(new ItemStack[]{

        });

        menu.addItem(reload);

        EXPLOSIONMENU = menu;
    }
}
