package randomizer;

import enums.Direction;

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

    public static Direction rollRandomDirection() {
        int num = ThreadLocalRandom.current().nextInt(Direction.values().length);
        return Direction.values()[num];
    }

    public static int rollRandomSpeed(int maxSpeed) {
        return ThreadLocalRandom.current().nextInt(maxSpeed + 1);
    }
}
