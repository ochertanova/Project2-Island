package ru.javarush.island.application.resources;

import ru.javarush.island.application.enums.AnimalTypes;

import java.util.Map;

public class AnimalCharacteristics {
    private AnimalTypes name;
    private int weight;
    private int maxCountOnThisAnimal;
    private int speed;
    private int saturation;
    private String emoji;
    private Map<AnimalTypes, Integer> chanceToEat;

    public void setName(AnimalTypes name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMaxCountOnThisAnimal(int maxCountOnThisAnimal) {
        this.maxCountOnThisAnimal = maxCountOnThisAnimal;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void setChanceToEat(Map<AnimalTypes, Integer> chanceToEat) {
        this.chanceToEat = chanceToEat;
    }

    public AnimalTypes getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxCountOnThisAnimal() {
        return maxCountOnThisAnimal;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSaturation() {
        return saturation;
    }

    public String getEmoji() {
        return emoji;
    }

    public Map<AnimalTypes, Integer> getChanceToEat() {
        return chanceToEat;
    }
}
