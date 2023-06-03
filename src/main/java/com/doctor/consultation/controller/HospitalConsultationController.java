package com.doctor.consultation.controller;

import com.doctor.consultation.model.ConsultQueue;
import com.doctor.consultation.model.Patient;
import com.doctor.consultation.model.PatientRecord;
import com.doctor.consultation.view.HospitalConsultationView;


public class HospitalConsultationController {
    private PatientRecord patientRecord;
    private ConsultQueue consultQueue;
    private HospitalConsultationView view;

    public HospitalConsultationController() {
        patientRecord = new PatientRecord();
        consultQueue = new ConsultQueue(100);
        view = new HospitalConsultationView();

    }

    public void addNewPatient() {
        String name = view.readStringInput("Enter patient name:");
        int age = view.readIntegerInput("Enter patient age:");
        if(age<=0)
        {
            view.displayMessage("patient age cannot be less then 0");
        }
        else {
            int patientId = patientRecord.registerPatient(name, age, consultQueue);
            view.displayMessage("Patient \"" + name + "\" added to the queue, current position is " + consultQueue.getSize());
        }
    }

    public void run() {
        view.displayMenu();
        int option = view.readIntegerInput("Enter an option:");

        while (option !=6) {
            switch (option) {
                case 1:
                    patientRecord.importPatientsFromFile("input.txt",consultQueue);
                    view.displayMessage("Imported " + patientRecord.getPatientList().size() + " patients from file.");
                    break;
                case 2:
                    addNewPatient();
                    break;
                case 3:
                    getNextPatient();
                    break;
                case 4:
                    patientRecord.outputCurrentPatientList("output.txt");
                    break;
                case 5:
                    patientRecord.clearImportedData("output.txt");
                    break;

                default:
                    view.displayMessage("Invalid option. Please try again.");
                    break;
            }

            view.displayMenu();
            option = view.readIntegerInput("Enter an option:");
        }
    }




    public void getNextPatient() {
        if (consultQueue.isEmpty()) {
            view.displayMessage("No patients in the queue.");
        } else {
            int age = consultQueue.peek();
            Patient patient = getPatientByAge(age);

            view.displayMessage("Next patient for consultation is: " + patient.getName());
            consultQueue.dequeuePatient();

        }
    }




    private Patient getPatientByAge(int age) {
        for (Patient patient : patientRecord.getPatientList()) {
            if (patient.getAge() == age) {
                return patient;
            }
        }
        return null;
    }
    private Patient getPatientById(int patientId) {
        for (Patient patient : patientRecord.getPatientList()) {
            if (patient.getPatientId() == patientId) {
                return patient;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HospitalConsultationController controller = new HospitalConsultationController();
        controller.addNewPatient();
        controller.getNextPatient();
    }


}

