package app;

import enums.HerbivorousEnum;
import enums.PredatorEnum;
import factory.AnimalFactory;
import factory.HerbFactory;
import interfaces.Entity;
import models.animal.Animal;
import models.herb.Herb;
import models.map.IslandMap;

import java.util.HashSet;
import java.util.Set;

public class Wildlife {
    private final LifeCreator lifeCreator;
    private final IslandMap islandMap;

    public Wildlife(IslandMap islandMap) {
        this.lifeCreator = new LifeCreator();
        this.islandMap = islandMap;
    }

    private Set<Entity> createEntities(int herbAmount, int predatorAmount, int herbivorousAmount) {
        Set<Animal> predatorSet = AnimalFactory.createAnimalSet(predatorAmount, islandMap, PredatorEnum.class);
        Set<Animal> herbivorousSet = AnimalFactory.createAnimalSet(herbivorousAmount, islandMap, HerbivorousEnum.class);
        Set<Herb> herbs = HerbFactory.createHerbs(herbAmount, islandMap);
        Set<Entity> entities = new HashSet<>();
        entities.addAll(predatorSet);
        entities.addAll(herbivorousSet);
        entities.addAll(herbs);
        return entities;
    }

    public void createWildlife(int herbAmount, int predatorAmount, int herbivorousAmount) {
        Set<Entity> entities = createEntities(herbAmount, predatorAmount, herbivorousAmount);
        lifeCreator.animateNewborns(entities);
        lifeCreator.getReproductionHandlerStarted();
    }

    public LifeCreator getLifeCreator() {
        return lifeCreator;
    }

    public void endWildlife() {
        lifeCreator.stop();
    }
}
