package models.animal.herbivorous;

import models.map.Coordinates;
import models.map.IslandMap;

public class Sheep extends Herbivorous {
    private static final String ICON = "\uD83D\uDC11";
    private static final String NAME = "sheep";
    private static final int MAX_SPEED = 3;
    private static final int MAX_SATIETY = 8;
    private static final int NUTRITION_VALUE = 50;

    public Sheep(IslandMap islandMap, Coordinates coords) {
        super(islandMap, coords);
        setAnimalName(NAME);
        setIcon(ICON);
        setMaxSpeed(MAX_SPEED);
        setChanceToConsumeMap(CONSUME_MAP);
        setMaxSatiety(MAX_SATIETY);
        setNutritionValue(NUTRITION_VALUE);
    }
}
