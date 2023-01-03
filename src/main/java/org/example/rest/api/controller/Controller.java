package org.example.rest.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Controller {
    private final String PROP_PATH = "src/main/resources/settings.properties";
    String baseUrl;

    Properties getProperties(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(PROP_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
