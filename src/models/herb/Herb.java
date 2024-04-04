package models.herb;

import interfaces.Entity;
import models.map.Coordinates;

public class Herb implements Entity {
    private static final String ICON = "\uD83C\uDF3F";
    private static final String name = "herb";
    private final Coordinates coords;

    public Herb(Coordinates coords) {
        this.coords = coords;
    }

    public Coordinates getCoords() {
        return coords;
    }

    @Override
    public String getEntityName() {
        return name;
    }

    @Override
    public String draw() {
        return ICON;
    }
}
