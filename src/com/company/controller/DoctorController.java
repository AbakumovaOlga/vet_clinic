package com.company.controller;

import com.company.dao.DoctorDAO;
import com.company.model.Doctor;

import java.util.Optional;

public class DoctorController {
    private static final DoctorDAO doctorDAO=new DoctorDAO();
    private static final DoctorController SINGLETON = new DoctorController();

    private DoctorController() {

    }
    public static DoctorController getDoctorController() {
        return SINGLETON;
    }

    public Optional<Integer> add(String fio) {
        return doctorDAO.add(new Doctor(fio));
    }



}
