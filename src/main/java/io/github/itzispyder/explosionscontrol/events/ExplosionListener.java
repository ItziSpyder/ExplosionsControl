package io.github.itzispyder.explosionscontrol.events;

import io.github.itzispyder.explosionscontrol.data.ExplosionConfig;
import io.github.itzispyder.explosionscontrol.data.Mode;
import io.github.itzispyder.explosionscontrol.utils.SoundPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
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

import java.util.List;

public class ExplosionListener implements Listener {

    @EventHandler
    private void onExplodeEntity(EntityExplodeEvent e) {
        try {
            this.handleEntityExplosion(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    private void onExplodeBlock(BlockExplodeEvent e) {
        try {
            this.handleBlockExplosion(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        try {
            this.handleEntityDamage(e);
        }
        catch (Exception ignore) {}
    }

    @EventHandler
    private void onEntityDamage(EntityDamageEvent e) {
        try {
            this.handleEntityDamage(e);
        }
        catch (Exception ignore) {}
    }

    private void handleEntityDamage(EntityDamageEvent e) {
        Entity ent = e.getEntity();
        World world = ent.getWorld();
        ExplosionConfig config = ExplosionConfig.load(world);
        EntityDamageEvent.DamageCause cause = e.getCause();

        if (cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            e.setCancelled(config.getBlockMode() == Mode.NONE);
        }
        else if (e instanceof EntityDamageByEntityEvent de) {
            Entity damager = de.getDamager();

            switch (damager.getType()) {
                case CREEPER -> e.setCancelled(config.getCreeperMode() == Mode.NONE);
                case MINECART_TNT -> e.setCancelled(config.getMinecartMode() == Mode.NONE);
                case FIREBALL -> e.setCancelled(config.getFireballMode() == Mode.NONE);
                case WITHER_SKULL -> e.setCancelled(config.getWitherMode() == Mode.NONE);
                case PRIMED_TNT -> e.setCancelled(config.getTntMode() == Mode.NONE);
                case ENDER_CRYSTAL -> e.setCancelled(config.getCrystalMode() == Mode.NONE);
            }
        }
    }

    private void handleEntityExplosion(EntityExplodeEvent e) {
        Entity ent = e.getEntity();
        World world = ent.getWorld();
        ExplosionConfig config = ExplosionConfig.load(world);

        switch (ent.getType()) {
            case CREEPER -> determineOutcome(config.getCreeperMode(), e);
            case MINECART_TNT -> determineOutcome(config.getMinecartMode(), e);
            case FIREBALL -> determineOutcome(config.getFireballMode(), e);
            case WITHER_SKULL -> determineOutcome(config.getWitherMode(), e);
            case PRIMED_TNT -> determineOutcome(config.getTntMode(), e);
            case ENDER_CRYSTAL -> determineOutcome(config.getCrystalMode(), e);
        }
    }

    private void handleBlockExplosion(BlockExplodeEvent e) {
        Block block = e.getBlock();
        World world = block.getWorld();
        ExplosionConfig config = ExplosionConfig.load(world);

        determineOutcome(config.getBlockMode(), e);
    }

    private void determineOutcome(Mode mode, EntityExplodeEvent e) {
        e.setCancelled(mode.shouldCancel());
        switch (mode) {
            case DYNAMIC -> dynamicExplode(e.getLocation(), e.blockList());
            case DISABLED -> fakeExplode(e.getLocation());
        }
    }

    private void determineOutcome(Mode mode, BlockExplodeEvent e) {
        e.setCancelled(mode.shouldCancel());
        switch (mode) {
            case DYNAMIC -> dynamicExplode(e.getBlock().getLocation(), e.blockList());
            case DISABLED -> fakeExplode(e.getBlock().getLocation());
        }
    }

    private void dynamicExplode(Location center, List<Block> blocks) {
        for (Block block : blocks) {
            Location loc = block.getLocation();
            Vector dir = loc.toVector().subtract(center.toVector()).normalize().add(new Vector(0, 1, 0));
            FallingBlock fb = loc.getWorld().spawnFallingBlock(loc.add(0.5, 0.5, 0.5), block.getBlockData());
            fb.setVelocity(dir);
        }
    }

    private void fakeExplode(Location center) {
        SoundPlayer sound = new SoundPlayer(center, Sound.ENTITY_GENERIC_EXPLODE, 10.0F, 0.7F);
        sound.playWithin(100.0);
        center.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, center, 1, 0, 0, 0, 0);
    }
}
