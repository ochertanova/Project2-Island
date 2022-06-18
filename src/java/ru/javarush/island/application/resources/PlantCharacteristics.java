package ru.javarush.island.application.resources;

public class PlantCharacteristics {
    private int maxCountOnPosition;

    public int getMaxCountOnThisAnimal() {
        return maxCountOnPosition;
    }

    public void setMaxCountOnPosition(int maxCountOnPosition) {
        this.maxCountOnPosition = maxCountOnPosition;
    }
}
