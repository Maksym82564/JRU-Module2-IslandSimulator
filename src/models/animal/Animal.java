package models.animal;

import enums.Direction;
import interfaces.Entity;
import models.map.Coordinates;
import models.map.IslandMap;
import models.map.Area;
import randomizer.RandomizerUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class Animal implements Entity, Cloneable {
    private static final Set<Animal> reproducedAnimals = new CopyOnWriteArraySet<>();
    private static final int CYCLES_BEFORE_NEW_REPRODUCTION = 3;
    private volatile boolean isAlive;
    private final Map<String, Integer> chanceToConsumeMap = new HashMap<>();
    private final IslandMap islandMap;
    private final Coordinates currentCoords;
    private int maxSpeed;
    private String animalName;
    private String icon;
    private volatile boolean reproduced;
    private int cyclesAfterReproductionCounter;
    private int maxSatiety;
    private int satiety;
    private int nutritionValue;

    public Animal(IslandMap islandMap, Coordinates coords) {
        super();
        this.islandMap = islandMap;
        this.currentCoords = coords;
        beBorn();
    }

    private void beBorn() {
        cyclesAfterReproductionCounter = 0;
        reproduced = true;
        isAlive = true;
        satiety = 0;
        islandMap.getArea(currentCoords).addEntity(this);
    }

    @Override
    public String draw() {
        return icon;
    }

    @Override
    public String getEntityName() {
        return animalName;
    }

    @Override
    public int getNutritionValue() {
        return nutritionValue;
    }

    @Override
    public Animal clone() {
        try {
            Animal newBorn = (Animal) super.clone();
            newBorn.beBorn();
            return newBorn;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void setEaten() {
        isAlive = false;
        islandMap.getArea(currentCoords).removeEntity(this);
        Thread.currentThread().interrupt();
    }

    public void move() {
        int speed = RandomizerUtil.rollSpeed(maxSpeed);
        Direction direction = RandomizerUtil.rollDirection();
        int newX = currentCoords.getX();
        int newY = currentCoords.getY();
        switch (direction) {
            case NORTH -> newX = newX - speed;
            case SOUTH -> newX = newX + speed;
            case EAST -> newY = newY + speed;
            case WEST -> newY = newY - speed;
        }
        if (newX < 0) {
            newX = 0;
        } else if (newX > islandMap.getMaxX()) {
            newX = islandMap.getMaxX();
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY > islandMap.getMaxY()) {
            newY = islandMap.getMaxY();
        }
        Coordinates newCoords = new Coordinates(newX, newY);
        Area currentArea = islandMap.getArea(currentCoords);
        Area newArea = islandMap.getArea(newCoords);
        if (currentArea.removeEntity(this)) {
            newArea.addEntity(this);
            currentCoords.setX(newX);
            currentCoords.setY(newY);
        }
    }

    private void eat(Entity entity) {
        satiety += entity.getNutritionValue();
        if (satiety > maxSatiety) {
            satiety = maxSatiety;
        }
        entity.setEaten();
    }

    public void countReproductionCycles() {
        if (reproduced && cyclesAfterReproductionCounter == CYCLES_BEFORE_NEW_REPRODUCTION) {
            cyclesAfterReproductionCounter = 0;
            reproduced = false;
        } else {
            cyclesAfterReproductionCounter++;
        }
    }

    public void reproduce(Animal otherAnimal) {
        reproduced = true;
        otherAnimal.setReproduced();
        Animal newborn = clone();
        reproducedAnimals.add(newborn);
    }

    public boolean tryToReproduce(Entity otherEntity, String otherEntityName) {
        if (otherEntityName.equals(this.getEntityName())) {
            Animal animal = (Animal) otherEntity;
            return !reproduced && !animal.isReproduced();
        }
        return false;
    }

    public boolean exploreArea() {
        Area area = islandMap.getArea(currentCoords);
        for (Entity otherEntity : area.getEntities()) {
            String otherEntityName = otherEntity.getEntityName();
            if (tryToReproduce(otherEntity, otherEntityName)) {
                reproduce((Animal) otherEntity);
            } else if (chanceToConsumeMap.containsKey(otherEntityName)) {
                int chance = chanceToConsumeMap.get(otherEntityName);
                if (RandomizerUtil.rollChanceToConsume(chance)) {
                    eat(otherEntity);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setChanceToConsumeMap(Map<String, Integer> map) {
        this.chanceToConsumeMap.clear();
        this.chanceToConsumeMap.putAll(map);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAnimalName(String name) {
        this.animalName = name;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxSatiety(int maxSatiety) {
        this.maxSatiety = maxSatiety;
    }

    public boolean isReproduced() {
        return reproduced;
    }

    public void setReproduced() {
        this.reproduced = true;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public int getMaxSatiety() {
        return maxSatiety;
    }

    public void setNutritionValue(int nutritionValue) {
        this.nutritionValue = nutritionValue;
    }

    public static Set<Animal> getReproducedAnimals() {
        return reproducedAnimals;
    }
}