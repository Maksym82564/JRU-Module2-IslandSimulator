package factory;

import models.herb.Herb;
import models.map.Coordinates;
import models.map.IslandMap;
import randomizer.RandomizerUtil;

public class HerbFactory {
    public static void createHerbs(int herbAmount, IslandMap islandMap) {
        for (int i = 0; i < herbAmount; i++) {
            Coordinates randomCoords = RandomizerUtil.rollRandomCoords(islandMap);
            new Herb(randomCoords, islandMap);
        }
    }
}
