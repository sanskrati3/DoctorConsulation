package com.doctor.consultation.model;

public class Patient {
    private String name;
    private int age;
    private int patientId;

    public Patient(String name, int age, int patientId) {
        this.name = name;
        this.age = age;
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPatientId() {
        return patientId;
    }

}