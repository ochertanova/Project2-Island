package ru.javarush.island.application.enums;

//Выбор направления движения
public enum Directions {
    AHEAD(1, "вперед"),
    BACK(2, "назад"),
    RIGHT(3, "направо"),
    LEFT(4, "налево");

    private final int countDirection;
    private final String description;

    private Directions(int countDirection, String description) {
        this.countDirection = countDirection;
        this.description = description;
    }

    public int getCountDirection() {
        return countDirection;
    }

    public String getDescription() {
        return description;
    }


}
