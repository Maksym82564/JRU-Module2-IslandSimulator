package models.map;

import interfaces.Drawable;
import interfaces.Entity;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Area implements Drawable {
    private final static String ICON = "\uD83D\uDFEB";
    private final Set<Entity> entities = new CopyOnWriteArraySet<>();
    private final Lock removingLock = new ReentrantLock();
    private final Lock addingLock = new ReentrantLock();

    public boolean removeEntity(Entity entity) {
        boolean status = false;
        try {
            removingLock.lockInterruptibly();
            try {
                status = entities.remove(entity);
            } finally {
                removingLock.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return status;
    }

    public boolean addEntity(Entity entity) {
        boolean status = false;
        try {
            addingLock.lockInterruptibly();
            try {
                status = entities.add(entity);
            } finally {
                addingLock.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return status;
    }

    @Override
    public String draw() {
        StringBuilder image = new StringBuilder("|");
        if (entities.isEmpty()) {
            return "|" + ICON + "|";
        }
        for (Entity entity : entities) {
            image.append(entity.draw()).append("|");
        }
        return image.toString();
    }

    public Set<Entity> getEntities() {
        return entities;
    }
}