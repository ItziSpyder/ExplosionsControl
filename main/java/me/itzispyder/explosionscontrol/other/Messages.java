package me.itzispyder.explosionscontrol.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Messages {
    // §
    static HashMap<String,Long> messageCooldown = new HashMap<>();

    public static String starter = "§8[§eExplosions§6Control§8] §";
    public static String noPerms = starter + "4Sorry, but I'm afraid you do not have access to that!";
    public static String cannotUse = starter + "4Sorry, but I'm afraid you cannot do this here!";


    public static void send(Player player, String message) {
        if (messageCooldown.containsKey(player.getName()) && messageCooldown.get(player.getName()) > System.currentTimeMillis()) {
            // empty
        } else {
            messageCooldown.put(player.getName(),System.currentTimeMillis() + (1000));
            player.sendMessage(message);
        }
    }

    public static void bm(String message) {
        if (messageCooldown.containsKey("everyone") && messageCooldown.get("everyone") > System.currentTimeMillis()) {
            // empty
        } else {
            messageCooldown.put("everyone",System.currentTimeMillis() + (1000));
            Bukkit.getServer().broadcastMessage(message);
        }
    }
}
