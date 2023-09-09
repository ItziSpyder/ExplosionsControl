package io.github.itzispyder.explosionscontrol.data;

import java.io.File;

public class PluginConfig implements JsonSerializable<PluginConfig> {

    public static final String PATH = "plugins/ExplosionsControl/config.json";
    public static final int MAX_Y = 319;
    public static final int MIN_Y = -63;
    private int minYLevel, maxYLevel;

    public PluginConfig() {
        this.maxYLevel = 319;
        this.minYLevel = -63;
    }

    @Override
    public File getFile() {
        return new File(PATH);
    }

    public int getMinYLevel() {
        return getOrDef(minYLevel, MIN_Y);
    }

    public void setMinYLevel(int minYLevel) {
        this.minYLevel = minYLevel;
        fixExplosionLevels();
    }

    public int getMaxYLevel() {
        return getOrDef(maxYLevel, MAX_Y);
    }

    public void setMaxYLevel(int maxYLevel) {
        this.maxYLevel = maxYLevel;
        fixExplosionLevels();
    }

    public boolean isOOB(int yLevel) {
        return yLevel > maxYLevel || yLevel < minYLevel;
    }

    public void fixExplosionLevels() {
        if (minYLevel >= maxYLevel) {
            minYLevel = maxYLevel - 1;
        }
        if (maxYLevel < minYLevel) {
            maxYLevel = minYLevel + 1;
        }

        maxYLevel = Math.min(MAX_Y, maxYLevel);
        minYLevel = Math.max(MIN_Y, minYLevel);
    }
}
