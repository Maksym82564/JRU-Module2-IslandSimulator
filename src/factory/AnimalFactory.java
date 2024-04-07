package factory;

import interfaces.AnimalEnum;
import models.animal.Animal;
import models.map.Coordinates;
import models.map.IslandMap;
import randomizer.RandomizerUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class AnimalFactory {
    public static <T extends AnimalEnum> Set<Animal> createAnimalSet(int animalAmount, IslandMap map, Class<T> animalEnum) {
        Set<Animal> animalSet = new HashSet<>();
        for (int i = 0; i < animalAmount; i++) {
            AnimalEnum randomAnimal = RandomizerUtil.rollAnimal(animalEnum);
            Coordinates randomCoords = RandomizerUtil.rollRandomCoords(map);
            Constructor<?> declaredConstructor = randomAnimal.getAnimalClass().getDeclaredConstructors()[0];
            try {
                Animal animal = (Animal) declaredConstructor.newInstance(map, randomCoords);
                animalSet.add(animal);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return animalSet;
    }
}