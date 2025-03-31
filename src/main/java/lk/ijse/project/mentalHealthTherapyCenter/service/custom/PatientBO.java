package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PatientBO extends SuperBO {
    boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;

    boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;

    List<PatientDTO> getALL() throws Exception;
    boolean deletePatient(String patientID) throws SQLException, ClassNotFoundException;

    String getNextPatientID();
}
