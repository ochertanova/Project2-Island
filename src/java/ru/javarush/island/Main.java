package ru.javarush.island;

import ru.javarush.island.application.general.GameManagement;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

//      GameManagement gameManagement = new GameManagement("settings.json", 10);
        GameManagement gameManagement = new GameManagement();
        gameManagement.startGame();
        gameManagement.play();
    }
}
