package ru.javarush.island.application.residents;

import ru.javarush.island.application.enums.Directions;

import java.util.Arrays;
import java.util.List;


public class Animal extends BasisItem {
    private int weight;
    private int maxCountOnThisAnimal;
    private int speed;
    private int saturation;
    private boolean isHungry;
    private boolean isAlive;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected Animal(Position position) {
        super(position);
    }

    public void eat() {
    }

    public void move() {
        Directions currentDirection = chooseDirection();
        switch (currentDirection) {
            case AHEAD -> position.setY(position.getY() + 1);
            case BACK -> position.setY(position.getY() - 1);
            case RIGHT -> position.setX(position.getX() + 1);
            case LEFT -> position.setX(position.getX() - 1);
            default -> throw new IllegalStateException("Unexpected value: " + currentDirection);
        }
    }

    public void die() {
    }

    public void overbreed() {
    }

    //рандомно выбираем направление движения
    public Directions chooseDirection() {
        // по умолчанию будет использоваться направление движения "вперед"
        int randomDirection = 1;
        List<Directions> countDirections = Arrays.stream(Directions.values()).toList();
        randomDirection = (int) (Math.random() * countDirections.size());
        return countDirections.get(randomDirection);
    }
}