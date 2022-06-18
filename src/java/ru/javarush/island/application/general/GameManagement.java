package ru.javarush.island.application.general;

import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.islandDescription.Island;
import ru.javarush.island.application.residents.Animal;
import ru.javarush.island.application.residents.Position;
import ru.javarush.island.application.residents.carnivoreAnimals.*;
import ru.javarush.island.application.residents.herbivoreAnimals.*;
import ru.javarush.island.application.residents.plant.Herb;
import ru.javarush.island.application.resources.AnimalCharacteristics;
import ru.javarush.island.application.resources.ApplicationSettings;
import ru.javarush.island.application.resources.PlantCharacteristics;
import ru.javarush.island.application.resources.Settings;

import java.util.*;

public class GameManagement {
    private String fileNameSettings = "C://Users//username//IdeaProjects//Project2-Island//src//java//ru//javarush//island//settings.json";
    private Settings settings;
    private Island island;
    private Position[][] islandPositions;
    // Хранение всех животных на острове в каждой позиции
    private Map<Position, Map<AnimalCharacteristics, List<Animal>>> animalsOnIsland = new HashMap<>();
    // Хранение всех животных на острове в одной позиции
    private Map<AnimalCharacteristics, List<Animal>> animalsOnPositionMap = new HashMap<>();

    public GameManagement(String fileNameSettings) {
        this.fileNameSettings = fileNameSettings;
    }

    public GameManagement() {
    }

    public Settings initSettings() {
        Settings settings = initSettings(fileNameSettings);
        return settings;
    }

    public void startGame() {
        settings = initSettings();
        createIsland();
        initAnimalsOnIsland();
        printIslandInfo();
    }

    public void stopGame() {
    }

    public void pauseGame() {
    }

    public Settings initSettings(String fileNameSettings) {
        ApplicationSettings settings = new ApplicationSettings(fileNameSettings);
        // useInterface();TODO:не реализовано
        return settings.getSettings();
    }

    public Position[][] createIsland() {
        island = new Island(settings.getWidth(), settings.getLength());
        islandPositions = island.createIsland();
        return islandPositions;
    }

    //помещаем животных в каждую ячейку на остров
    public Map<Position, Map<AnimalCharacteristics, List<Animal>>> initAnimalsOnIsland() {
        for (int x = 0; x < islandPositions.length; x++) {
            for (int y = 0; y < islandPositions[0].length; y++) {
                Position position = new Position(x, y);
                islandPositions[x][y] = position;
                for (AnimalCharacteristics animalCharacteristics : settings.getAnimalCharacteristics()) {
                    //содержит кол-во животных одного вида в точке с координатами (x,y)
                    List<Animal> animalsOfTypeOnPosition = createAnimalsOfTypeOnPosition(animalCharacteristics, position);
                    animalsOnPositionMap.put(animalCharacteristics, animalsOfTypeOnPosition);
                }
                animalsOnIsland.put(position, animalsOnPositionMap);
            }
        }
        return animalsOnIsland;
    }

    private List<Animal> createAnimalsOfTypeOnPosition(AnimalCharacteristics animalCharacteristic, Position position) {
        List<Animal> animalList = new ArrayList<>();
        int randomValue = (int) (Math.random() * animalCharacteristic.getMaxCountOnThisAnimal() + 1);
        for (int count = 0; count <= randomValue; count++) {
            var animalsOfType = createAnimalsOfType(animalCharacteristic.getName(), position);
            animalList.add(animalsOfType);
        }
        return animalList;
    }

    // TODO: пробовала через рефлексию+aннотации, пока не получилось. В итоге потеряла много времени
    //  Пока решила оставить так. Если останется время, попробую еще переделать
    private Animal createAnimalsOfType(AnimalTypes name, Position position) {
        var animal =
                switch (name) {
                    case WOLF -> new Wolf(position);
                    case BEAR -> new Bear(position);
                    case FOX -> new Fox(position);
                    case BOA -> new Boa(position);
                    case HORSE -> new Horse(position);
                    case DEER -> new Deer(position);
                    case RABBIT -> new Rabbit(position);
                    case NANNY -> new Nanny(position);
                    case SHEEP -> new Sheep(position);
                    case BOAR -> new Boar(position);
                    case EAGLE -> new Eagle(position);
                    case DUCK -> new Duck(position);
                    case MOUSE -> new Mouse(position);
                    case CATERPILLAR -> new CaterPillar(position);
                    case BUFFALO -> new Buffalo(position);
                    default -> throw new IllegalStateException("Unexpected value: " + name);
                };
        return animal;
    }

    public Map<Position, List<Herb>> initHerbOnIsland() {
        Map<Position, List<Herb>> herbOnIsland = new HashMap<>();
        for (int x = 0; x < islandPositions.length; x++) {
            for (int y = 0; y < islandPositions[0].length; y++) {
                Position position = new Position(x, y);
                islandPositions[x][y] = position;
                List<Herb> herbOnPosition = new ArrayList<>();
                herbOnPosition = createHerbOnPosition(settings.getPlant(), position);
                herbOnIsland.put(position, herbOnPosition);
            }
        }
        return herbOnIsland;
    }

    private List<Herb> createHerbOnPosition(PlantCharacteristics plantCharacteristics, Position position) {
        List<Herb> herbList = new ArrayList<>();
        Random random = new Random(plantCharacteristics.getMaxCountOnThisAnimal());
        for (int count = 0; count <= random.nextInt(); count++) {
            Herb herb = new Herb(position);
            herbList.add(herb);
        }
        return herbList;
    }

    // отображаем произвольный emoji из животных на позиции
    public void printIslandInfo() {
        for (int x = 0; x < islandPositions.length; x++) {
            for (int y = 0; y < islandPositions[0].length; y++) {
                int random = (int) (Math.random() * animalsOnPositionMap.keySet().size());
                String emoji = animalsOnPositionMap
                        .keySet()
                        .stream()
                        .toList()
                        .get(random)
                        .getEmoji();
                System.out.print("[" + emoji + "]");
            }
            System.out.print("\n");
        }
    }

    //TODO: реализовать!
    private void useInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("начинаем игру. Хотите изменить настройки острова?\\n" + "Введите - да\\нет");
        String answer = scanner.nextLine();
        if (answer.equals("Да")) {
            updateSettings();
        }
    }

    //TODO:реализовать!
    private void updateSettings() {

    }
}

