package ru.javarush.island.application.general;

import ru.javarush.island.application.annotations.Carnivore;
import ru.javarush.island.application.annotations.Herbivore;
import ru.javarush.island.application.enums.AnimalTypes;
import ru.javarush.island.application.islandDescription.Island;
import ru.javarush.island.application.residents.Animal;
import ru.javarush.island.application.residents.Position;
import ru.javarush.island.application.residents.carnivoreAnimals.Wolf;
import ru.javarush.island.application.residents.carnivoreAnimals.Bear;
import ru.javarush.island.application.residents.carnivoreAnimals.Boa;
import ru.javarush.island.application.residents.carnivoreAnimals.Boar;
import ru.javarush.island.application.residents.carnivoreAnimals.Eagle;
import ru.javarush.island.application.residents.carnivoreAnimals.Fox;
import ru.javarush.island.application.residents.herbivoreAnimals.Buffalo;
import ru.javarush.island.application.residents.herbivoreAnimals.CaterPillar;
import ru.javarush.island.application.residents.herbivoreAnimals.Deer;
import ru.javarush.island.application.residents.herbivoreAnimals.Duck;
import ru.javarush.island.application.residents.herbivoreAnimals.Horse;
import ru.javarush.island.application.residents.herbivoreAnimals.Mouse;
import ru.javarush.island.application.residents.herbivoreAnimals.Nanny;
import ru.javarush.island.application.residents.herbivoreAnimals.Rabbit;
import ru.javarush.island.application.residents.herbivoreAnimals.Sheep;
import ru.javarush.island.application.residents.plant.Herb;
import ru.javarush.island.application.resources.AnimalCharacteristics;
import ru.javarush.island.application.resources.ApplicationSettings;
import ru.javarush.island.application.resources.PlantCharacteristics;
import ru.javarush.island.application.resources.Settings;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class GameManagement {
    private String fileNameSettings = "C://Users//username//IdeaProjects//Project2-Island//src//java//ru//javarush//island//application//resources//settings.json";
    private Settings settings;
    private Island island;
    private boolean stop;

    public Position[][] getIslandPositions() {
        return islandPositions;
    }

    public void setIslandPositions(Position[][] islandPositions) {
        this.islandPositions = islandPositions;
    }

    public int getDiedAnimalsCount() {
        return diedAnimalsCount;
    }

    public void setDiedAnimalsCount(int diedAnimalsCount) {
        this.diedAnimalsCount = diedAnimalsCount;
    }

    public Map<Position, List<List<Animal>>> getAnimalsOnIsland() {
        return animalsOnIsland;
    }

    public void setAnimalsOnIsland(Map<Position, List<List<Animal>>> animalsOnIsland) {
        this.animalsOnIsland = animalsOnIsland;
    }

    public Map<Position, List<Herb>> getHerbOnIsland() {
        return herbOnIsland;
    }

    public void setHerbOnIsland(Map<Position, List<Herb>> herbOnIsland) {
        this.herbOnIsland = herbOnIsland;
    }

    private Position[][] islandPositions;
    private Integer diedAnimalsCount = 0;

    /*
    Пользователь будет задавать кол-во тактов игры
     */
    private int numberOfTasks = 20;

    /*
    Хранение всех животных на острове в каждой позиции
     */
    private Map<Position, List<List<Animal>>> animalsOnIsland = new HashMap<>();

    /*
    Хранение всех животных на острове в одной позиции
     */
    private List<List<Animal>> animalsOnPosition = new ArrayList<>();

    /*
    Хранение всех растений на острове в одной позиции
     */
    Map<Position, List<Herb>> herbOnIsland = new HashMap<>();

    public GameManagement(String fileNameSettings, int numberOfTasks) {
        this.fileNameSettings = fileNameSettings;
        this.numberOfTasks = numberOfTasks;
    }

    public GameManagement() {
    }

    public Settings initSettings() {
        Settings settings = initSettings(fileNameSettings);
        return settings;
    }

//    private String checkFile(String fileNameSettings) {
//        URL url = getClass().getClassLoader().getResource(fileNameSettings);
//        if (url == null) {
//            throw new IllegalArgumentException("file settings not found!");
//        } else return url.getFile();
//    }

    public void startGame() {
//        String fileSettings=checkFile(fileNameSettings);
        settings = initSettings();
        createIsland();
        initResidentsOnIsland();
        printIslandInfo();
        printStatistic(animalsOnIsland, herbOnIsland, diedAnimalsCount);
    }

    public void play() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AnimalLifeCycle animalLifeCycle = new AnimalLifeCycle(animalsOnIsland, herbOnIsland, island, diedAnimalsCount);
        while (numberOfTasks > 0) {
            doOption(animalLifeCycle);
            printStatistic(animalsOnIsland, herbOnIsland, diedAnimalsCount);
            numberOfTasks--;
        }
    }

    public void stopGame() {
        //TODO: Придумать критериии выхода из игры.
        System.out.println("Игра завершена");
    }

    public void pauseGame() {
        stop = true;
    }

    public void unPauseGame() {
        stop = false;
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

    /*
    помещаем животных в каждую ячейку на остров
     */
    public void initResidentsOnIsland() {
        for (int x = 0; x < islandPositions.length; x++) {
            for (int y = 0; y < islandPositions[0].length; y++) {
                Position position = new Position(x, y);
                islandPositions[x][y] = position;
                for (AnimalCharacteristics animalCharacteristics : settings.getAnimalCharacteristics()) {
                    //содержит кол-во животных одного вида в точке с координатами (x,y)
                    List<Animal> animalsOfTypeOnPosition = createAnimalsOfTypeOnPosition(animalCharacteristics, position);
                    animalsOnPosition.add(animalsOfTypeOnPosition);
                }
                animalsOnIsland.put(position, animalsOnPosition);

                List<Herb> herbOnPosition = new ArrayList<>();
                herbOnPosition = createHerbOnPosition(settings.getPlant(), position);
                herbOnIsland.put(position, herbOnPosition);
            }
        }
    }

    private List<Animal> createAnimalsOfTypeOnPosition(AnimalCharacteristics animalCharacteristic, Position position) {
        List<Animal> animalList = new ArrayList<>();
        int randomValue = (int) (Math.random() * animalCharacteristic.getMaxCountOnThisAnimal() + 1);
        for (int count = 0; count <= randomValue; count++) {
            var animalsOfType = createAnimalsOfType(animalCharacteristic.getName(), position);
            animalsOfType.setAnimalCharacteristics(animalCharacteristic);
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
        List<Herb> herbs = new ArrayList<>();
        int random = (int) (Math.random() * plantCharacteristics.getMaxCountOnThisAnimal() + 1);
        for (int count = 0; count <= random; count++) {
            Herb herb = new Herb(position);
            herbs.add(herb);
        }
        return herbs;
    }

    /*
    отображаем произвольный emoji из животных на позиции
     */
    public void printIslandInfo() {
        for (int x = 0; x < islandPositions.length; x++) {
            for (int y = 0; y < islandPositions[0].length; y++) {
                int randomTypeAnimal = (int) (Math.random() * animalsOnPosition.size());
                String emoji = animalsOnPosition
                        .stream()
                        .toList()
                        .get(randomTypeAnimal)
                        .get(0)
                        .getAnimalCharacteristics()
                        .getEmoji();
                System.out.print("[" + emoji + "]");
            }
            System.out.print("\n");
        }
    }

    /*
    Так как в каждом списке сохранены животные одного вида, то
    Берем из каждого списка первое животное и проверяем, к какому классу оно относится.
    Далее собираем общую статистику по всему острову, суммируя размеры списков:
    - кол-во хищников
    - кол-во травоядных
    - кол-во растений
     */
    private void printStatistic(Map<Position, List<List<Animal>>> animalsOnIsland, Map<Position, List<Herb>> herbOnIsland, int diedAnimalsCount) {
        int carnivoreCount = 0;
        int herbivoreCount = 0;
        int plantCount = 0;
        for (Map.Entry<Position, List<List<Animal>>> entry : animalsOnIsland.entrySet()) {
            if (entry.getValue() != null) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    List<Animal> animals = entry.getValue().get(i);
                    if (animals.size() != 0) {
                        Animal animal = animals.get(0);
                        if ((animal.getClass()).isAnnotationPresent(Carnivore.class)) {
                            carnivoreCount = carnivoreCount + animals.size();
                        }
                        if (animal.getClass().isAnnotationPresent(Herbivore.class)) {
                            herbivoreCount = herbivoreCount + animals.size();
                        }
                    }
                }
            }
        }

        /*
        Определяем кол-во растений на острове
        Для этого суммируем все растения в каждой позиции
         */
        for (Map.Entry<Position, List<Herb>> entry : herbOnIsland.entrySet()) {
            if (entry.getValue() != null) {
                plantCount = plantCount + entry.getValue().size();
            }
        }
        System.out.println("Статистика:\n" +
                "- количество хищных животных на острове: " + carnivoreCount + "\n" +
                "- количество травоядных животных на острове: " + herbivoreCount + "\n" +
                "- количество умерших животных на острове: " + diedAnimalsCount + "\n" +
                "- количество растений на острове: " + plantCount);
    }

    public void doOption(AnimalLifeCycle animalLifeCycle) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int numOption = randomNumberOfOptionOnPosition();
//        int numOption = 1;
        switch (numOption) {
            case 1 -> {
                animalLifeCycle.toEat();
//                animalLifeCycle.toDie();
//                animalLifeCycle.toOverbreed();
                animalLifeCycle.toMove();
            }
            case 2 -> animalLifeCycle.toDie();
            case 3 -> animalLifeCycle.toMove();
//            case 4 -> animalLifeCycle.toOverbreed();
            default -> throw new IllegalArgumentException("Передан неизвестный номер операции - "
                    + numOption + "\n");

        }
    }

    public int randomNumberOfOptionOnPosition() {
        return (int) (Math.random() * 3 + 1);
    }
}