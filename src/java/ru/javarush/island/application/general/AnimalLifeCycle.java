package ru.javarush.island.application.general;

import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.enums.Directions;
import ru.javarush.island.application.islandDescription.Island;
import ru.javarush.island.application.residents.Animal;
import ru.javarush.island.application.residents.Position;
import ru.javarush.island.application.residents.plant.Herb;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
Общий класс для регулирования жизнедеятельности животных
 */
public class AnimalLifeCycle {
    private Integer diedAnimalsCount;
    private Island island;

    public int getDiedAnimalsCount() {
        return diedAnimalsCount;
    }

    public void setDiedAnimalsCount(int diedAnimalsCount) {
        this.diedAnimalsCount = diedAnimalsCount;
    }

    public Map<Position, List<List<Animal>>> getAnimalsOnIsland() {
        return animalsOnIsland;
    }

    public Map<Position, List<Herb>> getHerbOnIsland() {
        return herbOnIsland;
    }

    // Хранение всех животных на острове в каждой позиции
    private Map<Position, List<List<Animal>>> animalsOnIsland;

    // Хранение всех растений на острове в одной позиции
    Map<Position, List<Herb>> herbOnIsland = new HashMap<>();

    public AnimalLifeCycle(Map<Position, List<List<Animal>>> animalsOnIsland,
                           Map<Position, List<Herb>> herbOnIsland, Island island, Integer diedAnimalsCount) {
        this.animalsOnIsland = animalsOnIsland;
        this.herbOnIsland = herbOnIsland;
        this.island = island;
        this.diedAnimalsCount = diedAnimalsCount;
    }

    /* Выбираем произвольно на каждой позиции из листа 2 List<Animal>
     - animalsToEat - это будет кандидат, который будет пытаться покушать
     - animalsForEat - это будет кандидат, которого будут пытаться съесть
     Если результат поедания животного будет удачный, то переменной result=true
     Далее если животное травоядное, то будет уменьшено кол-во растений на позиции
     А если животное плотоядное, то будет удален элемент из списка животных animalsForEat
     */
    public void toEat() {
        for (Map.Entry<Position, List<List<Animal>>> entry : animalsOnIsland.entrySet()) {
            boolean result = false;
            List<List<Animal>> animals = entry.getValue();
            int random = (int) (Math.random() * (animals.size() - 1));
            List<Animal> animalsToEat = animals.get(random);
            List<Animal> animalsForEat = animals.get(random + 1);
            Position position = entry.getKey();
            if (animalsToEat.size() != 0 && animalsForEat.size() != 0) {
                Animal animal = animalsForEat.get(0);
                if (animal.isHungry() && animal.isAlive() && ((animalsToEat.get(0).getClass())).isAnnotationPresent(Carnivore.class)) {
                    if (animal.getAnimalCharacteristics().getChanceToEat().get(animalsForEat.get(0).getAnimalCharacteristics().getName()) != null) {
                        int chance = animal.getAnimalCharacteristics().getChanceToEat().get(animalsForEat.get(0).getAnimalCharacteristics().getName());
                        int probability = (int) (Math.random() * 100 + 1);
                        if (probability < chance) {
                            animal.eat(true);
                            animalsForEat.remove(0);
                            diedAnimalsCount++;
                        } else {
                            animal.eat(false);
                        }
                    }
                }
                if ((animalsToEat.get(0).getClass()).isAnnotationPresent(Herbivore.class)) {
                    List<Herb> herbsOnPosition = herbOnIsland.get(position);
                    /*
                    Для травоядных животных примем вероятность "покушать" 50/50
                     */
                    if (herbsOnPosition.size() != 0) {
                        int probability = (int) (Math.random() * 100 + 1);
                        if (probability < 50) {
                            animal.eat(true);
                            System.out.println("******* Покушал хорошо" + animal.getClass() + "- animal.isAlive()" + "saturation - " + animal.getSaturation());
                            herbsOnPosition.remove(0);
                        } else {
                            animal.eat(false);
                            System.out.println("******* Покушал хорошо" + animal.getClass());
                        }
                    }
                }
            }
        }
    }

