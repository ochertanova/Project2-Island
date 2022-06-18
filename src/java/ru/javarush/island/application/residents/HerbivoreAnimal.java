package ru.javarush.island.application.residents;

public class HerbivoreAnimal extends Animal{
    private Position position;
    public HerbivoreAnimal(Position position) {
        super(position);
    }

    @Override
    public void eat() {
    }
}
