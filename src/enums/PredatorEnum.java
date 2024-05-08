package enums;

import interfaces.AnimalEnum;
import models.animal.Animal;
import models.animal.predators.Wolf;

public enum PredatorEnum implements AnimalEnum {
    WOLF(Wolf.class);

    private final Class<? extends Animal> animalClass;

    PredatorEnum(Class<? extends Animal> animalClass) {
        this.animalClass = animalClass;
    }

    @Override
    public Class<? extends Animal> getAnimalClass() {
        return animalClass;
    }
}
