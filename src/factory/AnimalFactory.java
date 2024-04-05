package factory;

import enums.HerbivorousEnum;
import enums.PredatorEnum;
import models.animal.herbivorous.Herbivorous;
import models.animal.herbivorous.Sheep;
import models.animal.predators.Predator;
import models.animal.predators.Wolf;
import models.map.Coordinates;
import models.map.IslandMap;
import randomizer.RandomizerUtil;

import java.util.HashSet;
import java.util.Set;

public class AnimalFactory {
    public static Set<Predator> createPredatorSet(int predatorAmount, IslandMap map) {
        Set<Predator> set = new HashSet<>();
        for (int i = 0; i < predatorAmount; i++) {
            PredatorEnum predatorEnum = RandomizerUtil.rollAnimal(PredatorEnum.class);
            Coordinates randCoords = RandomizerUtil.rollRandomCoords(map);
            switch (predatorEnum) {
                case WOLF -> set.add(new Wolf(map, randCoords));
            }
        }
        return set;
    }

    public static Set<Herbivorous> createHerbivorSet(int herbivorAmount, IslandMap map) {
        Set<Herbivorous> set = new HashSet<>();
        for (int i = 0; i < herbivorAmount; i++) {
            HerbivorousEnum herbivorEnum = RandomizerUtil.rollAnimal(HerbivorousEnum.class);
            Coordinates randCoords = RandomizerUtil.rollRandomCoords(map);
            switch (herbivorEnum) {
                case SHEEP -> set.add(new Sheep(map, randCoords));
            }
        }
        return set;
    }
}