package me.improper.explosionscontrol.other;

import me.improper.explosionscontrol.ExplosionsControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerSound {

    private Location location;
    private Sound sound;
    private float volume;
    private float pitch;

    public ServerSound(Location location, Sound sound, float volume, float pitch) {
        this.location = location;
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
    }


    public void play(Player player) {
        player.playSound(this.location,this.sound,this.volume,this.pitch);
    }

    public void playAt(Player player) {
        player.playSound(player.getLocation(),this.sound,this.volume,this.pitch);
    }

    public void playWithin(double distance) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != null && p.getWorld() == this.location.getWorld() && p.getLocation().distanceSquared(this.location) < distance) {
                p.playSound(this.location,this.sound,this.volume,this.pitch);
            }
        }
    }

    public void playWithinAt(double distance) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != null && p.getWorld() == this.location.getWorld() && p.getLocation().distanceSquared(this.location) < distance) {
                p.playSound(p.getLocation(),this.sound,this.volume,this.pitch);
            }
        }
    }

    public void playAll() {
        for (Player p : Bukkit.getOnlinePlayers()) p.playSound(this.location,this.sound,this.volume,this.pitch);
    }

    public void playAllAt() {
        for (Player p : Bukkit.getOnlinePlayers()) p.playSound(p.getLocation(),this.sound,this.volume,this.pitch);
    }

    public void repeat(Player player, int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    play(player);
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public void repeatAt(Player player, int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    playAt(player);
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public void repeatAll(int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    playAll();
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public void repeatAllAt(int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    playAllAt();
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public void repeatAll(double radius,int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    playWithin(radius);
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public void repeatAllAt(double distance, int times, int tickDelay) {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < times) {
                    playWithinAt(distance);
                    i ++;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(ExplosionsControl.getInstance(),0,tickDelay);
    }

    public Sound getSound() {
        return sound;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    public Location getLocation() {
        return location;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
