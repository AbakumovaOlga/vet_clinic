package com.company.service;

import com.company.controller.DoctorController;
import com.company.controller.PatientController;
import com.company.controller.ReceptionController;

import java.util.Scanner;

public class CommandReader {

    PatientController patientController = PatientController.getPatientController();
    DoctorController doctorController = DoctorController.getDoctorController();
    ReceptionController receptionController = ReceptionController.getReceptionController();

    Scanner in;

    public CommandReader(Scanner in) {
        this.in = in;
    }

    public int getCommand(ComandType comandType) {
        switch (comandType) {
            case Help: {
//                System.out.println("PatientAdd - добавить пациента");
//                System.out.println("DoctorAdd - добавить врача");
//                System.out.println("ReceptionAdd - добавить прием");
//                System.out.println("ReceptionInProgress - прием в процессе");
//                System.out.println("ReceptionCancel - прием отменен");
//                System.out.println("ReceptionAwaitingPayment - прием ожидает оплаты");
//                System.out.println("ReceptionFinished - прием завершен");
//                System.out.println("ReceptionPatient - все записи пациента");
//                System.out.println("PatientGetAll - список пациентов");
//                System.out.println("PatientUpdate - изменить фио пациента");
//                System.out.println("PatientDelete - удалить пациента");
                for (ComandType ct: ComandType.values()
                     ) {
                    System.out.println(ct);
                }
                return 1;
            }
            case PatientAdd: {
                System.out.print("Input a fio: ");
                patientController.add(in.nextLine());
                return 1;
            }
            case DoctorAdd: {
                System.out.print("Input a fio: ");
                doctorController.add(in.nextLine());
                return 1;
            }
            case ReceptionAdd: {
                System.out.print("Input a doctorId: ");
                String doctorFio = in.nextLine();
                System.out.print("Input a patientId: ");
                String patientFio = in.nextLine();
                System.out.print("Input a receptionTime (yyyy-MM-dd hh:mm:ss): ");
                String receptionTime = in.nextLine();
                receptionController.add(doctorFio, patientFio, receptionTime);// status=new
                return 1;
            }
            case ReceptionInProgress: {
                System.out.print("Input a id: ");
                receptionController.inProgress(in.nextLine()); //status=inProgress
                return 1;
            }
            case ReceptionCancel: {
                System.out.print("Input a id: ");
                receptionController.cancel(in.nextLine()); //status=cancel
                return 1;
            }
            case ReceptionAwaitingPayment: {
                System.out.print("Input a id: ");
                receptionController.awaitingPayment(in.nextLine()); //status=awaitingPayment
                return 1;
            }
            case ReceptionFinished: {
                System.out.print("Input a id: ");
                receptionController.finished(in.nextLine()); //status=finished
                return 1;
            }
            case ReceptionPatient: {
                System.out.print("Input a patientFio: ");
                receptionController.getAll(in.nextLine());
                return 1;
            }
            case PatientGetAll: {
                patientController.getAll();
                return 1;
            }
            case PatientUpdate: {
                System.out.print("Input a id: ");
                String id = in.nextLine();
                System.out.print("Input a fio: ");
                String fio = in.nextLine();
                patientController.update(id, fio);
                return 1;
            }
            case PatientDelete: {
                System.out.print("Input a id: ");
                String id = in.nextLine();
                patientController.delete(id);
                return 1;
            }
            case Exit: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
}
