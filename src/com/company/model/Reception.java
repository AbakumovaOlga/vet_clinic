package com.company.model;

import java.sql.Timestamp;

public class Reception {

    private int id;
    private Timestamp receptionTime;
    private int patientId;
    private int doctorId;

    @Override
    public String toString() {
        return "Reception{" +
                "id=" + id +
                ", receptionTime=" + receptionTime +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", status=" + status +
                '}';
    }

    public Reception(Timestamp receptionTime, int patientId, int doctorId, ReceptionStatus status) {
        this.receptionTime = receptionTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
    }

    public Reception(int id, ReceptionStatus status) {
        this.id = id;
        this.status = status;
    }

    public Reception(int patientIdid) {
        this.patientId = patientIdid;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public ReceptionStatus getStatus() {
        return status;
    }

    public void setStatus(ReceptionStatus status) {
        this.status = status;
    }

    private ReceptionStatus status;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(Timestamp receptionTime) {
        this.receptionTime = receptionTime;
    }
}
