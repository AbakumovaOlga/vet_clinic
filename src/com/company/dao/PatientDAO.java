package com.company.dao;

import com.company.service.JdbcConnection;
import com.company.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO implements DAO<Patient, Integer> {

    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    private final Optional<Connection> connection;

    public PatientDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Patient> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Patient> patient = Optional.empty();
            String sql = "SELECT * FROM customer WHERE customer_id = " + id;

            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    String fio = resultSet.getString("fio");

                    patient = Optional.of(new Patient(id, fio));

                    LOGGER.log(Level.INFO, "Found {0} in database", patient.get());
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return patient;
        });
    }

    @Override
    public Collection<Patient> getAll() {
        Collection<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String fio = resultSet.getString("fio");
                    Timestamp registerTime = resultSet.getTimestamp("registerTime");

                    Patient patient = new Patient(id, fio, registerTime);

                    patients.add(patient);

                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return patients;
    }

    @Override
    public Optional<Integer> add(Patient patient) {
        String message = "The patient to be added should not be null";
        Patient nonNullPatient = Objects.requireNonNull(patient, message);
        String sql = "INSERT INTO "
                + "patient(fio, registerTime) "
                + "VALUES(?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nonNullPatient.getFio());

                statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

                int numberOfInsertedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Patient patient) {
        String message = "The customer to be updated should not be null";
        Patient nonNullPatient = Objects.requireNonNull(patient, message);

        String sql = "UPDATE patient "
                + "SET "
                + "fio = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullPatient.getFio());
                statement.setInt(2, nonNullPatient.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(Patient patient) {
        String message = "The customer to be deleted should not be null";
        Patient nonNullPatient = Objects.requireNonNull(patient, message);
        String sql = "DELETE FROM patient WHERE id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullPatient.getId());

                int numberOfDeletedRows = statement.executeUpdate();


            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
