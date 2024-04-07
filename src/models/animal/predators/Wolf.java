package models.animal.predators;

import models.map.Coordinates;
import models.map.IslandMap;

import java.util.Map;

public class Wolf extends Predator {
    private static final String ICON = "\uD83D\uDC3A";
    private static final String NAME = "wolf";
    private static final int MAX_SPEED = 3;

    private static final Map<String, Integer> CONSUME_MAP = Map.of(
            "horse", 10,
            "deer", 15,
            "rabbit", 60,
            "goat", 60,
            "sheep", 70,
            "boar", 15,
            "buffalo", 10,
            "duck", 40
    );

    public Wolf(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
        setAnimalName(NAME);
        setIcon(ICON);
        setMaxSpeed(MAX_SPEED);
        setChanceToConsumeMap(CONSUME_MAP);
    }
}
