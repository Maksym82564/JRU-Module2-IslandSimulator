package app;

import interfaces.Entity;
import interfaces.ReproductionHandler;
import models.animal.Animal;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeCreator implements ReproductionHandler {
    private final ScheduledExecutorService newbornEntityExecutor;
    private final ScheduledExecutorService reproducedAnimalExecutor;
    private final Set<Entity> newbornEntities = new HashSet<>();
    private final Set<Animal> reproducedAnimals = Animal.getReproducedAnimals();

    public LifeCreator() {
        int processorsNum = Runtime.getRuntime().availableProcessors();
        this.newbornEntityExecutor = Executors.newScheduledThreadPool(processorsNum - 1);
        this.reproducedAnimalExecutor = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void run() {
        animateReproduced();
    }

    public void animateReproduced() {
        Set<Animal> copiedReproducedAnimals = new HashSet<>(reproducedAnimals);
        for (Animal reproducedAnimal : copiedReproducedAnimals) {
            newbornEntities.add(reproducedAnimal);
            newbornEntityExecutor.scheduleAtFixedRate(reproducedAnimal,0, 1000, TimeUnit.MILLISECONDS);
            reproducedAnimals.remove(reproducedAnimal);

        }
    }

    public void getReproductionHandlerStarted() {
        reproducedAnimalExecutor.scheduleAtFixedRate(this, 500, 1000, TimeUnit.MILLISECONDS);
    }

    public void animateNewborns(Set<Entity> entities) {
        newbornEntities.clear();
        newbornEntities.addAll(entities);
        for (Entity entity : entities) {
            newbornEntityExecutor.scheduleAtFixedRate(entity,500, 1000, TimeUnit.MILLISECONDS);
        }
    }

    public void stop() {
        newbornEntityExecutor.shutdownNow();
        reproducedAnimalExecutor.shutdownNow();
    }

    public Set<Entity> getNewbornEntities() {
        return newbornEntities;
    }

    public void setNewbornEntities(Set<Entity> entities) {
        newbornEntities.addAll(entities);
    }
}