package ru.javarush.island.application.islandDescription;

import ru.javarush.island.application.residents.Position;

import java.util.Arrays;

public class Island {
    private int width;
    private int length;
    private Position[][] positions;

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Position[][] getPositions() {
        return positions;
    }

    public Island(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public Position[][] createIsland() {
        positions = new Position[width][length];
        return positions;
    }

    public Position[][] getIsland() {
        return positions;
    }

    public void setIsland(Position[][] positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Island{" +
                "width=" + width +
                ", length=" + length +
                ", positions=" + Arrays.deepToString(positions) +
                '}';
    }
}
