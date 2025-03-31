package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.util.List;

public interface AppointmentBO extends SuperBO {
    boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO , SessionDTO sessionDTO, PaymentDTO paymentDTO);
    String getNextPatientID();
    String getNextSessionID();
    String getNextPaymentID();
    List<PatientDTO> searchPatientBYName(String searchBYName);
}
