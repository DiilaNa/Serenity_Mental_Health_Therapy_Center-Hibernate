package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.MedicalHistoryDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientsInEveryProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ViewSessionTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.SuperDAO;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO  extends SuperDAO {
    List<ViewSessionDTO>getAllAppointments();
    List<MedicalHistoryDTO> getALLMedicalHistory();
    List<PatientsInEveryProgramDTO> getPatientsInEveryProgram();
}
