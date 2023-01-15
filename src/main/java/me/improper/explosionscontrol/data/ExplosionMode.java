package me.improper.explosionscontrol.data;

import java.io.Serializable;

public enum ExplosionMode implements Serializable {

    ENABLED(0),
    DISABLED(1),
    DYNAMIC(2),
    NONE(3);

    private final int index;

    ExplosionMode(int index) {
        this.index = index;
    }
}
