package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;


@Herbivore
@AnimalInfo(nameType = "deer", description = "олень")
public class Deer extends HerbivoreAnimal {
    public Deer(Position position) {
        super(position);
    }
}
