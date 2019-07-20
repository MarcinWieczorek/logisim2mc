package co.marcin.logisim2mc.parser;

import org.bukkit.Location;

public interface LogisimElement {
    int getX();

    int getY();

    void build(Location location);
}
