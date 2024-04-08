package models.herb;

import interfaces.Entity;
import models.map.Coordinates;
import models.map.IslandMap;

public class Herb implements Entity, Runnable {
    private static final String ICON = "\uD83C\uDF3F";
    private static final String NAME = "herb";
    private final Coordinates coords;
    private final IslandMap islandMap;
    private volatile boolean isEaten = false;
    private int timeSinceHerbEaten = 0;

    public Herb(Coordinates coords, IslandMap islandMap) {
        this.coords = coords;
        this.islandMap = islandMap;
        grow();
    }

    @Override
    public void run() {
        if (isEaten) {
            if (++timeSinceHerbEaten == 4) {
                grow();
                timeSinceHerbEaten = 0;
            }
        }
    }

    private void grow() {
        isEaten = false;
        islandMap.getArea(coords).addEntity(this);
    }

    @Override
    public String getEntityName() {
        return NAME;
    }

    @Override
    public String draw() {
        return ICON;
    }

    public void setEaten() {
        isEaten = true;
        islandMap.getArea(coords).removeEntity(this);
    }

    public Coordinates getCoords() {
        return coords;
    }

    public IslandMap getIslandMap() {
        return islandMap;
    }
}
