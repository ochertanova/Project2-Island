package ru.javarush.island.application.residents;

import java.util.List;

public class CarnivoreAnimal extends Animal {
    private int chanceToEat;
    private List<Animal> animalSetting;
    private Position position;

    public CarnivoreAnimal(Position position) {
        super(position);
    }

}