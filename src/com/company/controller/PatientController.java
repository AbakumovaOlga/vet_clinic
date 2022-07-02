package com.company.controller;

import com.company.dao.DoctorDAO;
import com.company.dao.PatientDAO;
import com.company.model.Patient;

import java.util.Collection;
import java.util.Optional;

public class PatientController {
    private static final PatientDAO patientDAO=new PatientDAO();
    private static final PatientController SINGLETON = new PatientController();

    private PatientController() {

    }
    public static PatientController getPatientController() {
        return SINGLETON;
    }

    public Optional<Integer> add(String fio) {
        return patientDAO.add(new Patient(fio));
    }

    public void update(String idStr, String fio) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        patientDAO.update(new Patient(id, fio));

    }

    public Collection<Patient> getAll() {
        Collection<Patient> patients = patientDAO.getAll();
        for (Patient patient : patients
        ) {
            System.out.println(patient);
        }
        return patients;
    }
    public void delete(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        patientDAO.delete(new Patient(id));

    }
}
