package enums;

import interfaces.AnimalEnum;
import models.animal.Animal;
import models.animal.herbivorous.Sheep;

public enum HerbivorousEnum implements AnimalEnum {
    SHEEP(Sheep.class);

    private final Class<? extends Animal> animalClass;

    HerbivorousEnum(Class<? extends Animal> animalClass) {
        this.animalClass = animalClass;
    }

    @Override
    public Class<? extends Animal> getAnimalClass() {
        return animalClass;
    }
}
