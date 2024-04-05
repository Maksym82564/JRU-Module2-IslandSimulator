package randomizer;

import enums.Direction;
import models.map.Coordinates;
import models.map.IslandMap;

import java.util.concurrent.ThreadLocalRandom;

public class RandomizerUtil {
    private static final int MAX_CHANCE = 100;

    private RandomizerUtil() {
    }

    public static boolean rollChanceToConsume(int chance) {
        if (chance == 100) {
            return true;
        }
        else {
            int randNum = ThreadLocalRandom.current().nextInt(MAX_CHANCE + 1);
            return randNum <= chance;
        }
    }

    public static Direction rollDirection() {
        int num = ThreadLocalRandom.current().nextInt(Direction.values().length);
        return Direction.values()[num];
    }

    public static int rollSpeed(int maxSpeed) {
        return ThreadLocalRandom.current().nextInt(maxSpeed + 1);
    }

    public static Coordinates rollRandomCoords(IslandMap islandMap) {
        int x = ThreadLocalRandom.current().nextInt(islandMap.getMaxX() + 1);
        int y = ThreadLocalRandom.current().nextInt(islandMap.getMaxY() + 1);
        return new Coordinates(x, y);
    }

    public static <T extends Enum<T>> T rollAnimal(Class<T> tClass) {
        T[] values = tClass.getEnumConstants();
        int rand = ThreadLocalRandom.current().nextInt(values.length);
        return values[rand];
    }
}
