package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorStatsDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface TherapistBO extends SuperBO {
    List<DoctorDTO> getALLDoctors() throws Exception;
    boolean saveTherapist(DoctorDTO doctorDTO);
    boolean updateTherapist(DoctorDTO doctorDTO);
    boolean deleteTherapist(String DoctorID) throws SQLException, ClassNotFoundException;
    String getNextTherapyID();
    List<DoctorDTO>getDocNames() throws Exception;
    List<DoctorStatsDTO> loadDoctorStatistics();
}
