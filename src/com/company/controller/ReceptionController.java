package com.company.controller;

import com.company.dao.PatientDAO;
import com.company.dao.ReceptionDAO;
import com.company.model.Patient;
import com.company.model.Reception;
import com.company.model.ReceptionStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class ReceptionController {
    private static final ReceptionDAO receptionDAO=new ReceptionDAO();
    private static final ReceptionController SINGLETON = new ReceptionController();

    private ReceptionController() {

    }
    public static ReceptionController getReceptionController() {
        return SINGLETON;
    }

    public void add(String doctorIdStr, String patientIdStr, String receptionTimeStr) {
        int doctorId;
        int patientId;
        Timestamp receptionTime;
        try {
            doctorId = Integer.parseInt(doctorIdStr);
            patientId = Integer.parseInt(patientIdStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //2000-10-10 00:00:00
            receptionTime = Timestamp.valueOf(receptionTimeStr);
        } catch (NumberFormatException e) {
            System.out.println("input data not correct");
            return;
        }
        catch (Exception e){
            System.out.println("smt wrong");
            return;
        }
        receptionDAO.add(new Reception(receptionTime, patientId, doctorId, ReceptionStatus.NEW));
    }

    public void inProgress(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        receptionDAO.update(new Reception(id, ReceptionStatus.IN_PROGRESS));
    }

    public void cancel(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        receptionDAO.update(new Reception(id, ReceptionStatus.CANCEL));
    }

    public void awaitingPayment(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        receptionDAO.update(new Reception(id, ReceptionStatus.AWAITING_PAYMENT));
    }

    public void finished(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("id is not int");
            return;
        }
        receptionDAO.update(new Reception(id, ReceptionStatus.FINISHED));
    }

    public Collection<Reception> getAll(String patentFio) {

        Collection<Reception> receptions = receptionDAO.getAll(patentFio);
        for (Reception reception : receptions
        ) {
            System.out.println(reception);
        }
        return receptions;
    }
}
