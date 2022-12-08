package me.itzispyder.explosionscontrol.events;

import com.google.common.io.BaseEncoding;
import me.itzispyder.explosionscontrol.ExplosionsControl;
import me.itzispyder.explosionscontrol.other.Messages;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.util.Vector;

import java.util.List;

public class Explosions implements Listener {

    // instance of the main class
    static ExplosionsControl plugin;
    public Explosions(ExplosionsControl plugin) {
        this.plugin = plugin;
    }

    // Events
    @EventHandler
    public static void OnEntityExplode(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();

        try {
            switch (entity.getType()) {
                case PRIMED_TNT:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".tnt")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
                case MINECART_TNT:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".tnt_minecart")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
                case ENDER_CRYSTAL:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".end_crystal")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
                case CREEPER:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".creeper_head")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
                case FIREBALL:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".fire_charge")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
                case WITHER_SKULL:
                    switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".wither_skeleton_skull")) {
                        case "off":
                            e.setCancelled(true);
                            fakeExplode(location);
                            break;
                        case "none":
                            e.setCancelled(true);
                            break;
                        case "dynamic":
                            List<Block> blocks = e.blockList();
                            for (Block block : blocks) {
                                FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(),block.getType(),block.getData());
                                fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                            }
                            break;
                    }
                    break;
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            Bukkit.getServer().getLogger().info("An explosion occurred in " + location.getWorld() + " @:" + location.getX() + "," + location.getY() + "," + location.getZ() + "! Config this in the menu to disable this message! /explosions");
        }
    }

    @EventHandler
    public static void OnBlockExplode(BlockExplodeEvent e) {
        Block block = e.getBlock();
        Location location = block.getLocation();
        World world = location.getWorld();

        try {
            switch (plugin.getConfig().getString("server.explosions." + world.getName() + ".respawn_anchor")) {
                case "off":
                    e.setCancelled(true);
                    fakeExplode(location);
                    break;
                case "none":
                    e.setCancelled(true);
                    break;
                case "dynamic":
                    List<Block> blocks = e.blockList();
                    for (Block exBlock : blocks) {
                        FallingBlock fb = exBlock.getWorld().spawnFallingBlock(exBlock.getLocation(),exBlock.getType(),exBlock.getData());
                        fb.setVelocity(new Vector(Math.random(),Math.random(),Math.random()));
                    }
                    break;
            }
        } catch (NullPointerException exception) {
            Bukkit.getServer().getLogger().info("An explosion occurred in " + location.getWorld() + " @:" + location.getX() + "," + location.getY() + "," + location.getZ() + "! Config this in the menu to disable this message! /explosions");
        }
    }

    @EventHandler
    public static void EntityDamageEvent(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        Location location = entity.getLocation();
        World world = location.getWorld();

        if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            if (plugin.getConfig().getString("server.explosions." + world.getName() + ".respawn_anchor").equalsIgnoreCase("none")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        Location location = entity.getLocation();
        World world = location.getWorld();

        switch (damager.getType()) {
            case PRIMED_TNT:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".tnt").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
            case MINECART_TNT:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".tnt_minecart").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
            case ENDER_CRYSTAL:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".end_crystal").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
            case CREEPER:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".creeper_head").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
            case FIREBALL:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".fire_charge").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
            case WITHER_SKULL:
                if (plugin.getConfig().getString("server.explosions." + world.getName() + ".wither_skeleton_skull").equalsIgnoreCase("none")) {
                    e.setCancelled(true);
                }
                break;
        }
    }

    // Methods
    public static void fakeExplode(Location location) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null && player.getWorld() == location.getWorld() && player.getLocation().distanceSquared(location) < 1000) {
                player.playSound(location, Sound.ENTITY_GENERIC_EXPLODE,10,0.8F);
            }
        }
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,location,1,1,1,1,0);
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,location,1,1,1,1,0);
        location.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL,location,5,1,1,1,0);
    }

}
