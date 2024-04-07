package models.animal.herbivorous;

import models.animal.Animal;
import models.map.Coordinates;
import models.map.IslandMap;

import java.util.Map;

public abstract class Herbivorous extends Animal {
    static final Map<String, Integer> CONSUME_MAP = Map.of(
            "herb", 100
    );

    public Herbivorous(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
    }

    @Override
    public void run() {
        if (isAlive()) {
            exploreArea();
        }
        else {
            Thread.currentThread().interrupt();
        }
        if (isAlive()) {
            move();
        }
        else {
            Thread.currentThread().interrupt();
        }
        if (isAlive()) {
            exploreArea();
        }
        else {
            Thread.currentThread().interrupt();
        }
    }
}