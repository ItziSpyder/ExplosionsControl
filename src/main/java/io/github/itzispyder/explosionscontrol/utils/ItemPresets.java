package io.github.itzispyder.explosionscontrol.utils;

import io.github.itzispyder.explosionscontrol.data.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemPresets {

    public static final ItemStack GUI_BLANK = ItemBuilder.create()
            .material(Material.GRAY_STAINED_GLASS_PANE)
            .name(" ")
            .build();

    public static final ItemStack GUI_BORDER = ItemBuilder.create()
            .material(Material.YELLOW_STAINED_GLASS_PANE)
            .name(" ")
            .build();

    public static final ItemStack GUI_FILL = ItemBuilder.create()
            .material(Material.BLACK_STAINED_GLASS_PANE)
            .name(" ")
            .build();

    public static final ItemStack GUI_FLOOR = ItemBuilder.create()
            .material(Material.ORANGE_STAINED_GLASS_PANE)
            .name(" ")
            .build();

    public static final ItemStack GUI_RESET = ItemBuilder.create()
            .material(Material.COMPASS)
            .name(Text.color("&bReset"))
            .lore(Text.color("&7- Click to reset"))
            .build();

    public static final ItemStack AIR = ItemBuilder.create()
            .material(Material.AIR)
            .build();

}
