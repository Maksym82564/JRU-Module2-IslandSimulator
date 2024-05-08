package models.animal.predators;

import models.animal.Animal;
import models.map.Coordinates;
import models.map.IslandMap;

public abstract class Predator extends Animal {
    public Predator(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
    }

    @Override
    public void run() {
        if (!exploreArea())
        {
            move();
        }
        handleStatuses();
    }
}
