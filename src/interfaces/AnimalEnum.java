package interfaces;

import models.animal.Animal;

public interface AnimalEnum {
    Class<? extends Animal> getAnimalClass();
}
