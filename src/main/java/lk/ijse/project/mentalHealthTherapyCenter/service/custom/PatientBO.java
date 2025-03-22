package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientBO extends SuperBO {
    boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    ArrayList<PatientDTO> getALL() throws Exception;
}
