package ru.javarush.island.application.residents;

public abstract class BasisItem {
    //текущее положение на карте острова
    private Position position;

    BasisItem(Position position) {
        this.position = position;
    }
}