    /*
    Метод проверки кол-ва мертвых животных на острове
    */
    public void toDie() {
        for (Map.Entry<Position, List<List<Animal>>> entry : animalsOnIsland.entrySet()) {
            List<List<Animal>> animals = entry.getValue();
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).size() > 0) {
                    Iterator<Animal> animalIterator = animals.get(i).iterator();
                    while (animalIterator.hasNext()) {
                        Animal animal = animalIterator.next();
                        animal.checkDeth();
                        if (!animal.isAlive()) {
                            diedAnimalsCount++;
                            animalIterator.remove();
                        }
                    }
                }
            }
        }
    }

    /*
    Метод для размножения животных
    Если кол-во животных одного вида выбранных произвольно будет больше 1го,
    то с вероятностью 50% будет рождаться потомок
    */
    public void toOverbreed() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (Map.Entry<Position, List<List<Animal>>> entry : animalsOnIsland.entrySet()) {
            boolean result = false;
            List<List<Animal>> animals = entry.getValue();
            int random = (int) (Math.random() * (animals.size() - 1));
            List<Animal> animalToOverBreed = animals.get(random);
            Position position = entry.getKey();
            if (animalToOverBreed.size() >= 2) {
                Animal animalMother = animalToOverBreed.get(0);
                int probability = (int) (Math.random() * 100 + 1);
                if (probability < 50) {
                    Class clazz = Class.forName(animalMother.getClass().getName());
                    Animal animalChild = (Animal) clazz.getConstructor().newInstance();
                    System.out.println("Ребенок родился!");
                    animalToOverBreed.add(animalChild);
                }
            }
        }
    }

    /*
    Так как животные меняют позиции, то при каждом движении собираем новую мапу положения животных на острове
    для упрощения пока животные ходят толпой
    далее заменяем Мапу animalsOnIsland на собранную с новыми положениями животных
    */
    public Map<Position, List<List<Animal>>> toMove() {
        Map<Position, List<List<Animal>>> newAnimalOnIsland = new HashMap<>();
        for (Map.Entry<Position, List<List<Animal>>> entry : animalsOnIsland.entrySet()) {
            Directions direction = chooseDirection();
            Position newPosition = entry.getKey();
            List<List<Animal>> animalsOnPosition = entry.getValue();
            for (int i = 0; i < animalsOnPosition.size(); i++) {
                for (Animal animal : animalsOnPosition.get(i)) {
                    System.out.println("пошли - " + direction.getDescription());
                    newPosition = getNewPosition(direction, animal.getPosition(), animal.getAnimalCharacteristics().getSpeed());
                    animal.move(newPosition);
                }
            }
            newAnimalOnIsland.put(newPosition, animalsOnPosition);
        }
        animalsOnIsland = newAnimalOnIsland;
        return animalsOnIsland;
    }

    //рандомно выбираем направление движения
    private Directions chooseDirection() {
        // по умолчанию будет использоваться направление движения "вперед"
        int randomDirection = 1;
        List<Directions> countDirections = Arrays.stream(Directions.values()).toList();
        randomDirection = (int) (Math.random() * countDirections.size());
        return countDirections.get(randomDirection);
    }

    private Position getNewPosition(Directions direction, Position position, int speed) {
        Position newPosition = position;
        switch (direction) {
            case AHEAD -> {
                if ((position.getY() + speed) < island.getWidth()) {
                    newPosition.setY(position.getY() + speed);
                    System.out.println("новая позиция по координате У - " + newPosition.getY());
                }
            }
            case BACK -> {
                if ((position.getY() - speed) > 0) {
                    newPosition.setY(position.getY() - speed);
                    System.out.println("новая позиция по координате У - " + newPosition.getY());
                }
            }
            case RIGHT -> {
                if ((position.getX() + speed) < island.getLength()) {
                    newPosition.setY(position.getX() + speed);
                    System.out.println("новая позиция по координате X - " + newPosition.getY());
                }
            }
            case LEFT -> {
                if ((position.getX() - speed) > 0) {
                    newPosition.setX(position.getX() - speed);
                    System.out.println("новая позиция по координате X - " + newPosition.getY());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        return newPosition;
    }
}
