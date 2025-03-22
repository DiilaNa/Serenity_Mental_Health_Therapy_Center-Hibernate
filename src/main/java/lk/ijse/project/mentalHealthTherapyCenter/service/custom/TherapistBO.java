package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

public interface TherapistBO extends SuperBO {
    boolean saveTherapist(DoctorDTO doctorDTO);
    boolean updateTherapist(DoctorDTO doctorDTO);
}
