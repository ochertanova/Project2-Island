package ru.javarush.island.application.enums;


import ru.javarush.island.application.residents.carnivoreAnimals.*;
import ru.javarush.island.application.residents.herbivoreAnimals.*;

public enum AnimalTypes {
    WOLF("wolf", "волк"),
    FOX("fox", "лиса"),
    BOA("boa", "удав"),
    BEAR("bear", "медведь"),
    EAGLE("eagle", "орел"),
    HORSE("horse", "лошадь"),
    DEER("deer", "олень"),
    RABBIT("rabbit", "кролик"),
    NANNY("nanny", "коза"),
    SHEEP("sheep", "овца"),
    BOAR("boar", "кабан"),
    DUCK("duck", "утка"),
    MOUSE("mouse", "мышь"),
    CATERPILLAR("caterpillar", "гусеница"),
    BUFFALO("buffalo", "буйвол");

    private final String nameType;
    private final String description;

    private AnimalTypes(String nameType, String description) {
        this.nameType = nameType;
        this.description = description;
    }

    public String getNameType() {
        return nameType;
    }

    public String getDescription() {
        return description;
    }
}
