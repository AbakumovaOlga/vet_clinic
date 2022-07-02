package com.company.dao;

import com.company.model.Reception;
import com.company.model.ReceptionStatus;
import com.company.service.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceptionDAO implements DAO<Reception, Integer> {

    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    private final Optional<Connection> connection;

    public ReceptionDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Reception> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Reception> getAll() {
        return null;
    }

    public Collection<Reception> getAll(String patentFio) {


        Collection<Reception> receptions = new ArrayList<>();
        String sql = "select * from reception r join patient p on r.patientid =p.id where p.fio = ? ";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, patentFio);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int patientId = resultSet.getInt("patientId");
                    Timestamp registerTime = resultSet.getTimestamp("registerTime");
                    int doctorId=resultSet.getInt("doctorId");
                    ReceptionStatus status=ReceptionStatus.valueOf(resultSet.getString("status"));
                    Reception patient = new Reception(registerTime, patientId, doctorId,status);

                    receptions.add(patient);

                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });


        return receptions;
    }

    @Override
    public Optional<Integer> add(Reception reception) {
        String message = "The reception to be added should not be null";
        Reception nonNullReception = Objects.requireNonNull(reception, message);
        String sql = "INSERT INTO "
                + "reception(receptionTime, patientId, doctorId, status) "
                + "VALUES(?,?,?,?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setTimestamp(1, nonNullReception.getReceptionTime());
                statement.setInt(2, nonNullReception.getPatientId());
                statement.setInt(3, nonNullReception.getDoctorId());
                statement.setString(4, nonNullReception.getStatus().toString());

                int numberOfInsertedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            return generatedId;
        });
    }

    @Override
    public void update(Reception reception) {
        String message = "The reception to be updated should not be null";
        Reception nonNullReception = Objects.requireNonNull(reception, message);

        String sql = "UPDATE reception "
                + "SET "
                + "status = ? "
                + "WHERE "
                + "id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullReception.getStatus().toString());
                statement.setInt(2, nonNullReception.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(Reception reception) {

    }
}
