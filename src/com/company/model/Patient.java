package com.company.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {


    private int id;
    private String fio;
    private Timestamp registerTime;

    public Patient(int id) {
        this.id = id;
    }

    public Patient(String fio) {
        this.fio=fio;
    }

    public Patient(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public Patient(int id, String fio, Timestamp registerTime) {
        this.id = id;
        this.fio = fio;
        this.registerTime = registerTime;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return id+", "+fio+", "+dateFormat.format(registerTime);
    }

}