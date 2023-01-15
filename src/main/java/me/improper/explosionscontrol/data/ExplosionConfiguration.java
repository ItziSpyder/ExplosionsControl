package me.improper.explosionscontrol.data;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.Serializable;

public class ExplosionConfiguration implements Serializable {

    private String world;
    private ExplosionMode allowTnt, allowCrystal, allowFireball, allowCreeper, allowMinecart, allowWither, allowBlock;

    /**
     * Constructs a world configuration for explosions.
     * Note that if the data file is null or does not exist it will create
     * a new instance of this class instead of loading the data.
     *
     * @param world World
     */
    public ExplosionConfiguration(World world) {
        try {
            File file = new File("plugins/ExplosionsControl/worldconfigurations/" + world.getName() + ".yml");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
                this.world = world.getName();
                this.allowTnt = ExplosionMode.ENABLED;
                this.allowCrystal = ExplosionMode.ENABLED;
                this.allowFireball = ExplosionMode.ENABLED;
                this.allowCreeper = ExplosionMode.ENABLED;
                this.allowMinecart = ExplosionMode.ENABLED;
                this.allowWither = ExplosionMode.ENABLED;
                this.allowBlock = ExplosionMode.ENABLED;
                return;
            }
            FileConfiguration data = YamlConfiguration.loadConfiguration(file);
            this.world = data.getString("worldconfig.world");
            this.allowTnt = ExplosionMode.valueOf(data.getString("worldconfig.allowTnt").toUpperCase());
            this.allowCrystal = ExplosionMode.valueOf(data.getString("worldconfig.allowCrystal").toUpperCase());
            this.allowFireball = ExplosionMode.valueOf(data.getString("worldconfig.allowFireball").toUpperCase());
            this.allowCreeper = ExplosionMode.valueOf(data.getString("worldconfig.allowCreeper").toUpperCase());
            this.allowMinecart = ExplosionMode.valueOf(data.getString("worldconfig.allowMinecart").toUpperCase());
            this.allowWither = ExplosionMode.valueOf(data.getString("worldconfig.allowWither").toUpperCase());
            this.allowBlock = ExplosionMode.valueOf(data.getString("worldconfig.allowBlock").toUpperCase());
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
    }

    /**
     * Save the current configuration to a file config.
     */
    public void save() {
        try {
            File file = new File("plugins/ExplosionsControl/worldconfigurations/" + world + ".yml");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            if (!file.exists()) file.createNewFile();
            FileConfiguration data = YamlConfiguration.loadConfiguration(file);
            data.set("worldconfig.world",world);
            data.set("worldconfig.allowBlock",allowBlock.name());
            data.set("worldconfig.allowCreeper",allowCreeper.name());
            data.set("worldconfig.allowCrystal",allowCrystal.name());
            data.set("worldconfig.allowFireball",allowFireball.name());
            data.set("worldconfig.allowMinecart",allowMinecart.name());
            data.set("worldconfig.allowTnt",allowTnt.name());
            data.set("worldconfig.allowWither",allowWither.name());
            data.save(file);
        } catch (Exception exception) {
            Bukkit.getLogger().warning(exception.toString());
        }
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setAllowBlock(ExplosionMode allowBlock) {
        this.allowBlock = allowBlock;
    }

    public void setAllowCreeper(ExplosionMode allowCreeper) {
        this.allowCreeper = allowCreeper;
    }

    public void setAllowCrystal(ExplosionMode allowCrystal) {
        this.allowCrystal = allowCrystal;
    }

    public void setAllowFireball(ExplosionMode allowFireball) {
        this.allowFireball = allowFireball;
    }

    public void setAllowMinecart(ExplosionMode allowMinecart) {
        this.allowMinecart = allowMinecart;
    }

    public void setAllowTnt(ExplosionMode allowTnt) {
        this.allowTnt = allowTnt;
    }

    public void setAllowWither(ExplosionMode allowWither) {
        this.allowWither = allowWither;
    }

    public String getWorld() {
        return world;
    }

    public ExplosionMode getAllowBlock() {
        return allowBlock;
    }

    public ExplosionMode getAllowCreeper() {
        return allowCreeper;
    }

    public ExplosionMode getAllowCrystal() {
        return allowCrystal;
    }

    public ExplosionMode getAllowFireball() {
        return allowFireball;
    }

    public ExplosionMode getAllowMinecart() {
        return allowMinecart;
    }

    public ExplosionMode getAllowTnt() {
        return allowTnt;
    }

    public ExplosionMode getAllowWither() {
        return allowWither;
    }
}
