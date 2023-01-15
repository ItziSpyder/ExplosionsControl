package me.improper.explosionscontrol.data;

import java.io.Serializable;

public enum ExplosionMode implements Serializable {

    ENABLED(0),
    DISABLED(1),
    DYNAMIC(2),
    NONE(3);

    /**
     * Attempts to get an explosion mode from the index value provided.
     *
     * @param index int
     * @return An explosion mode from the index value.
     */
    public static ExplosionMode fromIndex(int index) {
        for (ExplosionMode mode : ExplosionMode.values()) if (index == mode.getIndex()) return mode;
        return fromIndex(0);
    }

    private final int index;

    /**
     * Constructs an explosion mode from the index provided.
     *
     * @param index int
     */
    ExplosionMode(int index) {
        this.index = index;
    }

    /**
     * Returns the index of the current explosion mode value.
     * A number out of bounds will be read as 0!
     *
     * @return The index of the current value
     */
    public int getIndex() {
        if (index >= ExplosionMode.values().length || index < 0) return 0;
        return index;
    }
}
