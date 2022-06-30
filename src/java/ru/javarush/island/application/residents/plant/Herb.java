package ru.javarush.island.application.residents.plant;

import ru.javarush.island.application.annotations.HerbInfo;
import ru.javarush.island.application.residents.Plant;
import ru.javarush.island.application.residents.Position;

@HerbInfo
public class Herb extends Plant {
    private Position position;
    public Herb(Position position) {
        super(position);
    }
}
