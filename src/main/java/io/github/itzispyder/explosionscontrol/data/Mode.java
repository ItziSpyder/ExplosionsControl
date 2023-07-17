package io.github.itzispyder.explosionscontrol.data;

import io.github.itzispyder.explosionscontrol.utils.StringUtils;
import io.github.itzispyder.explosionscontrol.utils.Text;

import java.util.Arrays;

public enum Mode {

    ENABLED("✔", 0),
    DISABLED("✘", 1),
    NONE("◯", 2),
    DYNAMIC("☀", 3);

    private final String name;
    private final int id;

    Mode(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public boolean shouldCancel() {
        return this == NONE || this == DISABLED;
    }

    public boolean isDynamic() {
        return this == DYNAMIC;
    }

    public boolean isNormal() {
        return this == ENABLED;
    }

    public Mode next() {
        int i = id + 1;
        i = i >= values().length ? 0 : i;
        return fromId(i);
    }

    public static Mode fromId(int id) {
        return Arrays.stream(values()).filter(m -> m.id == id).toList().get(0);
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        String pre;
        switch (this) {
            case NONE -> pre = "&7";
            case DYNAMIC -> pre = "&6";
            case DISABLED -> pre = "&c";
            case ENABLED -> pre = "&a";
            default -> pre = "&8";
        }
        return Text.color("&f" + StringUtils.capitalize(name()) + " " + pre + name);
    }

    public int getId() {
        return id;
    }
}
