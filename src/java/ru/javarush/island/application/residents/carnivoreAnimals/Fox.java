package ru.javarush.island.application.residents.carnivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.residents.CarnivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Carnivore
@AnimalInfo(nameType = "fox",description = "лиса")
public class Fox extends CarnivoreAnimal {
    public Fox(Position position) {
        super(position);
    }
}
