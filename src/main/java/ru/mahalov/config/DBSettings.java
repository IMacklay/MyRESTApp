package ru.mahalov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DBSettings {

    @Value("${myrestapp2.jdbcDriver}")
    String driverDBPath;
    @Value("${myrestapp2.jdbcString}")
    String dbStringConnection;
    @Value("${myrestapp2.jdbcUser}")
    String dbUserName;
    @Value("${myrestapp2.jdbcPassword}")
    String dbPassword;

    public String getDriverDBPath() {
        return driverDBPath;
    }

    public String getDbStringConnection() {
        return dbStringConnection;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
