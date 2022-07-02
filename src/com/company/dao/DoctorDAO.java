package com.company.dao;

import com.company.service.JdbcConnection;
import com.company.model.Doctor;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO implements DAO<Doctor, Integer>{

    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    private final Optional<Connection> connection;

    public DoctorDAO() {
        this.connection = JdbcConnection.getConnection();
    }
    @Override
    public Optional<Doctor> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Doctor> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> add(Doctor doctor) {
        String message = "The patient to be added should not be null";
        Doctor nonNullDoctor = Objects.requireNonNull(doctor, message);
        String sql = "INSERT INTO "
                + "doctor(fio) "
                + "VALUES(?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nonNullDoctor.getFio());


                int numberOfInsertedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public void delete(Doctor doctor) {

    }
}
