package ru.javarush.island.application.annotations;

import ru.javarush.island.application.enums.AnimalTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnimalInfo {
    AnimalTypes nameType();
    String description();
}
