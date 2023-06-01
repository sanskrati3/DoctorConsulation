package com.doctor.consultation.model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientRecord {
    private List<Patient> patientList;

    public PatientRecord() {
        patientList = new ArrayList<>();
    }

    public int registerPatient(String name, int age) {
        int patientId = patientList.size() + 1;
        Patient patient = new Patient(name, age, patientId);
        patientList.add(patient);
        return patientId;
    }
    public void importPatientsFromFile(String filename) {
        try {
            String path = "C:\\Users\\I527299\\Documents\\" + filename;
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(", ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);

                registerPatient(name, age);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading input file: " + filename + " (" + e.getMessage() + ")");
        }
    }
    public void outputCurrentPatientList(String filename) {
        String path = "C:\\Users\\I527299\\Documents\\" + filename;
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (Patient patient : patientList) {
                writer.println(patient.getPatientId() + ", " + patient.getName() + ", " + patient.getAge());
            }
            System.out.println("Consultation queue output to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing output file: " + filename + " (" + e.getMessage() + ")");
        }
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

}
