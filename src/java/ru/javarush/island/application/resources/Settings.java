package ru.javarush.island.application.resources;

import java.util.List;

public class Settings {
    private int length;
    private int width;
    private PlantCharacteristics plant;
    private List<AnimalCharacteristics> animals;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<AnimalCharacteristics> getAnimalCharacteristics() {
        return animals;
    }

    public void setAnimalCharacterisrics(List<AnimalCharacteristics> animalCharacteristics) {
        this.animals = animalCharacteristics;
    }

    public void setPlant(PlantCharacteristics plant) {
        this.plant = plant;
    }

    public void setAnimals(List<AnimalCharacteristics> animals) {
        this.animals = animals;
    }

    public PlantCharacteristics getPlant() {
        return plant;
    }
}
