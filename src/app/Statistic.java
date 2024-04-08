package app;

import interfaces.Entity;
import models.animal.herbivorous.Herbivorous;
import models.animal.predators.Predator;
import models.herb.Herb;
import models.map.IslandMap;

public class Statistic implements Runnable {
    private final IslandMap islandMap;
    private final Wildlife wildlife;
    private int herbivorsNum;
    private int deadHerbivorsNum;
    private int predatorNum;
    private int deadPredatorNum;
    private int herbNum;
    private int deadHerbs;

    public Statistic(IslandMap islandMap, Wildlife wildlife) {
        this.islandMap = islandMap;
        this.wildlife = wildlife;
    }

    public void getStats() {
        herbivorsNum = 0;
        predatorNum = 0;
        herbNum = 0;
        deadHerbivorsNum = 0;
        deadPredatorNum = 0;
        deadHerbs = 0;
        for (Entity newbornEntity : wildlife.getLifeCreator().getNewbornEntities()) {
            if (newbornEntity instanceof Herbivorous herbivorous) {
                if (herbivorous.isAlive()) {
                    herbivorsNum++;
                } else {
                    deadHerbivorsNum++;
                }
            } else if (newbornEntity instanceof Predator predator) {
                if (predator.isAlive()) {
                    predatorNum++;
                } else {
                    deadPredatorNum++;
                }
            } else if (newbornEntity instanceof Herb herb) {
                if (herb.isEaten()) {
                    deadHerbs++;
                } else {
                    herbNum++;
                }
            }
        }
    }

    public void displayStats() {
        getStats();
        System.out.println();
        System.out.println("Alive Herbivorous = " + herbivorsNum + "\t\tDead Herbivorous = " + deadHerbivorsNum);
        System.out.println("Alive Predator = " + predatorNum + "\t\t\tDead Predator = " + deadPredatorNum);
        System.out.println("Alive Herbs = " + herbNum + "\t\t\tDead Herbs = " + deadHerbs);
        System.out.println();
    }

    @Override
    public void run() {
        displayStats();
        islandMap.drawIsland();
    }
}
