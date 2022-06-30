package ru.javarush.island.application.residents;

import ru.javarush.island.application.enums.Directions;
import ru.javarush.island.application.resources.AnimalCharacteristics;


public class Animal extends BasisItem {
    private int speed;
    /*
    по умолчанию при старте игры все животные максимально сыты
    каждый такт будет расходоваться 20% энергии
    для этого используется специальный метод useEnergy();
    */
    private int saturation = 100;
    private boolean isHungry;
    private boolean isAlive = true;
    private Position position;
    private AnimalCharacteristics animalCharacteristics;

    public AnimalCharacteristics getAnimalCharacteristics() {
        return animalCharacteristics;
    }

    public void setAnimalCharacteristics(AnimalCharacteristics animalCharacteristics) {
        this.animalCharacteristics = animalCharacteristics;
    }

    public Animal(Position position) {
        super(position);
        this.position = position;
    }

    public boolean isHungry() {
        if (saturation < 100) {
            isHungry = true;
        } else isHungry = false;
        return isHungry;
    }

    public void eat(boolean eatingResult) {
        if (eatingResult) {
            /*
            за успешную попытку "покушать" прибавляется 20% насыщения
            */
            saturation = saturation + 20;
        } else {
            useEnergy();
        }
    }

    public Position move(Position newPosition) {
        position = newPosition;
        useEnergy();
        return position;
    }

    /*
    не учтен алгоритм, когда животное съедают
    */
    public void checkDeth() {
        if (saturation <= 0) {
            setAlive(false);
        }
    }

    public void overbreed() {
    }


    private void useEnergy() {
        saturation = saturation - 20;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}