package ru.javarush.island.application.residents.carnivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.residents.CarnivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Carnivore
@AnimalInfo(nameType = "boar",description = "кабан")
public class Boar extends CarnivoreAnimal {
    public Boar (Position position) {
        super(position);
    }
}
