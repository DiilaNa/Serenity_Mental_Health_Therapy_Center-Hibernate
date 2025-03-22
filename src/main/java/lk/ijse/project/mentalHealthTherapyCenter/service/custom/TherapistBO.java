package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import jakarta.persistence.Id;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapistBO extends SuperBO {
    ArrayList<DoctorDTO>getALLTherapist();
    boolean saveTherapist(DoctorDTO doctorDTO);
    boolean updateTherapist(DoctorDTO doctorDTO);
    boolean deleteTherapist(String DoctorID) throws SQLException, ClassNotFoundException;
}
