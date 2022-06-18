package ru.javarush.island.application.residents;

public abstract class BasisItem {
    //текущее положение на карте острова
    Position position;

    BasisItem(Position position) {
        this.position = position;
    }
}