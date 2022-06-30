package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Herbivore
@AnimalInfo(nameType = AnimalTypes.BUFFALO, description = "буйвол")
public class Buffalo extends HerbivoreAnimal {
    public Buffalo(Position position) {
        super(position);
    }
}
