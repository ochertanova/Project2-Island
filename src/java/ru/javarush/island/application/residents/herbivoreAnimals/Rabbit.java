package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Herbivore
@AnimalInfo(nameType = "rabbit", description = "кролик")
public class Rabbit extends HerbivoreAnimal {
    public Rabbit(Position position) {
        super(position);
    }
}
