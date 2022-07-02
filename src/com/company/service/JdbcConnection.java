package com.company.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnection {
    private static final Logger LOGGER =
            Logger.getLogger(JdbcConnection.class.getName());
    private static Optional connection = Optional.empty();
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/vetclinic";
    static final String USER = "postgres";
    static final String PASS = "postgres";



    public static Optional getConnection() {
        if (connection.isEmpty()) {
            try {
                connection = Optional.ofNullable(
                        DriverManager.getConnection(DB_URL, USER, PASS));
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        return connection;
    }
}
