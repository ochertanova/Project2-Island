package ru.javarush.island.application.residents.herbivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.residents.HerbivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Herbivore
@AnimalInfo(nameType = AnimalTypes.MOUSE,description = "мышь")
public class Mouse extends HerbivoreAnimal {
    public Mouse(Position position) {
        super(position);
    }
}
