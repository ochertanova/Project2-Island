package ru.javarush.island.application.residents;

public abstract class Plant extends BasisItem {
    private Position position;

    protected Plant(Position position) {
        super(position);
    }
}
