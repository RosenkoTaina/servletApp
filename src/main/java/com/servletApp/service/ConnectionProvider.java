package com.servletApp.service;

import com.servletApp.enums.DataSourceFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionProvider {
    private static final String DRIVER = DataSourceFactory.DRIVER.getValue();
    private static final String CONNECTION_URL = DataSourceFactory.CONNECTION_URL.getValue();
    private static final String USERNAME = DataSourceFactory.USERNAME.getValue();
    private static final String PASSWORD = DataSourceFactory.PASSWORD.getValue();

    private static Connection con = null;

    static {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }
}
