package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;

public class AppointmentBOImpl implements AppointmentBO {

    @Override
    public boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, TherapistDetailsDTO therapistDetailsDTO, PaymentDTO paymentDTO) {
        return true;
    }

    @Override
    public String getNextPatientID() {
        return "P001";
    }

    @Override
    public String getNextSessionID() {
        return "S001";
    }

    @Override
    public String getNextPaymentID() {
        return "PAY001";
    }
}
