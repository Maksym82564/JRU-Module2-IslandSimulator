package models.animal.predators;

import interfaces.Drawable;
import models.animal.Animal;
import models.map.Coordinates;
import models.map.IslandMap;

import java.util.Map;

public abstract class Predator extends Animal {
    public Predator(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
    }

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted())
            move();
        if (!Thread.currentThread().isInterrupted())
            sweep();
    }
}
