package models.herb;

import interfaces.Entity;
import models.map.Coordinates;
import models.map.IslandMap;

public class Herb implements Entity {
    private static final String ICON = "\uD83C\uDF3F";
    private static final int NUTRITION_VALUE = 1;
    private static final int TIME_BEFORE_REGROWTH = 1;
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
            if (++timeSinceHerbEaten == TIME_BEFORE_REGROWTH) {
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

    @Override
    public void setDead() {
        isEaten = true;
        islandMap.getArea(coords).removeEntity(this);
    }

    @Override
    public int getNutritionValue() {
        return NUTRITION_VALUE;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public IslandMap getIslandMap() {
        return islandMap;
    }
}
