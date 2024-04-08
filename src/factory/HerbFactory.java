package factory;

import models.herb.Herb;
import models.map.Coordinates;
import models.map.IslandMap;
import randomizer.RandomizerUtil;
import java.util.HashSet;
import java.util.Set;

public class HerbFactory {
    public static Set<Herb> createHerbs(int herbAmount, IslandMap islandMap) {
        Set<Herb> herbSet = new HashSet<>();
        for (int i = 0; i < herbAmount; i++) {
            Coordinates randomCoords = RandomizerUtil.rollRandomCoords(islandMap);
            herbSet.add(new Herb(randomCoords, islandMap));
        }
        return herbSet;
    }
}
