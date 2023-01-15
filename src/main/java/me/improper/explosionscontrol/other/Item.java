package me.improper.explosionscontrol.other;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

public class Item extends ItemStack {

    private ItemMeta meta = super.getItemMeta();

    /**
     * This creates in instance of this class, which is an extension
     * or the ItemStack class, aiming to simplify the process of
     * modifying an item's item meta.
     *
     * @param itemStack ItemStack
     */
    public Item(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Set the display name of an item.
     *
     * @param displayName String
     */
    public void setDisplayName(String displayName) {
        meta.setDisplayName(displayName);
        super.setItemMeta(meta);
    }

    /**
     * Set the lore of an item.
     *
     * @param lore List<String></String>
     */
    public void setLore(List<String> lore) {
        meta.setLore(lore);
        super.setItemMeta(meta);
    }

    /**
     * Adds item flags to an item.
     *
     * @param flag ItemFlag...
     */
    public void addItemFlags(ItemFlag... flag) {
        meta.addItemFlags(flag);
        super.setItemMeta(meta);
    }

    /**
     * Sets the unbreakable state of an item.
     *
     * @param unbreakable boolean
     */
    public void setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        super.setItemMeta(meta);
    }

    /**
     * Sets the custom model data of an item.
     *
     * @param customModelData String
     */
    public void setCustomModelData(int customModelData) {
        meta.setCustomModelData(customModelData);
        super.setItemMeta(meta);
    }

    /**
     * Returns the display name of the item.
     *
     * @return A string as the display name.
     */
    public String getDisplayName() {
        return meta.getDisplayName();
    }

    /**
     * Returns the lore of the item.
     *
     * @return A string array as the lore.
     */
    public List<String> getLore() {
        return meta.getLore();
    }

    /**
     * Returns an array of the items flags of the item.
     *
     * @return An array of the item flags.
     */
    public Set<ItemFlag> getItemFlags() {
        return meta.getItemFlags();
    }

    /**
     * Returns the custom model data of the item.
     *
     * @return The custom model data of the item.
     */
    public int getCustomModelData() {
        return meta.getCustomModelData();
    }
}
