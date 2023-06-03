package com.doctor.consultation.view;

import java.util.Scanner;


public class HospitalConsultationView {
    private Scanner scanner;


    public HospitalConsultationView() {
        scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        System.out.println("Menu Options");
        System.out.println("1. Import patients from file");
        System.out.println("2. Enter new patient information");
        System.out.println("3. Display next patient in line");
        System.out.println("4. Output current patient waiting list");
        System.out.println("5. Exit");
    }

    public String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readIntegerInput(String prompt) {
        System.out.print(prompt);
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
        return input;
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
