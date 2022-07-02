package com.company.dao;

import com.company.service.JdbcConnection;
import com.company.model.User;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements DAO<User, Integer> {
    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    private final Optional<Connection> connection;

    public UserDAO() {
        this.connection = JdbcConnection.getConnection();
    }


    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> add(User user) {
        String message = "The patient to be added should not be null";
        User nonNullUser = Objects.requireNonNull(user, message);
        String sql = "INSERT INTO "
                + "user(login, password) "
                + "VALUES(?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nonNullUser.getLogin());
                statement.setString(2, nonNullUser.getPassword());

                int numberOfInsertedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public Optional<User> find(String login, String password) {

        return connection.flatMap(conn -> {
            Optional<User> user = Optional.empty();
            String sql = "Select * FROM \"user\" where login=? and password=? ";

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    user = Optional.of(new User(id, login, password));

                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return user;
        });
    }
}
