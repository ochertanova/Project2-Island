package ru.javarush.island;

import ru.javarush.island.application.general.GameManagement;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        GameManagement gameManagement = new GameManagement();
        gameManagement.initSettings();
        gameManagement.startGame();
    }

}
