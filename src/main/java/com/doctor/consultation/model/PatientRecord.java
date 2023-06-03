package com.doctor.consultation.model;

import java.io.*;
import java.util.*;


public class PatientRecord {
    private LinkedList<Patient> patientList;
    public static int sequenceNo=1;
    private ArrayList tempList=new ArrayList<Integer>();
    private ConsultQueue consultQueue;

    public PatientRecord() {
        patientList = new LinkedList<>();

    }

    public int registerPatient(String name, int age,ConsultQueue consultQueue) {
        int patientId = patientList.size() + 1;
        Patient patient = new Patient(name, age, patientId);
        patientList.add(patient);
        consultQueue.enqueuePatient(age);
        return patientId;
    }
    boolean imported=false;
    public void importPatientsFromFile(String filename,ConsultQueue consultQueue) {

            try {

                String path = "C:\\Users\\I527260\\Desktop\\" + filename;
                File file = new File(path);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(", ");
                    String name = data[0];
                    int age = Integer.parseInt(data[1]);
                    registerPatient(name, age, consultQueue);
                    imported=true;
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error reading input file: " + filename + " (" + e.getMessage() + ")");
            }

        if(imported==true)
        {
            clearImportedData(filename);
            imported=false;
        }

    }
    public void outputCurrentPatientList(String filename) {
        String path = "C:\\Users\\I527260\\Desktop\\" + filename;
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            Collections.sort(patientList, new Comparator<Patient>() {
                @Override
                public int compare(Patient t, Patient t1) {
                    return t1.getAge() - t.getAge();
                }
            });

            for (Patient patient : patientList) {
                writer.println(sequenceNo++ + ", " + patient.getPatientId() + ", " + patient.getName() + ", " + patient.getAge());

            }
            if(patientList.size()==0) {
                System.out.println("Consultation queue is empty cannot save data.");
            }
            else{
                System.out.println("Consultation queue output to file successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + filename + " (" + e.getMessage() + ")");
        }
    }

    public void clearImportedData(String filename)
    {
        String path = "C:\\Users\\I527260\\Desktop\\" + filename;
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(""); // Write an empty string to clear the file
            fileWriter.close();
            System.out.println("Records cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the records: " + e.getMessage());
        }

    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}