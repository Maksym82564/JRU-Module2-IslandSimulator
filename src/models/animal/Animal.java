package models.animal;

import enums.Direction;
import interfaces.Entity;
import models.herb.Herb;
import models.map.Coordinates;
import models.map.IslandMap;
import models.map.Area;
import randomizer.RandomizerUtil;

import java.util.HashMap;
import java.util.Map;

public abstract class Animal implements Entity, Runnable {
    private volatile boolean isAlive = true;
    private final Map<String, Integer> chanceToConsumeMap = new HashMap<>();
    private final IslandMap islandMap;
    private final Coordinates currentCoords;
    private int maxSpeed;
    private String animalName;
    private String icon;

    public Animal(IslandMap islandMap, Coordinates coords) {
        super();
        this.islandMap = islandMap;
        this.currentCoords = coords;
        islandMap.getArea(coords).addEntity(this);
    }

    @Override
    public String draw() {
        return icon;
    }

    @Override
    public String getEntityName() {
        return animalName;
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

    public void eat(Entity obj) {
        Area area = islandMap.getArea(currentCoords);
        area.removeEntity(obj);
        if (obj instanceof Animal animal) {
            animal.setDead();
            new Herb(currentCoords, islandMap);
        }
    }

    public void exploreArea() {
        Area area = islandMap.getArea(currentCoords);
        for (Entity entity : area.getEntities()) {
            if (entity.equals(this)) {
                continue;
            }
            String name = entity.getEntityName();
            if (chanceToConsumeMap.containsKey(name)) {
                int chance = chanceToConsumeMap.get(name);
                if (RandomizerUtil.rollChanceToConsume(chance)) {
                    eat(entity);
                    break;
                }
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setChanceToConsumeMap(Map<String, Integer> map) {
        this.chanceToConsumeMap.clear();
        this.chanceToConsumeMap.putAll(map);
    }

    private void setDead() {
        isAlive = false;
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
}