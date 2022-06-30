package ru.javarush.island.application.residents.carnivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.residents.CarnivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Carnivore
@AnimalInfo(nameType = AnimalTypes.EAGLE, description = "орел")
public class Eagle extends CarnivoreAnimal {
    public Eagle(Position position) {
        super(position);
    }
}
