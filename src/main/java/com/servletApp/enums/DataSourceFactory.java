package com.servletApp.enums;

public enum DataSourceFactory {
    DRIVER("org.postgresql.Driver"),
    CONNECTION_URL("jdbc:postgresql://localhost:5432/Employee"),
    USERNAME("postgres"),
    PASSWORD("admin");

    private final String value;

    DataSourceFactory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
