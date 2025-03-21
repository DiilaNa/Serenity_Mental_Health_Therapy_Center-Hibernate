package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

public interface AppointmentBO extends SuperBO {
    boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO , SessionDTO sessionDTO, TherapistDetailsDTO therapistDetailsDTO, PaymentDTO paymentDTO);
    String getNextPatientID();
    String getNextSessionID();
    String getNextPaymentID();
}
