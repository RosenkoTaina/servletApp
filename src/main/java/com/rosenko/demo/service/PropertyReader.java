package com.rosenko.demo.service;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public Properties getProperties(String propertyFile) {

        Properties properties = new Properties();
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(propertyFile)) {
            properties.load(is);
        } catch (Exception exception) {
            System.err.println("No such property file: '" + propertyFile + "'");
        }

        return properties;
    }

}

