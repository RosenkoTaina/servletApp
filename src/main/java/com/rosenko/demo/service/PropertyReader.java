package com.rosenko.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public Properties getProperties(String propertyFile) {
        Properties properties = new Properties();

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(propertyFile)) {
            if (is != null) {
                properties.load(is);
            } else {
                properties.setProperty("db.url", "jdbc:postgresql://localhost:5432/Employee");
                properties.setProperty("db.user", "postgres");
                properties.setProperty("db.pass", "admin");
                // не хотіло працювати з файликом проперті в WildFly
            }
        } catch (IOException exception) {
            System.err.println("Exception while loading properties: " + exception.getMessage());
        }
        return properties;
    }
}



