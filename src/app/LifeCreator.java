package app;

import interfaces.Entity;
import models.animal.Animal;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeCreator {
    private final ScheduledExecutorService executorService;
    private final Set<Entity> newbornEntities = new HashSet<>();

    public LifeCreator() {
        int processorsNum = Runtime.getRuntime().availableProcessors();
        this.executorService = Executors.newScheduledThreadPool(processorsNum - 1);
    }

    public void animateNewborn(Animal animal) {
        newbornEntities.add(animal);
        executorService.scheduleAtFixedRate(animal,0, 1, TimeUnit.SECONDS);
    }

    public void animateNewborn(Set<Entity> entities) {
        newbornEntities.clear();
        newbornEntities.addAll(entities);
        for (Entity entity : entities) {
            if (entity instanceof Animal animal) {
                animal.setLifeCreator(this);
            }
            executorService.scheduleAtFixedRate(entity,500, 1000, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public Set<Entity> getNewbornEntities() {
        return newbornEntities;
    }

    public void setNewbornEntities(Set<Entity> entities) {
        newbornEntities.addAll(entities);
    }
}