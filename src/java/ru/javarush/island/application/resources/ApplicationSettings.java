package ru.javarush.island.application.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ApplicationSettings {
    private Settings settings;
    private String fileNameSettings;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ApplicationSettings(String fileNameSettings) {
        this.fileNameSettings = fileNameSettings;
    }

    //TODO: это на всякий случай пока оставила
    // JsonNode jsonNode = objectMapper.readTree(Files.newBufferedReader(Path.of(fileNameSettings)));

    public Settings getSettings(String fileNameSettings)  {
        try {
            settings = objectMapper.readValue(Files.newBufferedReader(Path.of(fileNameSettings)), Settings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public Settings getSettings() {
        settings = getSettings(fileNameSettings);
        return settings;
    }

    public List<AnimalCharacteristics> getAnimalCharacteristics() throws IOException {
        settings = getSettings();
        return settings.getAnimalCharacteristics();
    }

    public AnimalCharacteristics getAnimalCharacteristicsForName(String name) throws IOException {
//  TODO: если не будет найдено по имени настройки, то будет возвращен null - так нельзя!!!Переделать
        AnimalCharacteristics result = null;
        settings = getSettings();
        for (AnimalCharacteristics animalCharacteristicSettings : settings.getAnimalCharacteristics()) {
            if (animalCharacteristicSettings.getName().equals(name)) {
                result = animalCharacteristicSettings;
                break;
            }
        }
        return result;
    }
}
