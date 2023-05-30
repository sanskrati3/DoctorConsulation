package com.doctor.consultation;

import com.doctor.consultation.controller.HospitalConsultationController;

public class HospitalConsultationApp {
    public static void main(String[] args) {
        HospitalConsultationController controller = new HospitalConsultationController();
        controller.run();
    }
}
