package ru.javarush.island.application.residents.carnivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.residents.CarnivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Carnivore
@AnimalInfo(nameType = AnimalTypes.WOLF, description = "волк")
public class Wolf extends CarnivoreAnimal {
    public Wolf(Position position) {
        super(position);
    }

}
