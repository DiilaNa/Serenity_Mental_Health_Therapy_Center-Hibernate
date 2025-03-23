package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.util.ArrayList;

public interface TProgramBO extends SuperBO {
    boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean deleteTProgram(String therapyProgramID);
    ArrayList<TherapyProgramDTO> getALLTPrograms();
}
