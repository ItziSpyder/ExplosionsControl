package io.github.itzispyder.explosionscontrol.data;

import com.google.gson.Gson;
import io.github.itzispyder.explosionscontrol.utils.FileValidationUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.*;

import static io.github.itzispyder.explosionscontrol.ExplosionsControl.instance;
import static io.github.itzispyder.explosionscontrol.ExplosionsControl.logger;

public class ExplosionConfig {

    private final String world;
    private Mode minecartMode;
    private Mode witherMode;
    private Mode tntMode;
    private Mode fireballMode;
    private Mode creeperMode;
    private Mode blockMode;
    private Mode crystalMode;

    public ExplosionConfig(World world) {
        this.world = world.getName();
        this.minecartMode = Mode.ENABLED;
        this.witherMode = Mode.ENABLED;
        this.tntMode = Mode.ENABLED;
        this.fireballMode = Mode.ENABLED;
        this.creeperMode = Mode.ENABLED;
        this.crystalMode = Mode.ENABLED;
        this.blockMode = Mode.ENABLED;
    }

    public World getWorld() {
        return Bukkit.getWorld(world);
    }

    public Mode getMinecartMode() {
        return minecartMode;
    }

    public void setMinecartMode(Mode minecartMode) {
        this.minecartMode = minecartMode;
    }

    public Mode getWitherMode() {
        return witherMode;
    }

    public void setWitherMode(Mode witherMode) {
        this.witherMode = witherMode;
    }

    public Mode getTntMode() {
        return tntMode;
    }

    public void setTntMode(Mode tntMode) {
        this.tntMode = tntMode;
    }

    public Mode getFireballMode() {
        return fireballMode;
    }

    public void setFireballMode(Mode fireballMode) {
        this.fireballMode = fireballMode;
    }

    public Mode getCreeperMode() {
        return creeperMode;
    }

    public void setCreeperMode(Mode creeperMode) {
        this.creeperMode = creeperMode;
    }

    public Mode getBlockMode() {
        return blockMode;
    }

    public void setBlockMode(Mode blockMode) {
        this.blockMode = blockMode;
    }

    public Mode getCrystalMode() {
        return crystalMode;
    }

    public void setCrystalMode(Mode crystalMode) {
        this.crystalMode = crystalMode;
    }

    public File getFile() {
        return getFileOf(getWorld());
    }

    public void save() {
        save(this);
    }

    public static File getFileOf(World world) {
        return new File(instance.getDataFolder(), "worlds/" + world.getName() + ".json");
    }

    public static void updateAllWorlds() {
        for (World world : Bukkit.getWorlds()) {
            load(world).save();
        }
    }

    private static <T> T getOrDef(T val, T def) {
        return val != null ? val : def;
    }

    public static void save(ExplosionConfig config) {
        File file = config.getFile();
        if (FileValidationUtils.validate(file)) {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(config);
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(json);
                bw.close();
            }
            catch (Exception ex) {
                logger.warning("An error occurred while saving config for \"" + config.world + "\"");
                ex.printStackTrace();
            }
        }
    }

    public static ExplosionConfig load(World world) {
        world = world != null ? world : Bukkit.getWorlds().get(0);
        File file = getFileOf(world);
        ExplosionConfig defaultConfig = new ExplosionConfig(world);

        if (FileValidationUtils.validate(file)) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                Gson gson = new Gson();
                String json = br.readLine();

                ExplosionConfig config = json == null ? defaultConfig : gson.fromJson(json, ExplosionConfig.class);

                br.close();
                config.save();
                return config;
            }
            catch (Exception ex) {
                logger.warning("An error occurred while loading config for \"" + file.getPath() + "\"");
                ex.printStackTrace();
            }
        }

        return defaultConfig;
    }
}
