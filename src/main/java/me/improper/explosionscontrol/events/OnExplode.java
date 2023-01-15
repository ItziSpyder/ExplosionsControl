package me.improper.explosionscontrol.events;

import me.improper.explosionscontrol.data.ExplosionConfiguration;
import me.improper.explosionscontrol.data.ExplosionMode;
import me.improper.explosionscontrol.other.ServerSound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

public class OnExplode implements Listener {

    @EventHandler
    public static void EntityExplodeEvent(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        Location loc = e.getLocation();
        World world = loc.getWorld();
        ExplosionConfiguration configuration = new ExplosionConfiguration(world);

        switch (entity.getType()) {
            case PRIMED_TNT -> {
                switch (configuration.getAllowTnt()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
            case FIREBALL,DRAGON_FIREBALL,SMALL_FIREBALL -> {
                switch (configuration.getAllowFireball()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
            case CREEPER -> {
                switch (configuration.getAllowCreeper()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
            case ENDER_CRYSTAL -> {
                switch (configuration.getAllowCrystal()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
            case MINECART_TNT -> {
                switch (configuration.getAllowMinecart()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
            case WITHER_SKULL -> {
                switch (configuration.getAllowWither()) {
                    case DISABLED -> {
                        e.setCancelled(true);
                        fakeExplode(loc);
                    }
                    case NONE -> {
                        e.setCancelled(true);
                    }
                    case DYNAMIC -> {
                        for (Block block : e.blockList()) launchBlock(block, loc);
                    }
                }
            }
        }
    }

    @EventHandler
    public static void BlockExplodeEvent(BlockExplodeEvent e) {
        Block block = e.getBlock();
        Location loc = block.getLocation();
        World world = loc.getWorld();
        ExplosionConfiguration configuration = new ExplosionConfiguration(world);

        switch (configuration.getAllowBlock()) {
            case DISABLED -> {
                e.setCancelled(true);
                fakeExplode(loc);
            }
            case NONE -> {
                e.setCancelled(true);
            }
            case DYNAMIC -> {
                for (Block b : e.blockList()) launchBlock(b, loc);
            }
        }
    }

    @EventHandler
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        try {
            Entity damager = e.getDamager();
            Location loc = e.getDamager().getLocation();
            World world = loc.getWorld();
            ExplosionConfiguration configuration = new ExplosionConfiguration(world);

            switch (damager.getType()) {
                case PRIMED_TNT -> {
                    if (configuration.getAllowTnt().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
                case FIREBALL,DRAGON_FIREBALL,SMALL_FIREBALL -> {
                    if (configuration.getAllowFireball().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
                case CREEPER -> {
                    if (configuration.getAllowCreeper().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
                case ENDER_CRYSTAL -> {
                    if (configuration.getAllowCrystal().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
                case MINECART_TNT -> {
                    if (configuration.getAllowMinecart().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
                case WITHER_SKULL -> {
                    if (configuration.getAllowWither().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
            }
        } catch (Exception exception) {}
    }

    @EventHandler
    public static void EntityDamageEvent(EntityDamageEvent e) {
        try {
            Entity entity = e.getEntity();
            Location loc = entity.getLocation();
            World world = loc.getWorld();
            ExplosionConfiguration configuration = new ExplosionConfiguration(world);
            EntityDamageEvent.DamageCause cause = e.getCause();

            switch (cause) {
                case BLOCK_EXPLOSION -> {
                    if (configuration.getAllowBlock().equals(ExplosionMode.NONE)) e.setCancelled(true);
                }
            }
        } catch (Exception exception) {}
    }

    /**
     * Attempts to launch a block as a falling block according to its original location
     * This feature is in beta, expect many bugs!
     *
     * @param block Block
     * @param origin Location
     */
    public static void launchBlock(Block block, Location origin) {
        Location loc = block.getLocation();
        Vector vector = origin
                .toVector()
                .subtract(loc.toVector())
                .normalize()
                .add(new Vector(0,1,0))
                .multiply(new Vector(-0.7,0.7,-0.7));
        FallingBlock fb = origin.getWorld().spawnFallingBlock(block.getLocation(),block.getBlockData());
        fb.setVelocity(vector);
        block.setType(Material.AIR);
    }

    /**
     * Plays a sound and displays the explosion particles at the provided world location.
     *
     * @param location Location
     */
    public static void fakeExplode(Location location) {
        ServerSound explosion = new ServerSound(location,Sound.ENTITY_GENERIC_EXPLODE,10,0.7F);
        explosion.playWithin(5000);
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,location,1,1,1,1,0);
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,location,1,1,1,1,0);
        location.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL,location,5,1,1,1,0);
    }
}
