package io.github.itzispyder.explosionscontrol.data;

import io.github.itzispyder.explosionscontrol.utils.ItemPresets;
import io.github.itzispyder.explosionscontrol.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static io.github.itzispyder.explosionscontrol.ExplosionsControl.starter;

public class ExplosionGui {

    public final ExplosionConfig config;

    public ExplosionGui(ExplosionConfig config) {
        this.config = config;
    }

    public Inventory loadGui() {
        String title = Text.color(starter + config.getWorld().getName());
        Inventory inv = Bukkit.createInventory(null, 54, title);
        ItemStack x = ItemPresets.GUI_BLANK;
        ItemStack o = ItemPresets.GUI_BORDER;
        ItemStack f = ItemPresets.GUI_FLOOR;

        ItemStack minecartMode = ItemBuilder.create()
                .material(Material.TNT_MINECART)
                .name(config.getMinecartMode().getDisplay())
                .build();
        ItemStack tntMode = ItemBuilder.create()
                .material(Material.TNT)
                .name(config.getTntMode().getDisplay())
                .build();
        ItemStack crystalMode = ItemBuilder.create()
                .material(Material.END_CRYSTAL)
                .name(config.getCrystalMode().getDisplay())
                .build();
        ItemStack creeperMode = ItemBuilder.create()
                .material(Material.CREEPER_HEAD)
                .name(config.getCreeperMode().getDisplay())
                .build();
        ItemStack witherMode = ItemBuilder.create()
                .material(Material.WITHER_SKELETON_SKULL)
                .name(config.getWitherMode().getDisplay())
                .build();
        ItemStack fireballMode = ItemBuilder.create()
                .material(Material.FIRE_CHARGE)
                .name(config.getFireballMode().getDisplay())
                .build();
        ItemStack blockMode = ItemBuilder.create()
                .material(Material.RESPAWN_ANCHOR)
                .name(config.getBlockMode().getDisplay())
                .build();

        inv.setContents(new ItemStack[]{
                o,o,o,o,o,o,o,o,o,
                o,x,x,x,x,x,x,x,o,
                o,minecartMode,x,tntMode,x,witherMode,x,fireballMode,o,
                o,x,creeperMode,x,crystalMode,x,blockMode,x,o,
                o,x,x,x,x,x,x,x,o,
                f,f,f,f,f,f,f,f,f
        });

        return inv;
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();

    }
}
