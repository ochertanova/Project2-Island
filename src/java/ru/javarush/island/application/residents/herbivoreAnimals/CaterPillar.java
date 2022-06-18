package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Herbivore
@AnimalInfo(nameType = "caterpillar" ,description = "гусеница")
public class CaterPillar extends HerbivoreAnimal {
    public CaterPillar(Position position) {
        super(position);
    }
}
