package ru.javarush.island.application.residents.carnivoreAnimals;

import ru.javarush.island.application.annotations.AnimalInfo;
import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.residents.CarnivoreAnimal;
import ru.javarush.island.application.residents.Position;

@Carnivore
@AnimalInfo(nameType = "wolf",description = "волк")
public class Wolf extends CarnivoreAnimal {
    private int x;
    private int y;

    public Wolf(Position position) {
        super(position);
    }
}
