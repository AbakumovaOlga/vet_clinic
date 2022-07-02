package com.company.model;

public class Doctor {
    private int id;
    private String fio;

    public Doctor(String fio) {
        this.fio = fio;
    }

    public Doctor(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
