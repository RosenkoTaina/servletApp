package com.rosenko.demo.enums;

import com.rosenko.demo.service.PropertyReader;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceFactory {
    private static DataSource dataSource;

    private DataSourceFactory() {}

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    private static DataSource createDataSource() {
        PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
        Properties properties = new PropertyReader().getProperties("app.properties");
        postgresDataSource.setURL(properties.getProperty("db.url"));
        postgresDataSource.setUser(properties.getProperty("db.user"));
        postgresDataSource.setPassword(properties.getProperty("db.pass"));
        return postgresDataSource;
    }
}