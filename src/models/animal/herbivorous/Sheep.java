package models.animal.herbivorous;

import models.map.Coordinates;
import models.map.IslandMap;

import java.util.Map;

public class Sheep extends Herbivorous {
    private static final String ICON = "\uD83D\uDC11";
    private static final String NAME = "sheep";
    private static final int MAX_SPEED = 3;

    public Sheep(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
        setAnimalName(NAME);
        setIcon(ICON);
        setMaxSpeed(MAX_SPEED);
        setChanceToConsumeMap(CONSUME_MAP);
    }
}
