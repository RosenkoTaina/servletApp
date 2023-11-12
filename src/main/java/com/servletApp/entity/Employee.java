package com.servletApp.entity;


public record Employee(int id, String name, String email, String country, String hashedPassword, String role) {
    public Employee(String name, String email, String country, String hashedPassword, String role) {
        this(0, name, email, country, hashedPassword, role);
    }
}
