package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;

@AnimalInfo(nameType = "duck",description = "утка")
public class Duck extends HerbivoreAnimal {
    public Duck(Position position) {
        super(position);
    }
}
