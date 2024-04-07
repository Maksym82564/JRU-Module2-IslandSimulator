package models.herb;

import interfaces.Entity;
import models.map.Coordinates;
import models.map.IslandMap;

public class Herb implements Entity {
    private static final String ICON = "\uD83C\uDF3F";
    private static final String name = "herb";
    private final Coordinates coords;
    private final IslandMap islandMap;

    public Herb(Coordinates coords, IslandMap islandMap) {
        this.coords = coords;
        this.islandMap = islandMap;
        islandMap.getArea(coords).addEntity(this);
    }

    public Coordinates getCoords() {
        return coords;
    }

    public IslandMap getIslandMap() {
        return islandMap;
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
